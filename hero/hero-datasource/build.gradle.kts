import org.jetbrains.kotlin.ir.backend.js.ic.ModuleName

apply {
    from("$rootDir/library-build.gradle")
}
plugins {
    kotlin(KotlinPlugins.serialization) version Kotlin.version
}
dependencies {
    //expand the model of hero data source (API/Cache response model)
    "implementation"(project(Modules.heroDomain))

    //Ktor library implementation
    "implementation"(Ktor.core)
    "implementation"(Ktor.clientSerialization)
    "implementation"(Ktor.android)

}