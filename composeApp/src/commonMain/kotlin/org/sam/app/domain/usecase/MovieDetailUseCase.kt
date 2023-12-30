package org.sam.app.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.sam.app.common.NetworkResource
import org.sam.app.data.mapper.toMovieDetail
import org.sam.app.domain.model.MovieDetail
import org.sam.app.domain.repository.MoviesRepository

class MovieDetailUseCase : KoinComponent {

    private val moviesRepository: MoviesRepository by inject()
    operator fun invoke(movieId: Int): Flow<NetworkResource<MovieDetail>> = flow {
        emit(NetworkResource.Loading())

        try {

            val movies = moviesRepository.getMovieDetail(movieId).toMovieDetail()
            emit(NetworkResource.Success(movies))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResource.Error(e.message))
        }
    }.catch {
        emit(NetworkResource.Error(it.message))
    }
}