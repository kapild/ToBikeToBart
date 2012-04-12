package com.kapild.bart.objects.business;


import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.kapild.bart.objects.bart.BartStation;

public class BartMeLocation implements Comparable<BartMeLocation>{

	public BartStation station;
	public LatLng myLocation; 
	public Double distance = null;
	
	public BartMeLocation(BartStation station, LatLng myLocation){
		this.station = station;
		this.myLocation = myLocation;
	}

	
	@Override
	public String toString(){
		
		return station.statioName ;
	}
	public double getDistance(){
		if(distance==null){
			distance = LatLngTool.distance(station.latLong, myLocation, LengthUnit.MILE);
		}
		return distance; 
	}


	@Override
	public int compareTo(BartMeLocation other) {
		return (getDistance() - other.getDistance() ) < 0 ?  -1 : 1;
	}
	
	
}
