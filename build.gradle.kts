import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.utils.addToStdlib.safeAs

plugins {
  application
  kotlin("jvm") version Ver.Kotlin
  id("org.openjfx.javafxplugin") version "0.0.9"
  id("io.gitlab.arturbosch.detekt") version "1.15.0-RC1"
  id("com.bnorm.power.kotlin-power-assert") version "0.6.0"
  id("org.sonarqube") version "3.0"
}

val Project.kotlinSourceSets
  get() = extensions.findByName("kotlin").safeAs<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>()!!.sourceSets

group = "im.tony"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
  maven(url = "https://dl.bintray.com/serpro69/maven-release-candidates/")
  maven(url = "https://kotlin.bintray.com/kotlinx/")
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

sonarqube {
  properties {
    property("sonar.sources", "src")
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

kotlinSourceSets {
  getByName("main").kotlin.srcDir("$projectDir/src/main/kotlin")
  getByName("main").kotlin.excludes += listOf("**/test/**", "**/*.Spec.kt", "**/*.Test.kt", "**/*/AcceptCancelModal.Spec.kt")
  getByName("main").resources.srcDir("$projectDir/src/main/resources")
  getByName("test").kotlin.srcDir("$projectDir/src/test/kotlin")
  getByName("test").kotlin.includes += listOf("**/test/**", "**/*.Spec.kt", "**/*.Test.kt")
  getByName("test").resources.srcDir("$projectDir/src/test/resources")
}

dependencies {
  // = Detekt Plugins =
  detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.15.0-RC1")

  // = TornadoFX =
  implementation("no.tornado:tornadofx:_")

  // = Kotlin Core =
  implementation(KotlinX.coroutines.core)
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:_")
  implementation(KotlinX.coroutines.javaFx)
  implementation(KotlinX.coroutines.debug)
  implementation(KotlinX.collections.immutableJvmOnly)
  implementation(Kotlin.stdlib.jdk8)
  implementation(KotlinX.serialization.core)
  implementation(KotlinX.serialization.json)
  implementation(KotlinX.serialization.properties)
  implementation(kotlin("reflect", "1.4.20"))
  implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")

  // = JFXtras - JavaFX Extensions =
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

  // = iKonli - JavaFX Icon Components =
  implementation("org.kordamp.ikonli:ikonli-core:_")
  implementation("org.kordamp.ikonli:ikonli-javafx:_")
  implementation("org.kordamp.ikonli:ikonli-devicons-pack:_")
  implementation("org.kordamp.ikonli:ikonli-materialdesign-pack:_")

  // = ControlsFx - JavaFX Extensions =
  implementation("org.controlsfx:controlsfx:_")
  implementation("no.tornado:tornadofx-controlsfx:_")

  // = Kotlin Version of Faker =
  implementation("io.github.serpro69:kotlin-faker:_")

  // = In-Memory Filesystem =
  implementation("com.google.jimfs:jimfs:_")

  implementation("net.raumzeitfalle.fx:scenic-view:11.0.2")

  // =====================
  // = Test Dependencies =
  // =====================
  testImplementation("org.jfxtras:jfxtras-test-support:_")
  testImplementation("org.testfx:testfx-core:4.0.16-alpha")
  testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")

  testImplementation(Testing.kotest.runner.junit5) // for kotest framework
  testImplementation(Testing.kotest.assertions.core) // for kotest core jvm assertions
  testImplementation(Testing.kotest.property) // for kotest property test
  testImplementation(Testing.kotest.extensions.testContainers)
  testImplementation(Testing.kotest.plugins.piTest)

  testImplementation(Testing.mockK.common)

  testImplementation(Kotlin.test.common)
  testImplementation(Kotlin.test.junit)
  testImplementation(Kotlin.test.junit5)
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
    testLogging {
      showStandardStreams = true
    }
    ignoreFailures = true
  }
  compileKotlin {
    kotlinOptions {
      // Provide source compatibility with the specified version of Kotlin
      languageVersion = "1.4"
      // apiVersion = "1.5" // Default = null
      jvmTarget = "11" // Default = 1.6
      noJdk = false // Default value
      noReflect = false // Default = true
      noStdlib = false // Default = true
      includeRuntime = true // Default = false
      useIR = true // Default = false
      freeCompilerArgs += listOf(
        "-Xopt-in=kotlin.time.ExperimentalTime",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
        "-Xopt-in=kotlin.contracts.ExperimentalContracts",
        "-Xopt-in=kotlin.io.path.ExperimentalPathApi",
        "-Xopt-in=kotlin.time.ExperimentalTime",
        "-Xopt-in=kotlin.ExperimentalStdlibApi",
        "-Xopt-in=kotlin.ExperimentalUnsignedTypes"
      )
    }

    kotlinOptions.jvmTarget = "11"
  }
  compileTestKotlin {
    kotlinOptions{
      jvmTarget = "11"
      useIR = false
      freeCompilerArgs += listOf("-Xskip-prerelease-check", "-Xallow-jvm-ir-dependencies")
    }
  }
}
