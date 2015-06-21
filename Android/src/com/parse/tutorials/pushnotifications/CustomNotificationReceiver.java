package com.parse.tutorials.pushnotifications;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class CustomNotificationReceiver extends BroadcastReceiver {
	String heading;
	public void onReceive(Context context, Intent intent) {
//        Intent i = new Intent();
//        i.setClassName("com.parse.tutorials.pushnotifications", "com.parse.tutorials.pushnotifications.Message");
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
//		Intent intentone = new Intent(context.getApplicationContext(), Messsage.class);
//		intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(intentone);
    	String action = intent.getAction();
		String channel = intent.getExtras().getString("com.parse.Channel");
		Bundle extras = intent.getExtras();
		String jsonData = extras.getString("data");
	    JSONObject jsonObject;
	    try {
	        jsonObject = new JSONObject(jsonData);
	        heading = jsonObject.getString("alert");
	        Toast.makeText(context, heading, 3000).show();
	        Uri notification;
	        try {
	        	if(heading.equalsIgnoreCase("p"))
	        	notification = Uri.parse("android.resource://com.parse.tutorials.pushnotifications/" + R.raw.p);
	        	else if(heading.equalsIgnoreCase("d"))
		        	notification = Uri.parse("android.resource://com.parse.tutorials.pushnotifications/" + R.raw.dinosor);
	        	else
	        		notification = Uri.parse("android.resource://com.parse.tutorials.pushnotifications/" + R.raw.ponna);
	            Ringtone r = RingtoneManager.getRingtone(context, notification);
	            r.play();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    NotificationCompat.Builder mBuilder =
	            new NotificationCompat.Builder(context)
	            .setSmallIcon(R.drawable.speak3)
	            .setContentTitle("IIYO - Test")
	            .setContentText("CU Sent IIYO");

	        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	        mNotificationManager.notify(1, mBuilder.build());

    }

    public static void generateNotification(Context context, int icon, String message) {
        // Show the notification
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        
        

    }
	
}
