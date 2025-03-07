package com.redvelvet.viewmodel.movieDetails

import androidx.lifecycle.SavedStateHandle
import com.redvelvet.entities.movie.details.MovieFullDetails
import com.redvelvet.usecase.usecase.detailsActions.AddMovieRatingUseCase
import com.redvelvet.usecase.usecase.detailsActions.DeleteMovieRatingUseCase
import com.redvelvet.usecase.usecase.detailsActions.ToggleMediaInFavoritesUsecase
import com.redvelvet.usecase.usecase.detailsActions.ToggleMediaInWatchListUsecase
import com.redvelvet.usecase.usecase.movie.GetMovieFullDetailsUseCase
import com.redvelvet.viewmodel.base.BaseViewModel
import com.redvelvet.viewmodel.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieFullDetails: GetMovieFullDetailsUseCase,
    private val addMovieRating: AddMovieRatingUseCase,
    private val deleteMovieRating: DeleteMovieRatingUseCase,
    private val toggleMediaInFavorites: ToggleMediaInFavoritesUsecase,
    private val toggleMediaInWatchList: ToggleMediaInWatchListUsecase,


    ) : BaseViewModel<MovieDetailsScreenUiState, MovieDetailsUiEffect>(MovieDetailsScreenUiState()),
    MovieDetailsInteraction {

    private val args: MovieDetailsArgs = MovieDetailsArgs(savedStateHandle)

    init {
        getData()
    }


    private fun getData() {
        tryToExecute(
            execute = { getMovieFullDetails(args.id.toInt()) },
            onSuccessWithData = ::onSuccess,
            onError = ::onError,
        )
    }

    private fun onSuccess(movieAllDetails: MovieFullDetails) {
        _state.update {
            MovieDetailsScreenUiState(
                data = movieAllDetails.toMovieFullDetailsScreenUiState(),
                isLoading = false,
                error = null,
            )
        }
    }

    private fun onError(error: ErrorUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                error = error,
            )
        }
    }

    override fun onClickFavorite(movieId: Int, mediaType: String) {
        _state.update {
            it.copy(
                favoriteActionState = FavoriteActionUiState(
                    isLoading = true
                )
            )
        }
        tryToExecute(
            execute = {
                toggleMediaInFavorites(
                    mediaType = "movie",
                    mediaId = movieId,
                    isSavedInFavorites = true
                )
            },
            onSuccessWithData = ::onFavoriteSuccess,
            onError = ::onFavoriteError,
        )
    }

    private fun onFavoriteSuccess(response: String) {
        _state.update {
            it.copy(
                favoriteActionState = FavoriteActionUiState(
                    isLoading = false,
                    data = response
                )
            )
        }
    }

    private fun onFavoriteError(error: ErrorUiState) {
        _state.update {
            it.copy(
                favoriteActionState = FavoriteActionUiState(
                    isLoading = false,
                    error = error
                )
            )
        }
    }

    override fun onClickSave(movieId: Int) {
        _state.update {
            it.copy(
                addToWatchListActionUiState = AddToWatchListActionUiState(
                    isLoading = true
                )
            )
        }
        tryToExecute(
            execute = {
                toggleMediaInWatchList(
                    mediaType = "movie",
                    mediaId = movieId,
                    isSavedInWatchList = true
                )
            },
            onSuccessWithData = ::onSaveSuccess,
            onError = ::onSaveError,
        )
    }

    private fun onSaveSuccess(response: String) {
        _state.update {
            it.copy(
                addToWatchListActionUiState = AddToWatchListActionUiState(
                    isLoading = false,
                    data = response
                )
            )
        }
    }

    private fun onSaveError(error: ErrorUiState) {
        _state.update {
            it.copy(
                addToWatchListActionUiState = AddToWatchListActionUiState(
                    isLoading = false,
                    error = error
                )
            )
        }
    }

    override fun onClickRateMovie(movieId: Int, rate: Double) {
        _state.update {
            it.copy(
                rateActionUiState = RateActionUiState(
                    isLoading = true
                )
            )
        }
//        deleteMovieRating(
//            movieId = movieId,
//        )
        tryToExecute(
            execute = {
                addMovieRating(
                    movieRating = rate,
                    movieId = movieId,
                )
            },
            onSuccessWithData = ::onRateSuccess,
            onError = ::onRateError,
        )

    }

    private fun onRateSuccess(response: String) {
        _state.update {
            it.copy(
                rateActionUiState = RateActionUiState(
                    isLoading = false,
                    data = response
                )
            )
        }
    }

    private fun onRateError(error: ErrorUiState) {
        _state.update {
            it.copy(
                rateActionUiState = RateActionUiState(
                    isLoading = false,
                    error = error
                )
            )
        }
    }


    override fun onClickPlayTrailer(movieUrl: String) {

    }

    override fun onClickGenre(genre: String) {

    }


    override fun onClickTopCastSeeAll(movieId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToTopCastSeeAllScreen(movieId))
    }

    override fun onClickCast(castId: Int) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToTopCastDetailsScreen(castId.toString()))
    }

    override fun onClickSimilarMoviesSeeAll(movieId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToSimilarMoviesSeeAllScreen(movieId))
    }

    override fun onClickMovie(movieId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToMovieDetailsScreen(movieId))
    }

    override fun onClickMovieImagesSeeAll(movieId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToMovieImagesSeeAllScreen(movieId))
    }

    override fun onClickPreviewImage(movieImageId: String) {

    }

    override fun onClickReviewsSeeAll(movieId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToReviewSeeAllScreen(movieId))
    }

    override fun onClickReview(reviewId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToReviewDetailsScreen(reviewId))
    }

    override fun onClickRecommendationsMoviesSeeAll(movieId: String) {
        sendUiEffect(MovieDetailsUiEffect.NavigateToRecommendedMoviesSeeAllScreen(movieId))
    }

    fun refresh() {
        getData()
    }
}