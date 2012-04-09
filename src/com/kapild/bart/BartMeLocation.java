package com.kapild.bart;


import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class BartMeLocation implements Comparable<BartMeLocation>{

	BartStation station;
	LatLng myLocation; 
	Double distance = null;
	
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
