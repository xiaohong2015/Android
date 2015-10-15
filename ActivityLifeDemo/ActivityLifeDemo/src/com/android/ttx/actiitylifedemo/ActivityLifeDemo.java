package com.android.ttx.actiitylifedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityLifeDemo extends Activity implements OnClickListener{
	private final static String TAG="ActivityLifeDemo";
	
	private Button btnSimple;
	private Button btnRevalue;
	private Button btnTransferData;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i(TAG, "onCreate");
        setupViews();
    }
    
    private void setupViews(){
    	btnSimple = (Button) findViewById(R.id.start_simple);
    	btnRevalue = (Button) findViewById(R.id.start_revalue);
    	btnTransferData = (Button) findViewById(R.id.start_transfer_data);
    	
    	btnSimple.setOnClickListener(this);
    	btnRevalue.setOnClickListener(this);
    	btnTransferData.setOnClickListener(this);
    }

	@Override
	protected void onStart() {
		Log.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "onStop");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}

	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.start_simple:
			intent = new Intent(ActivityLifeDemo.this,SimpleActivity.class);
			startActivity(intent);
			break;
		case R.id.start_revalue:
			intent = new Intent(ActivityLifeDemo.this,RevalueActivity.class);
			startActivityForResult(intent, 0x1001);
			break;
		case R.id.start_transfer_data:
			intent = new Intent(ActivityLifeDemo.this,TransferDataActivity.class);
			intent.putExtra("transfer_key", "hello TransferDataActivity");
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0x1001){
			String str = data.getStringExtra("revalue_key");
			Log.i(TAG, "返回的值为："+str);
		}
	}
}