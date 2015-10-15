package xh.cm.service.contact;

import android.content.Context;
import android.database.Cursor;
import xh.cm.model.PhoneAssortment;
import xh.cm.service.BaseService;

public class PhoneAssortmentService extends BaseService {

	public PhoneAssortmentService(Context context, String tableName) {
		super(context, tableName);
	}
	public PhoneAssortment findById(int id) {
		return new PhoneAssortment().entity(super.findById(id));
	}
	public Object[] list() {
		Cursor c= super.query(null, null, null, null, null, null, null);
		
		return new Object[]{c.getCount(), new PhoneAssortment().entitys(c)};
	}
}
