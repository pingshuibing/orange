package com.qut.spc;

import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.qut.spc.service.LocationService;
import com.qut.spc.task.LocationTask;

public class SearchActivity extends Activity {

	final String[] componentTypes = {
			"Panels",
			"Inverters",
			"Batteries",
	};

	private Spinner spComponent;
	private EditText etPostcode, etPriceMin, etPriceMax, etCapacityMin, etCapacityMax;
	private TextView tvAddress;
	
	private LocationService locationService;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		spComponent = (Spinner) findViewById(R.id.spComponent);
		etPostcode = (EditText) findViewById(R.id.postcode);
		tvAddress = (TextView) findViewById(R.id.current_address);
		etPriceMin = (EditText) findViewById(R.id.priceMin);
		etPriceMax = (EditText) findViewById(R.id.priceMax);
		etCapacityMin = (EditText) findViewById(R.id.capacityMin);
		etCapacityMax = (EditText) findViewById(R.id.capacityMax);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, componentTypes);
		spComponent.setAdapter(adapter);

		restoreInstanceState(savedInstanceState);
		
		// Get location first
		locationService = new LocationService(SearchActivity.this) {
			@Override
			public void onLocationChanged(Location location) {
				super.onLocationChanged(location);
				if (location != null) {
					String url = LocationTask.buildUrl(getLatitude(), getLongitude());
					new UpdateLocationTask().execute(url);
				} else {
					showError("Could not get location");
				}
			}
		};
		// Brisbane: -27.46197644877817, 153.0120849609375
		if (locationService.getLatitude() != 0 && locationService.getLongitude() != 0) {
			String url = LocationTask.buildUrl(locationService.getLatitude(),
					locationService.getLongitude());
			new UpdateLocationTask().execute(url);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.calculation:
			openCalculationPage();
			return true;
		default:
			return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onSaveInstanceState(Bundle state) {
		super.onSaveInstanceState(state);

		state.putString("type", spComponent.getSelectedItem().toString());
		state.putString("postcode", etPostcode.getText().toString());
		state.putString("priceMin", etPriceMin.getText().toString());
		state.putString("priceMax", etPriceMax.getText().toString());
		state.putString("capacityMin", etCapacityMin.getText().toString());
		state.putString("capacityMax", etCapacityMax.getText().toString());
	}

	@Override
	// onRestoreInstanceState is only called when the program is terminated
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		restoreInstanceState(savedInstanceState);
	}

	private void restoreInstanceState(Bundle state) {
		if (state == null) {
			return;
		}
		String str;
		str = state.getString("type");
		if (str.length() > 0) {
			int pos = Arrays.asList(componentTypes).indexOf(str);
			spComponent.setSelection(pos);
		}
		str = state.getString("postcode");
		if (str.length() > 0) {
			etPostcode.setText(str);
		}
		str = state.getString("priceMin");
		if (str.length() > 0) {
			etPriceMin.setText(str);
		}
		str = state.getString("priceMax");
		if (str.length() > 0) {
			etPriceMax.setText(str);
		}
		str = state.getString("capacityMin");
		if (str.length() > 0) {
			etCapacityMin.setText(str);
		}
		str = state.getString("capacityMax");
		if (str.length() > 0) {
			etCapacityMax.setText(str);
		}
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.btSearch:
			onSearchClick();
			break;
		case R.id.btUpdate:
			onUpdatePostcodeClick();
			break;
		case R.id.btMap:
			onMapClick();
			break;
		}
	}
	
	private void onSearchClick() {
		String component = "panel";
		String str;

		str = spComponent.getSelectedItem().toString();
		if (str.equals("Panels")) {
			component = "panel";
		} else if (str.equals("Inverters")) {
			component = "inverter";
		} else if (str.equals("Batteries")) {
			component = "battery";
		}

		String query = "";
		query += "postcode=" + etPostcode.getText().toString() + "&";
		query += "priceMin=" + etPriceMin.getText().toString() + "&";
		query += "priceMax=" + etPriceMax.getText().toString() + "&";
		query += "capacityMin=" + etCapacityMin.getText().toString() + "&";
		query += "capacityMax=" + etCapacityMax.getText().toString() + "&";

		search(getString(R.string.app_url) + "/" + component + "?" + query, component);
	}
	
	private void onUpdatePostcodeClick() {
		if (!locationService.updateLocation()) {
			showError("Could not get location. Please enable your GPS");
		}
	}
	
	private void onMapClick() {
		Intent i = new Intent(this, LocationActivity.class);
		startActivity(i);
	}
	
	private void showError(String msg) {
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setTitle("Error");
		alert.setMessage(msg);
		alert.setButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// do nothing
			}
		});
		alert.show();
	}

	private void search(String url, String component) {
		Intent i = new Intent(this, SearchResultActivity.class);
		i.putExtra("url", url);
		i.putExtra("component", component);
		startActivity(i);
	}
	
	private void openCalculationPage() {
		Intent i = new Intent(this, ElectricityProductionActivity.class);
		startActivity(i);
	}
	
	/**
	 * Update location task
	 */
	class UpdateLocationTask extends LocationTask {
		@Override
		protected void onPreExecute() {
			tvAddress.setText("Finding location...");
		}
		
		@Override
		protected void onPostExecute(boolean postcodeFound) {
			if (postcodeFound) {
				etPostcode.setText(postcode);
				tvAddress.setText("Current address: " + address);
			} else {
				tvAddress.setText("Unable to find your location");
			}
		}
		
	}
}
