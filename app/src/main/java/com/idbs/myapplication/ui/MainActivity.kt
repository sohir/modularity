package com.idbs.myapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import com.idbs.core.DataState
import com.idbs.core.Logger
import com.idbs.core.ProgressBarState
import com.idbs.core.UIComponent
import com.idbs.hero_interactors.HeroInteractors
import com.idbs.myapplication.R
import com.idbs.myapplication.ui.theme.MyApplicationTheme
import com.idbs.ui_herolist.HeroList
import com.idbs.ui_herolist.ui.HeroListState
import com.idbs.ui_herolist.ui.HeroListViewModel
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var imageLoader:ImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       imageLoader = ImageLoader.Builder(applicationContext)
           .error(R.drawable.error_image)
           .placeholder(R.drawable.white_background)
           .availableMemoryPercentage(.25) //this value from the official doc.
           .crossfade(true)
           .build()


        setContent {
            MyApplicationTheme {
                val viewModel :HeroListViewModel = hiltViewModel()
                HeroList(state = viewModel.state.value,
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
