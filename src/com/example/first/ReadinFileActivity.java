package com.example.first;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ReadinFileActivity extends Activity {
	public Intent data; // mozhe private potim
	public ArrayList<ArrayList<String>> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_readin_file);
		
		//data = new Intent();
		//list = new FileReaderSerializable(getApplicationContext()).getList();
		//assert(list!=null);
		//data.putExtra("returnedData", list);
	   // setResult(RESULT_OK, data);
	    //TextView v = (TextView) findViewById(R.id.someview);
	   // v.setText("e");
		//this.finish();
	}
	
//	@Override
//	  public void finish() {
//		//data.putExtra("returnedData", "You could be better then you are. ");

		
	    
//	    super.finish();
//	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.readin_file, menu);
		return true;
	}

}
