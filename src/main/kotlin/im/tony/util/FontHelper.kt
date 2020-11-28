package im.tony.util

import im.tony.gen.R
import javafx.scene.text.Font

object FontHelper {
  val Monofur14: Font = Font.loadFont(this.getResourceString(R.Fonts.monofur), 14.0)
  val LatoRegular14: Font = Font.loadFont(this.getResourceString(R.Fonts.Lato_Regular), 14.0)
  val FiraCodeRetina14: Font = Font.loadFont(this.getResourceString(R.Fonts.FiraCode_Retina_NF), 14.0)
}
