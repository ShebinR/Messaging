package com.example.messaging;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<SMSData> {

	private final Context context;
    // List values
    private final List<SMSData> smsList;

   public ListAdapter(Context context, List<SMSData> smsList) {
       super(context, R.layout.activity_main, smsList);
       this.context = context;
       this.smsList = smsList;
   }

   @SuppressLint("ViewHolder") @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       View rowView = inflater.inflate(R.layout.activity_main, parent, false);

       TextView senderNumber = (TextView) rowView.findViewById(R.id.number);
       senderNumber.setText(smsList.get(position).getNumber());
       TextView senderMsg = (TextView) rowView.findViewById(R.id.msg);
       senderMsg.setText(smsList.get(position).getBody());
       

       return rowView;
   }

}
