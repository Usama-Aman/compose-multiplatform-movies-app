package org.sam.app.pesentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.sam.app.pesentation.home.components.MoviesListItem
import org.sam.app.pesentation.home.components.HomeTopBar
import org.sam.app.pesentation.movie_detail.MovieDetailScreen

class HomeScreen : Screen {
    @Composable
    override fun Content() {


        val navigator = LocalNavigator.currentOrThrow


        val viewModel: HomeScreenViewModel = getScreenModel<HomeScreenViewModel>()
        val nowPlayingState by viewModel.nowPlayingState.collectAsState()
        val popularMoviesState by viewModel.popularMoviesState.collectAsState()
        val topRatedMoviesState by viewModel.topRatedMoviesState.collectAsState()


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(top = 20.dp)
        ) {

            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                HomeTopBar()

                MoviesListItem(
                    movies = popularMoviesState.movies,
                    title = "Popular",
                    isLoading = popularMoviesState.isLoading,
                    onMovieClicked = {
                        navigator.push(MovieDetailScreen(it))
                    }
                )

                MoviesListItem(
                    movies = nowPlayingState.movies,
                    title = "Now Playing",
                    isLoading = nowPlayingState.isLoading,
                    onMovieClicked = {
                        navigator.push(MovieDetailScreen(it))
                    }
                )

                MoviesListItem(
                    movies = topRatedMoviesState.movies,
                    isLoading = topRatedMoviesState.isLoading,
                    title = "Top Rated",
                    onMovieClicked = {
                        navigator.push(MovieDetailScreen(it))
                    }
                )

            }

        }
    }

}
