package com.kapild.bart.android;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

 
public class OnOrginSpinnerOnItemSelectedListener implements OnItemSelectedListener {
	
	private String type;
	public OnOrginSpinnerOnItemSelectedListener(String type){
		this.type = type;
	}
 
  public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
//	Toast.makeText(parent.getContext(),  "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
	Toast.makeText(parent.getContext(),  type + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
	  
  }
 
  @Override
  public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
  }
 
}