package im.tony.fragments

import im.tony.util.FontHelper
import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.text.TextAlignment
import tornadofx.*

class InfoBoxModal : Fragment("InfoBox") {
  var prefSize: Vector2D = Vector2D(300.0, 200.0)

  override val root = borderpane {
    setPrefSize(prefSize.x, prefSize.y)

    center {
      fitToParentWidth()
      prefHeight = prefSize.y * 0.8
      paddingAll = 6

      textflow {
        textAlignment = TextAlignment.LEFT
        text("This is a simple app used for testing out TornadoFx.\n") { font = FontHelper.LatoRegular14 }
        text(
          "It should be used only for development purposes" +
            " and no real human should really ever look at it.\n\n\n"
        ) { font = FontHelper.LatoRegular14 }
        separator()
        text("Github Repo: ") { font = FontHelper.Monofur14 }
        hyperlink("http://github.com/tonyb983/another-tornado") {
          this.font = FontHelper.Monofur14
        }
      }
    }

    bottom = hbox {
      fitToParentWidth()
      prefHeight = prefSize.y * 0.2
      spacer(Priority.SOMETIMES)
      button("Okay") {
        action {
          this@InfoBoxModal.close()
        }
      }
      spacer(Priority.SOMETIMES)
    }
  }
}
