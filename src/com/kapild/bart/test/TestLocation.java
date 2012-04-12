package com.kapild.bart.test;

import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;
import com.kapild.bart.api.BartStationNameAPI;
import com.kapild.bart.objects.business.BartMeLocation;
import com.kapild.bart.objects.business.SortedBartDistance;
import com.kapild.bart.utils.GeoHelper;

public class TestLocation {

	
	public static void main(String args[]){
	  	List<BartMeLocation> list = SortedBartDistance.getSortedBartStations();
		
	  	for(BartMeLocation lo: list){
	  		System.out.println(lo);
	  	}
		List<BartMeLocation> bartMeList = new ArrayList<BartMeLocation>(); ;
		LatLng myLocation = GeoHelper.getCurrentLocation();  
		
		System.out.println(myLocation);
		
		for(String abrv : BartStationNameAPI.stationAbrvMap.keySet()){
			BartMeLocation bartMeLoc = new BartMeLocation( BartStationNameAPI.stationAbrvMap.get(abrv), myLocation);
			bartMeList.add(bartMeLoc);
		}
			  	
	}
}
