package com.example.moviebuster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MovieListActivity : AppCompatActivity() {

    // Parallel arrays for movie data
    private val movieTitles = arrayOf("The Godfather", "The Dark Knight", "Pulp Fiction")
    private val movieDirectors = arrayOf("Francis Ford Coppola", "Christopher Nolan", "Quentin Tarantino")
    private val userRatings = arrayOf(5.0, 5.0, 4.0)
    private val movieComments = arrayOf(
        "A masterpiece of cinema",
        "Epic superhero film with iconic Joker performance",
        "Quirky and captivating"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        setupMovieList()
    }

    private fun setupMovieList() {
        val listViewMovies: ListView = findViewById(R.id.listViewMovies)
        val btnBack: Button = findViewById(R.id.btnBack)

        // Create movie list using loop
        val movieList = ArrayList<String>()
        for (i in movieTitles.indices) {
            val movieInfo = """
                ${movieTitles[i]}
                Director: ${movieDirectors[i]}
                Rating: ${userRatings[i]}/5
                ${movieComments[i]}
            """.trimIndent()
            movieList.add(movieInfo)
        }

        // Display movies in the list
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movieList)
        listViewMovies.adapter = adapter

        // Back button to return to main screen
        btnBack.setOnClickListener {
            finish()
        }

        // When user taps a movie, show details
        listViewMovies.setOnItemClickListener { parent, view, position, id ->
            showMovieDetails(position)
        }
    }

    private fun showMovieDetails(position: Int) {
        val title = movieTitles[position]
        val director = movieDirectors[position]
        val rating = userRatings[position]
        val comment = movieComments[position]

        val details = """
            Movie: $title
            
            Director: $director
            Rating: $rating/5
            
            Comment: $comment
        """.trimIndent()

        // Show popup with movie details
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Movie Details")
            .setMessage(details)
            .setPositiveButton("OK") { dialog, which ->
                // User clicked OK
            }
            .show()
    }
}