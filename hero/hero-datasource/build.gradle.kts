
apply {
    from("$rootDir/library-build.gradle")
}
plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
    id(SqlDelight.plugin)

}
dependencies {
    //expand the model of hero data source (API/Cache response model)
    "implementation"(project(Modules.heroDomain))

    //Ktor library implementation
    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)
    //caching library implementation
    "implementation"(SqlDelight.runtime)

}
sqldelight {
    database("HeroDatabase") {
        packageName = "com.idbs.hero_datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}
