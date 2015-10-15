package xh.cm.action;

import java.io.File;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ContactManagerActivity extends Activity {
	/** Called when the activity is first created. */
	
	private SQLiteDatabase db;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.init("data/data/xh.cm.action/databases/contactManage.db");
        this.listContact((ListView) findViewById(R.id.contactView)); 
        
    }
    public void init(String databases) {
        File f= new File(databases);
        if(f.isFile()) {
        	f.delete();
        }
    }

    private final class listListener implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListView lv= (ListView) parent;
			Cursor c= (Cursor) lv.getItemAtPosition(position);
			String bindId= String.valueOf(c.getInt(c.getColumnIndex("_id")));
			Toast.makeText(getApplicationContext(), bindId, 1).show();
			
	        
	        
	        Intent intent= new Intent();
	        intent.setClassName(getApplicationContext(), "xh.cm.action.ContactActivity");
	        Bundle b= new Bundle();
	        b.putString("_id", bindId);
	        intent.putExtra("b", b);
	        
	        startActivity(intent);
		}
    	
    }
    public void listContact(ListView tv) {
    	db= new DataHelper(this, null).getReadableDatabase();
    	Cursor c= db.query("contact", null, null, null, null, null, null);
    	//Cursor c= db.rawQuery("select id as _id,  name, phone from contact", null);
    	System.out.println(c.getCount());
		SimpleCursorAdapter adapter= new SimpleCursorAdapter(this, R.layout.contact_list, c
    			, new String[]{"name", "phone"}, new int[]{R.id.name, R.id.phone}, 0);
    	
    	tv.setAdapter(adapter);
    	tv.setOnItemClickListener(new listListener() );
    }
    public void deleteContact(int id) {
    	db.delete("contact", "id=?", new String[]{String.valueOf(id)});
    }
    
    public void insertContact(ContentValues cv) {
    	db.insert("contact", null, cv);
    }
    public void updateContact(ContentValues cv, int id) {
    	db.update("contact", cv, "id=?", new String[]{String.valueOf(id)});
    }
    
}
