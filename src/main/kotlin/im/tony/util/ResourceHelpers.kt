package im.tony.util

import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.media.Media
import tornadofx.ResourceLookup
import tornadofx.toJSON
import tornadofx.toJSONArray
import java.io.InputStream
import java.net.URL

private fun Any.url(resource: String): URL = this.javaClass.getResource(resource)
private fun Any.urlString(resource: String): String = this.url(resource).toExternalForm()
private fun Any.stream(resource: String): InputStream = this.javaClass.getResourceAsStream(resource)

/**
 * Gets the [URL] for the resource indicated by the given [resource] [String].
 *
 * @receiver[Any] Any Kotlin Object.
 * @param[resource] A String path to the resource.
 * @return[URL] The [URL] pointing to the indicated resource.
 */
fun Any.getResourceUrl(resource: String) = this.url(resource)
fun Any.getResourceString(resource: String): String = this.urlString(resource)
fun Any.getResourceAsStream(resource: String) = this.stream(resource)
fun Any.getResourceAsMedia(resource: String): Media = Media(this.urlString(resource))
fun Any.getResourceAsImage(resource: String): Image = Image(this.stream(resource))
fun Any.getResourceAsImageView(resource: String, lazyload: Boolean = false): ImageView = ImageView(Image(this.urlString(resource), lazyload))
fun Any.getResourceAsJson(resource: String) = this.stream(resource).toJSON()
fun Any.getResourceAsJsonArray(resource: String) = this.stream(resource).toJSONArray()
fun Any.getResourceAsText(resource: String): String = this.stream(resource).use { it.bufferedReader().readText() }
