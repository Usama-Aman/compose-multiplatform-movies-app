package org.sam.app.domain.usecase

import org.sam.app.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.sam.app.common.NetworkResource
import org.sam.app.data.mapper.toMovies
import org.sam.app.domain.repository.MoviesRepository

class TopRatedMoviesUseCase() : KoinComponent {

    private val moviesRepository: MoviesRepository by inject()

    operator fun invoke(): Flow<NetworkResource<List<MoviesModel>>> = flow {
        emit(NetworkResource.Loading())

        try {

            val movies = moviesRepository.getTopRatedMovies().results.map { it.toMovies() }
            emit(NetworkResource.Success(movies))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResource.Error(e.message))
        }
    }.catch {
        emit(NetworkResource.Error(it.message))
    }
}