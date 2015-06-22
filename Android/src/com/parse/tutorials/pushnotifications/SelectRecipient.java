package com.parse.tutorials.pushnotifications;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SelectRecipient extends Activity {
	
	// List view
	private ListView lv;
	
	// Listview Adapter
	ArrayAdapter<String> adapter;
	
	// Search EditText
	EditText inputSearch;
	
	
	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_list);
        
        ArrayList<String> names = new ArrayList<String>();
        final ArrayList<String> number = new ArrayList<String>();
       
        // Listview Data
        Cursor cursor = null;
	    try {
	        cursor = getApplicationContext().getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
	        int contactIdIdx = cursor.getColumnIndex(Phone._ID);
	        int nameIdx = cursor.getColumnIndex(Phone.DISPLAY_NAME);
	        int phoneNumberIdx = cursor.getColumnIndex(Phone.NUMBER);
	        int photoIdIdx = cursor.getColumnIndex(Phone.PHOTO_ID);
	        cursor.moveToFirst();
	        do {
	            String idContact = cursor.getString(contactIdIdx);
	            String name = cursor.getString(nameIdx);
	            String phoneNumber = cursor.getString(phoneNumberIdx);
	            if(phoneNumber!=null){
	            	names.add(name);
	            	number.add(PhoneNumber(phoneNumber));
	            }
//	            	Log.e("Mobile no", phoneNumber);
	        } while (cursor.moveToNext());  
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (cursor != null) {
	            cursor.close();
	        }
	    }
        String products[] = {"Dell Inspiron", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
        						"iPhone 4S", "Samsung Galaxy Note 800",
        						"Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro", "HTC One X", "HTC Wildfire S", "HTC Sense", "HTC Sensation XE",
        						"iPhone 4S", "Samsung Galaxy Note 800",
        						"Samsung Galaxy S3", "MacBook Air", "Mac Mini", "MacBook Pro"};
        
        lv = (ListView) findViewById(R.id.list_view);
//        inputSearch = (EditText) findViewById(R.id.inputSearch);
        
        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, names);
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String selectedFromList = (String) (lv.getItemAtPosition(position));
				String no = number.get(position).toString();
				Intent i = new Intent(SelectRecipient.this ,MainActivity.class);
				i.putExtra("name",selectedFromList);
				i.putExtra("number",no);
				setResult(RESULT_OK,i);
				finish();
			}
		});

        /**
         * Enabling Search Filter
         * */
//        inputSearch.addTextChangedListener(new TextWatcher() {
//			
//			@Override
//			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//				// When user changed the Text
//				SelectRecipient.this.adapter.getFilter().filter(cs);	
//			}
//			
//			@Override
//			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//					int arg3) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void afterTextChanged(Editable arg0) {
//				// TODO Auto-generated method stub							
//			}
//		});
    }
	public String PhoneNumber(String number) {
		String mobile;
		mobile = number.replace(" ", "");
		Log.e("no", mobile.replace("+91", ""));
		return mobile;
	}
}
