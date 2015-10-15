package xh.cm.action;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactManageActivity extends Activity {
	/** Called when the activity is first created. */
	
	private SQLiteDatabase db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.listContact(R.id.contactView, R.layout.contact_list, new String[]{"name", "phone"}, new int[]{R.id.name, R.id.phone});
        this.bindListener(R.id.search, R.id.contactView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(1, Menu.FIRST+0, 1, "添加");
    	menu.add(1, Menu.FIRST+1, 1, "退出");
    	return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
	    	case 1: {
	    		// 跳转到 ContactActivity 类
				Intent intent= new Intent(getApplicationContext(), ContactActivity.class);
				
				startActivity(intent);
	    	} break;
	    	case 2: {
	    		finish();
	    	} break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    private final class listListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Cursor c= (Cursor) ((ListView) parent).getItemAtPosition(position);
	        
			 // 跳转到 ContactActivity 类
	        Intent intent= new Intent(getApplicationContext(), ContactActivity.class);
	        
	        Bundle b= new Bundle();
	        b.putInt("_id", c.getInt(c.getColumnIndex("_id")));
	        intent.putExtra("b", b);	        
	        startActivity(intent);
		}
    	
    }
    public void listContact(int viewId, int listId, String[] strs, int[] ids) {
    	ListView lv= (ListView) findViewById(viewId);
    	db= new DataHelper(this, null).getReadableDatabase();
    	
    	// 以下两句任选一句, 注意表名与DataHelper类创建的表名一致
    	Cursor c= db.query("contact", null, null, null, null, null, null);
    	//Cursor c= db.rawQuery("select id as _id,  name, phone from contact", null);
    	
		SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, listId, c, strs, ids, 0);
    	
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new listListener());
    	
    }

    public void bindListener(int searchId, final int viewId) {
    	EditText et= (EditText) findViewById(searchId);
    	et.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				ListView lv= (ListView) findViewById(viewId);

				for(int i=0; i< lv.getCount(); i++) {
					lv.getChildAt(i).setVisibility(View.GONE);
				}
				for(int i=0; i< lv.getCount(); i++) {
					Cursor c= (Cursor) lv.getItemAtPosition(i);
					String name= c.getString(c.getColumnIndex("name"));
					String phone= c.getString(c.getColumnIndex("phone"));

					if(name.contains(s)|| phone.indexOf(s.toString())== 0) {
						lv.getChildAt(i).setVisibility(View.VISIBLE);
					}
				}
			}			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}			
			public void afterTextChanged(Editable s) {}
		});
    }
    
}
