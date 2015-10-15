package xh.ts.model;

import android.content.ContentValues;

/**
 * @author IronMan
 *
 * 2015年5月21日
 */
public class Assortment {

	private int id;
	
	private String name;
	

	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("name", getName());
		return cv;
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
