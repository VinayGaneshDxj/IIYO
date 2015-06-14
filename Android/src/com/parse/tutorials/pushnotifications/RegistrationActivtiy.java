package com.parse.tutorials.pushnotifications;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivtiy extends Activity {
	Button reg;
	EditText mobile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		Intent i = new Intent(RegistrationActivtiy.this, MainActivity.class);
		startActivity(i);
		finish();
		
		
		mobile = (EditText) findViewById(R.id.editText1);
		reg= (Button) findViewById(R.id.button1);
		reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ParseInstallation.getCurrentInstallation().put("mobile", Integer.valueOf(mobile.getText().toString()));
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mobile.getWindowToken(), 0);
				
				ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
					@Override
					public void done(ParseException e) {
						if (e == null) {
							Intent i = new Intent(RegistrationActivtiy.this, MainActivity.class);
							startActivity(i);
							finish();
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
}
