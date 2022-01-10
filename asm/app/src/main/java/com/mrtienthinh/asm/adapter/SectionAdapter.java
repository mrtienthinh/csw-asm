package com.mrtienthinh.asm.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrtienthinh.asm.R;
import com.mrtienthinh.asm.model.Section;
import com.mrtienthinh.asm.response.MovieDto;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Section> sectionList;

    public SectionAdapter(Activity activity, List<Section> sectionList) {
        this.activity = activity;
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_section, parent, false);
        SectionHolder sectionHolder = new SectionHolder(view);
        return sectionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionHolder sectionHolder = (SectionHolder) holder;
        Section section = sectionList.get(position);
        sectionHolder.tvSection.setText(section.getTitle());
        //B1: Data source
        List<MovieDto> listMovies = section.getListMovies();

        //B2: Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        //B3: Adapter
        MovieAdapter adapter = section.getAdapter();
        adapter.setSectionTitle(section.getTitle());

        //B4: RV
        sectionHolder.rvMovie.setLayoutManager(layoutManager);
        sectionHolder.rvMovie.setHasFixedSize(true);
        sectionHolder.rvMovie.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public void reloadData(List<Section> listSection) {
        this.sectionList = listSection;
        this.notifyDataSetChanged();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        TextView tvSection;
        RecyclerView rvMovie;

        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            this.tvSection = itemView.findViewById(R.id.tvTitle);
            this.rvMovie = itemView.findViewById(R.id.rvMovie);;
        }
    }
}
