package com.luthfirr.submissioncompose1.di

import com.luthfirr.submissioncompose1.data.MovieRepository

object Injection {
    fun provideRepository(): MovieRepository {
        return MovieRepository.getInstance()
    }
}