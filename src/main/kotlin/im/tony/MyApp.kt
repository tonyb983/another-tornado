package im.tony

import im.tony.styles.Styles
import im.tony.util.repeat
import im.tony.views.EntryView
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import org.scenicview.ScenicView
import tornadofx.*

class MyApp : App(EntryView::class, Styles::class) {
  override fun start(stage: Stage) {
    println('*'.repeat(10))
    println("MyApp - start")
    super.start(stage)
    stage.scene?.addEventFilter(KeyEvent.KEY_PRESSED, globalShortcuts)
  }

  init {
    FX.layoutDebuggerShortcut = KeyCodeCombination(KeyCode.L, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN)
    FX.reloadStylesheetsOnFocus = true
    FX.reloadViewsOnFocus = true
    // addEventFilter(KeyEvent.KEY_PRESSED, stageGlobalShortcuts)
  }

  companion object {
    val globalShortcuts = object : EventHandler<KeyEvent> {
      private val keyCode = KeyCodeCombination(KeyCode.D, KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN)
      /**
       * Invoked when a specific event of the type for which this handler is
       * registered happens.
       *
       * @param event the event which occurred
       */
      override fun handle(event: KeyEvent?) {
        if(keyCode.match(event)) {
          ScenicView.show(FX.primaryStage.scene)
        }
      }
    }
  }
}

/*
val Stage.stageGlobalShortcuts: EventHandler<KeyEvent>
        get() {
            val key = "tornadofx.stageGlobalShortcuts"
            if (properties[key] == null) {
                properties[key] = EventHandler<KeyEvent> {
                    if (FX.layoutDebuggerShortcut?.match(it) ?: false)
                        LayoutDebugger.debug(scene)
                    else if (FX.osgiDebuggerShortcut?.match(it) ?: false && FX.osgiAvailable)
                        find<OSGIConsole>().openModal(modality = Modality.NONE)
                }
            }
            return properties[key] as EventHandler<KeyEvent>
        }
 */
