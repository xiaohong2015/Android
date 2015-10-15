package xh.cm.model;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public interface BaseModel<T> {
	
	public ContentValues contentValues();
	
	public List<T> entitys(Cursor c);
	
	public T entity(Cursor c);
}
