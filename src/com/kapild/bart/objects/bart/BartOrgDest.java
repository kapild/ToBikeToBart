package com.kapild.bart.objects.bart;

public class BartOrgDest {

	public String org;
	public String dest;
	public String orginDate;
	public String destDate;
	public String originTime;
	public String destTime; 
	

	public BartOrgDest(String org, String dest, String originDate, String originTime, String destDate, String destTime){

		this.org = org;
		this.dest = dest;
		this.orginDate = originDate;
		this.originTime = originTime;
		this.destDate = destDate;
		this.destTime = destTime;
	}
}
