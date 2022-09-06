apply {
    from("$rootDir/android-library-build.gradle")
}
dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.heroDomain))
    "implementation"(project(Modules.heroInteractors))
    //caching
    "implementation"(SqlDelight.androidDriver)
    //Coil image loading
    "implementation"(Coil.coil)
    //Hilt
    "implementation"(Hilt.android)
   "kapt"(Hilt.compiler)
}