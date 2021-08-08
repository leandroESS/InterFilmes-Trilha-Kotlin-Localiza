package com.example.paralelo3.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.paralelo3.MovieDetailsAdapter
import com.example.paralelo3.view.activity.viewModel.MovieListViewModel
import com.example.paralelo3.databinding.ActivityListaBinding
import model.repository.MovieRepository


class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
        setupList()
    }

    private fun initLayout() {
        binding = ActivityListaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupList() {
        val adapter = MovieDetailsAdapter({ id ->
            openDetailsActivity(id)
        })
        binding.movie.adapter = adapter
        MovieRepository.getPopular({ pageOne ->
            adapter.addItemList(pageOne)
            MovieRepository.getPopular({ pageTwo ->
                adapter.addItemList(pageTwo)
            }, 2)
        })

    }

    private fun openDetailsActivity(id: Int) {
        val intent = Intent(this, DetalhesActivity::class.java)
        intent.putExtra("chaveDoId", id)

        startActivity(intent)
    }

}