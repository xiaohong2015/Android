package xh.ts.service.user;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import xh.ts.model.Assortment;
import xh.ts.service.BaseService;

public class AssortmentService extends BaseService {

	public AssortmentService(Context context, String tableName) {
		super(context, tableName);
	}
	
	public Assortment findById(int id) {
		Cursor c= super.findById(id);
		Assortment n= null;
		if(c.moveToFirst()) {
			n= new Assortment();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setName(c.getString(c.getColumnIndex("name")));
		}
		return n;
	}

	public Object[] list(int firstResult, int maxResults) {
		Cursor c= this.query(null, null, null, null, null, null, null);

		List<Assortment> list= new ArrayList<Assortment>();
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Assortment n= new Assortment();
			n.setId(c.getInt(c.getColumnIndex("id")));
			n.setName(c.getString(c.getColumnIndex("name")));
			
			list.add(n);
		}
		return new Object[]{c.getCount(), list};
	}
	
}
