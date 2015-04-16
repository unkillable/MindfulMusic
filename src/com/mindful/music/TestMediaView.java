package com.mindful.music;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TestMediaView extends Activity {
	ActionBar.Tab tab1, tab2, tab3;
	Fragment fragmentTab1 = new FragmentTab1();
	Fragment fragmentTab2 = new FragmentTab2();
	Fragment fragmentTab3 = new FragmentTab3();
	List<String> jpgFiles(String directory) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".jpg"))) {
		      textFiles.add(file.getName());
		    }
		  }
		  return textFiles;
	    } 
	List<String> videoFiles(String directory) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".mp4"))) {
		      textFiles.add(file.getName());
		    }
		  }
		  return textFiles;
	    } 
	List<String> audioFiles(String directory) {
		  List<String> textFiles = new ArrayList<String>();
		  File dir = new File(directory);
		  for (File file : dir.listFiles()) {
		    if (file.getName().endsWith((".mp3"))) {
		      textFiles.add(file.getName());
		    }
		  }
		  return textFiles;
	    } 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memories);
        
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        tab1 = actionBar.newTab().setText("1");
        tab2 = actionBar.newTab().setText("2");
        tab3 = actionBar.newTab().setText("3");
        
        tab1.setTabListener(new MyTabListener(fragmentTab1));
        tab2.setTabListener(new MyTabListener(fragmentTab2));
        tab3.setTabListener(new MyTabListener(fragmentTab3));
        
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }
    public class FragmentTab1 extends ListFragment {
    	  public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    	                           Bundle savedInstanceState){
    			final List fileNames = jpgFiles(getExternalFilesDir(null) + "/");
    			super.onCreate(savedInstanceState);
    			//setContentView(R.layout.familytab);
        		View view = inflater.inflate(R.layout.familytab, container, false);
    			//String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
    			ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(TestMediaView.this, android.R.layout.simple_list_item_1, fileNames);
    			final ListView codeLearnLessons = (ListView)getActivity().findViewById(R.id.familylist);
    			codeLearnLessons.setAdapter(codeLearnArrayAdapter);
    			codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    			    public void onItemClick(AdapterView<?> parent, View view,
    			        int position, long id) {
    			    	  //final String text = ((TextView)view).getText();
    			          //String text = codeLearnLessons.get(position).tostring().trim();//first method
    			    	  String text = (String) fileNames.get(position);
    					  Intent intent = new Intent();  
    					  intent.setAction(android.content.Intent.ACTION_VIEW);  
    					  File file = new File(getExternalFilesDir(null) + "/" + codeLearnLessons.getItemAtPosition(position).toString());  
    					  intent.setDataAndType(Uri.fromFile(file), "image/*");  
    					  startActivity(intent);   
    			        }
    		       });
    	        return view;
    	  		}
    	  	}
    public class FragmentTab2 extends Fragment {
  	  public View onCreateView(LayoutInflater inflater, ViewGroup container, 
              Bundle savedInstanceState){
				final List fileNames = videoFiles(getExternalFilesDir(null) + "/");
				super.onCreate(savedInstanceState);
				//setContentView(R.layout.familytab);
				View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
				//String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
				ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(TestMediaView.this, android.R.layout.simple_list_item_1, fileNames);
				final ListView codeLearnLessons = (ListView)findViewById(R.id.familylist);
				codeLearnLessons.setAdapter(codeLearnArrayAdapter);
				codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
			return view;
  	  	}
  	}
    public class FragmentTab3 extends Fragment {
    	  public View onCreateView(LayoutInflater inflater, ViewGroup container, 
                  Bundle savedInstanceState){
    				final List fileNames = audioFiles(getExternalFilesDir(null) + "/");
    				super.onCreate(savedInstanceState);
    				//setContentView(R.layout.familytab);
    				View view = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
    				//String[] codeLearnChapters = new String[] { "Android Introduction","Android Setup/Installation","Android Hello World","Android Layouts/Viewgroups","Android Activity & Lifecycle","Intents in Android"};
    				ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(TestMediaView.this, android.R.layout.simple_list_item_1, fileNames);
    				final ListView codeLearnLessons = (ListView)findViewById(R.id.familylist);
    				codeLearnLessons.setAdapter(codeLearnArrayAdapter);
    				codeLearnLessons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    				public void onItemClick(AdapterView<?> parent, View view,
    				   int position, long id) {
    					  //final String text = ((TextView)view).getText();
    				     //String text = codeLearnLessons.get(position).tostring().trim();//first method
    					  String text = (String) fileNames.get(position);
    					  Intent intent = new Intent();  
    					  intent.setAction(android.content.Intent.ACTION_VIEW);  
    					  File file = new File(getExternalFilesDir(null) + "/" + codeLearnLessons.getItemAtPosition(position).toString());  
    					  intent.setDataAndType(Uri.fromFile(file), "audio/*");  
    					  startActivity(intent);   
    				   }
    				});
    			return view;
      	  	}
  	}
    public class MyTabListener implements ActionBar.TabListener {
    	Fragment fragment;
    	
    	public MyTabListener(Fragment fragment) {
    		this.fragment = fragment;
    	}
    	
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
    		ft.replace(android.R.id.tabs, fragment);
    	}
    	
    	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    		ft.remove(fragment);
    	}
    	
    	public void onTabReselected(Tab tab, FragmentTransaction ft) {
    		// nothing done here
    	}
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }//end method    
}//end class