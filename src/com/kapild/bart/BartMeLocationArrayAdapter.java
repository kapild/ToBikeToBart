package com.kapild.bart;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class BartMeLocationArrayAdapter extends ArrayAdapter<BartMeLocation> {

        private List<BartMeLocation> items;
        private LayoutInflater mInflater;
    	
        public BartMeLocationArrayAdapter(Context context, int textViewResourceId, List<BartMeLocation> items) {
                super(context, textViewResourceId, items);
                mInflater = LayoutInflater.from(context);
        		this.items = items;
        }
        
    	@Override
    	public int getCount() {
    		return items.size();
    	}        

    	@Override
    	 public View getDropDownView(int position, View convertView,
                 ViewGroup parent) {
    		 	return getView(position, convertView, null);
    	 }    	
    	@Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	ViewHolder holder;        	
                if (convertView == null) {
                	convertView = mInflater.inflate(R.layout.bartmelocationrow, parent, false);
                	holder = new ViewHolder();
                	holder.stationName =(TextView) convertView.findViewById(R.id.bartmestationname);
                	holder.distance =(TextView) convertView.findViewById(R.id.bartmestationdistance);
                	convertView.setTag(holder);                	
                }else{
                	holder = (ViewHolder) convertView.getTag();
                }
                BartMeLocation o = items.get(position);
                if (o != null) {
                    holder.stationName.setText(o.station.statioName);
                    holder.distance.setText(String.format("%.02f", o.distance) + " miles");
                }
           
                return convertView;
        }
        
    	static class ViewHolder {
     		TextView stationName;
     		TextView distance;
     	}        
}