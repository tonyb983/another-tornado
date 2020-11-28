package im.tony.view

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import javafx.util.Duration
import tornadofx.*

class EntryView : View("Starting") {
  /**
   * Called when a Component becomes the Scene root or
   * when its root node is attached to another Component.
   * @see UIComponent.add
   */
  override fun onDock() {
    super.onDock()
    replaceWith<MainView>(ViewTransition.Metro(Duration.seconds(5.0)))
  }

  override val root = pane {
    prefWidth = 640.0
    prefHeight = 480.0
    background = Background(BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY))
    label("Loading...") {
      fitToParentSize()
      alignment = Pos.CENTER
      style {
        backgroundColor += Color.TRANSPARENT
        fontSize = 18.pt
        this.textFill = Color.DARKSLATEGRAY
        textAlignment = TextAlignment.CENTER
      }
    }
  }
}

/*
rectangle {
      fitToParentSize()
      fill = Color.BLACK
    }.animateFill(
      Duration.seconds(5.0),
      Color.BLACK,
      Color.ALICEBLUE,
      Interpolator.EASE_IN,
      reversed = false,
      play = true).onFinished = EventHandler {
        replaceWith<MainMenu>()
    }
 */
