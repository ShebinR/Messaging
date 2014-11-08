package com.example.messaging;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class AddBL extends Activity implements OnClickListener{

	TextView number;
	Button add;
	
	SharedPreferences writeData;
	int c;
	String count;
	public static String filename= "MyBlackListData";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Enter Black List Number");
		actionBar.setIcon(R.drawable.add_bl);
		setContentView(R.layout.addbl);
		setupVariables();
		Bundle bun=getIntent().getExtras();
		c=bun.getInt("nor");
		count=String.valueOf(c);
		
	}
	
	void setupVariables()
	{
		writeData = getSharedPreferences(filename, 0);
		number=(TextView)findViewById(R.id.number_bl);
		add=(Button)findViewById(R.id.addblno);
	
		add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String stringData= number.getText().toString();
		if(stringData!="" && stringData.length() < 10 )
		{
			stringData= number.getText().toString();
			Toast.makeText(getBaseContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
			
		}
		else
		{
			SharedPreferences.Editor editor = writeData.edit();
			c++;
			count=String.valueOf(c);
			editor.putString(count, stringData);
			editor.putString("CountBL", count);
			editor.commit();
			Toast.makeText(getBaseContext(), stringData+" Added as Black List", Toast.LENGTH_SHORT).show();
		
			Intent i=new Intent(AddBL.this,MainActivity.class);
			startActivity(i);
			finish();
		}
	}
	
	@Override
	public void onBackPressed()
	{
	    super.onBackPressed(); 
	    startActivity(new Intent(AddBL.this, BlackList.class));
	    finish();

	}

}
