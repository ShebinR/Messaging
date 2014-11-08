package com.example.messaging;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


@SuppressLint("NewApi") public class BlackList extends ListActivity{

	public static String filename= "MyBlackListData";
	SharedPreferences someData;
	String bl[]={" "," "," "," "," "," "," "," "," "," "};
	String bldefault[]={"No numbers till now","Add BlackList"};
	int count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Black Listed Numbers");
		actionBar.setIcon(R.drawable.settings_bl);
		someData = getSharedPreferences(filename, 0);
		
		String dataReturned = someData.getString("CountBL", "no value");
		if(dataReturned.equals("no value"))
		{
			
			SharedPreferences.Editor editor = someData.edit();
			editor.putString("CountBL", "0");
			editor.commit();
			count=0;
			
			setListAdapter(new ArrayAdapter<String>(BlackList.this,android.R.layout.simple_list_item_1,bldefault));
			
		}
		else
		{
		
			count=Integer.parseInt(dataReturned);
			
			int i=1;
			for(i=1;i<=count;i++)
			{
				String key=String.valueOf(i);
				String blno=someData.getString(key, "novalue");
				bl[i-1]=String.valueOf(blno);
			}

			bl[i-1]="Add BlackList";
		
			setListAdapter(new ArrayAdapter<String>(BlackList.this,android.R.layout.simple_list_item_1,bl));
		}
		
		
		
	}
	@Override
	public void onBackPressed()
	{
	    super.onBackPressed(); 
	    startActivity(new Intent(BlackList.this, Settings.class));
	    finish();

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		String option;
		if(count==0)
			option=bl[position];
		else
			option=bl[position];
		if(option.equals("Add BlackList"))
		{
			Intent i=new Intent(BlackList.this,AddBL.class);
			i.putExtra("nor", count);
			startActivity(i);
	
		}
	
	}
	
	

}

