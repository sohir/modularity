apply{
    from("$rootDir/library-build.gradle")
}
dependencies{
"implementation"(project(Modules.core))
    "implementation"(project(Modules.heroDataSource))
    "implementation"(project(Modules.heroDomain))
//working with flows in use cases
    "implementation"(Kotlinx.coroutinesCore)
}