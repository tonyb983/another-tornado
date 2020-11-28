import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.jvm.jvmMultifileClass
import org.gradle.api.Action
import org.gradle.api.file.FileVisitDetails
import org.gradle.api.logging.Logger
import org.gradle.language.jvm.tasks.ProcessResources
import java.io.File

fun File.segments(root: File): List<String> = this.relativeTo(root).invariantSeparatorsPath.split("/")

object CustomTasks {
  sealed class FileInfo(val target: File, root: File) {
    val namerTag: String = if (target.isDirectory) target.segments(root).joinToString(".") else target.segments(root).dropLast(1)
      .joinToString(".") + target.nameWithoutExtension
    val namerSuggestion: String = if (target.isDirectory) target.name else target.nameWithoutExtension
    @Suppress("unused")
    val extension: String? = if (target.isDirectory) null else target.extension
    @Suppress("unused")
    val isDirectory: Boolean = target.isDirectory
    val resourcePath: String = if (target.isDirectory) "" else "/${target.relativeTo(root).invariantSeparatorsPath}"

    class F(target: File, root: File) : FileInfo(target, root)
    class D(target: File, root: File) : FileInfo(target, root)

    companion object {
      fun getNamerTag(target: File, root: File): String =
        if (target.isDirectory) target.segments(root).joinToString(".") else target.segments(root).dropLast(1)
          .joinToString(".") + target.nameWithoutExtension

      fun getNamerSuggestion(target: File): String = if (target.isDirectory) target.name else target.nameWithoutExtension

      fun extract(target: File, root: File): FileInfo = if (target.isDirectory) D(target, root) else F(target, root)
    }
  }

  @Suppress("UnstableApiUsage")
  object ResourceGenerator : Action<ProcessResources> {
    private const val generatedModule = "im.tony.gen"
    private const val generatedDir = "src/main/kotlin"
    private const val generatedFileName = "ResourceNames"
    private const val generatedObjectName = "R"
    private const val inputDir = "src/main/resources"

    /**
     * Performs this action against the given object.
     *
     * @param[t] The object to perform the action on.
     */
    override fun execute(t: ProcessResources) {
      if (!t.didWork) {
        t.logger.warn("ResourceGenerator: No work done by ProcessResources task, early exit.")
        return
      }

      generatedFile?.delete()

      val projDir = t.project.layout.projectDirectory

      val genDir = projDir.dir(generatedDir)
      genDir.asFile.mkdirs()

      val logger = t.logger
      val namer = NameAllocator()
      val root = projDir.dir(inputDir).asFile

      logger.warn("Generating names")
      root.walkTopDown().forEach {
        namer.newName(FileInfo.getNamerSuggestion(it), FileInfo.getNamerTag(it, root))
      }

      val cn = ClassName(generatedModule, generatedObjectName)
      var rootBuilder = TypeSpec.objectBuilder(cn).addKdoc(CodeBlock.of("""
        |This is an object containing typesafe access to all resources in the project.
      """.trimMargin()))

      logger.warn("Preparing Root Directory")

      val (dirs, files) = root.listFiles()!!.map { FileInfo.extract(it, root) }.partition { it is FileInfo.D }
      logger.warn("Processing Files in ${root.name}")
      files.forEach { rootBuilder = processFile(it as FileInfo.F, namer, rootBuilder, logger) }

      logger.warn("Processing Directories in ${root.name}")
      dirs.forEach { rootBuilder = processDirectory(it as FileInfo.D, root, namer, rootBuilder, logger) }

      logger.warn("Building combined object to FileSpec")
      val fSpec = FileSpec
        .builder(generatedModule, generatedFileName)
        .addComment("""
          |This file allows type-safe access to correct resource paths.          
          |This is a generated file, any changes made will be erased on the next build.         
        """.trimMargin())
        .addAnnotation(
          AnnotationSpec
            .builder(Suppress::class)
            .addMember("%S, %S, %S", "unused", "RedundantVisibilityModifier", "SpellCheckingInspection")
            .build())
        .addType(rootBuilder.build())
        .build()

      logger.warn("Writing to ${genDir.toString()}:")
      logger.warn(fSpec.toString())

//      gf.bufferedWriter().apply {
//        this.write("package im.tony.gen")
//        this.newLine()
//        this.newLine()
//        fSpec.writeTo(this)
//      }

      fSpec.writeTo(genDir.asFile)
      generatedFile = genDir.file(generatedFileName).asFile
      // fSpec.writeTo(generatedFile!!)
    }

    private fun processDirectory(
      dir: FileInfo.D,
      root: File,
      namer: NameAllocator,
      parent: TypeSpec.Builder,
      l: Logger? = null
    ): TypeSpec.Builder {
      l?.warn("Entering Process Directory - dir: ${dir.namerSuggestion}")

      val (dirs, files) = dir.target.listFiles()?.map { FileInfo.extract(it, root) }?.partition { it is FileInfo.D } ?: return parent
      var builder = TypeSpec.objectBuilder(namer[dir.namerTag].capitalize()).addKdoc(CodeBlock.of("""
        |This object contains typesafe access to all resources in the '%L' directory.
        |
        |This object is automatically generated.
      """.trimMargin(), dir.target.name))

      l?.warn("Processing Files in ${dir.namerSuggestion}")
      files.forEach { builder = processFile(it as FileInfo.F, namer, builder, l) }

      l?.warn("Processing Directories in ${dir.namerSuggestion}")
      dirs.forEach { builder = processDirectory(it as FileInfo.D, root, namer, builder, l) }

      return parent.addType(builder.build())
    }

    private fun processFile(
      file: FileInfo.F,
      namer: NameAllocator,
      parent: TypeSpec.Builder,
      l: Logger? = null
    ): TypeSpec.Builder {
      l?.warn("Entering Process File - file: ${file.namerSuggestion}")
      parent.addProperty(PropertySpec.builder(namer[file.namerTag], String::class)
        .addKdoc(CodeBlock.of("""
        |This property represents the following resource:
        |Item: %L
        |Path: %L
        |
        |This object is automatically generated.
        |
        |@returns[String] The resource path, usable with [Class.getResource] or [tornadofx.ResourceLookup]
      """.trimMargin(), file.target.nameWithoutExtension, file.resourcePath))
        .getter(FunSpec.getterBuilder()
          .addModifiers(KModifier.INLINE)
          .addStatement("return %S", file.resourcePath)
          .build())
        .build()
      )

      return parent
    }

    private var generatedFile: File? = null
  }
}
