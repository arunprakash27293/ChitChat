package com.usimedia.chitchat;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Contacts extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final ListView listviewcontact = (ListView)findViewById(R.id.contactlist);

        ContentResolver contentresolver = getContentResolver();

        final Uri providerUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //Second parameter in cursor.query()
        String[] elementsreq = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME ,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        //Fivth  parameter in cursor.query() it's for sorting the array in ASC or DESC
        String sortingOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +" ASC";



        Cursor cursor = contentresolver.query(
                providerUri,
                elementsreq,
                null,null,null

        );
        //converting list to set
        //List<String> ContactName = new ArrayList<>(ContactNumber);


        String contactName;

        Set<String>  ContactNumber = new HashSet<>();
        while(cursor.moveToNext())
        {
           contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if(null != contactName)
            {
                ContactNumber.add(contactName.replaceAll("\\a+",""));

            }


        }

        List<String> ContactNumbersList = new ArrayList<>(ContactNumber);

        ArrayAdapter<String> ContactListAdapter = new ArrayAdapter<>
                (
                Contacts.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                ContactNumbersList

                );
               listviewcontact .setAdapter(ContactListAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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
