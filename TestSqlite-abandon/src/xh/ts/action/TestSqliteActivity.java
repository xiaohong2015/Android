package xh.ts.action;

import java.util.List;

import xh.ts.model.Assortment;
import xh.ts.model.User;
import xh.ts.service.user.AssortmentService;
import xh.ts.service.user.UserService;
import android.app.Activity;
import android.os.Bundle;

// String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit
// 上传头像
public class TestSqliteActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	System.out.println("begin");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AssortmentService as= new AssortmentService(this, "Assortment");
        /*
        Assortment a1= new Assortment();
        a1.setName("add1");
        as.insert(a1.contentValues());
        //*/
        
        UserService us= new UserService(this, "User");
        User u1= us.findById(2);
        if(u1!= null) {
        	System.out.println("u1 is not null");
        	Assortment a1= as.findById(u1.getAssortmentId());
        	System.out.println(a1==null);
        	if(a1!= null) {
        		System.out.println("a1 is not null");
        		System.out.println(a1.getName());
        	} else {
        		u1.setAssortmentId(1);
        		us.update(u1.contentValues(), u1.getId());
        	}
        }
        /*
        User u1= new User();
        u1.setUsername("xxx");
        u1.setPassword("h1");
        //u1.setAssortmentId(1);
        us.insert(u1.contentValues());
        //*/

        this.showAssortment(as);
        this.showUser(us);
        System.out.println("end");
    }
    
    public void showAssortment(AssortmentService as) {
        @SuppressWarnings("unchecked")
		List<Assortment> list= (List<Assortment>) as.list(0, 1)[1];
        for(Assortment m: list) {
        	System.out.println(m.getId()+ "\t"+ m.getName());
        }
    }
    public void showUser(UserService us) {
		Object[] obj= us.list(0, 1);
        @SuppressWarnings("unchecked")
        List<User> list= (List<User>) obj[1];
        for(User m: list) {
        	System.out.println(m.getId()+ "\t"+ m.getAssortmentId());
        }
    }
    
    
}