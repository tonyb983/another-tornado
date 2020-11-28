package im.tony.view

import im.tony.gen.R
import javafx.beans.property.StringProperty
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import tornadofx.*
import java.net.URL

class MainView : View("Hello TornadoFX") {
  override val root = borderpane {
    top<MenuBar>()
  }
}

