package xh.cm.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public final class FinalData {
	
	public ContentValues table= new ContentValues();	
	public FinalData() {
		table.put("Cluster", "cluster");
		table.put("Contact", "contact");
		table.put("PhoneAssortment", "phoneAssortment");
		table.put("Phone", "phone");
		table.put("Message", "message");
		table.put("Record", "record");
	}

	public String name= "contactManage.db";
	public int version= 1;

	public String[] sql= new String[]{
		"create table if not exists cluster(id integer primary key autoincrement"
			+ ", name varchar not null)" // 群组名称
		,

		"create table if not exists contact(id integer primary key autoincrement"
			+ ", name varchar" // 联系人姓名
			+ ", picture varchar" // 头像文件地址
			+ ", company varchar" // 公司
			+ ", career  varchar" // 职业
			+ ", isBlacklist int" // 是否黑名单
			+ ", email varchar" // 电子邮件
			+ ", address varchar" // 居住地址
			+ ", nickname varchar" // 昵称
			+ ", site varchar" // 网站
			+ ", birthday varchar" // 生日
			+ ", lunarBirthday varchar" // 农历生日 
			+ ", mark varchar" // 备注
			+ ", createTime varchar" // 
			+ ", clusterId integer references cluster(id)" // 群组外键
			+ ", isFavorite int)" // 是否收藏
		,
		
		"create table if not exists phoneAssortment(id integer primary key autoincrement"
			+ ", name varchar not null)" // 手机类别
		,
			
		"create table if not exists phone(id integer primary key autoincrement"
			+ ", phone varchar not null" // 手机号码
			+ ", phoneAssortmentId integer references phoneAssortment(id)" // 手机类别外键
			+ ", contactId integer references contact(id))" // 联系人外键
		,
		
		"create table if not exists message(id integer primary key autoincrement"
			+ ", isSuccess int" // 是否发送成功
			+ ", isDraft int" // 是否草稿
			+ ", isSend int" // 是否发送
			+ ", content varchar" // 信息内容
			+ ", createTime varchar" // 发送或接收时间
			+ ", phone varchar)" // 对方手机号码
		,
		
		"create table if not exists record(id integer primary key autoincrement"
			+ ", isCall int" // 是否主拨
			+ ", state varchar" // 通话时间或未接通
			+ ", createTime varchar" // 拨打时间
			+ ", phone varchar)" // 对方手机号码
		
	};
	
	public String[] init= new String[] {
		"insert into cluster(id, name) values(1, '家庭');",
		"insert into cluster(id, name) values(2, '同事');",
		"insert into cluster(id, name) values(3, '同学');",
		
		"insert into contact(id, name, picture, company, clusterId, isBlacklist, createTime) values(1, 'xh', '1.jpg', '小洪工作室', 2, 0, '2015-05-23');",
		"insert into contact(id, name, picture, company, clusterId, isBlacklist, createTime) values(2, 'je', '2.jpg', '小洪工作室', 2, 0, '2015-05-23');",
		
		"insert into phoneAssortment(id, name) values(1, '手机');",
		"insert into phoneAssortment(id, name) values(2, '短号');",
		"insert into phoneAssortment(id, name) values(3, '住宅');",
		"insert into phoneAssortment(id, name) values(4, '单位');",
		
		"insert into phone(id, contactId, phoneAssortmentId, phone) values(1, 1, 1, '15766227845');",
		"insert into phone(id, contactId, phoneAssortmentId, phone) values(2, 2, 2, '647845');"
	};
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("no working");
		// db.execSQL("alter table group add column other string");
	}
}
