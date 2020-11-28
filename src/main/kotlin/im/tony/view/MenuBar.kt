package im.tony.view

import im.tony.styles.Styles
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import tornadofx.*

class MenuBar : View("My View") {
  override val root = hbox {
    fitToParentWidth()
    prefHeight = 50.0
    minHeight = 20.0
    maxHeight = 60.0

    hbox {
      alignment = Pos.CENTER_LEFT
      button("File") {
        style {
          addClass(Styles.coolBlueBtn)
        }
      }
      button("Edit") {
        style {
          addClass(Styles.coolBlueBtn)
        }
      }
      button("View") {
        style {
          addClass(Styles.coolBlueBtn)
        }
      }
    }

    spacer(Priority.SOMETIMES)

    hbox {
      alignment = Pos.CENTER_RIGHT
      button("Help") {
        style {
          addClass(Styles.coolBrownBtn)
        }
      }
    }
  }
}
