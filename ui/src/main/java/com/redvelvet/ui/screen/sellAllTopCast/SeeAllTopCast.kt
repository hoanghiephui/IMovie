package com.redvelvet.ui.screen.sellAllTopCast

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.redvelvet.ui.LocalNavController
import com.redvelvet.ui.composable.ItemBasicCard
import com.redvelvet.ui.composable.MovieScaffold
import com.redvelvet.ui.theme.spacing
import com.redvelvet.viewmodel.movie_details_seeall.SeeAllTopCastUiState
import com.redvelvet.viewmodel.movie_details_seeall.SeeAllTopCastViewModel

@Composable
fun SeeAllTopCastScreen(
    viewModel: SeeAllTopCastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    SeeAllTopCastContent(state)
}

@Composable
private fun SeeAllTopCastContent(state: SeeAllTopCastUiState) {

    MovieScaffold(
        title = state.title,
        isLoading = state.isLoading,
        error = state.error,
        hasTopBar = true
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = MaterialTheme.spacing.spacing64)
        ) {
            LazyVerticalGrid(
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.spacing16,
                    vertical = MaterialTheme.spacing.spacing64
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
                items(state.topCast.size) {
                    val media = state.topCast[it]
                    ItemBasicCard(
                        imagePainter = rememberAsyncImagePainter(model = media.castImage),
                        hasName = true,
                        name = media.castName,
                        modifier = Modifier
                            .width(104.dp)
                            .height(154.dp)
                    )
                }
            }
        }
    }
}