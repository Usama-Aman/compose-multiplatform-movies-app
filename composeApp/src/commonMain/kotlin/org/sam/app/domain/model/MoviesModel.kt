package org.sam.app.domain.model

import org.sam.app.common.Constants


data class MoviesModel(
    var adult: Boolean? = null,
    var genreIds: List<Int?>? = null,
    var id: Int? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
){
    val fullPosterPath = "${Constants.IMAGE_URL}/$posterPath"
}