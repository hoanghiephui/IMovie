package com.redvelvet.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.redvelvet.ui.theme.dimens
import com.redvelvet.ui.theme.spacing
import com.redvelvet.viewmodel.search.SearchCardUiState
import com.redvelvet.viewmodel.utils.getFullImage
import com.redvelvet.viewmodel.utils.isPerson

@Composable
fun CustomLazyGrid(
    searchCardUiStates: LazyPagingItems<SearchCardUiState>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(
            top = MaterialTheme.spacing.spacing16,
            bottom = MaterialTheme.spacing.spacing80,
            start = MaterialTheme.spacing.spacing16,
            end = MaterialTheme.spacing.spacing16
        ),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.spacing8,
            Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.spacing16,
            Alignment.CenterVertically
        )
    ) {
        items(searchCardUiStates.itemCount) {
            val mediaUiState = searchCardUiStates[it]
            ItemBasicCard(
                imagePainter = rememberAsyncImagePainter(model = mediaUiState!!.getFullImage()),
                modifier = Modifier
                    .width(MaterialTheme.dimens.dimens104)
                    .height(MaterialTheme.dimens.dimens176),
                hasName = true,
                name = mediaUiState.name,
                hasDateAndCountry = !mediaUiState.isPerson(),
                date = mediaUiState.releaseDate,
                country = mediaUiState.country
            )
        }
        if (searchCardUiStates.loadState.append is LoadState.Loading) {
        item(
            span = { GridItemSpan(3) }
        ) {
            LoadingPage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
            )
        }
    }
    }
}