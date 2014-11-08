package com.example.messaging;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

@SuppressLint("NewApi") public class Spam extends ListActivity{

	public static String filename= "MyBlackListData";
	SharedPreferences someData;
	int count;

	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setIcon(R.drawable.ic_spam_msg);
		actionBar.setTitle("Spam Messages");
		 List<SMSData> smsSpamList = new ArrayList<SMSData>();
		 Uri uri = Uri.parse("content://sms/inbox");
	     Cursor c= getContentResolver().query(uri, null, null ,null,null);
	     startManagingCursor(c);

	     someData = getSharedPreferences(filename, 0);
     	String dataReturned = someData.getString("CountBL", "no value");
     	count=Integer.parseInt(dataReturned);
     	String blno[]=new String[1000];
			   for(int j=1;j<=count;j++){
  					String key=String.valueOf(j);
  					blno[j-1]=someData.getString(key, "novalue");
			   }
     	
	     
	     if(c.moveToFirst()) {
	         for(int i=0; i < c.getCount(); i++) {
	            
	        	 SMSData sms = new SMSData();
	        	 sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
	        	 sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
	        	 
	        	 String text=sms.getNumber();
	                
	        	 if (text.matches("[a-zA-Z0-9 -]+") ){
	        		 smsSpamList.add(sms);
	        	 }
	        	 else
	        	 {
	        		 int j;
	        		 for(j=0;j<count;j++)
	            	 {
	            		   if(text.matches(blno[j]) || text.contains(blno[j]))
	            		   {
	            			   smsSpamList.add(sms);
	            			   break;
	            		   }
	            		   
	            	 }
	        		 
	        	 }
	             c.moveToNext();
	           }
	       }
	       c.close();

	       setListAdapter(new ListAdapterSpam(this,smsSpamList ));
	}
	@Override
	public void onBackPressed()
	{
	    super.onBackPressed(); 
	    startActivity(new Intent(Spam.this, MainActivity.class));
	    finish();

	}
}

