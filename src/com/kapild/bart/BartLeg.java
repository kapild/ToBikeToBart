package com.kapild.bart;

public class BartLeg {

	String order;
	String transferCode;
	public BartOrgDest orgDest;	
	String line; 
	String bikeFlag;
	String trainHeadStation;
	
	public BartLeg (String order,String transferCode, String org, String dest,String originDate, String originTime, String destDate, String destTime
			, String line, String bikeFlag, String trainHeadStation){
		this.order = order;
		this.transferCode = transferCode;
		this.orgDest = new BartOrgDest(org, dest, originDate, originTime, destDate, destTime);
		this.order  = order;
		this.line = line;
		this.bikeFlag = bikeFlag;
		this.trainHeadStation = trainHeadStation;
	}
	
	public BartLeg(BartOrgDest orgDest, String bikeFlag, String line, String trainHeadStation){
		this.orgDest = orgDest;
		this.bikeFlag = bikeFlag;
		this.line = line;
		this.trainHeadStation = trainHeadStation;
	}
	
	@Override
	public String toString(){
		
		return orgDest.org + " to " + orgDest.dest + "\nHead station:" +trainHeadStation +
				 "\n" + "Origin date:" + orgDest.orginDate+
		 "\n" + "Origin time:" + orgDest.originTime +  "\n bikes:" + (bikeFlag.equalsIgnoreCase("1") ? "Allowed" : "Not Allowed");
		
	}
	
}
