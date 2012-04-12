package com.kapild.bart.activity;

import java.util.Calendar;
import java.util.List;

import com.googlecode.android.widgets.DateSlider.DateSlider;
import com.googlecode.android.widgets.DateSlider.DateTimeSlider;
import com.kapild.bart.R;
import com.kapild.bart.android.BartMeLocationArrayAdapter;
import com.kapild.bart.android.OnOrginSpinnerOnItemSelectedListener;
import com.kapild.bart.objects.business.BartMeLocation;
import com.kapild.bart.objects.business.MyLocation;
import com.kapild.bart.objects.business.SortedBartDistance;
import com.kapild.bart.objects.business.MyLocation.LocationResult;
import com.kapild.bart.utils.GeoHelper;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
//http://stackoverflow.com/questions/2227292/how-to-get-latitude-and-longitude-of-the-mobiledevice-in-android
//http://stackoverflow.com/questions/3145089/what-is-the-simplest-and-most-robust-way-to-get-the-users-current-location-in-a/3145655#3145655
import android.widget.Toast;


public class ToBikeOnBartActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Spinner spinnerOrg, spinnerDest;
	private Button btnCheck, refreshLoc,  dateTimeButton;
	private static boolean  isCheck; 
	static final int DATETIMESELECTOR_ID = 5;
    private static Calendar originCal = null;
	 
	 List<BartMeLocation> bartList;
	 BartMeLocationArrayAdapter dataAdapter;	 
	
	 private void locationClick() {
	    myLocation.getLocation(this, locationResult);
	}
	 
	MyLocation myLocation = new MyLocation();
	
	public LocationResult locationResult = new LocationResult(){
	    @Override
	    public void gotLocation(final Location location){
	    	if(location==null)
	    		return;
	    	GeoHelper.setMyLocation(location);
	    	bartList = SortedBartDistance.getSortedBartStations();
	    	dataAdapter.notifyDataSetChanged();
	    }
	};	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
          }        

        setContentView(R.layout.main);
