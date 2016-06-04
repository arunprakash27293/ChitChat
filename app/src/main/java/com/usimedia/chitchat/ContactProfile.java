package com.usimedia.chitchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.usimedia.chitchat.model.ChatContact;

public class ContactProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);
        Intent fromchatcontactactivity = getIntent();
        ChatContact contactprofile = (ChatContact) fromchatcontactactivity.getSerializableExtra("chatname");

        //Email
        final TextView EmailText = (TextView) findViewById(R.id.activity_name);
        EmailText.setText(contactprofile.getName());

        //Status
        final TextView status= (TextView) findViewById(R.id.email_id);
       status.setText(contactprofile.getStatus());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_contact_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
