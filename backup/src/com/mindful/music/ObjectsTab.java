package com.mindful.music;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ObjectsTab extends Activity{
	List<String> jpgFiles(String directory) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".mp4"))) {
		      textFiles.add(file.getName());
		    }
		  }
		  return textFiles;
		}
	public void onCreate(Bundle savedInstanceState) {
		final List fileNames = jpgFiles(getExternalFilesDir(null) + "/");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.familytab);
		//String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
		ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileNames);
		final ListView codeLearnLessons = (ListView)findViewById(R.id.familylist);
		codeLearnLessons.setAdapter(codeLearnArrayAdapter);
		codeLearnLessons.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	  //final String text = ((TextView)view).getText();
		          //String text = codeLearnLessons.get(position).tostring().trim();//first method
		    	  String text = (String) fileNames.get(position);
				  Intent intent = new Intent();  
				  intent.setAction(android.content.Intent.ACTION_VIEW);  
				  File file = new File(getExternalFilesDir(null) + "/" + codeLearnLessons.getItemAtPosition(position).toString());  
				  intent.setDataAndType(Uri.fromFile(file), "video/*");  
				  startActivity(intent);   
		        }
	       });
	    } 
	}
