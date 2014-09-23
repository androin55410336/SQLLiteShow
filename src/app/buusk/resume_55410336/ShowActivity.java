package app.buusk.resume_55410336;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShowActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		myDBClass myDB = new myDBClass(this);
		ArrayList<HashMap<String, String>> MemberList = myDB.SelectAllData();
		ListView listview = (ListView) findViewById(R.id.listView1);
		
		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(ShowActivity.this, MemberList, R.layout.activity_column, 
				new String[] {"Name", "University", "Faculty"}, new int[] {R.id.ColName,R.id.ColUnv, 
				R.id.ColFac});
		listview.setAdapter(sAdap);
		
		
		
	}

}
