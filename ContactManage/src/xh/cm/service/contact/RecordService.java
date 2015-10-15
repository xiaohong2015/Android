package xh.cm.service.contact;

import android.content.Context;
import android.database.Cursor;
import xh.cm.model.Record;
import xh.cm.service.BaseService;


public class RecordService extends BaseService {

	public RecordService(Context context, String tableName) {
		super(context, tableName);
	}
	public Record findById(int id) {
		return new Record().entity(super.findById(id));
	}
	public Object[] list() {
		Cursor c= super.query(null, null, null, null, null, null, null);
		
		return new Object[]{c.getCount(), new Record().entitys(c)};
	}

}
