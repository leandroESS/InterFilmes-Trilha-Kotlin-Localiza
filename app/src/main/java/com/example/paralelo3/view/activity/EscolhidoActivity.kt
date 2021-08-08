package com.example.paralelo2.view
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.paralelo3.R
import com.example.paralelo3.databinding.EscolhidoBinding
import model.repository.MovieRepository

class EscolhidoActivity : AppCompatActivity() {
    private lateinit var binding: EscolhidoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idMovie = intent.getIntExtra("chaveDoId", -1)
        binding = EscolhidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner1,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        MovieRepository.getMovie({
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(binding.poster)

        }, idMovie)


    }
}