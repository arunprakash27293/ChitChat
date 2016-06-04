package com.usimedia.chitchat.listadapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.usimedia.chitchat.R;
import com.usimedia.chitchat.model.ChatContact;

import java.text.SimpleDateFormat;

/**
 * Created by USI IT on 6/3/2016.
 */
public class ChatContactListAdapter extends ArrayAdapter<ChatContact>{

private static final int ChatContactLayoutId = R.layout.chat_contact_list_item;
    private  ChatContact[] contacts;
    private Activity context;
    private static final SimpleDateFormat DATE_UI_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm");


    @Override
    //fn+alt+insert and override-> getView() for get the data and mapping into the layout
    public View getView(int position, View convertView, ViewGroup parent){

        View chatcontactview ;



        if(convertView == null)
        {
            chatcontactview = context.getLayoutInflater().inflate(ChatContactLayoutId,parent,false);

        }
        else
        {
         chatcontactview=convertView;
        }
        ChatContact currentobject= contacts[position];

        TextView nameTextView = (TextView) chatcontactview.findViewById(R.id.chatcontactname);
        TextView statusTextView = (TextView) chatcontactview.findViewById(R.id.statusid);
        TextView lastseenTextView = (TextView) chatcontactview.findViewById(R.id.lastseenid);

        nameTextView.setText(currentobject.getName());
        statusTextView.setText(currentobject.getStatus());
        lastseenTextView.setText(DATE_UI_FORMAT.format(currentobject.getLastseen()));

        return chatcontactview;
    }

    public ChatContactListAdapter(Activity activity, ChatContact[] objects) {
        super(activity,ChatContactLayoutId ,objects);

        context=activity;
        contacts= objects;

    }
}
