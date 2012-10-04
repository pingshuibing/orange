package com.qut.spc;

import java.util.Arrays;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qut.spc.task.XmlRequestTask;

/**
 * Activity for Calculation of Electricity Production  
 * @author QuocViet
 */
public class ElectricityProductionActivity extends Activity {
	private EditText etSystemCost, etPanelOutput, etPanelEfficiency, etInverterEfficiency;
	
	private TextView tvElectricityProduction, tvReturnOnInvestment;
	
	private View vwResult;
	
	private static final String[] DURATIONS = {
			"week",
			"month",
			"year",
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_electricity_production);
		
		etSystemCost = (EditText) findViewById(R.id.system_cost);
		etPanelOutput = (EditText) findViewById(R.id.panel_output);
		etPanelEfficiency = (EditText) findViewById(R.id.panel_efficiency);
		etInverterEfficiency = (EditText) findViewById(R.id.inverter_efficiency);
		
		tvElectricityProduction = (TextView) findViewById(R.id.electricity_production);
		tvReturnOnInvestment = (TextView) findViewById(R.id.return_on_investment);
		
		vwResult = findViewById(R.id.result);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_electricity_production, menu);
		return true;
	}

	public void onButtonClick(View v) {
		switch(v.getId()) {
		case R.id.btCalculate:
			onCalculateClick();
			break;
		case R.id.btMap:
			onMapClick();
			break;
		}
	}
	
	private void onCalculateClick() {
		if (etSystemCost.getText().length() == 0) {
			showError("System cost must not be empty");
			return;
		}
		if (etPanelOutput.getText().length() == 0) {
			showError("Panel output must not be empty");
			return;
		}
		if (etPanelEfficiency.getText().length() == 0) {
			showError("Panel efficiency must not be empty");
			return;
		}
		if (etInverterEfficiency.getText().length() == 0) {
			showError("Inverter efficiency must not be empty");
			return;
		}
		
		String query = "";
		query += "systemCost=" + etSystemCost.getText().toString() + "&";
		query += "panelOutput=" + etPanelOutput.getText().toString() + "&";
		query += "panelEfficiency=" + etPanelEfficiency.getText().toString() + "&";
		query += "inverterEfficiency=" + etInverterEfficiency.getText().toString() + "&";

		calculate(getString(R.string.app_url) + "/calculate?" + query);
	}
	
	private void onMapClick() {
		Intent i = new Intent(this, LocationActivity.class);
		startActivity(i);
	}
	
	private void calculate(String url) {
		findViewById(R.id.result).setVisibility(View.GONE);
		new ElectricityProductionTask().execute(url);
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
	
	class ElectricityProductionTask extends XmlRequestTask {
		private int depth;
		private String category;
		private String type;
		
		private double[] electricityProduction;
		private double[] returnOnInvestment;
		
		private ProgressDialog progressDlg;
		
		public ElectricityProductionTask() {
			electricityProduction = new double[DURATIONS.length];
			returnOnInvestment = new double[DURATIONS.length];
		}

		@Override
		protected boolean onXmlTag(XmlPullParser parser, int eventType) {
			String name;
			
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				depth = 0;
				break;
				
			case XmlPullParser.START_TAG:
				++depth;
				name = parser.getName();
				if (depth == 2) {
					category = name;
					type = null;
				} else if (depth == 3) {
					type = name;
				}
				break;
			case XmlPullParser.END_TAG:
				if (depth == 2) {
					category = null;
					type = null;
				} else if (depth == 3) {
					type = null;
				}
				--depth;
				break;
			case XmlPullParser.TEXT:
				if (depth == 3) {
					setValue(category, type, parser.getText());
				}
				break;
			}
			return true;
		}
		
		@Override
		protected void onPreExecute() {
			vwResult.setVisibility(View.GONE);
			progressDlg = ProgressDialog.show(ElectricityProductionActivity.this,
					"In progress", "Loading...");
		}
		
		@Override
		protected void onPostExecute(XmlPullParser parser) {
			if (progressDlg != null) {
				progressDlg.dismiss();
				progressDlg = null;
			}
			vwResult.setVisibility(View.VISIBLE);
			// Display in months only
			tvElectricityProduction.setText(String.valueOf(electricityProduction[1]));
			tvReturnOnInvestment.setText(String.valueOf(returnOnInvestment[1]));
		}
		
		private void setValue(String category, String type, String value) {
			if (category == null || type == null) {
				return;
			}
			int durationIdx = Arrays.asList(DURATIONS).indexOf(type);
			
			if (durationIdx >= 0 && durationIdx < DURATIONS.length) {
				double calValue;
				
				try {
					calValue = Double.parseDouble(value);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					calValue = 0.0;
				}
				if (category.equals("electricityProduction")) {
					electricityProduction[durationIdx] = calValue;
				} else if (category.equals("returnOnInvestment")) {
					returnOnInvestment[durationIdx] = calValue;
				}
			}
		}
	}
}
