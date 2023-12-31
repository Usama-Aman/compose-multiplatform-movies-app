package org.sam.app.pesentation.home


import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.sam.app.common.NetworkResource
import org.sam.app.domain.model.MoviesModel
import org.sam.app.domain.usecase.NowPlayingMoviesUseCase
import org.sam.app.domain.usecase.PopularMoviesUseCase
import org.sam.app.domain.usecase.TopRatedMoviesUseCase

class HomeScreenViewModel(
    private val nowPlayingMoviesUseCase: NowPlayingMoviesUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
) : ScreenModel {

    data class MoviesState(
        var movies: List<MoviesModel> = listOf(),
        var isLoading: Boolean = false,
        var errorMessage: String = ""
    )

    var nowPlayingState = MutableStateFlow(MoviesState())
        private set

    var popularMoviesState = MutableStateFlow(MoviesState())
        private set

    var topRatedMoviesState = MutableStateFlow(MoviesState())
        private set


    init {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
    }

    private fun getNowPlayingMovies() {
        nowPlayingMoviesUseCase().onEach {
            when (it) {
                is NetworkResource.Success -> {
                    if (it.data != null)
                        nowPlayingState.update { state ->
                            state.copy(
                                movies = it.data,
                                isLoading = false
                            )
                        }
                }

                is NetworkResource.Error -> {
                    nowPlayingState.update { state ->
                        state.copy(
                            errorMessage = it.error ?: "",
                            isLoading = false
                        )
                    }
                }

                is NetworkResource.Loading -> {
                    nowPlayingState.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(screenModelScope)
    }

    private fun getPopularMovies() {
        popularMoviesUseCase().onEach {
            when (it) {
                is NetworkResource.Success -> {
                    if (it.data != null)
                        popularMoviesState.update { state ->
                            state.copy(
                                movies = it.data,
                                isLoading = false
                            )
                        }
                }

                is NetworkResource.Error -> {
                    popularMoviesState.update { state ->
                        state.copy(
                            errorMessage = it.error ?: "",
                            isLoading = false
                        )
                    }
                }

                is NetworkResource.Loading -> {
                    popularMoviesState.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(screenModelScope)
    }

    private fun getTopRatedMovies() {
        topRatedMoviesUseCase().onEach {
            when (it) {
                is NetworkResource.Success -> {
                    if (it.data != null)
                        topRatedMoviesState.update { state ->
                            state.copy(
                                movies = it.data,
                                isLoading = false
                            )
                        }
                }

                is NetworkResource.Error -> {
                    topRatedMoviesState.update { state ->
                        state.copy(
                            errorMessage = it.error ?: "",
                            isLoading = false
                        )
                    }
                }

                is NetworkResource.Loading -> {
                    topRatedMoviesState.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(screenModelScope)
    }


}