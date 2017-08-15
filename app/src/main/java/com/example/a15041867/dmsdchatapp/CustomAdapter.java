package com.example.a15041867.dmsdchatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15041867 on 15/8/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Messages> message;
    TextView tvDisplayName, tvDateTime, tvMessage;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        tvDisplayName = (TextView)rowView.findViewById(R.id.tvDisplayName);
        tvDateTime = (TextView)rowView.findViewById(R.id.tvDateTime);
        tvMessage = (TextView)rowView.findViewById(R.id.tvMessage);

        Messages currentItem = message.get(position);

        String displayName = currentItem.getMessageUser();
        String dateTime = currentItem.getMessageTime();
        String message = currentItem.getMessageText();

        tvDisplayName.setText(displayName);
        tvDateTime.setText(dateTime);
        tvMessage.setText(message);

        return rowView;
    }

    public CustomAdapter(Context context, int resource, ArrayList<Messages> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        message = objects;
    }

}
