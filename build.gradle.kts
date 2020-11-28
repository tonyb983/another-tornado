import io.gitlab.arturbosch.detekt.Detekt

plugins {
  application
  kotlin("jvm") version Ver.Kotlin
  id("org.openjfx.javafxplugin") version "0.0.9"
  id("io.gitlab.arturbosch.detekt") version "1.15.0-RC1"
}

group = "im.tony"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
  maven(url  = "https://dl.bintray.com/serpro69/maven-release-candidates/")
}

detekt {
  debug = true
  parallel = true
  failFast = false // fail build on any finding
  buildUponDefaultConfig = true // preconfigure defaults
  //config = files(detektConfigPath) // point to your custom config defining rules to run, overwriting default behavior
  config = Paths.Detekt.detektYaml(rootProject)
  baseline = Paths.Detekt.baselineXml(rootProject)

  reports {
    html.enabled = true // observe findings in your browser with structure and code snippets
    xml.enabled = true // checkstyle like format mainly for integrations like Jenkins
    txt.enabled = true // similar to the console output, contains issue signature to manually edit baseline files
  }
}

application {
  mainClassName = "im.tony.MainKt"
}

javafx {
  version = "15.0.1"
  modules = mutableListOf(
    "javafx.controls",
    "javafx.graphics",
    "javafx.fxml",
    "javafx.media",
    "javafx.swing"
  )
}

sourceSets {
  main {
    resources {
      filter.include("**/*.eot", "**/*.ttf", "**/*.woff", "**/*.woff2", "**/*.otf", "**/*.svg")
    }
  }
}

dependencies {
  // ==================
  // = Detekt Plugins =
  // ==================
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.15.0-RC1")

  implementation("no.tornado:tornadofx:_")

  // ===================================
  // = Kotlinx Coroutines Dependencies =
  // ===================================
  implementation(KotlinX.coroutines.core)
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:_")
  implementation(KotlinX.coroutines.javaFx)
  implementation(KotlinX.coroutines.debug)

  // ========================
  // = JFXtras Dependencies =
  // ========================

  implementation("org.jfxtras:jfxtras-controls:_")
  implementation("org.jfxtras:jfxtras-agenda:_")
  implementation("org.jfxtras:jfxtras-window:_")
  implementation("org.jfxtras:jfxtras-fxml:_")
  implementation("org.jfxtras:jfxtras-icalendarfx:_")
  implementation("org.jfxtras:jfxtras-menu:_")
  implementation("org.jfxtras:jfxtras-common:_")
  implementation("org.jfxtras:jfxtras-font-roboto:_")
  implementation("org.jfxtras:jfxtras-parent:_")
  implementation("org.jfxtras:jfxtras-gauge-linear:_")

  implementation("org.jfxtras:jmetro:_")

  implementation("de.jensd:fontawesomefx-commons:_")

  implementation("org.kordamp.ikonli:ikonli-core:_")
  implementation("org.kordamp.ikonli:ikonli-javafx:_")
  //implementation("org.kordamp.ikonli:ikonli-dashicons-pack:${Ver.Ikonli}")
  implementation("org.kordamp.ikonli:ikonli-devicons-pack:_")
  //implementation("org.kordamp.ikonli:ikonli-elusive-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-entypo-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-feather-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-fontawesome-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-fontawesome5-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-fontelico-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-foundation-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-hawconsfilled-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-hawconsstroke-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-icomoon-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-ionicons-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-ionicons4-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-ligaturesymbols-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-linecons-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-maki-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-maki2-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-mapicons-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-material-pack:${Ver.Ikonli}")
  implementation("org.kordamp.ikonli:ikonli-materialdesign-pack:_")
  //implementation("org.kordamp.ikonli:ikonli-metrizeicons-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-octicons-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-openiconic-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-themify-pack:${Ver.Ikonli}")
  //implementation("org.kordamp.ikonli:ikonli-typicons-pack:${Ver.Ikonli}")

  implementation("org.controlsfx:controlsfx:_")
  implementation("no.tornado:tornadofx-controlsfx:_")

  implementation("io.github.serpro69:kotlin-faker:_")

  implementation(Kotlin.stdlib.jdk8)
  implementation("org.jetbrains.kotlin:kotlin-reflect:_")

  implementation("com.google.jimfs:jimfs:_")

  // =====================
  // = Test Dependencies =
  // =====================
  testImplementation(kotlin("test-junit"))

  testImplementation("org.jfxtras:jfxtras-test-support:_")

  testImplementation(Testing.kotest.runner.junit5) // for kotest framework
  testImplementation(Testing.kotest.assertions.core) // for kotest core jvm assertions
  testImplementation(Testing.kotest.property) // for kotest property test
  testImplementation(Testing.kotest.extensions.testContainers)

  testImplementation(Testing.mockK)

  testImplementation(KotlinX.coroutines.core)
  testImplementation(KotlinX.coroutines.test)
}

tasks {
  processResources {
    doLast { CustomTasks.ResourceGenerator.execute(this@processResources) }
  }

  withType<Detekt> {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = "1.8"
  }
  withType<Test> {
    useJUnitPlatform()
  }
  compileKotlin {
    kotlinOptions.jvmTarget = "11"
  }
  compileTestKotlin {
    kotlinOptions.jvmTarget = "11"
  }
}
