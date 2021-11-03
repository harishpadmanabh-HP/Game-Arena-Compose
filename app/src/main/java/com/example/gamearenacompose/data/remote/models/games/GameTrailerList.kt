package com.example.gamearenacompose.data.remote.models.games


import com.google.gson.annotations.SerializedName

data class GameTrailerList(
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    var next: String?,
    @SerializedName("previous")
    var previous: String?,
    @SerializedName("results")
    var results: List<Result>
) {
    data class Result(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("preview")
        var preview: String,
        @SerializedName("data")
        var quality: Quality
    ) {
        data class Quality(
            @SerializedName("480")
            var x480: String,
            @SerializedName("max")
            var max: String
        )
    }
}