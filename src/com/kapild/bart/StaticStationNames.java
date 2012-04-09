package com.kapild.bart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StaticStationNames {

	//make the class as static
	private StaticStationNames(){
	}
	
	//map to map abbrevation to station Full Name
	public static final Map<String, String> stationAbrvMap ; 
	
	static {
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("12th",	"12th St. Oakland City Center");
		map.put("16th",	"16th St. Mission (SF)");
		map.put("19th",	"19th St. Oakland");
		map.put("24th",	"24th St. Mission (SF)");
		map.put("ashb",	"Ashby (Berkeley)");
		map.put("balb",	"Balboa Park (SF)");
		map.put("bayf",	"Bay Fair (San Leandro)");
		map.put("cast",	"Castro Valley");
		map.put("civc",	"Civic Center (SF)");
		map.put("cols",	"Coliseum/Oakland Airport");
		map.put("colm",	"Colma");
		map.put("conc",	"Concord");
		map.put("daly",	"Daly City");
		map.put("dbrk",	"Downtown Berkeley");
		map.put("dubl",	"Dublin/Pleasanton");
		map.put("deln",	"El Cerrito del Norte");
		map.put("plza",	"El Cerrito Plaza");
		map.put("embr",	"Embarcadero (SF)");
		map.put("frmt",	"Fremont");
		map.put("ftvl",	"Fruitvale (Oakland)");
		map.put("glen",	"Glen Park (SF)");
		map.put("hayw",	"Hayward");
		map.put("lafy",	"Lafayette");
		map.put("lake",	"Lake Merritt (Oakland)");
		map.put("mcar",	"MacArthur (Oakland)");
		map.put("mlbr",	"Millbrae");
		map.put("mont",	"Montgomery St. (SF)");
		map.put("nbrk",	"North Berkeley");
		map.put("ncon",	"North Concord/Martinez");
		map.put("orin",	"Orinda");
		map.put("pitt",	"Pittsburg/Bay Point");
		map.put("phil",	"Pleasant Hill");
		map.put("powl",	"Powell St. (SF)");
		map.put("rich",	"Richmond");
		map.put("rock",	"Rockridge (Oakland)");
		map.put("sbrn",	"San Bruno");
		map.put("sfia",	"San Francisco Int'l Airport");
		map.put("sanl",	"San Leandro");
		map.put("shay",	"South Hayward");
		map.put("ssan",	"South San Francisco");
		map.put("ucty",	"Union City");
		map.put("wcrk",	"Walnut Creek");
		map.put("woak",	"West Oakland");		

		//return a non-modifiable map
		stationAbrvMap = Collections.unmodifiableMap(map);
		
	}
	
	public static String getStationName(String abrv){
		if(stationAbrvMap.containsKey(abrv.toLowerCase())){
			return stationAbrvMap.get(abrv.toLowerCase());
		}
		
		return "**No Station exists**";
	}

}
