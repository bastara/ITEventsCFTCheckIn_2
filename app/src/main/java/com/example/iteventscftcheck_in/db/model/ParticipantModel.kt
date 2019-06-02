package com.example.iteventscftcheck_in.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ParticipantModel {
    @PrimaryKey
    var id: Int = 0
    var firstName: String? = null
    var lastName: String? = null
    var phone: String? = null
    var email: String? = null
    var city: String? = null
    var isVisited: Boolean = false
}