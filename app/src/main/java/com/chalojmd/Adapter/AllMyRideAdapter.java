package com.chalojmd.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chalojmd.R;
import com.chalojmd.model.BestSelling;

import java.util.List;

public class AllMyRideAdapter extends RecyclerView.Adapter<AllMyRideAdapter.MyViewHolder> {
    private Context context;
      private List<BestSelling> allMyRide;
    @NonNull
    @Override
    public AllMyRideAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyler_my_rides, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    public AllMyRideAdapter(List<BestSelling> allMyRide, int recycler_item, Context applicationContext) {
        this.context = applicationContext;
        this.allMyRide= allMyRide;
    }

    @Override
    public void onBindViewHolder(@NonNull AllMyRideAdapter.MyViewHolder myViewHolder, int position) {

        BestSelling bestSelling = allMyRide.get(position);
        myViewHolder.textViewtime.setText(bestSelling.getDTitle());
        myViewHolder.textViewdate.setText(bestSelling.getPDesc());
        myViewHolder.textViewDestination.setText(bestSelling.getPPrice());
        myViewHolder.textViewPrice.setText(bestSelling.getPQuantity());
        myViewHolder.textViewSource.setText(bestSelling.getPTitle());


    }

    @Override
    public int getItemCount() {
        return  allMyRide.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewtime,textViewSource,textViewDestination,textViewPrice,textViewdate,textViewCancel,textViewlink;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewtime = itemView.findViewById(R.id.time);
            textViewdate= itemView.findViewById(R.id.text_date);
            textViewSource = itemView.findViewById(R.id.Source1);
            textViewDestination= itemView.findViewById(R.id.destination);
            textViewPrice = itemView.findViewById(R.id.price);
            textViewCancel= itemView.findViewById(R.id.text_cancel);
            textViewlink= itemView.findViewById(R.id.link);

        }
    }
}
