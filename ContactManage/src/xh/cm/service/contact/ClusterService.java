package xh.cm.service.contact;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import xh.cm.model.Cluster;
import xh.cm.service.BaseService;

public class ClusterService extends BaseService {

	public ClusterService(Context context, String tableName) {
		super(context, tableName);
	}	
	public Cluster findById(int id)	{
		return new Cluster().entity(super.findById(id));
	}	
	public Object[] list() {
		Cursor c= super.query(null, null, null, null, null, null, null);
		
		return new Object[]{c.getCount(), new Cluster().entitys(c)};
	}
	
	public String print(List<Cluster> list) {
        String str= "";
        for(Cluster n: list) {
        	str+= n.getId()+ n.getName()+ "\n";
        }
        return str;
	}

}
