package com.parse.tutorials.pushnotifications;

import java.math.BigInteger;
import java.security.SecureRandom;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivtiy extends Activity {
	Button reg;
	EditText mobile;
	String android_id;
	private SecureRandom random = new SecureRandom();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		Regsucess();
//		ParseObject gameScore = new ParseObject("Users");
//		gameScore.put("deviceid", 1337);
//		gameScore.put("code", 123);
//		gameScore.saveInBackground(new SaveCallback() {
//			@Override
//			public void done(ParseException e) {
//				if (e == null) {
//					Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_success, Toast.LENGTH_SHORT);
//					toast.show();
//				} else {
//					e.printStackTrace();
//
//					Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_failed, Toast.LENGTH_SHORT);
//					toast.show();
//				}
//			}
//		});
		mobile = (EditText) findViewById(R.id.editText1);
		reg= (Button) findViewById(R.id.button1);
		reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				android_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
				ParseInstallation.getCurrentInstallation().put("code", 123);
//				ParseInstallation.getCurrentInstallation().put("deviceid", Integer.valueOf(android_id));
				ParseInstallation.getCurrentInstallation().put("mobile_number",mobile.getText().toString());
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mobile.getWindowToken(), 0);
				
				ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException e) {
						if (e == null) {
							Sendcode(123,mobile.getText().toString());
							Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_success, Toast.LENGTH_SHORT);
							toast.show();
						} else {
							e.printStackTrace();

							Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_failed, Toast.LENGTH_SHORT);
							toast.show();
						}
					}
				});
				
			}
		});
		
	}
	
	 public void Sendcode(Integer code, String deviceid) {
		 ParseQuery query = ParseInstallation.getQuery();
			query.whereEqualTo("mobile_number", deviceid);
			ParsePush androidPush = new ParsePush();
			androidPush.setMessage(code.toString());
			androidPush.setQuery(query);
			androidPush.sendInBackground();
	}
	
	 public void Regsucess() {
		 Intent i = new Intent(RegistrationActivtiy.this, MainActivity.class);
			startActivity(i);
			finish();
	}
	 public String nextSessionId() {
		    return new BigInteger(130, random).toString(32);
		  }
}
