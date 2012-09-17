package com.qut.spc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;
import com.qut.spc.model.SolarComponent;
import com.qut.spc.task.ListRequestTask;

public class SearchResultActivity extends Activity {

	ProgressDialog progressDlg;

	@TargetApi(11)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		String url = getIntent().getStringExtra("url");
		String component = getIntent().getStringExtra("component");
		if (component.equals("panel")) {
			new ComponentListTask<Panel>("panel", Panel.class).execute(url);
		} else if (component.equals("inverter")) {
			new ComponentListTask<Inverter>("inverter", Inverter.class)
					.execute(url);
		} else if (component.equals("battery")) {
			new ComponentListTask<Battery>("battery", Battery.class)
					.execute(url);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_search_result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class ComponentListTask<T extends SolarComponent> extends
			ListRequestTask<T> {

		public ComponentListTask(String componentName, Class<T> componentClass) {
			super(componentName, componentClass);
		}

		@Override
		protected void onPreExecute() {
			progressDlg = ProgressDialog.show(SearchResultActivity.this,
					"In progress", "Loading");
		}

		@Override
		protected void onPostExecute(List<T> list) {
			if (progressDlg != null) {
				progressDlg.dismiss();
				progressDlg = null;
			}
			if (list == null) {
				return;
			}

			List<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
			List<LinkedList<HashMap<String, String>>> childData = new ArrayList<LinkedList<HashMap<String, String>>>();

			for (T component : list) {
				// Group item
				HashMap<String, String> item = new HashMap<String, String>();

				item.put("name", component.getManufacturer() + " - "
						+ component.getModel());
				groupData.add(item);

				// Child item
				LinkedList<HashMap<String, String>> childRow = new LinkedList<HashMap<String, String>>();

				item = new HashMap<String, String>();
				item.put("key", "Price: ");
				item.put("value", component.getPrice().toString());
				childRow.add(item);
				item = new HashMap<String, String>();
				item.put("key", "Capacity: ");
				item.put("value", component.getCapacity().toString());
				childRow.add(item);
				item = new HashMap<String, String>();
				item.put("key", "Postcode: ");
				item.put("value", component.getPostcode());
				childRow.add(item);
				item = new HashMap<String, String>();
				item.put("key", "Voltage: ");
				item.put("value", component.getVoltage().toString());
				childRow.add(item);
				item = new HashMap<String, String>();
				item.put("key", "Dimensions: ");
				item.put("value", component.getDimensions());
				childRow.add(item);
				childData.add(childRow);
			}

			String[] groupFrom = new String[] { "name" };
			int[] groupTo = new int[] { R.id.component_name };

			String[] childFrom = new String[] { "key", "value" };
			int[] childTo = new int[] { R.id.component_key,
					R.id.component_value };

			ExpandableListAdapter adapter = new SimpleExpandableListAdapter(
					SearchResultActivity.this, groupData,
					R.layout.component_list, groupFrom, groupTo, childData,
					R.layout.component_detail, childFrom, childTo);

			ExpandableListView view = (ExpandableListView) SearchResultActivity.this
					.findViewById(R.id.search_result_view);
			view.setAdapter(adapter);
		}
	}
}
