package xh.ts.model;

import android.content.ContentValues;

/**
 * @author IronMan
 *
 * 2015年5月23日
 */
public class User {

	private int id;
	
	private String username;
	
	private String password;

	//private Assortment assortment;
	private int assortmentId;

	
	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("username", getUsername());
		cv.put("password", getPassword());
		cv.put("assortmentId", getAssortmentId());
		return cv;
	}
	

	// get set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAssortmentId() {
		return assortmentId;
	}
	public void setAssortmentId(int assortmentId) {
		this.assortmentId = assortmentId;
	}
	
}
