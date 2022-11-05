package com.moutamid.car_gps_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.model.CarDetails;

import java.util.ArrayList;

public class InfoListAdapter extends RecyclerView.Adapter<InfoListAdapter.PositionViewHolder>{

    private Context mContext;
    private ArrayList<CarDetails> positionArrayList;

    public InfoListAdapter(Context mContext, ArrayList<CarDetails> positionArrayList) {
        this.mContext = mContext;
        this.positionArrayList = positionArrayList;
    }

    @NonNull
    @Override
    public PositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.info_list_custom_layout,parent,false);
        return new PositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionViewHolder holder, int position) {
        CarDetails model = positionArrayList.get(position);
        holder.nametxt.setText(model.getCar());
        holder.timttext.setText(model.getTime());

        if (model.getStatus().equals("moving")){
            holder.posImg.setImageResource(R.drawable.ic_baseline_directions_24);
        }else {
            holder.posImg.setImageResource(R.drawable.ic_baseline_local_parking_24);
        }
    }

    @Override
    public int getItemCount() {
        return positionArrayList.size();
    }

    public class PositionViewHolder extends RecyclerView.ViewHolder{

        TextView nametxt,timttext;
        ImageView posImg;

        public PositionViewHolder(@NonNull View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.text1);
            timttext = itemView.findViewById(R.id.text3);
            posImg = itemView.findViewById(R.id.positionIcon);
        }
    }
}
