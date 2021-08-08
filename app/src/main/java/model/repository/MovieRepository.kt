package model.repository

import com.example.paralelo3.TheMoviesApi
import com.example.paralelo3.ListaPaginada
import kotlinx.coroutines.*
import model.model.MovieModel
import model.repository.MovieRepository.moviesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    val retrofit: Retrofit
    val moviesApi: TheMoviesApi

    init {
        retrofit = Retrofit.Builder() // Rtrofit criada
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org")
            .build()
        moviesApi = retrofit.create(TheMoviesApi::class.java) //API criada
    }

    fun getPopular(callback: (List<MovieModel>) -> Unit, pageMovie: Int = 1) {
        //fazer coisas de forma assincrona
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                val call = moviesApi.listPopular(page = pageMovie) // lista os filmes populares
                call.enqueue(object : Callback<ListaPaginada> { //
                    override fun onResponse(
                        call: Call<ListaPaginada>,
                        response: Response<ListaPaginada>
                    ) {

                        callback(response.body()?.results ?: mutableListOf())

                    }

                    override fun onFailure(call: Call<ListaPaginada>, t: Throwable) {


                    }
                })
            }
        }
    }


    fun getMovie(callback: (MovieModel) -> Unit, id: Int) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.getMovieById(id = id)
                callApi.enqueue(object : Callback<MovieModel> {
                    override fun onResponse(
                        call: Call<MovieModel>,
                        response: Response<MovieModel>
                    ) {
                        response.body()?.let { movie ->
                            callback(movie)
                        }

                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {

                    }
                })
            }
        }
    }
}
