package org.sam.app.pesentation.movie_detail

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.sam.app.common.NetworkResource
import org.sam.app.domain.model.MovieDetail
import org.sam.app.domain.usecase.MovieDetailUseCase

class MovieDetailViewModel(
    private val movieDetailUseCase: MovieDetailUseCase
) : ScreenModel {

    data class MovieDetailUIState(
        var isLoading: Boolean = false,
        var movieDetail: MovieDetail = MovieDetail(),
        var errorMessage: String = ""
    )

    var movieDetailState = MutableStateFlow(MovieDetailUIState())
        private set

    fun getMovieDetail(movieId: Int) {
        movieDetailUseCase(movieId = movieId).onEach {
            when (it) {
                is NetworkResource.Success -> {
                    if (it.data != null)
                        movieDetailState.update { state ->
                            state.copy(
                                movieDetail = it.data,
                                isLoading = false
                            )
                        }
                }

                is NetworkResource.Error -> {
                    movieDetailState.update { state ->
                        state.copy(
                            errorMessage = it.error ?: "",
                            isLoading = false
                        )
                    }
                }

                is NetworkResource.Loading -> {
                    movieDetailState.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(screenModelScope)
    }
}