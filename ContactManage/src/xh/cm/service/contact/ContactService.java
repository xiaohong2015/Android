package xh.cm.service.contact;

import android.content.Context;
import android.database.Cursor;
import xh.cm.model.Contact;
import xh.cm.service.BaseService;

public class ContactService extends BaseService {

	public ContactService(Context context, String tableName) {
		super(context, tableName);
	}
	public Contact findById(int id) {
		return new Contact().entity(super.findById(id));
	}
	public Object[] list() {
		Cursor c= super.query(null, null, null, null, null, null, null);
		
		return new Object[]{c.getCount(), new Contact().entitys(c)};
	}

}
