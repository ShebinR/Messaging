package com.example.messaging;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.ActionBar;

import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi") public class SendSMS extends Activity implements OnClickListener{

	EditText number;
	EditText body;
	Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendsms);
		setUpVariables();
		ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Send Message");
		actionBar.setIcon(R.drawable.ic_send_sms);
	}
	@Override
	public void onBackPressed()
	{
	    super.onBackPressed(); 
	    startActivity(new Intent(SendSMS.this, MainActivity.class));
	    finish();
	}
	public void setUpVariables()
	{
		number=(EditText)findViewById(R.id.number);
		body=(EditText)findViewById(R.id.msg);
		send=(Button)findViewById(R.id.send_msg);
		send.setOnClickListener(this);
	}
	
	private void sendSMS(String phoneNumber, String message)
	{

		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
	
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,new Intent(SENT), 0);
	
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
	
		//—when the SMS has been sent—
		registerReceiver(new BroadcastReceiver(){
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS sent",Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), "Generic failure", 
					Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), "No service", 
					Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), "Null PDU", 
					Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), "Radio off", 
					Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));
		
		//—when the SMS has been delivered—
		
		registerReceiver(new BroadcastReceiver(){
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				
				switch (getResultCode())
				{
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), "SMS delivered", 
					Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), "SMS not delivered", 
					Toast.LENGTH_SHORT).show();
					break; 
				}
			}
		}, new IntentFilter(DELIVERED));
	
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI); 
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String m_no=number.getText().toString();
		String msg_send=body.getText().toString();
		sendSMS(m_no,msg_send);
		
	}
	

}
