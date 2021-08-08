package com.example.paralelo3.view.activity.viewModel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.model.MovieModel
import model.repository.MovieRepository

class MovieListViewModel : ViewModel() {
    private val liveDataList: MutableLiveData<List<MovieModel>?> = MutableLiveData(null)

    fun getPopular() {
//        MovieRepository.getPopular {
//            liveDataList.postValue(it)
//        }
    }

    fun getLiveDataList(): LiveData<List<MovieModel>?>{
        return liveDataList
    }
}
