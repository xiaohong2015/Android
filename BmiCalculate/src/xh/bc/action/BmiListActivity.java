package xh.bc.action;

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

	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmi_list);
		
		this.init((ListView)findViewById(R.id.bmiList));
	}

	private final class listListener implements OnItemClickListener {

		public void onItemClick(final AdapterView<?> parent, final View view, final int position,
				long id) {
			new AlertDialog.Builder(BmiListActivity.this).setTitle("确定要删除此条目吗?")
				.setPositiveButton("确定", new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Cursor c= (Cursor) parent.getItemAtPosition(position);
						
						db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
						db.delete("bmi", "_id=?", new String[]{String.valueOf(c.getInt(c.getColumnIndex("_id")))});
						view.setVisibility(View.INVISIBLE);
					}
				})
				.setNegativeButton("取消", null)
				.show();

		}
		
	}
	public void init(ListView lv) {
		db= new DataHelper(this, null).getWritableDatabase();
		Cursor c= db.query("bmi", null, null, null, null, null, null);
		
		SimpleCursorAdapter sca= new SimpleCursorAdapter(this, R.layout.bmi, c
				, new String[]{"height", "width", "result", "createTime"}, new int[]{R.id.height, R.id.width, R.id.result, R.id.createTime}, 0);
		lv.setAdapter(sca);
		lv.setOnItemClickListener(new listListener());
		
	}
}
