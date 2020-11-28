package im.tony.fragments

import im.tony.util.FontHelper
import javafx.geometry.Pos
import javafx.scene.text.TextAlignment
import tornadofx.*

class InfoBoxModal : Fragment("InfoBox") {
  override val root = borderpane {
    setPrefSize(400.0, 400.0)

    center {
      fitToParentWidth()
      prefHeight = 300.0
      paddingAll = 6

      textflow {
        textAlignment = TextAlignment.JUSTIFY
        text("This is a simple app used for testing out TornadoFx.\n") { font = FontHelper.LatoRegular14 }
        text("It should be used only for development purposes and no real human should really ever look at it.\n") { font = FontHelper.LatoRegular14 }
        text("Github Repo: ") { font = FontHelper.FiraCodeRetina14 }
        hyperlink("http://github.com/tonyb983/") {
          this.font = FontHelper.FiraCodeRetina14
        }
      }
    }

    bottom {
      fitToParentWidth()
      prefHeight = 100.0
      button("Okay") {
        alignment = Pos.CENTER

        action {
          this@InfoBoxModal.close()
        }
      }
    }
  }
}
