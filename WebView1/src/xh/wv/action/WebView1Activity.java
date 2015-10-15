package xh.wv.action;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebView1Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /* run before
		WebView wv= (WebView) findViewById(R.id.webView1);
		wv.loadUrl("http://www.baidu.com");
		wv.setWebViewClient(new WebViewClient());
		//*/
        
        /* first method
		WebView wv= new WebView(this);
		//wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl("http://baidu.com");
		setContentView(wv);
		//*/
        
        //* second method
        setContentView(R.layout.main);
		EditText et= (EditText) findViewById(R.id.editText1);
		et.setOnKeyListener(new OnKeyListener() {
			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
		    	if(keyCode== KeyEvent.KEYCODE_ENTER) {
		    		String url= ((EditText)findViewById(R.id.editText1)).getText().toString();
		    		if(!url.contains("http")) {
		    			url= "http://"+ url;
		    		}
		    		
		    		WebView wv= (WebView) findViewById(R.id.webView1);
		    		wv.loadUrl(url);
		    		wv.setWebViewClient(new WebViewClient());
		    		return true;
		    	}
				return false;
			}
		});
		//*/
    }
    
}