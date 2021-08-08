package model.model

data class MovieModel (
    val title: String,
    val id: Int,
    val poster_path: String,
    val overview: String,
    val backdrop_path: String,
    val genres: List<GenreModel>?,
    val adult: Boolean

)