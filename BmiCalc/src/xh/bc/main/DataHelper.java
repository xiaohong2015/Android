package xh.bc.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper{

	// ���ݿ��ļ����� BmiCalc
	private final static String name= "BmiCalc.db";
	private final static int version= 1;
	
	public DataHelper(Context context, CursorFactory factory) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// �������ݿ�� Bmi
	db.execSQL("create table if not exists Bmi(_id integer primary key autoincrement"
				+ ", height double" // �߶�
				+ ", weith double"  // ����
				+ ", result double" // bmiֵ
				+ ", createTime varchar)"); // ����ʱ��
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
