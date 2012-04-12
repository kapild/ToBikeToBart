package com.kapild.bart.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kapild.bart.api.BartAPI;
import com.kapild.bart.objects.bart.BartLeg;

public class DummyData {
	
	
	public static ArrayList<BartLeg> getBikeBartDummydata() throws Exception{
	
  	    Map<String,String> commands = new HashMap<String, String>();
  	    commands.put("orig", "24th");
  	    commands.put("dest", "EMBR");
  	    commands.put("time", "now");
  	    commands.put("date", "today");
		
  	    return  (ArrayList<BartLeg>) BartAPI.toBikeOrNotToBike(commands);
	}
			

}
