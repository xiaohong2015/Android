package com.android.ttx.actiitylifedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SimpleActivity extends Activity{

	private Button simple;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple);
		
		simple = (Button) findViewById(R.id.simple);
		simple.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(SimpleActivity.this,ActivityLifeDemo.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
