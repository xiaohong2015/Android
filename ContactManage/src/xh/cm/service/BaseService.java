package xh.cm.service;

import java.io.Serializable;

import xh.cm.dao.BaseDao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class BaseService {

	private BaseDao dao;
	
	public BaseService(Context context, String tableName) {
		dao= new BaseDao(context, tableName);
	}

	public void insert(ContentValues cv) {
		dao.insert(cv);
	}

	public void delete(Serializable id) {
		dao.delete(id);
	}

	public void update(ContentValues cv, Serializable id) {
		dao.update(cv, id);
	}

	public Cursor findById(Serializable id) {
		return dao.findById(id);
	}
	
	public Cursor query(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit) {
		return dao.query(columns, selection, selectionArgs, groupBy, having, orderBy, limit);
	}
}
