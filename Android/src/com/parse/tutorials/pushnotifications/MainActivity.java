package com.parse.tutorials.pushnotifications;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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
	TextView ToBtn;
	Spinner ToSpinner;
	String mobileNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Track app opens.
		ParseAnalytics.trackAppOpened(getIntent());
//		 Bundle bundel = getIntent().getExtras();
//		    String to =bundel.getString("to");
		    
		// Set up our UI member properties.
		ToBtn = (TextView) findViewById(R.id.ToBtn);
		sendIIYO =  (Button) findViewById(R.id.sendIIYO);
//		if(to)
//			ToBtn.setText(to);
//		Intent i = new Intent(MainActivity.this, SelectRecipient.class);
//		startActivity(i);
//		ArrayAdapter<String> adapter = null;
//		adapter.add("Item1");adapter.add("Item2");adapter.add("Item3");adapter.add("Item4");
//		
//		ToSpinner.setAdapter(adapter);
		ToBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 Intent i = new Intent(MainActivity.this, SelectRecipient.class);
				 startActivityForResult(i, 1);
				
			}
		});
		
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
				
				Toast.makeText(getApplicationContext(), "Button pressed", 3000).show();
				ParseQuery query = ParseInstallation.getQuery();
				query.whereEqualTo("mobile", mobileNumber);
				ParsePush androidPush = new ParsePush();
				androidPush.setMessage("m");
				androidPush.setQuery(query);
				androidPush.sendInBackground();
			}
		});
		
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String name=data.getStringExtra("name");
	            String number=data.getStringExtra("number");
	            ToBtn.setText(name);
	            mobileNumber = number;
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}
}
