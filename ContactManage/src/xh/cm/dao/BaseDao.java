package xh.cm.dao;

import java.io.Serializable;

import xh.cm.data.FinalData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BaseDao {

	private DataHelper dataHelper;
	private SQLiteDatabase db;
	private String tableName;
	
	public BaseDao(Context context, String tableName) {
		dataHelper= new DataHelper(context);
		db= dataHelper.getWritableDatabase();

		FinalData fd= new FinalData();
		this.tableName= (String) fd.table.get(tableName);
	}
	
	public void insert(ContentValues cv) {
		db.insert(tableName, null, cv);
	}
	public void delete(Serializable id) {
		db.delete(tableName, "id=?", new String[]{String.valueOf(id)});
	}
	public void update(ContentValues cv, Serializable id) {
		db.update(tableName, cv, "id=?", new String[]{String.valueOf(id)});
	}
	public Cursor findById(Serializable id) {
		return db.rawQuery("select * from "+ tableName+ " where id=?", new String[]{String.valueOf(id)});
	}
	public Cursor query(String[] columns, String selection
			, String[] selectionArgs, String groupBy, String having
			, String orderBy, String limit) {
		return db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}
}
