package com.qut.spc;

import android.location.Location;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.qut.spc.service.LocationService;

public class LocationActivity extends MapActivity {
	private LocationService locationService;
	private MapView mapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		// fetch the map view from the layout
		mapView = (MapView) findViewById(R.id.mapview);

		// make available zoom controls
		mapView.setBuiltInZoomControls(true);

		locationService = new LocationService(this) {
			@Override
			public void onLocationChanged(Location location) {
				super.onLocationChanged(location);
				updateLocation();
			}
		};
		updateLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_location, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationService.updateLocation();
	}

	@Override
	protected void onPause() {
		super.onPause();
		locationService.cancelUpdateLocation();
	}

	private void updateLocation() {
		if (locationService.getLatitude() == 0
				&& locationService.getLongtude() == 0) {
			return;
		}

		GeoPoint point = new GeoPoint(
				(int) (locationService.getLatitude() * 1E6),
				(int) (locationService.getLongtude() * 1E6));

		// get the MapController object
		MapController controller = mapView.getController();

		// animate to the desired point
		controller.animateTo(point);

		// set the map zoom to 13
		// zoom 1 is top world view
		controller.setZoom(13);

		// invalidate the map in order to show changes
		mapView.invalidate();
	}
}
