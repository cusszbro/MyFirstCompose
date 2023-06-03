package com.luthfirr.submissioncompose1.data

import com.luthfirr.submissioncompose1.model.FavoriteMovies
import com.luthfirr.submissioncompose1.model.MoviesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MovieRepository {
    private val favoriteMovies = mutableListOf<FavoriteMovies>()

    init {
        if (favoriteMovies.isEmpty()) {
            MoviesData.movies.forEach {
                favoriteMovies.add(FavoriteMovies(it, 0))
            }
        }
    }

    fun getAllMovies(): Flow<List<FavoriteMovies>> {
        return flowOf(favoriteMovies)
    }

    fun getFavoriteMoviesById(movieId: Int?): FavoriteMovies {
        return favoriteMovies.first {
            it.movie.id == movieId
        }
    }

    fun updateFavoriteMovies(movieId: Int?, newCountValue: Int): Flow<Boolean> {
        val index = favoriteMovies.indexOfFirst { it.movie.id == movieId }
        val result = if (index >= 0) {
            val favoriteMovie = favoriteMovies[index]
            favoriteMovies[index] =
                favoriteMovie.copy(movie = favoriteMovie.movie, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedFavoriteMovies(): Flow<List<FavoriteMovies>> {
        return getAllMovies()
            .map { favoriteMoviess ->
                favoriteMoviess.filter { FavoriteMovies ->
                    FavoriteMovies.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository().apply {
                    instance = this
                }
            }
    }
}