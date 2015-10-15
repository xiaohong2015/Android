package xh.android.ch002;

import java.io.IOException;
import java.util.List;

import xh.android.ch002.R;
import xh.android.dao.Person;
import xh.android.service.DomReader;
import xh.android.service.PullReader;
import xh.android.service.SaxReader;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ch002Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button saxBtn= (Button) findViewById(R.id.ButtonSax);
        Button domBtn= (Button) findViewById(R.id.ButtonDom);
        Button pullBtn= (Button) findViewById(R.id.ButtonPull);

        saxBtn.setOnClickListener(saxClick);
        domBtn.setOnClickListener(domClick);
        pullBtn.setOnClickListener(pullClick);
        
    }
    
    public void showResult(String method, List<Person> list) {
        TextView resultStr= (TextView) findViewById(R.id.textView1);
        if(list== null|| list.isEmpty()) {
        	resultStr.setText(method+ "\n"+ "");
        	return;
        }
        String str= "id\t\tname\t\tage\n";
        for(Person n: list) {
        	str+= n.getId()+ "\t"+ n.getName()+ "\t"+ n.getAge()+ "\n";
        }
        resultStr.setText(method+ "\n"+ str);
    }
    
	private OnClickListener saxClick= new OnClickListener() {		
		public void onClick(View v) {
			try {
				SaxReader saxReader= new SaxReader();
				List<Person> list= saxReader.readXML(getAssets().open("itcast.xml"));
				showResult("SAX", list);
			} catch (IOException e) {
				showResult("SAX", null);
				e.printStackTrace();
			}
		}
	};
    private OnClickListener domClick= new OnClickListener() {    	
		public void onClick(View v) {
	        try {			
	        	DomReader domReader= new DomReader();
	        	List<Person> list= domReader.getPersons(getAssets().open("itcast.xml"));
	        	showResult("DOM", list);
			} catch (Exception e) {
	        	showResult("DOM", null);
				e.printStackTrace();
			}
		}
	};
	private OnClickListener pullClick= new OnClickListener() {
		public void onClick(View v) {
			try {
				PullReader pullReader= new PullReader();
				List<Person> list= pullReader.readXML(getAssets().open("itcast.xml"));
				showResult("PULL", list);
			} catch (IOException e) {
				showResult("PULL", null);
				e.printStackTrace();
			}
		}
	};
}