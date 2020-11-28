package im.tony.util

import javafx.scene.paint.Color
import tornadofx.c

/**
 * Change the [Color.red] value of a [Color] object.
 *
 * Returns this [Color] with the same [Color.green] and [Color.blue] values,
 * but the [Color.red] value changed to the given value.
 *
 * @param[r] The red value to substitute into the color.
 * @return The new [Color] with the red value modified.
 * @receiver[Color]
 */
fun Color.withRed(r: Double): Color = c(r, this.green, this.blue, this.opacity)

/**
 * Change the [Color.green] value of a [Color] object.
 *
 * Returns this [Color] with the same [Color.red] and [Color.blue] values,
 * but the [Color.green] value changed to the given value.
 *
 * @param[g] The green value to substitute into the color.
 * @return The new [Color] with the green value modified.
 * @receiver[Color]
 */
fun Color.withGreen(g: Double): Color = c(this.red, g, this.blue, this.opacity)

/**
 * Change the [Color.blue] value of a [Color] object.
 *
 * Returns this [Color] with the same [Color.red] and [Color.green] values,
 * but the [Color.blue] value changed to the given value.
 *
 * @param[b] The blue value to substitute into the color.
 * @return The new [Color] with the blue value modified.
 * @receiver[Color]
 */
fun Color.withBlue(b: Double): Color = c(this.red, this.green, b, this.opacity)

/**
 * Change the [Color.opacity] value of a [Color] object.
 *
 * Returns this [Color] with the same [Color.red], [Color.green], and [Color.blue] values,
 * but the [Color.opacity] value changed to the given value.
 *
 * @param[o] The opacity value to substitute into the color.
 * @return The new [Color] with the opacity value modified.
 * @receiver[Color]
 */
fun Color.withOpacity(o: Double): Color = c(this.red, this.green, this.blue, o)

/**
 * Makes a color clear.
 *
 * Returns the given color with the [Color.opacity] changed to 0%.
 *
 * @receiver[Color]
 * @return[Color] The new color with zero opacity.
 */
fun Color.asTransparent(): Color = this.withOpacity(0.0)

/**
 * Makes a color opaque.
 *
 * Returns the given color with the [Color.opacity] changed to 100%.
 *
 * @receiver[Color]
 * @return[Color] The new color with full opacity.
 */
fun Color.asOpaque(): Color = this.withOpacity(1.0)
