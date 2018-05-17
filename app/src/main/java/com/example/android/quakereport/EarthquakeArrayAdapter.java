package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {
    private DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private NumberFormat magFormat = new DecimalFormat("0.0");

    public EarthquakeArrayAdapter(@NonNull Context context, int resource, @NonNull List<Earthquake> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View listItemView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.magText = (TextView) listItemView.findViewById(R.id.item_mag);
            holder.locText = (TextView) listItemView.findViewById((R.id.item_location));
            holder.dateText = (TextView) listItemView.findViewById(R.id.item_date);
            holder.timeText = (TextView) listItemView.findViewById(R.id.item_time);
            holder.locOffsetText = (TextView) listItemView.findViewById(R.id.item_location_offset);
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        Earthquake currentEarthquake = getItem(position);
        String offsetLocation = "Near the";
        String location = currentEarthquake.getLocation();
        if(location.contains("of")){
            int index = location.indexOf("of");
            offsetLocation = location.substring(0, index+2);
            location = location.substring(index+3);
        }

        holder.magText.setText(magFormat.format(currentEarthquake.getMagnitude()));
        holder.locText.setText(location);
        holder.locOffsetText.setText(offsetLocation);
        holder.dateText.setText(dateFormat.format(new Date(currentEarthquake.getDate())));
        holder.timeText.setText(timeFormat.format(new Date(currentEarthquake.getDate())));

        GradientDrawable background = (GradientDrawable) holder.magText.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        background.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude){
        switch ((int) magnitude) {
            case 0:
            case 1:
              return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
    }

    static class ViewHolder {
        TextView magText;
        TextView locOffsetText;
        TextView locText;
        TextView dateText;
        TextView timeText;
    }
}
