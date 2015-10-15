package xh.cm.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class Phone implements BaseModel<Phone> {
	
	private int id;
	// 联系人外键
	private int contactId;
	// 手机类别外键
	private int phoneAssortmentId; 
	private String phone; // 手机号码

	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("contactId", getContactId());
		cv.put("phoneAssortmentId", getPhoneAssortmentId());
		cv.put("phone", getPhone());
		return cv;
	}
	
	public List<Phone> entitys(Cursor c) {
		List<Phone> list= new ArrayList<Phone>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Phone n= new Phone();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setContactId(c.getInt(c.getColumnIndex("contactId")));
			n.setPhoneAssortmentId(c.getInt(c.getColumnIndex("phoneAssortmentId")));
			n.setPhone(c.getString(c.getColumnIndex("phone")));
			
			list.add(n);
		}
		return list.isEmpty()?null:list;
	}

	public Phone entity(Cursor c) {
		List<Phone> list= this.entitys(c);
		return list==null?null:list.get(0);
	}

	
	// get set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getPhoneAssortmentId() {
		return phoneAssortmentId;
	}

	public void setPhoneAssortmentId(int phoneAssortmentId) {
		this.phoneAssortmentId = phoneAssortmentId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
