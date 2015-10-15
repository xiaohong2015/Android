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
public class PhoneAssortment implements BaseModel<PhoneAssortment> {

	private int id;
	private String name; // 手机类别


	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("name", getName());
		return cv;
	}
	public List<PhoneAssortment> entitys(Cursor c) {
		List<PhoneAssortment> list= new ArrayList<PhoneAssortment>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			PhoneAssortment n= new PhoneAssortment();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setName(c.getString(c.getColumnIndex("name")));
			
			list.add(n);
		}
		return list.isEmpty()?null:list;
	}
	public PhoneAssortment entity(Cursor c) {
		List<PhoneAssortment> list= this.entitys(c);
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
