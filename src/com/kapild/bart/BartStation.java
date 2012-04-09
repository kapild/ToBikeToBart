package com.kapild.bart;

import com.javadocmd.simplelatlng.LatLng;

public class BartStation {

	public String abrv;
	public String statioName; 
	LatLng latLong ;
	
	
	
	public BartStation (String abr, String stationName, String latStr , String longStr){
		this.abrv = abr;
		this.statioName = stationName;
		latLong  = new LatLng(Double.parseDouble(latStr), Double.parseDouble(longStr));
	}
	
	@Override
	public String toString(){
		return this.statioName;
	}
}
