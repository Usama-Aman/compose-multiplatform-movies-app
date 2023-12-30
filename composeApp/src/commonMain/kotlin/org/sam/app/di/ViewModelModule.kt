package org.sam.app.di

import org.sam.app.pesentation.movie_detail.MovieDetailViewModel
import org.sam.app.pesentation.home.HomeScreenViewModel
import org.koin.dsl.module

val viewModel = module {

    factory<HomeScreenViewModel> {
        HomeScreenViewModel(
            nowPlayingMoviesUseCase = get(),
            popularMoviesUseCase = get(),
            topRatedMoviesUseCase = get(),
        )
    }

    factory<MovieDetailViewModel> {
        MovieDetailViewModel(
            movieDetailUseCase = get(),
        )
    }
}