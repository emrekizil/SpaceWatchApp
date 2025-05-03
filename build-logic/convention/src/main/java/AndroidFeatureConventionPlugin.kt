import com.emrekizil.build_logic.convention.implementation
import com.emrekizil.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("spacewatchapp.android.library")
                apply("spacewatchapp.android.hilt")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(project(":core:model"))
                implementation(project(":core:data"))
                implementation(project(":core:common"))
                implementation(libs.findLibrary("androidx-navigation-compose").get())
                implementation(libs.findLibrary("kotlinx-serialization-json").get())
                implementation(libs.findLibrary("hilt-navigation").get())
            }
        }
    }
}