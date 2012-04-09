package com.kapild.bart;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class BartAPI {
	
	static private final String API_KEY ="ZGHM-XDER-IJQQ-KJYN";
	
	static private final String BART_API_URL = "http://api.bart.gov/api/sched.aspx?";
	static private final String BART_STNT_API_URL = "http://api.bart.gov/api/stn.aspx?";
	static String  [] toBikeOrNotToBikeCommand = new String[] {"cmd", "dest","orig"};
	private BartAPI(){
		
	}
	
	public static ArrayList<BartLeg> toBikeOrNotToBike(Map<String, String> commands) throws Exception{
		
		commands.put("key", API_KEY);
		if(!commands.containsKey("cmd")){
			commands.put("cmd", "depart");
		}
		commands.put("a", "4");
		commands.put("b", "4");
		
		Element docEle = URLHelper.getRequest(BART_API_URL + getBikeOrNotToBikeUrl(commands));
		NodeList nl = docEle.getElementsByTagName("schedule");
		ArrayList<BartLeg> bartLegs = new ArrayList<BartLeg>();
		if(nl!=null && nl.getLength() > 0){
			Element el = (Element) nl.item(0);
			NodeList nl2 = el.getElementsByTagName("request");
			if(nl2!=null && nl2.getLength() >0){
				for(int i = 0; i < nl2.getLength();i++){
					Element request = (Element) nl2.item(i);
					NodeList nl3 = request.getElementsByTagName("trip");
					for(int j =0; nl3!=null && j < nl3.getLength();j++){
						Element trip= (Element) nl3.item(j);
						Element leg = (Element) trip.getElementsByTagName("leg").item(0);
						String bikeFlag = leg.getAttribute("bikeflag");
						BartOrgDest bartOrgDest= new BartOrgDest(leg.getAttribute("origin"), leg.getAttribute("destination"), leg.getAttribute("origTimeDate"),
								leg.getAttribute("origTimeMin")	,leg.getAttribute("destTimeDate"),leg.getAttribute("destTimeMin"));
						BartLeg bartLeg  = new BartLeg(bartOrgDest, leg.getAttribute("bikeflag"), leg.getAttribute("line"), leg.getAttribute("trainHeadStation"));
						System.out.println(bikeFlag);
						bartLegs.add(bartLeg);
					}
				}
			}
		}
		
		return bartLegs;
	}
	
	public static List<BartStation> getBartStations(Map <String, String> commands) throws IOException, ParserConfigurationException, SAXException, Exception{
		List<BartStation> listOfBart = new ArrayList<BartStation>();
		
		if(commands == null){
			commands = new HashMap<String, String>();
		}
		commands.put("key", API_KEY);
		commands.put("cmd", "stns");

		Element docEle = URLHelper.getRequest(BART_STNT_API_URL + hashToParamsString(commands));
		NodeList nl = docEle.getElementsByTagName("stations");
		
		Element allStations  = (Element) nl.item(0);
		NodeList stations = allStations.getElementsByTagName("station");
		if(stations!=null && stations.getLength() > 0){
			for(int i = 0; i < stations.getLength();i++){
				Element station = (Element) stations.item(i);
				String stationName =  station.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
				String abbr =  station.getElementsByTagName("abbr").item(0).getFirstChild().getNodeValue();
				String lat =  station.getElementsByTagName("gtfs_latitude").item(0).getFirstChild().getNodeValue();
				String lomg =  station.getElementsByTagName("gtfs_longitude").item(0).getFirstChild().getNodeValue();
				
				BartStation bartStation = new BartStation(abbr, stationName, lat, lomg);
				listOfBart.add(bartStation);
			}
		}
		return listOfBart;
		
	}
	
	private static String hashToParamsString(Map<String, String> commands){
		
		StringBuffer buffer = new StringBuffer();
		boolean isFirst = true;
		
		for(String param: commands.keySet()){
			if(!isFirst){
				buffer.append("&");
			}
			buffer.append(param +"=" + commands.get(param));
			isFirst = false;
		}
		
		return buffer.toString();
		
	}
	private static String getBikeOrNotToBikeUrl(Map<String, String> commands) throws Exception{
		for(String pars: toBikeOrNotToBikeCommand){
			if(!commands.containsKey(pars)){
				throw new Exception(pars + " parameter missing");
			}
		}
		
		return hashToParamsString(commands);
	}

}
