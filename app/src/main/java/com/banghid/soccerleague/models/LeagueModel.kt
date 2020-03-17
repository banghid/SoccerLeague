package com.banghid.soccerleague.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueModel(
    var name: String,
    var imagePath: String,
    var country: String,
    var detail: String,
    var id: Int
):Parcelable