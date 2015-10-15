package xh.ch001;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

public class Ch001Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
        	FileInputStream f0= this.openFileInput("1.txt");
			FileOutputStream f= this.openFileOutput("1.txt", MODE_PRIVATE);
			byte[] b= new byte[1024];
			int r= 0;
			while((r= f0.read(b))> 0) {
				for(int i=0; i< r; i++) {
					System.out.println(b[i]);
					b[i]--;
				}
				f.write(b);
			}
			f0.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Environment.getExternalStorageState();
		Environment.getExternalStorageDirectory();
    }
}