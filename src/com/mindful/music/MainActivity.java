package com.mindful.music;
import com.mindful.music.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{
	@Override
	public void onBackPressed() {
	    // Otherwise defer to system default behavior.
	    super.onBackPressed();
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
        final Button addMemBtn = (Button) findViewById(R.id.add_a_memory);
        addMemBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(MainActivity.this, RecordActivity.class);
        		MainActivity.this.startActivity(myIntent);
            }
        });
        final Button viewMems = (Button) findViewById(R.id.view_memories);
        viewMems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(MainActivity.this, MemoryActivity.class);
        		MainActivity.this.startActivity(myIntent);
            }
        });
        final Button AutoPlayBtn = (Button) findViewById(R.id.auto_play);
        AutoPlayBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
        		Intent myIntent = new Intent(MainActivity.this, AutoPlayActivity.class);
        		MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
