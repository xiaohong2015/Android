这类文章在网上实在是多。无论是原创还是转载。本来不想写的了。就当是自己复习吧。

先看下图：

新建工程，编写如下代码：
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
			Log.i(TAG, "按下了：KEYCODE_BACK");
			break;
		case KeyEvent.KEYCODE_HOME:
			Log.i(TAG, "按下了：KEYCODE_HOME");
			break;
		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}
    
}
1、运行
看到如下打印日志：
08-31 08:46:53.916: INFO/ActivityLifeDemo(312): onCreate
08-31 08:46:53.916: INFO/ActivityLifeDemo(312): onStart
08-31 08:46:53.916: INFO/ActivityLifeDemo(312): onResume
2、按下返回按键：
08-31 09:29:57.231: INFO/ActivityLifeDemo(354): 按下了：KEYCODE_BACK
08-31 09:29:57.396: INFO/ActivityLifeDemo(354): onPause
08-31 09:29:58.216: INFO/ActivityLifeDemo(354): onStop
08-31 09:29:58.216: INFO/ActivityLifeDemo(354): onDestroy
3、长按Home键，弹出最近打开过的应用程序，点击ActivityLifeDemo
08-31 08:51:46.916: INFO/ActivityLifeDemo(312): onCreate
08-31 08:51:46.916: INFO/ActivityLifeDemo(312): onStart
08-31 08:51:46.936: INFO/ActivityLifeDemo(312): onResume
4、按Home键
08-31 08:53:32.676: INFO/ActivityLifeDemo(312): onPause
08-31 08:53:33.796: INFO/ActivityLifeDemo(312): onStop
5、在AllList中点击打开
08-31 08:54:14.286: INFO/ActivityLifeDemo(312): onRestart
08-31 08:54:14.286: INFO/ActivityLifeDemo(312): onStart
08-31 08:54:14.296: INFO/ActivityLifeDemo(312): onResume

通过打印日志信息，得出如下结论：按返回键时应用程序结束了生命周期，调用onDestroy方法；按Home键时只是暂停，调用onStop方法，在再次打开应用程序时会调用onRestart重写启动应用程序。

