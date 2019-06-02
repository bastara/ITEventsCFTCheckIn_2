package com.example.iteventscftcheck_in.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class EventsModel {

    @PrimaryKey
    var id: Int = 0
    var date: String? = null
    var city: String? = null
    var name: String? = null
    var count: String? = null
    var description: String? = null
    var urlBackground: String? = null
}


