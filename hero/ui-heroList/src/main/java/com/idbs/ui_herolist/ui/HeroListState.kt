package com.idbs.ui_herolist.ui

import com.idbs.core.ProgressBarState
import com.idbs.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
val heros:List<Hero> = listOf()
)
