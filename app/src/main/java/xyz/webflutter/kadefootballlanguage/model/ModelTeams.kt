package xyz.webflutter.kadefootballlanguage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelTeams (
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
) : Parcelable