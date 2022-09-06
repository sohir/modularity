package com.idbs.ui_herolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.idbs.core.ProgressBarState
import com.idbs.ui_herolist.components.HeroListItem
import com.idbs.ui_herolist.ui.HeroListState

@Composable
fun HeroList(
    state: HeroListState,
    imageLoader: ImageLoader
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn{
            items(state.heros){ hero ->
                //itemContent - the content displayed by a single item (HeroListItem == list_item.xml)
                HeroListItem(hero = hero,
                onSelectHero = {

                },imageLoader= imageLoader
                )

            }
        }
        if(state.progressBarState is ProgressBarState.Loading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}