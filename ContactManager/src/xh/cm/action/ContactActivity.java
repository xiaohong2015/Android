package xh.cm.action;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends Activity {

	private SQLiteDatabase db;
	private int _id;
	private EditText et;
	
	private int nameId;
	private int phoneId;
	private String phone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		
		nameId= R.id.name;
		phoneId= R.id.phone;
		
		Bundle b= getIntent().getBundleExtra("b");
		
		if(b!= null) {
			// 修改
			_id= b.getInt("_id");
			this.loadId(_id);
			this.bindListener(R.id.send, R.id.call, R.id.add, R.id.back);
		} else {
			// 添加
			this.bindListener2(R.id.send, R.id.call, R.id.add, R.id.back);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, Menu.FIRST+0, 1, "编辑");
		menu.add(1, Menu.FIRST+1, 1, "删除");
		menu.add(1, Menu.FIRST+2, 1, "返回");
		
		return super.onCreateOptionsMenu(menu);
	}	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case 1: {
				if(item.getTitle().equals("编辑")) {
					item.setTitle("保存");
			    	et= (EditText) findViewById(nameId);
		    		et.setEnabled(true);
			    	et= (EditText) findViewById(phoneId);
		    		et.setEnabled(true);
				} else {
					item.setTitle("编辑");
					ContentValues cv= new ContentValues();
			    	
			    	et= (EditText) findViewById(nameId);
					cv.put("name", et.getText().toString());
			    	et.setEnabled(false);
			    	
			    	et= (EditText) findViewById(phoneId);
					cv.put("phone", et.getText().toString());
			    	et.setEnabled(false);

			    	db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
			    	// contact为表名,注意表名与DataHelper类创建的表名一致
			    	db.update("contact", cv, "_id=?", new String[]{String.valueOf(_id)});
				}
			} break;
			
			case 2: {
				new AlertDialog.Builder(ContactActivity.this).setTitle("确定要删除吗?")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) { 
							db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
							// contact为表名,注意表名与DataHelper类创建的表名一致
					    	db.delete("contact", "_id=?", new String[]{String.valueOf(_id)});

							 // 跳转到 ContactManageActivity 类
					    	Intent intent= new Intent(getApplicationContext(), ContactManageActivity.class);
					    	
					    	startActivity(intent);
					    	finish();
						}
					})
					.setNegativeButton("取消", null)
					.show();
			} break;
			case 3: {
				// 跳转到 ContactManageActivity 类
				Intent intent= new Intent(getApplicationContext(), ContactManageActivity.class);
				
				startActivity(intent);
				finish();
			} break;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public void loadId(int _id) {    	
    	db= new DataHelper(this, null).getReadableDatabase();
    	
    	// 以下两句任选一句, 注意表名与DataHelper类创建的表名一致
    	Cursor c= db.query("contact", null, "_id=?", new String[]{String.valueOf(_id)}, null, null, null);
    	//Cursor c= db.rawQuery("select * from contact where _id=?", new String[]{String.valueOf(_id)});
    	
    	if(c.moveToFirst()) {	    	
	    	et= (EditText) findViewById(nameId);
	    	et.setText(c.getString(c.getColumnIndex("name")));
	    	et.setEnabled(false);
	    	
	    	// Color.BLUE、Color.YELLOW、Color.RED  等多种颜色任选	    	
	    	et.setTextColor(Color.GREEN);
	    	
	    	et= (EditText) findViewById(phoneId);
	    	et.setText(c.getString(c.getColumnIndex("phone")));
	    	phone= et.getText().toString();
	    	et.setEnabled(false);

	    	// Color.BLUE、Color.YELLOW、Color.RED  等多种颜色任选	
	    	et.setTextColor(Color.GREEN);
    	}
    }

    public void bindListener(int sendId, int callId, int addId, int backId) {
    	findViewById(addId).setVisibility(View.GONE);
    	findViewById(backId).setVisibility(View.GONE);
    	
		et= (EditText) findViewById(phoneId);
		
    	Button btn1= (Button) findViewById(sendId);
    	btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				Intent intent= new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ phone));
				startActivity(intent);
			}
		});
    	
    	Button btn2= (Button) findViewById(callId);
    	btn2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// 打电话就两句代码
				Intent intent= new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ et.getText()));
				startActivity(intent);
			}
		});
    }
    public void bindListener2(int sendId, int callId, int addId, final int backId) {
    	findViewById(sendId).setVisibility(View.GONE);
    	findViewById(callId).setVisibility(View.GONE);
    	
		Button btn1= (Button) findViewById(addId);
		btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ContentValues cv= new ContentValues();
				db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
				
				et= (EditText) findViewById(nameId);
				cv.put("name", et.getText().toString());
				
				et= (EditText) findViewById(phoneId);
				cv.put("phone", et.getText().toString());

				// contact为表名,注意表名与DataHelper类创建的表名一致
		    	db.insert("contact", null, cv);
		    	
				Button btn2= (Button) findViewById(backId);
				btn2.callOnClick();
			}
		});
		
		Button btn2= (Button) findViewById(backId);
		btn2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				 // 跳转到 ContactManageActivity 类
				Intent intent= new Intent(getApplicationContext(), ContactManageActivity.class);
				
				startActivity(intent);
				finish();
			}
		});
    }

}
