package im.tony.view

import javafx.geometry.Pos
import tornadofx.*

class MainMenu : View("My View") {
  override val root = vbox {
    alignment = Pos.CENTER

    button("View all Todos") {
    }
  }
}
