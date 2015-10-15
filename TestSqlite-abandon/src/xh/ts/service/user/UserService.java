package xh.ts.service.user;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import xh.ts.model.User;
import xh.ts.service.BaseService;

public class UserService extends BaseService {
	
	public UserService(Context context, String tableName) {
		super(context, tableName);
	}

	public User findById(int id) {
		Cursor c= super.findById(id);
		User n= null;
		if(c.moveToFirst()) {
			n= new User();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setUsername(c.getString(c.getColumnIndex("username")));
			n.setPassword(c.getString(c.getColumnIndex("password")));
			n.setAssortmentId(c.getInt(c.getColumnIndex("assortmentId")));
		}
		return n;
	}
	
	public Object[] list(int firstResult, int maxResults) {
		Cursor c= this.query(null, null, null, null, null, null, null);
		System.out.println(c.getCount());
		
		List<User> list= new ArrayList<User>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			User n= new User();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setUsername(c.getString(c.getColumnIndex("username")));
			n.setPassword(c.getString(c.getColumnIndex("password")));
			n.setAssortmentId(c.getInt(c.getColumnIndex("assortmentId")));
			
			list.add(n);
		}
		return new Object[]{c.getCount(), list};
	}

}
