package xh.bc.main;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// ������ִ�������
public class BmiCalcActivity extends Activity {
    /** Called when the activity is first created. */
	
	// �������ݿ��������
	private SQLiteDatabase db;
	private EditText et;
	private TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // �󶨰�ť�¼�
        this.bindListener();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, Menu.FIRST,  0, R.string.about);
    	menu.add(0, Menu.FIRST+1,0, R.string.exit);
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
	    	case 1: {
	    		new AlertDialog.Builder(BmiCalcActivity.this).setTitle(R.string.me)
	    			.setPositiveButton(R.string.ok, null)
	    			.show();
	    	} break;
	    	case 2: {
	    		finish();
	    	}
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    public void bindListener() {
    	Button btn1= (Button) findViewById(R.id.calc);
    	// ����bmiֵ
    	btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				et= (EditText) findViewById(R.id.heightId);
				String sh= et.getText().toString();
				et= (EditText) findViewById(R.id.weithId);
				String sw= et.getText().toString();
				// �жϽ���Ƿ�Ϊ��
				if(sh.equals("")|| sw.equals("")) {
					return;
				}
				double h= Double.parseDouble(sh)/ 100;
				double w= Double.parseDouble(sw);
				double result= (w/ ( h* h));
				DecimalFormat df= new DecimalFormat("0.00");
				
				// ����bmiֵȷ�����鷶Χ
				String suggest= R.string.your+ df.format(result)+ "\n";
				if(result< 20) {
					suggest+= R.string.bad;
				} else if(result> 25) {
					suggest+= R.string.toweight;
				} else {
					suggest+= R.string.good;
				}
				
				tv= (TextView) findViewById(R.id.result);
				// �ѽ��д��һ��TextView��
				tv.setText(suggest);
			}
		});
    	
    	Button btn2= (Button) findViewById(R.id.save);
    	// ������
    	btn2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ContentValues cv= new ContentValues();

				et= (EditText) findViewById(R.id.heightId);
				String sh= et.getText().toString();
				et= (EditText) findViewById(R.id.weithId);
				String sw= et.getText().toString();
				// �жϽ���Ƿ�Ϊ��
				if(sh.equals("")|| sw.equals("")) {
					return;
				}
				double h= Double.parseDouble(sh)/ 100;
				double w= Double.parseDouble(sw);
				double result= (w/ ( h* h));
				
				DecimalFormat df= new DecimalFormat("0.00");
				cv.put("result", df.format(result));
				tv= (TextView) findViewById(R.id.result);
				tv.setText("");
				
				et= (EditText) findViewById(R.id.heightId);
				cv.put("height", et.getText().toString());
				et.setText("");
				
				et= (EditText) findViewById(R.id.weithId);
				cv.put("weith", et.getText().toString());
				et.setText("");
				
				// ��ʽ��ʱ��
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				cv.put("createTime", sdf.format(new Date()));
				
				db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
				// �����ݷ�װ��ContentValues��������д�뵽���ݿ�
				db.insert("Bmi", null, cv);
			}
		});
    	
    	Button btn3= (Button) findViewById(R.id.list);
    	// ��ѯ������¼
    	btn3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent= new Intent(getApplicationContext(), BmiListActivity.class);
				startActivity(intent);
			}
		});
    }
}