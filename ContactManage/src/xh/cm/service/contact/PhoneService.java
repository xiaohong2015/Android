package xh.cm.service.contact;

import android.content.Context;
import android.database.Cursor;
import xh.cm.model.Phone;
import xh.cm.service.BaseService;

public class PhoneService extends BaseService {

	public PhoneService(Context context, String tableName) {
		super(context, tableName);
	}
	public Phone findById(int id) {
		return new Phone().entity(super.findById(id));
	}
	public Object[] list() {
		Cursor c= super.query(null, null, null, null, null, null, null);
		
		return new Object[]{c.getCount(), new Phone().entitys(c)};
	}

}
