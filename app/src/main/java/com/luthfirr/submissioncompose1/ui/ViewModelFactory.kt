package com.luthfirr.submissioncompose1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luthfirr.submissioncompose1.data.MovieRepository
import com.luthfirr.submissioncompose1.ui.screen.detail.DetailViewModel
import com.luthfirr.submissioncompose1.ui.screen.favorite.FavoriteViewModel
import com.luthfirr.submissioncompose1.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}