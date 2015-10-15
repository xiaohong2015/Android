package xh.ts.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public final class FinalData {
	
	public ContentValues table= new ContentValues();	
	public FinalData() {
		table.put("Assortment", "assortment");
		table.put("User", "user");
	}

	public String name= "test.db";
	public int version= 1;

	public String[] sql= new String[]{
		"create table if not exists assortment(id integer primary key autoincrement"
			+ ", name varchar not null)",
			
		"create table if not exists user(id integer primary key autoincrement"
		+ ", username varchar"
		+ ", password varchar"
		+ ", assortmentId integer references assortment(id))"
	};
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("no working");
		// db.execSQL("alter table group add column other string");
	}
}