//      locationClick(); 
        
        //add the origin Spinner
        addItemsOnSpinnerOrigin();
        
        //add the destination Spinner
        addItemsOnSpinnerDestination();
        
        //add Listener to Origin/Destination
        addListenerOnSpinnerItemSelection();
        
        
        addListenerOnButton();
        
        refreshLoc = (Button) this.findViewById(R.id.refreshLoc);
        dateTimeButton = (Button) this.findViewById(R.id.dateTimeSelectButton);
        // set up a listener for when the button is pressed
        refreshLoc.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
            	refreshLoc();
            }
        });
        
        dateTimeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // call the internal showDialog method using the predefined ID
                showDialog(DATETIMESELECTOR_ID);
            }
        });
        
    }
    
    // add items into spinner dynamically
    public void addItemsOnSpinnerOrigin() {
    	spinnerOrg = (Spinner) findViewById(R.id.spinnerOrg);
	  	bartList = SortedBartDistance.getSortedBartStations();
	  	dataAdapter = new BartMeLocationArrayAdapter(this, R.layout.bartmelocationrow, bartList);
	  	spinnerOrg.setAdapter(dataAdapter);
    }
    
    //add items to destination spinner 
    public void addItemsOnSpinnerDestination() {
    	   
    	spinnerDest = (Spinner) findViewById(R.id.spinnerDest);
	  	List<BartMeLocation> list = SortedBartDistance.getSortedBartStations();
	  	BartMeLocationArrayAdapter dataAdapter;	 
	  	
	  	dataAdapter = new BartMeLocationArrayAdapter(this, R.layout.bartmelocationrow, list);
	  	spinnerDest.setAdapter(dataAdapter);
    }
    
    
    public void addListenerOnSpinnerItemSelection() {
    	spinnerOrg = (Spinner) findViewById(R.id.spinnerOrg);
    	spinnerOrg.setOnItemSelectedListener(new OnOrginSpinnerOnItemSelectedListener("Origin Station:"));
    	
    	spinnerDest = (Spinner) findViewById(R.id.spinnerDest);
    	spinnerDest.setOnItemSelectedListener(new OnOrginSpinnerOnItemSelectedListener("Destination Station:"));
    	
    }
    
    
    public static String getBartDateString(Calendar cal){
    	if(cal==null){
    		return "Today";
    	}
    	StringBuffer buffer = new StringBuffer();
    	
    	buffer.append(String.format("%02d", (int)(cal.get(Calendar.MONTH) + 1)));
    	buffer.append("/");    	
    	buffer.append(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
    	buffer.append("/");
    	buffer.append(cal.get(Calendar.YEAR));
    	
    	return buffer.toString();
    }
    
    
    public static String getBartTimeString(Calendar cal){
    	if(cal==null){
    		return "Now";
    	}
    	StringBuffer buffer = new StringBuffer();
    	
    	buffer.append(String.format("%02d", cal.get(Calendar.HOUR)));
    	buffer.append(":");
    	buffer.append(String.format("%02d", cal.get(Calendar.MINUTE)));
    	buffer.append("+");
    	if(cal.get(Calendar.AM_PM) == Calendar.AM){
        	buffer.append("am");
    	}else{
        	buffer.append("pm");
    	}
    	
    	return buffer.toString();
    }
    // get the selected dropdown list value
    public void addListenerOnButton() {

    spinnerOrg = (Spinner) findViewById(R.id.spinnerOrg);
  	spinnerDest = (Spinner) findViewById(R.id.spinnerDest);
  	btnCheck = (Button) findViewById(R.id.btnSubmit);
   
  	btnCheck.setOnClickListener(new OnClickListener() {
   
  	  @Override
  	  public void onClick(View v) {
       
  	    BartMeLocation orgBartStation = (BartMeLocation) spinnerOrg.getSelectedItem();
  	    BartMeLocation destBartStation = (BartMeLocation) spinnerDest.getSelectedItem();
  	    
  	    //TODO: create a dialogue
  	    if(orgBartStation.station.abrv.equals(destBartStation.station.abrv)){
  			Toast.makeText(ToBikeOnBartActivity.this,  "Origin station cannot be same as destination", Toast.LENGTH_SHORT).show();
  			return;
  		}
  	    try {
			
			Intent intent=new Intent(ToBikeOnBartActivity.this,ResultBikeOnBartActivity.class);
			intent.putExtra("orig", orgBartStation.station.abrv);
			intent.putExtra("dest", destBartStation.station.abrv);
			intent.putExtra("time", getBartTimeString(originCal));
			intent.putExtra("isArrival", String.valueOf(isCheck));
			intent.putExtra("date", getBartDateString(originCal));
			
			
			final int result=1;
			startActivityForResult(intent, result);			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	  }
   
  	});
    }
    
    private DateSlider.OnDateSetListener mDateTimeSetListener =
            new DateSlider.OnDateSetListener() {
                public void onDateSet(DateSlider view, Calendar selectedDate, boolean isChecked) {
                    // update the dateText view with the corresponding date
                    originCal = selectedDate;
                    ToBikeOnBartActivity.isCheck = isChecked;
                    if(isChecked){
                    	dateTimeButton.setText("Arrives:" + getBartDateString(originCal) + " at " + getBartTimeString(originCal) );
                    }else{
                    	dateTimeButton.setText("Departs:" + getBartDateString(originCal) + " at " + getBartTimeString(originCal) );
                    }
                }
    };

    protected void refreshLoc() {
        myLocation.getLocation(this, locationResult);
  	
    }    
    
    @Override
    protected Dialog onCreateDialog(int id) {
        // this method is called after invoking 'showDialog' for the first time
        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
    	
        final Calendar c = Calendar.getInstance();
        switch (id) {
        case DATETIMESELECTOR_ID:
            return new DateTimeSlider(this,mDateTimeSetListener,c);
        }
        return null;
    }
    
//  dateText.setText("");
//  if(orgBartStation.station.abrv.equals(destBartStation.station.abrv)){
//	    dateText.setText("Either you are drunk or stoned!!" + "\n Origin station:" + orgBartStation.station.statioName + " cannot be same as destintion" + 
//	    		destBartStation.station.statioName);
//	    return;
//  	
//  }
//  dateText.setText("Date:" + getBartDateString(originCal)+ "\nTime:" +getBartTimeString(originCal));
    
//    private void makeToBikeToast(List<BartLeg> bartLegs){
    
//	    Toast.makeText(ToBikeOnBartActivity.this,
//		"OnClickListener : " + 
//              "\nOrigin: "+ String.valueOf(spinnerOrg.getSelectedItem()) + 
//              "\nDestination: "+ String.valueOf(spinnerDest.getSelectedItem()),
//			Toast.LENGTH_SHORT).show();
//    
//		for(BartLeg leg : bartLegs){
//	  	    Toast.makeText(ToBikeOnBartActivity.this,
//	  	                    "\nOrigin: "+ leg.orgDest.org + 
//	  	                    "\nDestination: "+ leg.orgDest.dest
//	  	                    + "\nBikeAllowed:" + leg.bikeFlag
//	  	                    + "\nHeadStation:" + leg.trainHeadStation,
//	  	    			Toast.LENGTH_SHORT).show();
//			
//		}
//    	
////                    dateText.setText(getBartTimeString(originCal));
//  dateText.append("\n" + String.format("The chosen date and time:%n%te. %tB %tY%n%tH:%02d",
//  selectedDate, selectedDate, selectedDate, selectedDate, minute));
    	
//    }
 }