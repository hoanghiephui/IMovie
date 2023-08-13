package com.redvelvet.viewmodel.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.redvelvet.entities.actor.Actor
import com.redvelvet.entities.movie.Movie
import com.redvelvet.entities.search.SearchResult
import com.redvelvet.entities.tv.TvShow
import com.redvelvet.usecase.usecase.search.GetAllSearchResultUseCase
import com.redvelvet.usecase.usecase.search.GetSearchMoviesUseCase
import com.redvelvet.usecase.usecase.search.GetSearchPeopleUseCase
import com.redvelvet.usecase.usecase.search.GetSearchTvShowUseCase
import com.redvelvet.viewmodel.base.BaseViewModel
import com.redvelvet.viewmodel.base.ErrorUiState
import com.redvelvet.viewmodel.search.uiStateMappers.*
import com.redvelvet.viewmodel.utils.SearchMedia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllSearchResultUseCase: GetAllSearchResultUseCase,
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase,
    private val getSearchPeopleUseCase: GetSearchPeopleUseCase,
    private val getSearchTvShowUseCase: GetSearchTvShowUseCase,
) : BaseViewModel<SearchUiState, SearchUiEffect>(SearchUiState()), SearchListener {

    private val _queryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            _queryFlow
                .debounce(1000)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .collect { query ->
                    onGetData(query)
                }
        }
    }

    fun onChangeSearchTextFiled(query: String) {
        _state.update { it.copy(inputText = query) }
        _queryFlow.value = query
    }

    private fun onGetData(query: String) {
        when (_state.value.selectedMediaType) {
            SearchMedia.ALL -> fetchResults {
                getAllSearchResultUseCase(query)
            }

            SearchMedia.MOVIE -> fetchMoviesResults {
                getSearchMoviesUseCase(query)
            }

            SearchMedia.PEOPLE -> fetchPeopleResults {
                getSearchPeopleUseCase(query)
            }

            SearchMedia.TV -> fetchTvShowResults {
                getSearchTvShowUseCase(query)
            }
        }
    }

    private fun fetchResults(fetchFunction: suspend () -> Flow<PagingData<SearchResult>>) {
        tryToExecutePaging(
            call = fetchFunction,
            onSuccess = { pagingData ->
                _state.update {
                    it.copy(
                        searchResult = flowOf(pagingData.map { it.toSearchCardUiState() }),
                        isLoading = false, isEmpty = false
                    )
                }
            },
            onError = ::onError
        )
    }

    private fun fetchMoviesResults(fetchFunction: suspend () -> Flow<PagingData<Movie>>) {
        tryToExecutePaging(
            call = fetchFunction,
            onSuccess = { pagingData ->
                _state.update {
                    it.copy(
                        searchResult = flowOf(pagingData.map { it.toSearchCardUiState() }),
                        isLoading = false, isEmpty = false
                    )
                }
            },
            onError = ::onError
        )
    }

    private fun fetchPeopleResults(fetchFunction: suspend () -> Flow<PagingData<Actor>>) {
        tryToExecutePaging(
            call = fetchFunction,
            onSuccess = { pagingData ->
                _state.update {
                    it.copy(
                        searchResult = flowOf(pagingData.map { it.toSearchCardUiState() }),
                        isLoading = false, isEmpty = false
                    )
                }
            },
            onError = ::onError
        )
    }

    private fun fetchTvShowResults(fetchFunction: suspend () -> Flow<PagingData<TvShow>>) {
        tryToExecutePaging(
            call = fetchFunction,
            onSuccess = { pagingData ->
                _state.update {
                    it.copy(
                        searchResult = flowOf(pagingData.map { it.toSearchCardUiState() }),
                        isLoading = false, isEmpty = false
                    )
                }
            },
            onError = ::onError
        )
    }

    private fun onError(errorUiState: ErrorUiState) {
        _state.update { it.copy(error = errorUiState, isLoading = false) }
    }

    fun onChangeCategory(type: SearchMedia) {
        _state.update { it.copy(selectedMediaType = type) }
        onGetData(_state.value.inputText)
    }

    /// region listeners
    override fun onClickClear() {
        _state.value = SearchUiState()
    }

    override fun onClickAllChip() {
        onChangeCategory(SearchMedia.ALL)
    }

    override fun onClickMovieChip() {
        onChangeCategory(SearchMedia.MOVIE)
    }

    override fun onClickTvShowChip() {
        onChangeCategory(SearchMedia.TV)
    }

    override fun onClickActorChip() {
        onChangeCategory(SearchMedia.PEOPLE)
    }

    override fun onClickItem(id: Int) {
        TODO("Not yet implemented")
    }
    ///endregion
}