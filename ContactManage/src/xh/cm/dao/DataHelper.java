package xh.cm.dao;

import xh.cm.data.FinalData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

	private static FinalData fd= new FinalData();
	
	public DataHelper(Context context) {
		super(context, fd.name, null, fd.version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] sql= fd.sql;
		for(String s: sql) {
			db.execSQL(s);
		}
		
		String[] init= fd.init;
		for(String s: init) {
			db.execSQL(s);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		fd.onUpgrade(db, oldVersion, newVersion);
	}
	
}
