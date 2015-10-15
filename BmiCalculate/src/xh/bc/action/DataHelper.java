package xh.bc.action;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper{

	private final static String name= "BmiCalculate.db";
	private final static int version= 1;
	
	public DataHelper(Context context, CursorFactory factory) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] sql= new String[] {
			"create table if not exists bmi(_id integer primary key autoincrement"
				+ ", height double"
				+ ", width double"
				+ ", result double"
				+ ", createTime varchar);"
		};
		String[] init= new String[] {
			"insert into bmi(_id, height, width, result, createTime) values(1, 165, 56, 20.0, '2015-05-20');"
		};
		
		for(String s: sql) {
			db.execSQL(s);
		}
		for(String s: init) {
			db.execSQL(s);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("no working");
	}
}
