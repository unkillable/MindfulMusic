package com.mindful.music;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class RecordActivity extends Activity{
	@Override
	public void onBackPressed() {
	    // Otherwise defer to system default behavior.
	    super.onBackPressed();
	}
	String mCurrentPhotoPath;

	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    Context context = getApplicationContext();
	    File storageDir = getExternalFilesDir(null);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		runOnUiThread(new Runnable() {
		     @Override
		     public void run() {
    	    	Context context1 = getApplicationContext();
   	    	CharSequence text1 = mCurrentPhotoPath;
   	    	Toast toast = Toast.makeText(context1, text1, Toast.LENGTH_SHORT);
   	    	toast.show();
		    }
		});		
	    return image;
	}
	private File createVideoFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "VID_" + timeStamp + "_";
	    Context context = getApplicationContext();
	    File storageDir = getExternalFilesDir(null);
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".mp4",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intents
	    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
		runOnUiThread(new Runnable() {
		     @Override
		     public void run() {
    	    	Context context1 = getApplicationContext();
   	    	CharSequence text1 = mCurrentPhotoPath;
   	    	Toast toast = Toast.makeText(context1, text1, Toast.LENGTH_SHORT);
   	    	toast.show();
		    }
		});		
	    return image;
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record);
        final ImageButton takePicBtn = (ImageButton) findViewById(R.id.take_picture);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	final int REQUEST_TAKE_PHOTO = 1;
        	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        	    // Ensure that there's a camera activity to handle the intent
        	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        	        // Create the File where the photo should go
        	        File photoFile = null;
        	        try {
        	            photoFile = createImageFile();
        	        } catch (IOException ex) {
        	            // Error occurred while creating the File
        	        }
        	        // Continue only if the File was successfully created
        	        if (photoFile != null) {
        	            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
        	                    Uri.fromFile(photoFile));
        	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        	        }
        	    }
            }
        });
        final ImageButton recordVidBtn = (ImageButton) findViewById(R.id.record_video);
        final int REQUEST_VIDEO_CAPTURE = 1;
        recordVidBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
        	        File photoFile = null;
        	        try {
        	            photoFile = createVideoFile();
        	        } catch (IOException ex) {
        	            // Error occurred while creating the File
        	        }
        	        // Continue only if the File was successfully created
        	        if (photoFile != null) {
        	            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT,
        	                    Uri.fromFile(photoFile));
                        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        	        }
                }

            }
        });
        final ImageButton startAudioRecord = (ImageButton) findViewById(R.id.record_audio);
        startAudioRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(RecordActivity.this, RecordAudioActivity.class);
        		RecordActivity.this.startActivity(myIntent);
            }
        });
    }
}
