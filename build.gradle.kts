val kotlinVersion: String by System.getProperties()

val kotlinReactVersion = "17.0.2-pre.290-kotlin-1.6.10"
val kotlinStyledVersion = "5.3.3-pre.290-kotlin-1.6.10"

val ktorVersion = "1.6.7"
val kotlinHtmlVersion = "0.7.3"
val serializationVersion = "1.3.2"
val retrofitVersion = "2.9.0"
val okHttpLoggingInterceptorVersion = "4.9.3"
val log4jVersion = "2.17.1"

plugins {
    val kotlinVersion: String by System.getProperties()
    kotlin("multiplatform") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    application
}

group = "altline"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()
    }
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
            }
        }
        val commonTest by getting
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-serialization:$ktorVersion")
                implementation("io.ktor:ktor-html-builder:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinHtmlVersion")
                implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
                implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
                implementation("com.squareup.okhttp3:logging-interceptor:$okHttpLoggingInterceptorVersion")
                implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
                implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
                implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:$kotlinReactVersion")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:$kotlinReactVersion")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:$kotlinStyledVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("altline.insurance.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))

    systemProperty("altline.insurance.httpLogging", false)
}