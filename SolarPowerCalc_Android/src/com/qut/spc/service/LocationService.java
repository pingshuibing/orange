package com.qut.spc.service;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public abstract class LocationService {

	protected abstract void onLocationUpdated();

	private double latitude;
	private double longtude;
	LocationManager LM;
	LocationListener locationListener;

	/**
	 * 
	 * @param currentActivity
	 *            the activity that you use the class from (general activity)
	 */
	public LocationService(Activity currentActivity) {

		LM = (LocationManager) currentActivity
				.getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();
		// we don't need high accuracy.. better save the battery
		crit.setAccuracy(Criteria.ACCURACY_LOW);
		String provider = LM.getBestProvider(crit, true);

		// this will not connect to GPS.. just Last Known Location
		Location location = LM.getLastKnownLocation(provider);

		latitude = location.getLatitude();
		longtude = location.getLongitude();

		// Define a listener that responds to location updates
		locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found after the location
				// manager request
				// the provider update
				UpdateLocation(location);
				onLocationUpdated();
			}

			// the code here is copied, I'll refactore later
			public void UpdateLocation(Location location) {
				latitude = location.getLatitude();
				longtude = location.getLongitude();
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}

		};

	}

	public void updateLocationFromGPS() {
		LM.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				locationListener);
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongtude() {
		return longtude;
	}

}
