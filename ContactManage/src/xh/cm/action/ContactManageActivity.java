package xh.cm.action;

import java.io.File;
import java.util.List;

import xh.cm.model.Cluster;
import xh.cm.model.Contact;
import xh.cm.service.contact.ClusterService;
import xh.cm.service.contact.ContactService;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

// 上传头像、查询条件、发送短信、电话呼叫、美化界面、
public class ContactManageActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	//*
        File f= new File("data/data/xh.cm.action/databases/contactManage.db");
        if(f.isFile()) {
        	f.delete();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /*/
        ContactService cs= new ContactService(this, "Contact");
        List<Contact> list= (List<Contact>) cs.list()[1];
        for(Contact n: list) {
        	System.out.println(n.getName());
        }

        //*/
        /*
        ClusterService cs= new ClusterService(this, "Cluster");
        Object[] obj= cs.list();
        @SuppressWarnings("unchecked")
		List<Cluster> list= (List<Cluster>) obj[1];
        
        String str= cs.print(list);
        TextView tv= (TextView) findViewById(R.id.tv);
        tv.setText(str);
        //*/
        
    }
}