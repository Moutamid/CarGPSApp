package com.moutamid.car_gps_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.listener.ItemPressListener;
import com.moutamid.car_gps_app.model.CarDetails;
import com.moutamid.car_gps_app.model.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AllCommandsListAdapter extends RecyclerView.Adapter<AllCommandsListAdapter.PositionViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<CarDetails> positionArrayList;
    private ItemPressListener itemPressListener;

    public AllCommandsListAdapter(Context mContext, ArrayList<CarDetails> positionArrayList) {
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<CarDetails> filterList = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filterList.addAll(positionArrayList);
            } else {
                for (CarDetails data : positionArrayList) {
                    if (data.getCar().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filterList.add(data);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            positionArrayList.clear();
            positionArrayList.addAll((Collection<? extends CarDetails>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    public class PositionViewHolder extends RecyclerView.ViewHolder{

        TextView nametxt,citytxt,positiontxt,timttext;
        ImageView posImg;

        public PositionViewHolder(@NonNull View itemView) {
            super(itemView);
            nametxt = itemView.findViewById(R.id.text1);
            citytxt = itemView.findViewById(R.id.text2);
            positiontxt = itemView.findViewById(R.id.position);
            timttext = itemView.findViewById(R.id.text3);
            posImg = itemView.findViewById(R.id.positionIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemPressListener != null){
                        itemPressListener.onItemClick(getAdapterPosition(),view);
                    }
                }
            });
        }
    }

    public void setItemPressListener(ItemPressListener itemPressListener){
        this.itemPressListener = itemPressListener;
    }
}
