package com.kapild.bart;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ResultBikeOnBartActivity extends Activity {
    /** Called when the activity is first created. */
	BartItemArrayAdapter dataAdapter;
	private ListView listView ;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
          }        
        
        setContentView(R.layout.tobikebartlist);
        
        View header = (View)getLayoutInflater().inflate(R.layout.tobikebartlistheader,null);
        
        listView = (ListView) findViewById(R.id.tobikebartlists);
        listView.addHeaderView(header);

        TextView headerText= (TextView) findViewById(R.id.headerText);
        
        Intent sender=getIntent();
        String originStation=sender.getStringExtra("orig");
        String destStation=sender.getStringExtra("dest");
        String originTime=sender.getStringExtra("time");
        String originDate=sender.getStringExtra("date");
        String isArrival=sender.getStringExtra("isArrival");
        
  	    Map<String,String> commands = new HashMap<String, String>();
  	    commands.put("orig", originStation);
  	    commands.put("dest", destStation);
  	    commands.put("time", originTime);
  	    commands.put("date", originDate);

  	    if(isArrival!=null && isArrival.equals("true")){
  	  	    commands.put("cmd", "arrive");
  	    }
		ArrayList<BartLeg> list = null;
		try {
			list = BartAPI.toBikeOrNotToBike(commands);
		} catch (Exception e) {
			e.printStackTrace();
		}
		headerText.setText("Origin station:" + StaticStationNames.getStationName(originStation)
				 		 + "\nDestination Station:" +  StaticStationNames.getStationName(destStation));
        dataAdapter = new BartItemArrayAdapter(this, R.layout.tobikebartrow, list);
        listView.setAdapter(dataAdapter);
	  	
    }
	
	

//     lv.setOnItemClickListener(new OnItemClickListener() {
//      public void onItemClick(AdapterView<?> parent, View view,
//          int position, long id) {
//        // When clicked, show a toast with the TextView text
//        Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
//            Toast.LENGTH_SHORT).show();
//      }
//    });        
}