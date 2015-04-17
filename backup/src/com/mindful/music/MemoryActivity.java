package com.mindful.music;
import com.mindful.music.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoryActivity extends Activity{
	@Override
	public void onBackPressed() {
	    // Otherwise defer to system default behavior.
	    super.onBackPressed();
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memories);
        final Button viewPicsBtn = (Button) findViewById(R.id.view_pictures);
        viewPicsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(MemoryActivity.this, FamilyTab.class);
        		MemoryActivity.this.startActivity(myIntent);
            }
        });
        final Button viewVidsBtn = (Button) findViewById(R.id.view_videos);
        viewVidsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(MemoryActivity.this, ObjectsTab.class);
        		MemoryActivity.this.startActivity(myIntent);
            }
        });
        final Button viewAudioBtn = (Button) findViewById(R.id.view_audio);
        viewAudioBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(MemoryActivity.this, OthersTab.class);
        		MemoryActivity.this.startActivity(myIntent);
            }
        });
    }
}
