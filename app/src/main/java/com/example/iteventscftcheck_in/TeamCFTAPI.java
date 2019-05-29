package com.example.iteventscftcheck_in;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeamCFTAPI {
//    @GET("/members/{id}")
//    public Call<Events> getPostWithID(@Path("id") int id);

    @GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    public Call<List<Events>> getAllPosts();

    @GET("/api/v1/Registration/members/event/{id}?token=cftte@mtest20!9")
    public Call<List<Members>> getMembersId(@Path("id") int id);

    @GET("/api/v1/Registration/members/event/106?token=cftte@mtest20!9")
    public Call<List<Members>> getMembers();

//    @POST("/members")
//    public Call<Events> postData(@Body Events data);
}
