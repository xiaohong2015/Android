package com.android.ttx.actiitylifedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RevalueActivity extends Activity {

	private Button btnFinish;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transfer_data);
		
		btnFinish = (Button) findViewById(R.id.transfer_data);
		
		btnFinish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent  = new Intent();
				intent.putExtra("revalue_key", "haha-revalueActivity");
				setResult(0x1001, intent);
				finish();
			}
		});
	}
}
