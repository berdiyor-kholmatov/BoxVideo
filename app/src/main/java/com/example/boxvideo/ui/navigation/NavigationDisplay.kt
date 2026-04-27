package com.example.boxvideo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.boxvideo.ui.movieDetail.Detail
import com.example.boxvideo.ui.movieDetail.DetailViewModel
import com.example.boxvideo.ui.movieList.MovieList
import com.example.boxvideo.ui.movieList.MovieViewModel

@Composable
fun NavigationRoot(){
    val rootBackStack = rememberNavBackStack(Route.Home)
    NavDisplay(
        backStack = rootBackStack,
        onBack = { rootBackStack.removeLastOrNull() },
        entryProvider = { key ->

            when (key) {
                Route.Home -> NavEntry(key) {
                    val movieList: MovieViewModel = hiltViewModel()
                    val state by movieList.state.collectAsState()
                    MovieList(
                        state,
                        movieList::onEvent,
                        onClick = { rootBackStack.add(Route.Details(it))})
                }

                is Route.Details -> NavEntry(key) {
                    val detailViewModel: DetailViewModel =
                        hiltViewModel<DetailViewModel, DetailViewModel.Factory>(
                            creationCallback = { factory ->
                                factory.create(movieId = key.id)
                            },
                            key = key.id.toString()
                        )
                    val state by detailViewModel.state.collectAsState()
                    Detail(
                        state,
                        detailViewModel::onEvent,
                        onClick = {}
                    )
                }
                else -> throw IllegalArgumentException("Unknown route: $key")
            }
        }
    )


}