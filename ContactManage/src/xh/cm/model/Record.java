package xh.cm.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class Record implements BaseModel<Record> {

	private int id;
	private int isCall; // 1主拨
	private String state; // 5月20日 呼出8分钟40秒
	private String createTime; // 通话开始时间
	private String phone; // 对方手机号码
	
	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("isCall", getIsCall());
		cv.put("state", getState());
		cv.put("createTime", getCreateTime());
		cv.put("phone", getPhone());
		return cv;
	}

	public List<Record> entitys(Cursor c) {
		List<Record> list= new ArrayList<Record>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Record n= new Record();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setIsCall(c.getInt(c.getColumnIndex("isCall")));
			n.setState(c.getString(c.getColumnIndex("state")));
			n.setCreateTime(c.getString(c.getColumnIndex("createTime")));
			n.setPhone(c.getString(c.getColumnIndex("phone")));
			
			list.add(n);
		}
		return list.isEmpty()?null:list;
	}

	public Record entity(Cursor c) {
		List<Record> list= this.entitys(c);
		return list==null?null:list.get(0);
	}


	// get set 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsCall() {
		return isCall;
	}

	public void setIsCall(int isCall) {
		this.isCall = isCall;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
