package com.example.iteventscftcheck_in.api;

import com.example.iteventscftcheck_in.model.Events;
import com.example.iteventscftcheck_in.model.Members;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeamCFTAPI {

    @GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    Call<List<Events>> getAllPosts();

    @GET("/api/v1/Registration/members/event/{id}?token=cftte@mtest20!9")
    Call<List<Members>> getMembersId(@Path("id") int id);
}
