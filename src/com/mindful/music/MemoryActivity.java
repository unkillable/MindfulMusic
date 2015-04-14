package com.mindful.music;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//@SuppressLint("NewApi")
public class MemoryActivity extends Activity {

    private String TAG  = getClass().getName();
    private Intent i    = null;
    private ActionBar actionBar;
    private Tab one;
    private Tab two;
    private Tab three;

    // create a tab listener that is called when the user changes tabs
    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
            if (tab.getTag().equals("one")){
                Log.d(TAG, "tab one selected");
                i = new Intent(getApplicationContext(), FamilyTab.class);
                determineRun();
            }
            if (tab.getTag().equals("two")){
                Log.d(TAG, "tab two selected");
                i = new Intent(getApplicationContext(), ObjectsTab.class);
                determineRun();
            }
            if (tab.getTag().equals("three")){
                Log.d(TAG, "tab three selected");
                i = new Intent(getApplicationContext(), OthersTab.class);  
                determineRun();
            }           
        }

        @Override
        public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
            // TODO Auto-generated method stub          
        }

        @Override
        public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
            // TODO Auto-generated method stub      
        }
    };  

    // we only need to start the Activity if it's not actually already the current Activity!
    void determineRun(){
        if (!TAG.equals(i.getComponent().getClassName())){
            startActivity(i);
        }   
        return;
    }//end method

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState); 

        actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setSubtitle(getResources().getString("subtitle"));        
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
        one = actionBar.newTab();
        one.setText("Pictures").setTag("one");
        two = actionBar.newTab();
        two.setText("Videos").setTag("two");
        three = actionBar.newTab();
        three.setText("Audio").setTag("three");                
        one.setTabListener(tabListener);
        two.setTabListener(tabListener);
        three.setTabListener(tabListener);

        // You will have to set the selected Tab manually
        // A good idea would be to create a subclass for each Tab based on this code
        // Then, just create a new Activity which extends ActionBarActivity
        actionBar.addTab(one, 0, false);
        actionBar.addTab(two, 1, true); // selected Tab
        actionBar.addTab(three, 2, false);
    }//end method

    @Override
    public void onResume(){
        super.onResume();

        Log.d(TAG, "onResume()");
        Log.d(TAG, ""+i.getComponent().getClassName());
        // again, here you need to select the Tab manually
        if (!TAG.equals(i.getComponent().getClassName())){
            actionBar.selectTab(two); // selected Tab
        }   
    }//end method

    @Override
    public void onPause(){
        super.onPause();

        Log.d(TAG, "onPause()");
    }//end method

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }//end method    
}//end class