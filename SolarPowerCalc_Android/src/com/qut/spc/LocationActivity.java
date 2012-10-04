package com.qut.spc;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.qut.spc.service.LocationService;

public class LocationActivity extends MapActivity {
	private LocationService locationService;
	private MapView mapView;
	private GeoPoint selectedPoint;
	private static final String API_KEY_DEBUG = "0TZUBCgVFqPlFD0_tuWFPEooI6FvTfTRE6L9Gng";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_location);

		// fetch the map view from the layout
		mapView = new MapView(this, API_KEY_DEBUG);
		// make available zoom controls
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);

		setContentView(mapView);
		
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

		selectedPoint = new GeoPoint(
				(int) (locationService.getLatitude() * 1E6),
				(int) (locationService.getLongtude() * 1E6));

		// get the MapController object
		MapController controller = mapView.getController();

		// animate to the desired point
		controller.animateTo(selectedPoint);

		// set the map zoom
		// zoom 1 is top world view
		controller.setZoom(12);

		// Add a location marker
		LocationOverlay overlay = new LocationOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.add(overlay);
		
		// invalidate the map in order to show changes
		mapView.invalidate();
	}
	
	class LocationOverlay extends Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow);
			if (selectedPoint != null) {
				// ---translate the GeoPoint to screen pixels---
				Point screenPts = new Point();
				mapView.getProjection().toPixels(selectedPoint, screenPts);
	
				// ---add the marker---
				Bitmap bmp = BitmapFactory.decodeResource(getResources(),
						R.drawable.pinsmall);
			
				canvas.drawBitmap(bmp,
						screenPts.x - bmp.getWidth() / 2,
						screenPts.y - bmp.getHeight(), null);
			}
			return true;
		}

		@Override
		public boolean onTap(GeoPoint p, MapView mapView) {
			selectedPoint = p;
			return false;
		}
		
//		@Override
//		public boolean onTouchEvent(MotionEvent event, MapView mapView) {
//			if (event.getAction() == MotionEvent.ACTION_UP) {
//				selectedPoint = mapView.getProjection().fromPixels(
//						(int) event.getX(), (int) event.getY());
//			}
//			return false;
//		}
	}

}
