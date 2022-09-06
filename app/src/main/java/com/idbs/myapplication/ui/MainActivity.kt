package com.idbs.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import coil.ImageLoader
import com.idbs.core.DataState
import com.idbs.core.Logger
import com.idbs.core.ProgressBarState
import com.idbs.core.UIComponent
import com.idbs.hero_interactors.HeroInteractors
import com.idbs.myapplication.R
import com.idbs.myapplication.ui.theme.MyApplicationTheme
import com.idbs.ui_herolist.HeroList
import com.idbs.ui_herolist.HeroListState
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    private val state: MutableState<HeroListState> = mutableStateOf(HeroListState())
    private val progressBarState: MutableState<ProgressBarState> = mutableStateOf(ProgressBarState.Idle)
    private lateinit var imageLoader:ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       imageLoader = ImageLoader.Builder(applicationContext)
           .error(R.drawable.error_image)
           .placeholder(R.drawable.white_background)
           .availableMemoryPercentage(.25) //this value from the official doc.
           .crossfade(true)
           .build()
        val getHeros = HeroInteractors.build(
            sqlDriver = AndroidSqliteDriver(
                schema = HeroInteractors.schema,
                context = this,
                name = HeroInteractors.dbName
            )
        ).getHeros
        val logger = Logger("GetHerosTest")
        getHeros.execute().onEach { dataState ->
            when(dataState){
                is DataState.Response -> {
                    when(dataState.uiComponent){
                        is UIComponent.Dialog -> {
                            logger.log((dataState.uiComponent as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.uiComponent as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    state.value = state.value.copy(heros = dataState.data?: listOf())
                }
                is DataState.Loading -> {
                    progressBarState.value = dataState.progressBarState
                }
            }
        }.launchIn(CoroutineScope(IO))
        setContent {
            MyApplicationTheme {
                HeroList(state = state.value,
                imageLoader = imageLoader)
            }
        }
    }
}

/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}*/
