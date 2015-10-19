package com.example.artroo;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import java.util.ArrayList;
import java.util.List;

public class RelaxBillActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		Intent intent=getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_layout);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();
		

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Bills");
		

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("EMIs");
		spec2.setContent(R.id.tab2);

		

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.green));
		}
}