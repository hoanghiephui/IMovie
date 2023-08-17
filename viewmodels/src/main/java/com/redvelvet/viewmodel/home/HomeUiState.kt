package com.redvelvet.viewmodel.home

import com.redvelvet.viewmodel.base.BaseUiState

data class HomeUiState(
    val movieCategories: List<MovieCategoryUiState> = emptyList(),
    val tvShowCategories: List<TvShowCategoryUiState> = emptyList(),
    val tabLayoutTitles: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val isError: String? = null,
) : BaseUiState

data class MovieCategoryUiState(
    val title: String = "",
    val movies: List<MovieUiState>,
    val hasMore: Boolean = false
)

data class TvShowCategoryUiState(
    val title: String = "",
    val tvShows: List<TvShowUiState>,
    val hasMore: Boolean = false
)

data class MovieUiState(
    val id: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val date: String = "",
    val country: String = "",
)

data class TvShowUiState(
    val seriesImage: String = "",
    val seriesName: String = "",
    val seriesDate: String = "",
    val seriesCountry: String = ""
)
