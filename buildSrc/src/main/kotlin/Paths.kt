import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import java.io.File

object Paths {
  object Detekt {
    private const val detektPath = "config/detekt"

    private fun Project.getRootPath() = this.rootProject.projectDir.absolutePath

    fun detektYaml(rootProj: Project): ConfigurableFileCollection = rootProj
      .files("${rootProj.getRootPath()}/${detektPath}/detekt.yml")
    fun baselineXml(rootProj: Project): File = rootProj
      .file("${rootProj.getRootPath()}/${detektPath}/baseline.xml")
  }
}
