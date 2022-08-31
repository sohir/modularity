package com.idbs.hero_interactors

import com.idbs.hero_datasource.network.HeroService
import com.idbs.hero_domain.Hero

data class HeroInteractors(
    val getHeros: GetHeros
    // TODO(Add other hero interactors)
){
    companion object Factory{
        fun build(): HeroInteractors{
            val service = HeroService.build()
            return HeroInteractors(GetHeros(service))
        }
    }

}
