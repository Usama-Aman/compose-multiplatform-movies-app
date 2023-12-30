package org.sam.app.pesentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sam.app.domain.model.MoviesModel

@Composable
fun MoviesListItem(
    modifier: Modifier = Modifier,
    movies: List<MoviesModel>,
    title: String,
    isLoading: Boolean,
    onMovieClicked: (movieId: Int) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = Color.White
                ),
                modifier = Modifier.padding(20.dp)
            )

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    CircularProgressIndicator(
                        color = Color.Red
                    )
                }

            } else
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(movies.size) { index ->
                        println("Movies : $index")
                        MovieItem(movie = movies[index]) {
                            onMovieClicked(it)
                        }
                    }
                }

        }
    }
}

@Composable
fun MovieItem(movie: MoviesModel, onMovieClicked: (id: Int) -> Unit) {
//    AsyncImage(
//        modifier = Modifier
//            .height(200.dp)
//            .width(150.dp)
//            .clip(RoundedCornerShape(8.dp))
//            .clickable {
//                onMovieClicked(movie.id ?: -1)
//            },
//        model = movie.fullPosterPath,
//        contentScale = ContentScale.Crop,
//        contentDescription = null,
//    )
}

//
//@Preview
//@Composable
//fun MoviesListItemPreview() {
//    MoviesListItem(
//        movies = listOf(), title = "NowPlaying",
//        isLoading = false,
//        onMovieClicked = { }
//    )
//}