package xh.bc.action;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BmiCalculateActivity extends Activity {
    /** Called when the activity is first created. */
	
	private SQLiteDatabase db;
    private EditText et;
    private TextView tv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.bindListener(); // 绑定按钮事件
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(1, Menu.FIRST+0, 1, "关于");
    	menu.add(1, Menu.FIRST+1, 1, "退出");
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
	    	case 1: {
	    		new AlertDialog.Builder(BmiCalculateActivity.this).setTitle("by-12网工-201224132156洪浩然")
	    			.setPositiveButton("确定", null)
	    			.show();
	    	} break;
	    	case 2: {
	    		finish(); // 退出程序
	    	}
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    public double calc(int heightId, int widthId) {
		et= (EditText) findViewById(heightId);
		// 判断是否为空
		if(TextUtils.isEmpty(et.getText())) {
			return 0;
		}
		double h= Double.parseDouble(et.getText().toString())/ 100;
		et= (EditText) findViewById(widthId);;
		// 判断是否为空
		if(TextUtils.isEmpty(et.getText())) {
			return 0;
		}
		double w= Double.parseDouble(et.getText().toString());
		return (w/ ( h* h));
    }
    public void bindListener() {
    	Button btn1= (Button) findViewById(R.id.calc);
    	// 计算BMI事件
    	btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				double result= BmiCalculateActivity.this.calc(R.id.heightId, R.id.widthId);
				if(result== 0) {
					return;
				}
				DecimalFormat df= new DecimalFormat("0.00");
				// 根据计算值范围确定建议内容
				String suggest= "你的BMI值为 : "+ df.format(result)+ "\n建议 : ";
				if(result< 20) {
					suggest+= "你应该回去多吃一点!太瘦啦";
				} else if(result> 25) {
					suggest+= "你应该去体育馆锻炼一下拉!太胖啦";
				} else {
					suggest+= "恭喜你！身材好棒!继续保持";
				}
				
				tv= (TextView) findViewById(R.id.result);				
				tv.setText(suggest);
			}
		});
    	
    	Button btn2= (Button) findViewById(R.id.save);
    	// 保存事件
    	btn2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ContentValues cv= new ContentValues();
				
				double result= BmiCalculateActivity.this.calc(R.id.heightId, R.id.widthId);
				if(result== 0) {
					return;
				}
				DecimalFormat df= new DecimalFormat("0.00");
				cv.put("result", df.format(result));
				tv= (TextView) findViewById(R.id.result);
				tv.setText("");
				
				et= (EditText) findViewById(R.id.heightId);
				cv.put("height", et.getText().toString());
				et.setText("");
				
				et= (EditText) findViewById(R.id.widthId);
				cv.put("width", et.getText().toString());
				et.setText("");
				
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
				cv.put("createTime", sdf.format(new Date()));
				
				db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
				db.insert("bmi", null, cv);
				Toast.makeText(getApplicationContext(), "已保存", 1);
			}
		});
    	
    	Button btn3= (Button) findViewById(R.id.list);
    	btn3.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent= new Intent();
				intent.setClassName(getApplicationContext(), "xh.bc.action.BmiListActivity");
				startActivity(intent);
			}
		});
    }
}