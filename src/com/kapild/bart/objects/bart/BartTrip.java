package com.kapild.bart.objects.bart;

import java.util.ArrayList;
import java.util.List;


public class BartTrip {

	public double fare;
	public BartOrgDest orgDest;
	List<BartLeg> legs = null;
	
	public BartTrip (String org, String dest, float fare, String originDate, String originTime, String destDate, String destTime){
		this.fare = fare;
		this.orgDest = new BartOrgDest(org, dest, originDate, originTime, destDate, destTime);
	}
	
	public BartTrip (String org, String dest, float fare, String originDate, String originTime, String destDate, String destTime, BartLeg leg){
		this.fare = fare;
		this.orgDest = new BartOrgDest(org, dest, originDate, originTime, destDate, destTime);
		addLeg(leg);
	}
	
	public void addLeg(BartLeg leg){
		
		if(legs == null){
			legs = new ArrayList<BartLeg>();
		}
		legs.add(leg);
	}
	
}
