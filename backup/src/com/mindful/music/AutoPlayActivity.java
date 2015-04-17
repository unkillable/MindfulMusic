package com.mindful.music;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AutoPlayActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.familytab);
		scanSdcard();
	}
	private void scanSdcard(){
	    String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
	    final String[] projection = {
	            MediaStore.Audio.Media.TITLE,
	            MediaStore.Audio.Media.ARTIST,
	            MediaStore.Audio.Media.DATA,
	            MediaStore.Audio.Media.DISPLAY_NAME,
	            MediaStore.Audio.Media.DURATION
	    };
	    final String sortOrder = MediaStore.Audio.AudioColumns.TITLE + " COLLATE LOCALIZED ASC";
	    final List titles = new ArrayList();
	    final List paths = new ArrayList();
	    Cursor cursor = null;
	    try {
	        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	        cursor = AutoPlayActivity.this.getContentResolver().query(uri, projection, selection, null, sortOrder);
	        if( cursor != null){
	            cursor.moveToFirst();
	            while( !cursor.isAfterLast() ){
	                Media media = new Media();
	                String title = cursor.getString(0);
	                String artist = cursor.getString(1);
	                String path = cursor.getString(2);
	                String displayName  = cursor.getString(3);
	                String songDuration = cursor.getString(4);
	                titles.add(title);
	                paths.add(path);
	                cursor.moveToNext();
	            }

	        }

	    } catch (Exception e) 
	    {
	    }finally{
	        if( cursor != null){
	            cursor.close();
	        }
	    }
		ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
		final ListView codeLearnLessons = (ListView)findViewById(R.id.familylist);
		codeLearnLessons.setAdapter(codeLearnArrayAdapter);
		codeLearnLessons.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	  //final String text = ((TextView)view).getText();
		          //String text = codeLearnLessons.get(position).tostring().trim();//first method
				  //Intent intent = new Intent();
				  //intent.setAction(android.content.Intent.ACTION_VIEW);
				  //File file = new File(paths.get(position).toString());
				  //intent.setDataAndType(Uri.fromFile(file), "audio/*");
				  final MediaPlayer mp = new MediaPlayer();
				  try {
					mp.setDataSource(paths.get(position).toString());
					mp.prepare();
					mp.seekTo((int) (mp.getDuration()*.40));
					mp.start();
				    } catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    } catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    } catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    }
			 		AlertDialog.Builder preview = new AlertDialog.Builder(AutoPlayActivity.this);
					preview.setMessage("Previewing song for 10 seconds..").setTitle("Previewing");
					final AlertDialog previewDialog = preview.create();
					previewDialog.show();
			    	Context context1 = getApplicationContext();
			    	CharSequence text1 = "Previewing song for 10 seconds..";
			    	Toast toast = Toast.makeText(context1, text1, Toast.LENGTH_SHORT);
			    	toast.show();
				    CountDownTimer cntr_aCounter = new CountDownTimer(10000, 1000) {
				        public void onTick(long millisUntilFinished) {
				            mp.start();
				        }
				        public void onFinish() {
				            //code fire after finish
				               mp.pause();
							   mp.seekTo(0);
				               final int position = mp.getCurrentPosition();
				               previewDialog.dismiss();
								runOnUiThread(new Runnable() {
								     @Override
								     public void run() {
								 		AlertDialog.Builder builder = new AlertDialog.Builder(AutoPlayActivity.this);
										builder.setMessage("Would you like to continue playing the song?")
									       .setTitle("Continue?")
										// Add the buttons
										.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
										           public void onClick(DialogInterface dialog, int id) {
														try {
															//mp.prepare();
														    mp.seekTo(position);
														    mp.start();
													 		AlertDialog.Builder builder = new AlertDialog.Builder(AutoPlayActivity.this);
															builder.setMessage("Press button to stop song")
														       .setTitle("Playing")
															// Add the buttons
															.setPositiveButton("Stop song", new DialogInterface.OnClickListener() {
															           public void onClick(DialogInterface dialog, int id) {
															        	   mp.stop();
															           }
															       })

															.show();
														} catch (IllegalStateException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
										           }
										       })
										.setNegativeButton("No", new DialogInterface.OnClickListener() {
										           public void onClick(DialogInterface dialog, int id) {
										               // User cancelled the dialog
										           }
										       })
										.show();
								    }
								});
				        	}
				  };cntr_aCounter.start();
		    }
		});
	}
}
		
