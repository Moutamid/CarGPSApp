package com.moutamid.car_gps_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.model.CarDetails;
import com.moutamid.car_gps_app.model.Position;

import java.util.ArrayList;

public class PositionListAdapter extends RecyclerView.Adapter<PositionListAdapter.PositionViewHolder>{

    private Context mContext;
    private ArrayList<CarDetails> positionArrayList;

    public PositionListAdapter(Context mContext, ArrayList<CarDetails> positionArrayList) {
        this.mContext = mContext;
        this.positionArrayList = positionArrayList;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.postion_list_custom_layout,parent,false);
        return new PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {
        CarDetails model = positionArrayList.get(position);
        holder.nametxt.setText(model.getCar());
        holder.positiontxt.setText("OH 59min");
        holder.citytxt.setText(model.getLocation());
        holder.timttext.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return positionArrayList.size();
    }

    public class PositionViewHolder extends RecyclerView.ViewHolder{

        TextView nametxt,citytxt,positiontxt,timttext;

        public PositionViewHolder(@NonNull View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.text1);
            citytxt = itemView.findViewById(R.id.text2);
            positiontxt = itemView.findViewById(R.id.position);
            timttext = itemView.findViewById(R.id.text3);
        }
    }
}
