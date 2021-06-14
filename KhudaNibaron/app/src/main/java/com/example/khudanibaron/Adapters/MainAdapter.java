package com.example.khudanibaron.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khudanibaron.Models.MainModel;
import com.example.khudanibaron.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Viewholder> {

    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_layout, parent, false);
        return new Viewholder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final MainModel model = list.get(position);
        holder.foodimage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView foodimage;
        TextView mainName, price, description;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            foodimage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.nameTextViewId);
            price = itemView.findViewById(R.id.orderPriceTextViewId);
            description = itemView.findViewById(R.id.descriptionTextViewId);
        }
    }
}
