package com.idbs.hero_interactors

import com.idbs.core.DataState
import com.idbs.core.ProgressBarState
import com.idbs.core.UIComponent
import com.idbs.hero_datasource.network.HeroService
import com.idbs.hero_domain.Hero
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//Describe what this useCase does
//in this use-case we need to get the data from the server insert into the cache

class GetHeros(private val service:HeroService,
    //TODO(Add caching
    ) {
  fun execute():Flow<DataState<List<Hero>>> = flow {
      try {
          emit(DataState.Loading(progressBarState =  ProgressBarState.Loading))
      delay(1000)
          val hero :List<Hero> = try {
              //catch network exception
              service.getHeroStats()
          }catch (e:Exception)
          {
              e.printStackTrace() //log to crashlytics?
              emit(DataState.Response<List<Hero>>(
                  uiComponent = UIComponent.Dialog(
                      title = "Network Data Error",
                      description = e.message ?: "UnownError"
                  )
              ))
              listOf()
          }
          emit(DataState.Data(hero))
      }catch (e:Exception){
          e.printStackTrace()
          emit(DataState.Response<List<Hero>>(
              uiComponent = UIComponent.Dialog(
                  title = "Error",
                  description = e.message ?: "UnKnown Error"
              )
          ))
      }finally {
          emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
      }
  }
}