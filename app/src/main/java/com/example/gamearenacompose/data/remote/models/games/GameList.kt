package com.example.gamearenacompose.data.remote.models.games


import com.google.gson.annotations.SerializedName

data class GameList(
    @SerializedName("count")
    var count: Int,
    @SerializedName("next")
    var next: String?,
    @SerializedName("previous")
    var previous: String?,
    @SerializedName("results")
    var results: List<Result>,
    @SerializedName("seo_title")
    var seoTitle: String,
    @SerializedName("seo_description")
    var seoDescription: String,
    @SerializedName("seo_keywords")
    var seoKeywords: String,
    @SerializedName("seo_h1")
    var seoH1: String,
    @SerializedName("noindex")
    var noindex: Boolean,
    @SerializedName("nofollow")
    var nofollow: Boolean,
    @SerializedName("description")
    var description: String,
    @SerializedName("filters")
    var filters: Filters,
    @SerializedName("nofollow_collections")
    var nofollowCollections: List<String>
) {
    data class Result(
        @SerializedName("id")
        var id: Int,
        @SerializedName("slug")
        var slug: String,
        @SerializedName("name")
        var name: String,
        @SerializedName("released")
        var released: String,
        @SerializedName("tba")
        var tba: Boolean,
        @SerializedName("background_image")
        var backgroundImage: String,
        @SerializedName("rating")
        var rating: Double,
        @SerializedName("rating_top")
        var ratingTop: Int,
        @SerializedName("ratings")
        var ratings: List<Rating>,
        @SerializedName("ratings_count")
        var ratingsCount: Int,
        @SerializedName("reviews_text_count")
        var reviewsTextCount: Int,
        @SerializedName("added")
        var added: Int,
        @SerializedName("added_by_status")
        var addedByStatus: AddedByStatus,
        @SerializedName("metacritic")
        var metacritic: Int,
        @SerializedName("playtime")
        var playtime: Int,
        @SerializedName("suggestions_count")
        var suggestionsCount: Int,
        @SerializedName("updated")
        var updated: String,
        @SerializedName("user_game")
        var userGame: Any?,
        @SerializedName("reviews_count")
        var reviewsCount: Int,
        @SerializedName("saturated_color")
        var saturatedColor: String,
        @SerializedName("dominant_color")
        var dominantColor: String,
        @SerializedName("platforms")
        var platforms: List<Platform>,
        @SerializedName("parent_platforms")
        var parentPlatforms: List<ParentPlatform>,
        @SerializedName("genres")
        var genres: List<Genre>,
        @SerializedName("stores")
        var stores: List<Store>,
        @SerializedName("clip")
        var clip: Any?,
        @SerializedName("tags")
        var tags: List<Tag>,
        @SerializedName("esrb_rating")
        var esrbRating: EsrbRating,
        @SerializedName("short_screenshots")
        var shortScreenshots: List<ShortScreenshot>
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

        data class AddedByStatus(
            @SerializedName("yet")
            var yet: Int,
            @SerializedName("owned")
            var owned: Int,
            @SerializedName("beaten")
            var beaten: Int,
            @SerializedName("toplay")
            var toplay: Int,
            @SerializedName("dropped")
            var dropped: Int,
            @SerializedName("playing")
            var playing: Int
        )

        data class Platform(
            @SerializedName("platform")
            var platform: Platform,
            @SerializedName("released_at")
            var releasedAt: String?,
            @SerializedName("requirements_en")
            var requirementsEn: Any?,
            @SerializedName("requirements_ru")
            var requirementsRu: Any?
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
                var yearStart: Int?,
                @SerializedName("games_count")
                var gamesCount: Int,
                @SerializedName("image_background")
                var imageBackground: String
            )
        }

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

        data class Store(
            @SerializedName("id")
            var id: Int,
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

        data class EsrbRating(
            @SerializedName("id")
            var id: Int,
            @SerializedName("name")
            var name: String,
            @SerializedName("slug")
            var slug: String
        )

        data class ShortScreenshot(
            @SerializedName("id")
            var id: Int,
            @SerializedName("image")
            var image: String
        )
    }

    data class Filters(
        @SerializedName("years")
        var years: List<Year>
    ) {
        data class Year(
            @SerializedName("from")
            var from: Int,
            @SerializedName("to")
            var to: Int,
            @SerializedName("filter")
            var filter: String,
            @SerializedName("decade")
            var decade: Int,
            @SerializedName("years")
            var years: List<Year>,
            @SerializedName("nofollow")
            var nofollow: Boolean,
            @SerializedName("count")
            var count: Int
        ) {
            data class Year(
                @SerializedName("year")
                var year: Int,
                @SerializedName("count")
                var count: Int,
                @SerializedName("nofollow")
                var nofollow: Boolean
            )
        }
    }
}