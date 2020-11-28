plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

repositories {
  mavenCentral()
  google()
  jcenter()
}

kotlinDslPluginOptions {
  experimentalWarning.set(false)
}

dependencies {
  implementation(gradleApi())
  implementation(localGroovy())
  implementation(gradleKotlinDsl())
  implementation("com.squareup:kotlinpoet:1.7.2")
}
