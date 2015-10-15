package xh.cm.service.contact;

import android.content.Context;
import android.database.Cursor;
import xh.cm.model.Message;
import xh.cm.service.BaseService;


public class MessageService extends BaseService {

	public MessageService(Context context, String tableName) {
		super(context, tableName);
	}
	public Message findById(int id) {
		return new Message().entity(super.findById(id));
	}
	public Object[] list() {
		Cursor c= super.query(null, null, null, null, null, null, null);
		
		return new Object[]{c.getCount(), new Message().entitys(c)};
	}

}
