package com.redvelvet.viewmodel.movie_details_seeall

import androidx.lifecycle.SavedStateHandle

class TopCastArgs(savedStateHandle: SavedStateHandle) {
    val id: String = savedStateHandle[ID] ?: "0"

    companion object{
        const val ID = "id"
    }
}