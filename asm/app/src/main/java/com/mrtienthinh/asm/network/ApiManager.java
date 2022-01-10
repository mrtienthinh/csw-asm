package com.mrtienthinh.asm.network;

import com.mrtienthinh.asm.response.BaseResponseDto;
import com.mrtienthinh.asm.response.HomeContentDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String SERVER = "http://192.168.14.103:8080";

    @GET("/")
    Call<BaseResponseDto<HomeContentDto>> apiHome();
}
