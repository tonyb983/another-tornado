// runSonar.main.kts
#!/usr/bin/env kotlin

@file:DependsOn("https://dl.bintray.com/jakubriegel/kotlin-shell")
@file:DependsOn("java.lang.ProcessBuilder")
@file:DependsOn("java.io.File")

fun String.runCommand(workingDir: File) {
  ProcessBuilder(*split(" ").toTypedArray())
    .directory(workingDir)
    .redirectOutput(Redirect.INHERIT)
    .redirectError(Redirect.INHERIT)
    .start()
    .waitFor(60, TimeUnit.MINUTES)
}

fun String.runCommand(workingDir: File): String? {
  try {
    val parts = this.split("\\s".toRegex())
    val proc = ProcessBuilder(*parts.toTypedArray())
      .directory(workingDir)
      .redirectOutput(ProcessBuilder.Redirect.PIPE)
      .redirectError(ProcessBuilder.Redirect.PIPE)
      .start()

    proc.waitFor(60, TimeUnit.MINUTES)
    return proc.inputStream.bufferedReader().readText()
  } catch(e: IOException) {
    e.printStackTrace()
    return null
  }
}

"sonar-scanner.bat -D\"sonar.projectKey=im.tony.another-tornado\" -D\"sonar.sources=.\" -D\"sonar.host.url=http://localhost:9000\" -D\"sonar.login=c65a8e99eb043101e86f632078a880d638683f66\"".runCommand()
