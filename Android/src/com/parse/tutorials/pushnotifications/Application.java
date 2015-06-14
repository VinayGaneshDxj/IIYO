package com.parse.tutorials.pushnotifications;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

	// Initialize the Parse SDK.
    Parse.initialize(this, "t3GqjgVYsr6gbHnPtMtOvQ1zCv1s240J9OHWRo4M", "9FJN9PDJV48Iv4wbvc9UFBNIKUjlqAMerbuuV1xL"); 

	// Specify an Activity to handle all pushes by default.
	PushService.setDefaultPushCallback(this, MainActivity.class);
  }
}