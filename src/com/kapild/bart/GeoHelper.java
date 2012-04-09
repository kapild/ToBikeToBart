package com.kapild.bart;


import android.location.Location;

import com.javadocmd.simplelatlng.LatLng;

public class GeoHelper {
	

	private GeoHelper(){
		;
	}
	public static  LatLng MY_LOC = new LatLng(Double.parseDouble("37.752254"), Double.parseDouble("-122.418466"));
	

	
	public static LatLng getCurrentLocation(){
		return MY_LOC;
	}
	
	public static void setMyLocation(Double lat, Double log){
		MY_LOC = new LatLng(lat, log);
	}
	public static void setMyLocation(Location location){
		if(location==null){
			return;
		}
		MY_LOC = new LatLng(location.getLatitude(), location.getLongitude());
	}
			
	
}
