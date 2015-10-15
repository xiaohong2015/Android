package xh.mp.action;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_player);
		
		this.VideoRun(R.id.videoView1, R.drawable.chenzhen);
	}
	
	public void VideoRun(int controlId, int videoId) {
		VideoView vv= (VideoView) findViewById(controlId);
		vv.setVideoURI(Uri.parse("android.resource://xh.mp.action/" + videoId));
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // 设置全屏
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 设置屏幕常亮
		vv.setMediaController(new MediaController(this)); // 设置媒体控制条
		vv.requestFocus();
		vv.start();
	}
}
