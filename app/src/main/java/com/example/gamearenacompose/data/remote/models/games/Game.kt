package com.example.gamearenacompose.data.remote.models.games


import com.google.gson.annotations.SerializedName

data class Game(
    @SerializedName("id")
    var id: Int,
    @SerializedName("slug")
    var slug: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("name_original")
    var nameOriginal: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("metacritic")
    var metacritic: Int,
    @SerializedName("metacritic_platforms")
    var metacriticPlatforms: List<Any>,
    @SerializedName("released")
    var released: String,
    @SerializedName("tba")
    var tba: Boolean,
    @SerializedName("updated")
    var updated: String,
    @SerializedName("background_image")
    var backgroundImage: String,
    @SerializedName("background_image_additional")
    var backgroundImageAdditional: String,
    @SerializedName("website")
    var website: String,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("rating_top")
    var ratingTop: Int,
    @SerializedName("ratings")
    var ratings: List<Rating>,
    @SerializedName("reactions")
    var reactions: Reactions,
    @SerializedName("added")
    var added: Int,
    @SerializedName("added_by_status")
    var addedByStatus: AddedByStatus,
    @SerializedName("playtime")
    var playtime: Int,
    @SerializedName("screenshots_count")
    var screenshotsCount: Int,
    @SerializedName("movies_count")
    var moviesCount: Int,
    @SerializedName("creators_count")
    var creatorsCount: Int,
    @SerializedName("achievements_count")
    var achievementsCount: Int,
    @SerializedName("parent_achievements_count")
    var parentAchievementsCount: Int,
    @SerializedName("reddit_url")
    var redditUrl: String,
    @SerializedName("reddit_name")
    var redditName: String,
    @SerializedName("reddit_description")
    var redditDescription: String,
    @SerializedName("reddit_logo")
    var redditLogo: String,
    @SerializedName("reddit_count")
    var redditCount: Int,
    @SerializedName("twitch_count")
    var twitchCount: Int,
    @SerializedName("youtube_count")
    var youtubeCount: Int,
    @SerializedName("reviews_text_count")
    var reviewsTextCount: Int,
    @SerializedName("ratings_count")
    var ratingsCount: Int,
    @SerializedName("suggestions_count")
    var suggestionsCount: Int,
    @SerializedName("alternative_names")
    var alternativeNames: List<Any>,
    @SerializedName("metacritic_url")
    var metacriticUrl: String,
    @SerializedName("parents_count")
    var parentsCount: Int,
    @SerializedName("additions_count")
    var additionsCount: Int,
    @SerializedName("game_series_count")
    var gameSeriesCount: Int,
    @SerializedName("user_game")
    var userGame: Any?,
    @SerializedName("reviews_count")
    var reviewsCount: Int,
    @SerializedName("saturated_color")
    var saturatedColor: String,
    @SerializedName("dominant_color")
    var dominantColor: String,
    @SerializedName("parent_platforms")
    var parentPlatforms: List<ParentPlatform>,
    @SerializedName("platforms")
    var platforms: List<Platform>,
    @SerializedName("stores")
    var stores: List<Store>,
    @SerializedName("developers")
    var developers: List<Developer>,
    @SerializedName("genres")
    var genres: List<Genre>,
    @SerializedName("tags")
    var tags: List<Tag>,
    @SerializedName("publishers")
    var publishers: List<Publisher>,
    @SerializedName("esrb_rating")
    var esrbRating: Any?,
    @SerializedName("clip")
    var clip: Any?,
    @SerializedName("description_raw")
    var descriptionRaw: String
) {
    data class Rating(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("count")
        var count: Int,
        @SerializedName("percent")
        var percent: Double
    )

    data class Reactions(
        @SerializedName("6")
        var x6: Int
    )

    data class AddedByStatus(
        @SerializedName("yet")
        var yet: Int,
        @SerializedName("owned")
        var owned: Int,
        @SerializedName("beaten")
        var beaten: Int,
        @SerializedName("dropped")
        var dropped: Int,
        @SerializedName("playing")
        var playing: Int
    )

    data class ParentPlatform(
        @SerializedName("platform")
        var platform: Platform
    ) {
        data class Platform(
            @SerializedName("id")
            var id: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String
        )
    }

    data class Platform(
        @SerializedName("platform")
        var platform: Platform,
        @SerializedName("released_at")
        var releasedAt: String,
        @SerializedName("requirements")
        var requirements: Requirements
    ) {
        data class Platform(
            @SerializedName("id")
            var id: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String,
            @SerializedName("image")
            var image: Any?,
            @SerializedName("year_end")
            var yearEnd: Any?,
            @SerializedName("year_start")
            var yearStart: Any?,
            @SerializedName("games_count")
            var gamesCount: Int,
            @SerializedName("image_background")
            var imageBackground: String
        )

        data class Requirements(
            @SerializedName("minimum")
            var minimum: String
        )
    }

    data class Store(
        @SerializedName("id")
        var id: Int,
        @SerializedName("url")
        var url: String,
        @SerializedName("store")
        var store: Store
    ) {
        data class Store(
            @SerializedName("id")
            var id: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String,
            @SerializedName("domain")
            var domain: String,
            @SerializedName("games_count")
            var gamesCount: Int,
            @SerializedName("image_background")
            var imageBackground: String
        )
    }

    data class Developer(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String,
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("image_background")
        var imageBackground: String
    )

    data class Genre(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String,
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("image_background")
        var imageBackground: String
    )

    data class Tag(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String,
        @SerializedName("language")
        var language: String,
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("image_background")
        var imageBackground: String
    )

    data class Publisher(
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("slug")
        var slug: String,
        @SerializedName("games_count")
        var gamesCount: Int,
        @SerializedName("image_background")
        var imageBackground: String
    )
}