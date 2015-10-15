package xh.mp.action;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class MediaPlayerActivity extends Activity {
    /** Called when the activity is first created. */

	private MediaPlayer mp= null;
	private SeekBar seekBar1= null;
	private Handler handler= new Handler();
	Runnable updateSeekBar= new Runnable() {
		public void run() {
			seekBar1.setProgress(mp.getCurrentPosition());
			handler.postDelayed(updateSeekBar, 100);
		}
	};
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, Menu.FIRST, 1, R.string.about);
		menu.add(1, Menu.FIRST+1, 1, R.string.exit);
		return super.onCreateOptionsMenu(menu);
		
	};
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case 1: {
				new AlertDialog.Builder(this)
					.setTitle(R.string.me)
					.setPositiveButton(R.string.ok, null)
					.show();
			} break;
			case 2: {
				finish();
			} break;
		}
		return super.onOptionsItemSelected(item);
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		System.out.println("begin");

		this.bindListener(R.drawable.avenge, R.id.seekBar1, R.id.beginB, R.id.pauseB, R.id.stopB, R.id.videoB);
		//this.MusicRun(R.drawable.avenge, R.id.seekBar1);

		System.out.println("end");
	}
    public void MusicRun(int musicId, int seekBarId) {
		try {
			//mp= MediaPlayer.create(this, musicId);
			mp = new MediaPlayer();
			mp.setDataSource(this, Uri.parse("android.resource://xh.mp.action/" + musicId));
			
			mp.prepare();
			mp.start();
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(mp!= null&& mp.getDuration()!= 0) {
			seekBar1= (SeekBar) findViewById(seekBarId);
			seekBar1.setMax(mp.getDuration());			
			
			seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {		
				public void onStopTrackingTouch(SeekBar seekBar) {}				
				public void onStartTrackingTouch(SeekBar seekBar) {}
				
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					if(fromUser) {
						mp.seekTo(progress);
					}
				}
			});
			updateSeekBar.run();
		}
	}
    public void bindListener(final int musicId, final int seekBarId, int beginId, int pauseId, int stopId, int videoId) {
    	final Button btn3= (Button) findViewById(pauseId);
		btn3.setEnabled(false);
    	btn3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(mp!= null) {
					if(mp.isPlaying()) {
						mp.pause();
						btn3.setText(R.string.going);
					} else {
						mp.start();
						handler.post(updateSeekBar);
						btn3.setText(R.string.pauseB);
					}
				}
			}
		});
    	
    	Button btn2= (Button) findViewById(beginId);
    	btn2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(mp== null|| !mp.isPlaying()) {
					MediaPlayerActivity.this.MusicRun(musicId, seekBarId);
					btn3.setEnabled(true);
				}
			}
		});
    	
    	Button btn4= (Button) findViewById(stopId);
    	btn4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(mp!= null) {
					handler.removeCallbacks(updateSeekBar);
					mp.stop();
					mp= null;
					if(seekBar1!= null) {
						seekBar1.setProgress(0);
						btn3.setText(R.string.pauseB);
						btn3.setEnabled(false);
					}
				}
			}
		});
    	
    	Button btn5= (Button) findViewById(videoId);
    	btn5.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent= new Intent(getApplicationContext(), VideoActivity.class);
				startActivity(intent);
			}
		});
    }
	
}