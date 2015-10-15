package wj.android.wujie;

import java.io.IOException;
import java.util.List;

import wj.android.dao.Person;
import wj.android.service.DomReader;
import wj.android.service.PullReader;
import wj.android.service.SaxReader;
import wj.android.wujie.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WujieActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button saxBtn= (Button) findViewById(R.id.ButtonSax);
        Button domBtn= (Button) findViewById(R.id.ButtonDom);
        Button pullBtn= (Button) findViewById(R.id.ButtonPull);
        saxBtn.setOnClickListener(saxListener);
        domBtn.setOnClickListener(domListener);
        pullBtn.setOnClickListener(pullListener);
    }
       
	private OnClickListener saxListener= new OnClickListener() {		
		public void onClick(View v) {
			try {
				SaxReader saxReader= new SaxReader();
				List<Person> list= saxReader.readXML(getAssets().open("itcast.xml"));
		        TextView resultStr= (TextView) findViewById(R.id.textView1);
		        String str= "";
		        for(Person n: list) {
		        	str+= n.getId()+ n.getName()+ n.getAge()+ "\n";
		        }
		        resultStr.setText("Sax\n"+ str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
    private OnClickListener domListener= new OnClickListener() {    	
		public void onClick(View v) {
	        try {			
	        	DomReader domReader= new DomReader();
	        	List<Person> list= domReader.getPersons(getAssets().open("itcast.xml"));
		        TextView resultStr= (TextView) findViewById(R.id.textView1);
		        String str= "";
		        for(Person n: list) {
		        	str+= n.getId()+ n.getName()+ n.getAge()+ "\n";
		        }
		        resultStr.setText("DOM\n"+ str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	private OnClickListener pullListener= new OnClickListener() {
		public void onClick(View v) {
			try {
				PullReader pullReader= new PullReader();
				List<Person> list= pullReader.readXML(getAssets().open("itcast.xml"));
		        TextView resultStr= (TextView) findViewById(R.id.textView1);
		        String str= "";
		        for(Person n: list) {
		        	str+= n.getId()+ n.getName()+ n.getAge()+ "\n";
		        }
		        resultStr.setText("PULL\n"+ str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
}