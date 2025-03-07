package com.redvelvet.ui.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.redvelvet.ui.R
import com.redvelvet.ui.composable.CategoriesChips
import com.redvelvet.ui.composable.CustomLazyGrid
import com.redvelvet.ui.composable.FlixMovieScaffold
import com.redvelvet.ui.composable.PrimaryTextField
import com.redvelvet.ui.composable.SpacerVertical
import com.redvelvet.ui.theme.BackgroundPrimary
import com.redvelvet.ui.theme.FontAccent
import com.redvelvet.ui.theme.FontPrimary
import com.redvelvet.ui.theme.Typography
import com.redvelvet.ui.theme.color
import com.redvelvet.ui.theme.dimens
import com.redvelvet.ui.theme.spacing
import com.redvelvet.viewmodel.search.SearchUiState
import com.redvelvet.viewmodel.search.SearchViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(BackgroundPrimary, darkIcons = false)

    val state by viewModel.state.collectAsState()
    FlixMovieScaffold(
        isLoading = state.isLoading,
        error = state.error
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.color.backgroundPrimary)
                .fillMaxSize()
        ) {
            SearchField(
                value = state.inputText,
                onTextChange = viewModel::onChangeSearchTextFiled,
                onClickClear = viewModel::onClickClear
            )
            CategoriesChips(
                onChangeCategory = viewModel::onChangeCategory,
                selectedType = state.selectedMediaType,
                categories = state.getCategories,
                title = "Categories"
            )
            SearchContent(state)
        }
    }
}

@Composable
fun SearchContent(state: SearchUiState) {
    if (state.inputText.isEmpty()) {
        EmptyContent()
    } else {
        CustomLazyGrid(searchCardUiStates = state.searchResult.collectAsLazyPagingItems())
    }
}

@Composable
fun EmptyContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.vector_serach),
                contentDescription = stringResource(R.string.search_empty)
            )
            SpacerVertical(32.dp)
            Text(
                text = stringResource(R.string.search_in_movie_screen),
                style = Typography.bodyLarge,
                color = FontPrimary
            )
            SpacerVertical(4.dp)
            Text(
                text = stringResource(R.string.search_for_your_favorite_movies_and_tv_shows_that_you_love),
                style = Typography.displaySmall,
                color = FontAccent,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onTextChange: (String) -> Unit,
    onClickClear: () -> Unit
) {
    PrimaryTextField(
        value = value,
        onTextChange = onTextChange,
        modifier = Modifier
            .focusable(enabled = true)
            .padding(
            top = MaterialTheme.dimens.dimens36,
            start = MaterialTheme.spacing.spacing16,
            end = MaterialTheme.spacing.spacing16
        ),
        leadingIcon = painterResource(id = R.drawable.icon_search),
        placeHolderText = stringResource(id = R.string.search_place_holder),
        trailingIcon = painterResource(R.drawable.icon_cancel),
        onClickTrailingIcon = onClickClear,
        isError = false,
    )
}