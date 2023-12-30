package org.sam.app.domain.usecase

import org.sam.app.domain.repository.MoviesRepository

class MovieDetailUseCase (
    private val moviesRepository: MoviesRepository
) {

//    operator fun invoke(movieId: Int): Flow<NetworkResource<MovieDetail>> = flow {
//        emit(NetworkResource.Loading())
//
//        try {
//
//            val movies = moviesRepository.getMovieDetail(movieId).toMovieDetail()
//            emit(NetworkResource.Success(movies))
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(NetworkResource.Error(e.localizedMessage))
//        }
//    }.flowOn(Dispatchers.IO)
//        .catch {
//            emit(NetworkResource.Error(it.localizedMessage))
//        }
}