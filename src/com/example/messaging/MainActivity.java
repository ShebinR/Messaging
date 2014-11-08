package com.example.messaging;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ListActivity {
	
	public static String filename= "MyBlackListData";
	SharedPreferences someData;
	int count;
	
	
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getActionBar();
        actionBar.setTitle("Inbox");
        	List<SMSData> smsInboxList = new ArrayList<SMSData>();

        	Uri uri = Uri.parse("content://sms/inbox");
        	Cursor c= getContentResolver().query(uri, null, null ,null,null);
        	startManagingCursor(c);

        	someData = getSharedPreferences(filename, 0);
        	String dataReturned = someData.getString("CountBL", "no value");
		   
        	if(dataReturned.equals("no value"))
			{

				SharedPreferences.Editor editor = someData.edit();
				editor.putString("CountBL", "0");
				editor.commit();
				count=0;
				
				if(c.moveToFirst()) {
			           for(int i=0; i < c.getCount(); i++) {
			               SMSData sms = new SMSData();
			               
			               sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
			               sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
			               String text=sms.getNumber();
			    
			               if (text.matches("^[0-9+ ]+") && text.length() > 10) 
			               {
			                   smsInboxList.add(sms);
			               }
			               c.moveToNext();
			           }
			       }
			       c.close();   
			}
        	else
        	{
			   
			   count=Integer.parseInt(dataReturned);
				
			   String blno[]=new String[1000];
			   for(int j=1;j<=count;j++){
  					String key=String.valueOf(j);
  					blno[j-1]=someData.getString(key, "novalue");
			   }
			   if(c.moveToFirst()){
		           for(int i=0; i < c.getCount(); i++) {
		               SMSData sms = new SMSData();
		               
		               sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
		               sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
		               String text=sms.getNumber();
		               
		               if (text.matches("^[0-9+ ]+") && text.length() > 10) 
				       {
		            	   int j;
		            	   for(j=0;j<count;j++)
		            	   {
		            		   //String no="91"+blno[j];
		            		   
		            		   if(text.matches(blno[j]) || text.contains(blno[j]))
		            			   break;
		            	   }
		            	   if(j==count)
		   						smsInboxList.add(sms);
				       }			               
		               c.moveToNext();
		           }
		       }
		       c.close();
            }
        	setListAdapter(new ListAdapter(this, smsInboxList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch(item.getItemId())
    	{
	    	case R.id.action_newSMS:
	    		newSMS();
	    		break;
	    	case R.id.action_spam:
	    		spam_folder();
	    		break;
	    	case R.id.action_settings_app:
	    		settings();
	    		break;
	    	case R.id.action_folder:
	    		folder();
	    		break;
    	}
        return super.onOptionsItemSelected(item);
    }


	private void folder() {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,Folder.class);
		startActivity(i);
		
	}

	private void settings() {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,Settings.class);
		startActivity(i);
		
	}

	private void spam_folder() {
		
		Intent i=new Intent(MainActivity.this,Spam.class);
		startActivity(i);
	}

	private void newSMS() {

		Intent i=new Intent(MainActivity.this,SendSMS.class);
		startActivity(i);
		//finish();
	}
}
