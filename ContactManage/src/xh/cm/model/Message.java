package xh.cm.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class Message implements BaseModel<Message> {
	
	private int id;
	private int isSuccess; // 1发送成功
	private int isDraft; // 1草稿
	private int isSend; // 0接收短信、1发送短信
	private String content; // 内容
	private String createTime; // 创建时间
	private String phone; // 手机号码

	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("isSuccess", getIsSuccess());
		cv.put("isDraft", getIsDraft());
		cv.put("isSend", getIsSend());
		cv.put("content", getContent());
		cv.put("createTime", getCreateTime());
		cv.put("phone", getPhone());
		return cv;
	}

	public List<Message> entitys(Cursor c) {
		List<Message> list= new ArrayList<Message>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Message n= new Message();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setIsSuccess(c.getInt(c.getColumnIndex("isSuccess")));
			n.setIsDraft(c.getInt(c.getColumnIndex("isDraft")));
			n.setIsSend(c.getInt(c.getColumnIndex("isSend")));
			n.setContent(c.getString(c.getColumnIndex("content")));
			n.setCreateTime(c.getString(c.getColumnIndex("createTime")));
			n.setPhone(c.getString(c.getColumnIndex("phone")));
			
			list.add(n);
		}
		return list.isEmpty()?null:list;
	}

	public Message entity(Cursor c) {
		List<Message> list= this.entitys(c);
		return list==null?null:list.get(0);
	}

	
	// get set 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(int isDraft) {
		this.isDraft = isDraft;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
