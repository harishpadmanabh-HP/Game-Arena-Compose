package com.example.gamearenacompose.data.remote.models.games


import com.google.gson.annotations.SerializedName

data class ScreenshotList(
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    var next: Any?,
    @SerializedName("previous")
    var previous: Any?,
    @SerializedName("results")
    var results: List<Result>
) {
    data class Result(
        @SerializedName("id")
        var id: Int,
        @SerializedName("image")
        var image: String,
        @SerializedName("width")
        var width: Int,
        @SerializedName("height")
        var height: Int,
        @SerializedName("is_deleted")
        var isDeleted: Boolean
    )
}