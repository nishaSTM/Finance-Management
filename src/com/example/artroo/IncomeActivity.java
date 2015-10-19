package com.example.artroo;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class IncomeActivity extends Activity {
	ListView mainList;
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datalayout);
		HashMap<String,String> questions;
		  questions = (HashMap<String,String>)intent.getSerializableExtra("key");
		//HashMap<long,String> hp=intent.getStringExtra(Constant.DATA_VALUE);
		//((TextView)findViewById(R.id.data_item)).setText();	
		
		mainList =(ListView)findViewById(R.id.mainList2);
		
		//ArrayList<DataForMain> list=getList(); 
		mainList.setAdapter(new HashTableAdapter(this,questions));
	}

	public void onHomeClick(View v) {			
    	startActivity(new Intent(this, MainActivity.class));
    	finish();    
	}


}
