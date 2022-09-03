package com.idbs.ui_herolist

import com.idbs.core.ProgressBarState
import com.idbs.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
val heros:List<Hero> = listOf()
)
