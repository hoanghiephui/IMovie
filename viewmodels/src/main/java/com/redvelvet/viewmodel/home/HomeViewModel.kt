package com.redvelvet.viewmodel.home

import com.redvelvet.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : BaseViewModel<HomeUiState, Unit>(HomeUiState()) {

    init {
        fakeData()
    }

    fun onClickTab(index: Int) {
        _state.update {
            it.copy(selectedTabIndex = index)
        }
    }

    private fun fakeData() {
        _state.update {
            it.copy(
                movieCategories = fakeMoviesCategories(),
                tvShowCategories = fakeTvShowCategories(),
                tabLayoutTitles = listOf("Movies", "TV Shows"),
                selectedTabIndex = 0
            )
        }
    }

    private fun fakeMoviesCategories() =
         listOf(
            fakeMovieCategory("Popular Movies"),
            fakeMovieCategory("Now Playing"),
            fakeMovieCategory("Upcoming"),
            fakeMovieCategory("Top Rated"),
        )

    private fun fakeTvShowCategories() =
        listOf(
            fakeTvShowCategory("Popular TV Shows"),
            fakeTvShowCategory("Now Playing"),
            fakeTvShowCategory("Upcoming"),
            fakeTvShowCategory("Top Rated"),
        )

    private fun fakeMovieCategory(title: String) =
        MovieCategoryUiState(
            title = title,
            movies = fakeMoviesList()
        )

    private fun fakeTvShowCategory(title: String) =
        TvShowCategoryUiState(
            title = title,
            tvShows = fakeTvShowList()
        )

    private fun fakeMoviesList(): List<MovieUiState> {
        return listOf(
            MovieUiState(
                movieImage = "https://www.realmadrid.com/StaticFiles/RealMadridResponsive/images/static/twitter-image.png",
                movieDate = "23/8/2001",
                movieName = "Real Madrid",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://th.bing.com/th/id/R.288a9cdbf8a965fba8a044e1aac9f2eb?rik=DBP9DF9RfFfb%2fw&riu=http%3a%2f%2fwww.barcelonaconnect.com%2fwp-content%2fuploads%2f2016%2f02%2f1389120212768861.jpg&ehk=r3LRNPFmRnje0DlkhtyOzcrbJ%2fRgrAjS1J882gOJj6U%3d&risl=&pid=ImgRaw&r=0",
                movieDate = "23/8/2001",
                movieName = "Barcelona",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://www.realmadrid.com/StaticFiles/RealMadridResponsive/images/static/twitter-image.png",
                movieDate = "23/8/2001",
                movieName = "Real Madrid",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://th.bing.com/th/id/R.288a9cdbf8a965fba8a044e1aac9f2eb?rik=DBP9DF9RfFfb%2fw&riu=http%3a%2f%2fwww.barcelonaconnect.com%2fwp-content%2fuploads%2f2016%2f02%2f1389120212768861.jpg&ehk=r3LRNPFmRnje0DlkhtyOzcrbJ%2fRgrAjS1J882gOJj6U%3d&risl=&pid=ImgRaw&r=0",
                movieDate = "23/8/2001",
                movieName = "Barcelona",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://www.realmadrid.com/StaticFiles/RealMadridResponsive/images/static/twitter-image.png",
                movieDate = "23/8/2001",
                movieName = "Real Madrid",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://th.bing.com/th/id/R.288a9cdbf8a965fba8a044e1aac9f2eb?rik=DBP9DF9RfFfb%2fw&riu=http%3a%2f%2fwww.barcelonaconnect.com%2fwp-content%2fuploads%2f2016%2f02%2f1389120212768861.jpg&ehk=r3LRNPFmRnje0DlkhtyOzcrbJ%2fRgrAjS1J882gOJj6U%3d&risl=&pid=ImgRaw&r=0",
                movieDate = "23/8/2001",
                movieName = "Barcelona",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://www.realmadrid.com/StaticFiles/RealMadridResponsive/images/static/twitter-image.png",
                movieDate = "23/8/2001",
                movieName = "Real Madrid",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://th.bing.com/th/id/R.288a9cdbf8a965fba8a044e1aac9f2eb?rik=DBP9DF9RfFfb%2fw&riu=http%3a%2f%2fwww.barcelonaconnect.com%2fwp-content%2fuploads%2f2016%2f02%2f1389120212768861.jpg&ehk=r3LRNPFmRnje0DlkhtyOzcrbJ%2fRgrAjS1J882gOJj6U%3d&risl=&pid=ImgRaw&r=0",
                movieDate = "23/8/2001",
                movieName = "Barcelona",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://www.realmadrid.com/StaticFiles/RealMadridResponsive/images/static/twitter-image.png",
                movieDate = "23/8/2001",
                movieName = "Real Madrid",
                countryOfMovie = "Espania"
            ),
            MovieUiState(
                movieImage = "https://th.bing.com/th/id/R.288a9cdbf8a965fba8a044e1aac9f2eb?rik=DBP9DF9RfFfb%2fw&riu=http%3a%2f%2fwww.barcelonaconnect.com%2fwp-content%2fuploads%2f2016%2f02%2f1389120212768861.jpg&ehk=r3LRNPFmRnje0DlkhtyOzcrbJ%2fRgrAjS1J882gOJj6U%3d&risl=&pid=ImgRaw&r=0",
                movieDate = "23/8/2001",
                movieName = "Barcelona",
                countryOfMovie = "Espania"
            ),
        )
    }

    private fun fakeTvShowList() =
        listOf(
            TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            ),
            TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            ),
            TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            ),
            TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            ), TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            ),
            TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            ),
            TvShowUiState(
                seriesImage = "https://cdn1.edgedatg.com/aws/v2/abc/TheGoodDoctor/showimages/d970024e1e411bee6f4fef77b3ee6040/1200x627-Q80_d970024e1e411bee6f4fef77b3ee6040.jpg",
                seriesDate = "25/9/2017",
                seriesName = "The good doctor"
            )
        )


}