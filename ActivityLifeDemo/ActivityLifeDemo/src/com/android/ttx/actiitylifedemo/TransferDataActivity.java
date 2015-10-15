package com.android.ttx.actiitylifedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TransferDataActivity extends Activity{

	
	private Button btnTransferData;
	private TextView txtShowData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transfer_data);
		
		setupViews();
	}
	
	private void setupViews(){
		btnTransferData = (Button) findViewById(R.id.transfer_data);
		txtShowData = (TextView) findViewById(R.id.show_data);
		
		Intent intent = this.getIntent();
		
		String str = intent.getStringExtra("transfer_key");
		txtShowData.setText(str);
		
		btnTransferData.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	
}
