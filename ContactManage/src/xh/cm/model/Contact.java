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
public class Contact implements BaseModel<Contact> {

	private int id;
	
	private String name; // 姓名	
	private String picture; // 头像文件地址	
	private String company; // 公司	
	private String career; // 职业	
	// 所属群组外键	
	private int clusterId; 
	private String email; // 电子邮箱	
	private String address; // 居住地址	
	private String nickname; // 昵称	
	private String site; // 网站	
	private String birthday; // 生日	
	private String lunarBirthday; // 农历生日	
	private String mark; // 备注	
	private int isBlacklist; // 1黑名单	
	private String createTime; // 创建时间
	private int isFavorite; // 1收藏


	public ContentValues contentValues() {
		ContentValues cv= new ContentValues();
		cv.put("name", getName());
		cv.put("picture", getPicture());
		cv.put("company", getCompany());
		cv.put("career", getCareer());
		cv.put("clusterId", getClusterId());
		cv.put("email", getEmail());
		cv.put("address", getAddress());
		cv.put("nickname", getNickname());
		cv.put("site", getSite());
		cv.put("birthday", getBirthday());
		cv.put("lunarBirthday", getLunarBirthday());
		cv.put("mark", getMark());
		cv.put("isBlacklist", getIsBlacklist());
		cv.put("createTime", getCreateTime());
		cv.put("isFavorite", getIsFavorite());
		return cv;
	}
	public List<Contact> entitys(Cursor c) {
		List<Contact> list= new ArrayList<Contact>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Contact n= new Contact();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setName(c.getString(c.getColumnIndex("name")));
			n.setPicture(c.getString(c.getColumnIndex("picture")));
			n.setCompany(c.getString(c.getColumnIndex("company")));
			n.setCareer(c.getString(c.getColumnIndex("career")));
			n.setClusterId(c.getInt(c.getColumnIndex("clusterId")));
			n.setEmail(c.getString(c.getColumnIndex("email")));
			n.setAddress(c.getString(c.getColumnIndex("address")));
			n.setNickname(c.getString(c.getColumnIndex("nickname")));
			n.setSite(c.getString(c.getColumnIndex("site")));
			n.setBirthday(c.getString(c.getColumnIndex("birthday")));
			n.setLunarBirthday(c.getString(c.getColumnIndex("lunarBirthday")));
			n.setMark(c.getString(c.getColumnIndex("mark")));
			n.setIsBlacklist(c.getInt(c.getColumnIndex("isBlacklist")));
			n.setCreateTime(c.getString(c.getColumnIndex("createTime")));
			n.setIsFavorite(c.getInt(c.getColumnIndex("isFavorite")));
			
			list.add(n);
		}
		return list.isEmpty()?null:list;
	}
	public Contact entity(Cursor c) {
		List<Contact> list= this.entitys(c);
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public int getClusterId() {
		return clusterId;
	}

	public void setClusterId(int clusterId) {
		this.clusterId = clusterId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLunarBirthday() {
		return lunarBirthday;
	}

	public void setLunarBirthday(String lunarBirthday) {
		this.lunarBirthday = lunarBirthday;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(int isBlacklist) {
		this.isBlacklist = isBlacklist;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(int isFavorite) {
		this.isFavorite = isFavorite;
	}

}
