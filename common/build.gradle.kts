plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "cn.snowlie.app"
version = "1.0-SNAPSHOT"

kotlin {
    dependencies {
        implementation("androidx.compose.foundation:foundation:1.0.1")
        implementation("androidx.compose.ui:ui-tooling:1.0.1")
        implementation("androidx.compose.material3:material3:1.0.1")
        implementation("androidx.compose.material3:material3-window-size-class:1.0.1")
        implementation("androidx.compose.material:material-icons-extended:1.0.1")
        implementation("com.acornui:gdx-font-processor:0.5.49")
        implementation("com.acornui:gdx-font-processor:0.5.49")
    }
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.5.1")
                api("androidx.core:core-ktx:1.9.0")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdkVersion(33)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(33)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
dependencies {
    implementation("com.acornui:gdx-font-processor:0.5.49")
    implementation("com.acornui:gdx-font-processor:0.5.49")
}
