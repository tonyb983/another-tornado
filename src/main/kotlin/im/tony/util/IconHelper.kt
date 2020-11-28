package im.tony.util

import org.controlsfx.glyphfont.FontAwesome
import org.controlsfx.glyphfont.Glyph
import org.controlsfx.glyphfont.GlyphFontRegistry
import java.util.MissingResourceException
import java.util.concurrent.ConcurrentHashMap
import kotlin.concurrent.thread

object IconHelper {
  private val ccache: ConcurrentHashMap<FontAwesome.Glyph, Glyph> = ConcurrentHashMap()

  fun get(glyph: FontAwesome.Glyph): Glyph = ccache.getOrPut(glyph, { FA.create(glyph) })

  val FA by lazy {
    GlyphFontRegistry.font("FontAwesome") ?: throw MissingResourceException(
      "GlyphFontRegistry unable to load fond 'FontAwesome'.",
      "Font",
      "FontAwesome"
    )
  }
}
