package com.kapild.bart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class BartStationNameAPI {
	
	public static  Map<String, BartStation> stationAbrvMap ; 
	
	static {
		getInstance();
	}
	private static BartStationNameAPI _instance=null;
	
	private BartStationNameAPI() {
		loadBartStation();
		
	}
	
	private static void loadBartStation() {
		stationAbrvMap  = new HashMap<String, BartStation>();
		try {
			List<BartStation> bartStations = BartAPI.getBartStations(null);
			for(BartStation station: bartStations){
				stationAbrvMap.put(station.abrv, station);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static  BartStationNameAPI getInstance(){
		
		if(_instance == null){
			_instance = new BartStationNameAPI();
		}
		return _instance;
	}

}
