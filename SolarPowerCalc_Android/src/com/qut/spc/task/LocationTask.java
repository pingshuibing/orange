package com.qut.spc.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public abstract class LocationTask extends AsyncTask<String, Void, String> {

	public enum LocationRequiredData {
		Address, PostCode, Country
	}

	private LocationRequiredData requiredData;
	
	public void requestAddress()
	{
		requiredData=requiredData.Address;
	}
	
	public void requestCountry()
	{
		requiredData=requiredData.Country;
	}
	
	public void requestPostcode()
	{
		requiredData=requiredData.PostCode;
	}

	@Override
	protected String doInBackground(String... params) {
		String jsonString = RestfulRequest(params[0]);
		try {

			switch (requiredData) {
			case Address:

				getAddress(jsonString);

				break;
			case Country:
				getCountry(jsonString);
				break;
			case PostCode:
				getPostcode(jsonString);
				break;
			default:
				getAddress(jsonString);
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonString;
	}

	// override this methode and request which data you want using ether:
	// requestPostcode, requestCountry or requestAddress
	protected abstract void onPostExecute(String result);
	

	public String getAddress(String JSONString) throws JSONException {
		JSONObject job = new JSONObject(JSONString);
		String placemark = job.getJSONArray("Placemark").getString(0);
		JSONObject jobPlacemark = new JSONObject(placemark);
		String Address = jobPlacemark.getString("address");
		return Address;
	}

	public String getCountry(String JSONString) throws JSONException {
		JSONObject job = new JSONObject(JSONString);
		String placemark = job.getJSONArray("Placemark").getString(4);
		JSONObject jobPlacemark = new JSONObject(placemark);
		String Address = jobPlacemark.getString("address");
		return Address;
	}

	public String getPostcode(String JSONString) throws JSONException {
		JSONObject job = new JSONObject(JSONString);
		String placemark = job.getJSONArray("Placemark").getString(0);
		JSONObject jobPlacemark = new JSONObject(placemark)
				.getJSONObject("AddressDetails").getJSONObject("Country")
				.getJSONObject("AdministrativeArea").getJSONObject("Locality")
				.getJSONObject("PostalCode");
		String postcode = jobPlacemark.getString("PostalCodeNumber");

		return postcode;
	}

	private static JSONObject getJSONObjectFromURL(String URL)
			throws JSONException {
		String JSONString = RestfulRequest(URL);
		JSONObject job = new JSONObject(JSONString);

		return job;
	}

	private static String RestfulRequest(String ServiceRequest) {
		String Output = "error";
		try {

			URL url = new URL(ServiceRequest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// this code is to check for the response health later
			// if (conn.getResponseCode() != HttpURLConnection.) {
			// throw new RuntimeException("Failed : HTTP error code : "
			// + conn.getResponseCode());
			// }
			//
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			Output = "";
			while ((output = br.readLine()) != null) {
				Output += output;
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return Output;
	}

}
