package xh.bc.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class BmiListActivity extends Activity {

	// 声明数据库操作对象
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmi_list);
		
		// 初始化列表
		this.init((ListView)findViewById(R.id.bmiList));
	}

	// 点击列表条目相应事件
	private final class listListener implements OnItemClickListener {

		public void onItemClick(final AdapterView<?> parent, final View view, final int position,
				long id) {
			new AlertDialog.Builder(BmiListActivity.this).setTitle(R.string.isDelete)
				.setPositiveButton(R.string.ok, new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Cursor c= (Cursor) parent.getItemAtPosition(position);
						
						db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
						// 根据id删除数据表某条记录
						db.delete("Bmi", "_id=?", new String[]{String.valueOf(c.getInt(c.getColumnIndex("_id")))});
						view.setVisibility(View.GONE);
					}
				})
				.setNegativeButton(R.string.cancer, null)
				.show();
		}
	}
	public void init(ListView lv) {
		db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
		// 获取数据库表bmi所有记录
		Cursor c= db.query("Bmi", null, null, null, null, null, null);
		// 通过SimpleCursorAdaper适配器封装数据
		SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.bmi, c
				, new String[]{"height", "weith", "result", "createTime"}, new int[]{R.id.height, R.id.weith, R.id.result, R.id.createTime}, 0);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new listListener());
		
	}
}
