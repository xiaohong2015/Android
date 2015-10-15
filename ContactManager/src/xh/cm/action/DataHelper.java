package xh.cm.action;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper{

	private final static int version= 1;
	
	public DataHelper(Context context, CursorFactory factory) {
		super(context, "contactManage.db", factory, version);// 数据库名称可修改, 例如  xiaohong.db
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// contact表名可修改		
		db.execSQL("create table if not exists contact(_id integer primary key autoincrement"
				+ ", name varchar"
				+ ", phone varchar)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
