package com.example.paralelo3
import com.example.paralelo3.ApiConsts.PATH_MOVIE_ID
import model.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {

    @GET("3/movie/popular")
    fun listPopular(
        @Query("api_key") apiKey: String = ApiConsts.API_KEY,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1

    ): Call<ListaPaginada>

    @GET("3/movie/{$PATH_MOVIE_ID}")
    fun getMovieById(
        @Path(PATH_MOVIE_ID) id: Int,
        @Query("api_key") apiKey: String = ApiConsts.API_KEY,
        @Query("language") language: String = "pt-BR",
    ): Call<MovieModel>
}

object ApiConsts {
    const val API_KEY = "ae3d23ec9fa3fb30df1db734ad6bb1e2"
    const val PATH_MOVIE_ID = "id"
}

class ListaPaginada(val page: Int, val results: List<MovieModel>)