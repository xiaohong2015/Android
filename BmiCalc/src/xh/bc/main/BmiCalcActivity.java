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

// 本程序执行入口类
public class BmiCalcActivity extends Activity {
    /** Called when the activity is first created. */
	
	// 声明数据库操作对象
	private SQLiteDatabase db;
	private EditText et;
	private TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 绑定按钮事件
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
    	// 计算bmi值
    	btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				et= (EditText) findViewById(R.id.heightId);
				String sh= et.getText().toString();
				et= (EditText) findViewById(R.id.weithId);
				String sw= et.getText().toString();
				// 判断结果是否为空
				if(sh.equals("")|| sw.equals("")) {
					return;
				}
				double h= Double.parseDouble(sh)/ 100;
				double w= Double.parseDouble(sw);
				double result= (w/ ( h* h));
				DecimalFormat df= new DecimalFormat("0.00");
				
				// 根据bmi值确定建议范围
				String suggest= R.string.your+ df.format(result)+ "\n";
				if(result< 20) {
					suggest+= R.string.bad;
				} else if(result> 25) {
					suggest+= R.string.toweight;
				} else {
					suggest+= R.string.good;
				}
				
				tv= (TextView) findViewById(R.id.result);
				// 把结果写在一个TextView中
				tv.setText(suggest);
			}
		});
    	
    	Button btn2= (Button) findViewById(R.id.save);
    	// 保存结果
    	btn2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ContentValues cv= new ContentValues();

				et= (EditText) findViewById(R.id.heightId);
				String sh= et.getText().toString();
				et= (EditText) findViewById(R.id.weithId);
				String sw= et.getText().toString();
				// 判断结果是否为空
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
				
				// 格式化时间
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				cv.put("createTime", sdf.format(new Date()));
				
				db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
				// 把数据封装到ContentValues变量，再写入到数据库
				db.insert("Bmi", null, cv);
			}
		});
    	
    	Button btn3= (Button) findViewById(R.id.list);
    	// 查询以往记录
    	btn3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent= new Intent(getApplicationContext(), BmiListActivity.class);
				startActivity(intent);
			}
		});
    }
}