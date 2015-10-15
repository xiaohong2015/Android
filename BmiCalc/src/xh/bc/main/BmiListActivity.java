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

	// �������ݿ��������
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmi_list);
		
		// ��ʼ���б�
		this.init((ListView)findViewById(R.id.bmiList));
	}

	// ����б���Ŀ��Ӧ�¼�
	private final class listListener implements OnItemClickListener {

		public void onItemClick(final AdapterView<?> parent, final View view, final int position,
				long id) {
			new AlertDialog.Builder(BmiListActivity.this).setTitle(R.string.isDelete)
				.setPositiveButton(R.string.ok, new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Cursor c= (Cursor) parent.getItemAtPosition(position);
						
						db= new DataHelper(getApplicationContext(), null).getWritableDatabase();
						// ����idɾ�����ݱ�ĳ����¼
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
		// ��ȡ���ݿ��bmi���м�¼
		Cursor c= db.query("Bmi", null, null, null, null, null, null);
		// ͨ��SimpleCursorAdaper��������װ����
		SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.bmi, c
				, new String[]{"height", "weith", "result", "createTime"}, new int[]{R.id.height, R.id.weith, R.id.result, R.id.createTime}, 0);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new listListener());
		
	}
}
