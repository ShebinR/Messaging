package com.example.messaging;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("ViewHolder") public class ListAdapterSpam extends ArrayAdapter<SMSData> {

	private final Context context;
    // List values
    private final List<SMSData> smsList;

   public ListAdapterSpam(Context context, List<SMSData> smsList) {
       super(context, R.layout.spam, smsList);
       this.context = context;
       this.smsList = smsList;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       View rowView = inflater.inflate(R.layout.spam, parent, false);

       TextView senderNumber = (TextView) rowView.findViewById(R.id.spam_number);
       senderNumber.setText(smsList.get(position).getNumber());
       TextView senderMsg = (TextView) rowView.findViewById(R.id.spam_msg);
       senderMsg.setText(smsList.get(position).getBody());
       

       return rowView;
   }

}

