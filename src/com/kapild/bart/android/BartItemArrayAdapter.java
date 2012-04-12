package com.kapild.bart.android;

import java.util.ArrayList;

import com.kapild.bart.R;
import com.kapild.bart.R.drawable;
import com.kapild.bart.R.id;
import com.kapild.bart.R.layout;
import com.kapild.bart.objects.bart.BartLeg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class BartItemArrayAdapter extends ArrayAdapter<BartLeg> {

        private ArrayList<BartLeg> items;
        private LayoutInflater mInflater;
    	
        public BartItemArrayAdapter(Context context, int textViewResourceId, ArrayList<BartLeg> items) {
                super(context, textViewResourceId, items);
                mInflater = LayoutInflater.from(context);
        		this.items = items;
        }
        
    	@Override
    	public int getCount() {
    		return items.size();
    	}        

    	@Override
        public View getView(int position, View convertView, ViewGroup parent) {
        	ViewHolder holder;        	
                if (convertView == null) {
                	convertView = mInflater.inflate(R.layout.tobikebartrow, parent, false);
                	holder = new ViewHolder();
                	holder.startTime =(TextView) convertView.findViewById(R.id.startTime);
                	holder.endTime =(TextView) convertView.findViewById(R.id.endTime);
                	holder.stationName =(TextView) convertView.findViewById(R.id.headTrain);
                	holder.image= (ImageView) convertView.findViewById(R.id.bikeAllowedIcon);
                	holder.image.setMaxHeight(10);
                	holder.image.setMaxWidth(10);
                	convertView.setTag(holder);                	
                }else{
                	holder = (ViewHolder) convertView.getTag();
                }
                BartLeg o = items.get(position);
                if (o != null) {
                    holder.startTime.setText(o.orgDest.originTime);
                    holder.endTime.setText(o.orgDest.destTime);
                    holder.stationName.setText(o.trainHeadStation);
                    if(o.bikeFlag.equals("1")){
                      holder.image.setImageResource(R.drawable.bikesyes);                    
                    }else{
                        holder.image.setImageResource(R.drawable.bikesno);                    
                    }
                }
           
                return convertView;
        }
        
    	static class ViewHolder {
     		ImageView image;
     		TextView startTime;
     		TextView endTime;
     		TextView stationName   ;  		
     	}        
}