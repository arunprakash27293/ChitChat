package com.usimedia.chitchat;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Contacts extends AppCompatActivity {

    private static final String CONTACTS_SERVICE_URL = "http:192.168.1.13:8000/api/contacts";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    private int numberOfContact;
    private Set<String> contactNumbers;
    private ListView contactListView;


    private class contactResolverTask extends AsyncTask<String, Void, List<String>> {
        @Override
        protected List<String> doInBackground(String... params) {
            List<String> phoneNumbers = Arrays.asList(params);

            try {
                return getContacts(phoneNumbers);
            } catch (JSONException e) {
                Log.d("Contacts" , "Json parser exception");
                e.printStackTrace();
                return Collections.emptyList();
            } catch (IOException e) {
                Log.d("Contacts" , "Network Exception");
                e.printStackTrace();
                return Collections.emptyList();
            }

        }

        @Override
        protected void onPostExecute(List<String> names) {
            super.onPostExecute(names);
            setValuesToUiListView(names);
        }
    }

    private List<String> getContacts(List<String> phoneNumbers) throws JSONException, IOException {

        JSONArray jsonNumbers = new JSONArray(phoneNumbers);
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("numbers", jsonNumbers);

        String rawResult = post(CONTACTS_SERVICE_URL, jsonRequest.toString());

        JSONObject jsonResult = new JSONObject(rawResult);

        JSONArray jsonContacts = jsonResult.getJSONArray("contact");

        Log.d("Contacts" , "Number of contacts received from backend = " + jsonContacts.length());

        final List<String> contactList = new ArrayList<>();

        String currentContactName;

        for (int i=0; i<jsonContacts.length(); i++){
            currentContactName = jsonContacts.getJSONObject(i).getString("name");
            contactList.add(currentContactName);
            Log.d("Contacts", "Current contact name being parsed = " + currentContactName);
        }

        return contactList;
    }

    private String post(String url, String json) throws IOException {

        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();

        return response.body().string();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactListView = (ListView) findViewById(R.id.contactlist);

        ContentResolver contentResolver = getContentResolver();

        final Uri providerUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        final String[] elementsRequired = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = contentResolver.query(
                providerUri,
                elementsRequired,
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        numberOfContact = cursor.getCount();


        contactNumbers = new HashSet<>();

        String number;

        while (cursor.moveToNext())
        {
            number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if(null != number) {
                contactNumbers.add(number.replaceAll("\\s+", ""));
               contactNumbers.add(number.replaceAll("()", ""));
            }

        }

        final List<String> distinctNumbers = new ArrayList<>(contactNumbers);

        String[] numbersBuffer = new String[distinctNumbers.size()];

        new contactResolverTask().execute(distinctNumbers.toArray(numbersBuffer));
    }

    private void setValuesToUiListView(List<String> elements) {

        final ArrayAdapter<String> contactListAdapter = new ArrayAdapter<>(
                Contacts.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                elements
        );

        contactListView.setAdapter(contactListAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Contact", "Number of contacts = "+numberOfContact);
        Toast.makeText(Contacts.this, "Number of contacts = " + numberOfContact, Toast.LENGTH_LONG).show();
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