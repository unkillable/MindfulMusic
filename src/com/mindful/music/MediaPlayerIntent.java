package com.mindful.music;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MediaPlayerIntent extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		Bundle b = getIntent().getExtras();
		int position = b.getInt("position");
		String file_name = b.getString("file");
	  Context context = getApplicationContext();
	  //final String text = ((TextView)view).getText();
    //String text = codeLearnLessons.get(position).tostring().trim();//first method
	  Intent intent = new Intent();
	  intent.setAction(android.content.Intent.ACTION_VIEW);
	  File file = new File(file_name);
	  intent.setDataAndType(Uri.fromFile(file), "audio/*");
      startActivity(intent);
      try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    finish();
	}
}
