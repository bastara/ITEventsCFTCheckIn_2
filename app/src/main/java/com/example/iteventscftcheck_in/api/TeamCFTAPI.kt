package com.example.iteventscftcheck_in.api

import com.example.iteventscftcheck_in.model.Events
import com.example.iteventscftcheck_in.model.Members

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface TeamCFTAPI {

    @get:GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    val allPosts: Call<List<Events>>

    // RXJAVA не успел доделать
    @GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    fun getAllEvents(): Observable<List<Events>>

    @GET("/api/v1/Registration/members/event/{id}?token=cftte@mtest20!9")
    fun getMembersId(@Path("id") id: Int): Call<List<Members>>
}
