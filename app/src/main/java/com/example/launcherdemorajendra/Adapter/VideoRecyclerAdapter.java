package com.example.launcherdemorajendra.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.launcherdemorajendra.View.PlayerActivity;
import com.example.launcherdemorajendra.R;
import com.example.launcherdemorajendra.Models.VideoModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.VideoViewHolder> {
    private List<VideoModel> videoList;

    public VideoRecyclerAdapter(List<VideoModel> videoList) {
        this.videoList = videoList;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgThumb;

        VideoViewHolder(View view) {
            super(view);
            imgThumb = view.findViewById(R.id.imageViewThumb);
        }
    }
    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_row, parent, false);
        return new VideoViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final VideoViewHolder holder, int position) {
        final VideoModel mVideo = videoList.get(position);

        Glide.with(holder.itemView.getContext()).load(mVideo.getVideoThumb()).into(holder.imgThumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mPlayerIntent = PlayerActivity.getStartIntent(holder.itemView.getContext(), mVideo.getFilePath());
                holder.itemView.getContext().startActivity(mPlayerIntent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return videoList.size();
    }
}

