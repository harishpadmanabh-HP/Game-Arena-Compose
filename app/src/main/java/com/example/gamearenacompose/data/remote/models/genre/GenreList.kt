package com.example.gamearenacompose.data.remote.models.genre


import com.google.gson.annotations.SerializedName

data class GenreList(
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
        @SerializedName("slug")
        var slug: String,
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("image_background")
        var imageBackground: String,
        @SerializedName("games")
        var games: List<Game>
    ) {
        data class Game(
            @SerializedName("id")
            var id: Int,
            @SerializedName("slug")
            var slug: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("added")
            var added: Int
        )
    }
}