package im.tony.util

import javafx.geometry.Point2D
import javafx.geometry.Point3D
import javafx.scene.layout.Region

fun Region.setPrefSize(pair: Pair<Double, Double>) = this.setPrefSize(pair.first, pair.second)
fun Char.repeat(times: Int): String = "$this".repeat(times)
fun String.borderedWith(
  borderChar: Char,
  prependBorder: Boolean = true,
  appendBorder: Boolean = true,
  newLine: String = System.lineSeparator(),
  prependBorderCharOnSameLine: Boolean = true,
  appendBorderCharOnSameLine: Boolean = true,
  startPadding: Int = 1,
  endPadding: Int = 1,
): String {
  val borderSize: Int = this.length +
    (if (prependBorderCharOnSameLine) 1 else 0) +
    (if (appendBorderCharOnSameLine) 1 else 0) +
    startPadding + endPadding
  return "" +
    (if(prependBorder) "${"$borderChar".repeat(borderSize)}$newLine" else "") +
    (if(prependBorderCharOnSameLine) "$borderChar" else "") +
    (if(startPadding <= 0) "" else " ".repeat(startPadding.coerceAtLeast(0))) +
    this +
    (if(endPadding <= 0) "" else " ".repeat(endPadding.coerceAtLeast(0))) +
    (if(appendBorderCharOnSameLine) "$borderChar" else "") +
    (if(appendBorder) "$newLine${borderChar.repeat(borderSize)}" else "")
}

operator fun Point2D.component1(): Double = this.x
operator fun Point2D.component2(): Double = this.y

operator fun Point3D.component1(): Double = this.x
operator fun Point3D.component2(): Double = this.y
operator fun Point3D.component3(): Double = this.z
