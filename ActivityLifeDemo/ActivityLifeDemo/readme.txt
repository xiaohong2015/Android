��������������ʵ���Ƕࡣ������ԭ������ת�ء���������д���ˡ��͵����Լ���ϰ�ɡ�

�ȿ���ͼ��

�½����̣���д���´��룺
package com.android.ttx.actiitylifedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class ActivityLifeDemo extends Activity {
	private final static String TAG="ActivityLifeDemo";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.i(TAG, "onCreate");
    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStop");
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Log.i(TAG, "�����ˣ�KEYCODE_BACK");
			break;
		case KeyEvent.KEYCODE_HOME:
			Log.i(TAG, "�����ˣ�KEYCODE_HOME");
			break;
		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}
    
}
1������
�������´�ӡ��־��
08-31 08:46:53.916: INFO/ActivityLifeDemo(312): onCreate
08-31 08:46:53.916: INFO/ActivityLifeDemo(312): onStart
08-31 08:46:53.916: INFO/ActivityLifeDemo(312): onResume
2�����·��ذ�����
08-31 09:29:57.231: INFO/ActivityLifeDemo(354): �����ˣ�KEYCODE_BACK
08-31 09:29:57.396: INFO/ActivityLifeDemo(354): onPause
08-31 09:29:58.216: INFO/ActivityLifeDemo(354): onStop
08-31 09:29:58.216: INFO/ActivityLifeDemo(354): onDestroy
3������Home������������򿪹���Ӧ�ó��򣬵��ActivityLifeDemo
08-31 08:51:46.916: INFO/ActivityLifeDemo(312): onCreate
08-31 08:51:46.916: INFO/ActivityLifeDemo(312): onStart
08-31 08:51:46.936: INFO/ActivityLifeDemo(312): onResume
4����Home��
08-31 08:53:32.676: INFO/ActivityLifeDemo(312): onPause
08-31 08:53:33.796: INFO/ActivityLifeDemo(312): onStop
5����AllList�е����
08-31 08:54:14.286: INFO/ActivityLifeDemo(312): onRestart
08-31 08:54:14.286: INFO/ActivityLifeDemo(312): onStart
08-31 08:54:14.296: INFO/ActivityLifeDemo(312): onResume

ͨ����ӡ��־��Ϣ���ó����½��ۣ������ؼ�ʱӦ�ó���������������ڣ�����onDestroy��������Home��ʱֻ����ͣ������onStop���������ٴδ�Ӧ�ó���ʱ�����onRestart��д����Ӧ�ó���

