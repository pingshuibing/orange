package com.qut.spc;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	private Intent calculatorIntent, searchIntent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TabHost host = getTabHost();

		// Tab for Calculator
		TabSpec calculatorSpec = host.newTabSpec("Calculator");
		calculatorSpec.setIndicator("Calculator",
				getResources().getDrawable(R.drawable.calculator));
		calculatorIntent = new Intent(this, ElectricityProductionActivity.class);
		calculatorSpec.setContent(calculatorIntent);

		// Tab for Search
		TabSpec searchSpec = host.newTabSpec("Search");
		searchSpec.setIndicator("Search",
				getResources().getDrawable(R.drawable.search));
		searchIntent = new Intent(this, SearchActivity.class);
		searchSpec.setContent(searchIntent);
				
		// Adding all tabs
		host.addTab(calculatorSpec);
		host.addTab(searchSpec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
	    }
	}
}
