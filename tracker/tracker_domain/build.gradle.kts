plugins {
    id("tracker-domain-plugin")
}

dependencies {
    implementation(project(Modules.core))
    implementation(Coroutines.coroutines)
}