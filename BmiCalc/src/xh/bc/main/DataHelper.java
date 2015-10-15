package xh.bc.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper{

	// 数据库文件名称 BmiCalc
	private final static String name= "BmiCalc.db";
	private final static int version= 1;
	
	public DataHelper(Context context, CursorFactory factory) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建数据库表 Bmi
	db.execSQL("create table if not exists Bmi(_id integer primary key autoincrement"
				+ ", height double" // 高度
				+ ", weith double"  // 体重
				+ ", result double" // bmi值
				+ ", createTime varchar)"); // 创建时间
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
