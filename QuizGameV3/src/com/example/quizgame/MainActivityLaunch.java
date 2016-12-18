package com.example.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivityLaunch extends Activity {

	public Button b1;
	public Button b2;
	public Button b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_launch);
		
		  b1 = (Button) findViewById(R.id.Button01);
		 
		
 b1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent(MainActivityLaunch.this,MainActivity1.class));
		}
	});
 b2 = (Button) findViewById(R.id.Button02); 
 b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivityLaunch.this,MainActivity2.class));
			}
		});
 b3 = (Button) findViewById(R.id.Button03); 
 b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivityLaunch.this,MainActivity3.class));
			}
		});
 
		
	}
}
