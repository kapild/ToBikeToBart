package com.kapild.bart.objects.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;
import com.kapild.bart.api.BartStationNameAPI;
import com.kapild.bart.utils.GeoHelper;


public class SortedBartDistance {
	
	private static List<BartMeLocation> bartMeList = null;
	private static LatLng oldLocation = null;
	
	public static List<BartMeLocation> getSortedBartStations(){

		LatLng myLocation = GeoHelper.getCurrentLocation();  
		
		if(bartMeList == null || oldLocation == null || !oldLocation.equals(myLocation)){
			if(bartMeList == null){
				bartMeList = new ArrayList<BartMeLocation>();
			}
			oldLocation = myLocation;
			bartMeList.clear();
			for(String abrv : BartStationNameAPI.stationAbrvMap.keySet()){
				BartMeLocation bartMeLoc = new BartMeLocation( BartStationNameAPI.stationAbrvMap.get(abrv), myLocation);
				bartMeList.add(bartMeLoc);
			}
			Collections.sort(bartMeList);
			
		}
		return bartMeList;
	}

}
