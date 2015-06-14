package com.parse.tutorials.pushnotifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.RefreshCallback;
import com.parse.SaveCallback;

public class MainActivity extends Activity {

	Button sendIIYO;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Track app opens.
		ParseAnalytics.trackAppOpened(getIntent());
		
		// Set up our UI member properties.
		sendIIYO =  (Button) findViewById(R.id.sendIIYO);
		
		sendIIYO.setOnTouchListener(new OnTouchListener() {

	        public boolean onTouch(View v, MotionEvent event) {
	            switch (event.getAction()) {
	                case MotionEvent.ACTION_DOWN: {
	                    v.getBackground().setColorFilter(0xe0f47521,PorterDuff.Mode.SRC_ATOP);
	                    v.invalidate();
	                    break;
	                }
	                case MotionEvent.ACTION_UP: {
	                    v.getBackground().clearColorFilter();
	                    v.invalidate();
	                    break;
	                }
	            }
	            return false;
	        }


	    });
	
		
		sendIIYO.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
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
			            if(phoneNumber!=null)
			            	Log.e("no", phoneNumber);
			        } while (cursor.moveToNext());  
			    } catch (Exception e) {
			        e.printStackTrace();
			    } finally {
			        if (cursor != null) {
			            cursor.close();
			        }
			    }
				Toast.makeText(getApplicationContext(), "Button pressed", 3000).show();
				ParseQuery query = ParseInstallation.getQuery();
				query.whereEqualTo("mobile", 123456);
				ParsePush androidPush = new ParsePush();
				androidPush.setMessage("I love coding");
				androidPush.setQuery(query);
				androidPush.sendInBackground();
			}
		});
		
	}

}
