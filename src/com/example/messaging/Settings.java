package com.example.messaging;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi") public class Settings extends ListActivity {

	String settingMenu[] = { "BlackListedNumbers" };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Settings");
		actionBar.setIcon(R.drawable.settings_gear);
		setListAdapter(new ArrayAdapter<String>(Settings.this, android.R.layout.simple_list_item_1, settingMenu));
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		switch(position)
		{
			case 0:
				Intent nextAct=new Intent(Settings.this,BlackList.class);
				startActivity(nextAct);
				
			break;
		}	
	}	
	@Override
	public void onBackPressed()
	{
	    super.onBackPressed(); 
	    startActivity(new Intent(Settings.this, MainActivity.class));
	    finish();

	}
}
