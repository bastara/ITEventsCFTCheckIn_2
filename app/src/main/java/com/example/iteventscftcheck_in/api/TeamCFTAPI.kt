package com.example.iteventscftcheck_in.api

import com.example.iteventscftcheck_in.model.Events
import com.example.iteventscftcheck_in.model.Members

import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface TeamCFTAPI {

    @GET("/api/v1/Events/registration?token=cftte@mtest20!9")
    fun getAllEvents(): Observable<List<Events>>

    @GET("/api/v1/Registration/members/event/{id}?token=cftte@mtest20!9")
    fun getMembersIdRX(@Path("id") id: Int): Observable<List<Members>>
}
