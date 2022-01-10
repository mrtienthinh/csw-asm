package com.mrtienthinh.asm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.mrtienthinh.asm.adapter.MovieAdapter;
import com.mrtienthinh.asm.adapter.SectionAdapter;
import com.mrtienthinh.asm.model.Section;
import com.mrtienthinh.asm.network.ApiManager;
import com.mrtienthinh.asm.response.BaseResponseDto;
import com.mrtienthinh.asm.response.HomeContentDto;
import com.mrtienthinh.asm.response.MovieDto;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    int[] sampleImages = {R.drawable.img, R.drawable.img6, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    List<Section> listSection = new ArrayList<>();
    SectionAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCarouselView();
        initRecyclerView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiManager service = retrofit.create(ApiManager.class);

        service.apiHome().enqueue(new Callback<BaseResponseDto<HomeContentDto>>() {
            @Override
            public void onResponse(Call<BaseResponseDto<HomeContentDto>> call, Response<BaseResponseDto<HomeContentDto>> response) {
                Log.d("TAG", "onResponse: ");
                BaseResponseDto<HomeContentDto> model = response.body();
                listSection.add(new Section("Trending", model.getData().getListTrending(),
                        new MovieAdapter(MainActivity.this, model.getData().getListTrending())));

                listSection.add(new Section("Hot", model.getData().getListHot(),
                        new MovieAdapter(MainActivity.this, model.getData().getListHot())));

                listSection.add(new Section("Suggest", model.getData().getListSuggest(),
                        new MovieAdapter(MainActivity.this, model.getData().getListSuggest())));

                listSection.add(new Section("Watch", model.getData().getListWatch(),
                        new MovieAdapter(MainActivity.this, model.getData().getListWatch())));
                sectionAdapter.reloadData(listSection);
            }

            @Override
            public void onFailure(Call<BaseResponseDto<HomeContentDto>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rvHome);
        sectionAdapter = new SectionAdapter(this, listSection);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(sectionAdapter);

    }

    private void initCarouselView() {
        CarouselView carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };
        carouselView.setImageListener(imageListener);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked item: "+ position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}