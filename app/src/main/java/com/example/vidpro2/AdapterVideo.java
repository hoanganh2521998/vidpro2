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

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.viewholder> {
    List<Video> videoList;
    IonClickVideo ionClickVideo;

    public AdapterVideo(List<Video> videoList) {
        this.videoList = videoList;
    }

    public void setIonClickVideo(IonClickVideo ionClickVideo) {
        this.ionClickVideo = ionClickVideo;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_video, parent, false);
        viewholder viewholder = new viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Video video = videoList.get(position);
        holder.tvNameVideo.setText(video.getTitle());
        holder.tvDes.setText(video.getDateCreated());
        Picasso.get().load(video.getAvatar()).into(holder.imgVideo);
        holder.imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickVideo.onClickVideo(video.getTitle(), video.getAvatar(), video.getFileMp4(), video.getDateCreated());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvNameVideo, tvDes;
        ImageView imgVideo;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvNameVideo = itemView.findViewById(R.id.tvNameVideo);
            tvDes = itemView.findViewById(R.id.tvDes);
            imgVideo = itemView.findViewById(R.id.imgVideo);
        }
    }
}
