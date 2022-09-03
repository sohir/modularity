package com.idbs.hero_interactors

import com.idbs.hero_datasource.cache.HeroCache
import com.idbs.hero_datasource.network.HeroService
import com.idbs.hero_domain.Hero
import com.squareup.sqldelight.db.SqlDriver

data class HeroInteractors(
    val getHeros: GetHeros
    // TODO(Add other hero interactors)
){
    companion object Factory{
        fun build(sqlDriver:SqlDriver): HeroInteractors{
            val service = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(GetHeros(service = service, cache = cache))
        }
        val schema: SqlDriver.Schema = HeroCache.schema

        val dbName: String = HeroCache.dbName
    }

}
