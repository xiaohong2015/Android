package xh.cm.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;


/**
 * @author IronMan
 *
 * 2015年5月23日
 */
public class Cluster implements BaseModel<Cluster> {

	private int id;
	
	private String name; // 群组名称
	
	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("name", getName());
		return cv;
	}
	public List<Cluster> entitys(Cursor c) {
		List<Cluster> list= new ArrayList<Cluster>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Cluster n= new Cluster();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setName(c.getString(c.getColumnIndex("name")));
			
			list.add(n);
		}
		return list.isEmpty()?null:list;
	}
	public Cluster entity(Cursor c) {
		List<Cluster> list= this.entitys(c);
		return list==null?null:list.get(0);
	}

	// get set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
