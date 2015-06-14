package com.parse.tutorials.pushnotifications;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Messsage extends Activity{
	MediaPlayer mMediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 MediaPlayer mMediaPlayer = new MediaPlayer();
	        mMediaPlayer = new MediaPlayer();
			mMediaPlayer = MediaPlayer.create(this, R.raw.p);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setLooping(true);
			mMediaPlayer.start();
	}
}
