package com.redvelvet.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.redvelvet.ui.theme.spacing

@Composable
fun ItemsSection(
    label: String,
    images: List<String>,
    hasName: Boolean = false,
    name: List<String> = emptyList(),
    hasDateAndCountry: Boolean = false,
    date: List<String> = emptyList(),
    country: List<String> = emptyList(),
    hasCustomList: Boolean = false,
    customListItemComposable: @Composable ((index: Int, image: String) -> Unit)? = null,
    onClickSeeAll: () -> Unit = {},
    onClickItem: (id: Int) -> Unit = {}
) {
    SectionHeader(label, onClickSeeAll = onClickSeeAll)
    VerticalSpacer(space = 8)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.spacing8),
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.spacing16)
    ) {
        itemsIndexed(images) { index, image ->
            Log.i("MovieDetailsScreen", "index: $index, image: $image")
            Log.i("MovieDetailsScreen", "hasCustomList: $hasCustomList")
            if (hasCustomList) {
                customListItemComposable!!(index, image)
            }
            if (!hasCustomList) {
                ItemBasicCard(
                    image = image,
                    hasName = hasName,
                    name = name[index],
                    hasDateAndCountry = hasDateAndCountry,
                    date = if (hasDateAndCountry) date[index] else "",
                    country = if (hasDateAndCountry) country[index] else "",
                    onClick = { onClickItem(index) }
                )
            }
        }

    }
}