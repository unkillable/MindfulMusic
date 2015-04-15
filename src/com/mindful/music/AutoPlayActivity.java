package com.mindful.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
				  Intent intent = new Intent();
				  intent.setAction(android.content.Intent.ACTION_VIEW);
				  File file = new File(paths.get(position).toString());
				  intent.setDataAndType(Uri.fromFile(file), "audio/*");
				  startActivity(intent);
		        }
	       });
	    //for (int i = 0; i < projection.length; i++) {
		//	System.out.println(projection[i]);
		//}
	}
}
