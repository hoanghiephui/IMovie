package com.redvelvet.repository.mapper

import com.redvelvet.entities.actor.Actor
import com.redvelvet.entities.movie.Movie
import com.redvelvet.entities.search.SearchResult
import com.redvelvet.entities.tv.TvShow
import com.redvelvet.repository.dto.movie.MovieDto
import com.redvelvet.repository.dto.person.PersonDto
import com.redvelvet.repository.dto.search.MultiSearchResultDto
import com.redvelvet.repository.dto.tvShow.TvShowDto

fun MultiSearchResultDto.toSearchResult(): SearchResult {
    return SearchResult(
        id = this.id ?: 0,
        mediaType = this.mediaType ?: "",
        name = this.name ?: "",
        language = this.originalLanguage ?: "",
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "",
        firstAirDate = this.firstAirDate ?: "",
        originalName = this.title ?: "",
        profilePath = this.profilePath ?: ""
    )
}

fun PersonDto.toActor() = Actor(
    id = id ?: 0,
    name = name.orEmpty(),
    profileImageUrl = profilePath.orEmpty(),
    birthday = birthday.orEmpty(),
    country = placeOfBirth.orEmpty()
)

fun MovieDto.toMovie() = Movie(
    id = id ?: 0,
    name = originalTitle.orEmpty(),
    image = posterPath.orEmpty(),
    country = originalLanguage.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
)

fun TvShowDto.toTvShow() = TvShow(
    id = id ?: 0,
    name = name.orEmpty(),
    imageUrl = posterPath.orEmpty(),
    country = originalLanguage.orEmpty(),
    releaseDate = firstAirDate.orEmpty(),
)