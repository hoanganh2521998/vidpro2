package com.example.vidpro2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.viewholder> {
    List<Categories> categoriesList;
    IonClickVideo ionClickVideo;

    public AdapterCategories(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cagetories, parent, false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Categories categories = categoriesList.get(position);
        holder.tvName.setText(categories.getTitle());
        Picasso.get().load(categories.getThumb()).into(holder.imgCate);
        holder.imgCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imgCate;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCate);
            imgCate = itemView.findViewById(R.id.imgCate);
        }
    }
}
