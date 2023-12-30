package org.sam.app.di

import org.koin.dsl.module
import org.sam.app.domain.usecase.MovieDetailUseCase
import org.sam.app.domain.usecase.NowPlayingMoviesUseCase
import org.sam.app.domain.usecase.PopularMoviesUseCase
import org.sam.app.domain.usecase.TopRatedMoviesUseCase

val useCasesModule = module {

    factory {
        NowPlayingMoviesUseCase()
    }

    factory {
        PopularMoviesUseCase()
    }

    factory {
        TopRatedMoviesUseCase()
    }

    factory {
        MovieDetailUseCase()
    }

}