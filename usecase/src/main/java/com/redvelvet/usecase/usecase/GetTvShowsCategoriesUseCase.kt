package com.redvelvet.usecase.usecase

import com.redvelvet.entities.movie.Movie
import com.redvelvet.entities.movie.details.MovieDetails
import com.redvelvet.entities.tv.TvShow
import com.redvelvet.usecase.repository.MovieRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetTvShowsCategoriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    private val limit = 10
    suspend operator fun invoke(): List<List<TvShow>> {
        return coroutineScope {
            val popularSeries = async { getPopularSeries() }
            val airingTodayTv = async { getAiringTodayTv() }
            val onTv = async { getOnTv()}
            val topRated = async { getTopRatedTv() }
            listOf(
                popularSeries.await(),
                airingTodayTv.await(),
                onTv.await(),
                topRated.await()
            )
        }
    }

     suspend fun getPopularSeries() = movieRepository.getPopularTv()
     suspend fun getAiringTodayTv() = movieRepository.getAiringTodayTv()
     suspend fun getOnTv() = movieRepository.getOnTheAir()
     suspend fun getTopRatedTv() = movieRepository.getTopRatedTv()
}