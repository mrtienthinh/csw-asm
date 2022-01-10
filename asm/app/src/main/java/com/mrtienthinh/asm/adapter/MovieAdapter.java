package com.mrtienthinh.asm.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrtienthinh.asm.R;
import com.mrtienthinh.asm.response.MovieDto;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<MovieDto> listMovie;
    private String sectionTitle;


    public MovieAdapter(Activity activity, List<MovieDto> listMovie) {
        this.activity = activity;
        this.listMovie = listMovie;
    }

    public MovieAdapter(List<MovieDto> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (sectionTitle.equalsIgnoreCase("Hot") || sectionTitle.equalsIgnoreCase("Watch")) {
            itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie, parent, false);
        } else {
            itemView = ((Activity) parent.getContext()).getLayoutInflater().inflate(R.layout.item_movie_2, parent, false);
        }
        MovieHolder holder = new MovieHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieDto movieDto = listMovie.get(position);
        MovieHolder vh = (MovieHolder) holder;
        vh.tvTitle.setText(movieDto.getName());
        Glide.with(activity).load(movieDto.getThumbnail()).into(vh.ivCover);
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public void reloadData(List<MovieDto> movieDtoList) {
        this.listMovie = movieDtoList;
        this.notifyDataSetChanged();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

        ImageView ivCover;
        TextView tvTitle;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivCover = itemView.findViewById(R.id.ivCover);
        }
    }


    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }
}
