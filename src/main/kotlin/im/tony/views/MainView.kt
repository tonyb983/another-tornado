package im.tony.views

import javafx.scene.Node
import org.controlsfx.control.NotificationPane
import org.controlsfx.control.action.Action
import tornadofx.*
import tornadofx.controlsfx.notificationPane
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
data class NotificationRequest(
  val message: String,
  val graphic: Node? = null,
  val actions: List<Action>? = null,
  val showExit: Boolean = false,
  val timer: Duration? = null
) {
  constructor(message: String, graphic: Node? = null, vararg actions: Action) : this(message, graphic, actions.toList())
  constructor(message: String, vararg actions: Action) : this(message, null, actions.toList())
}

@ExperimentalTime
class NotificationEvent(val request: NotificationRequest) : FXEvent()

@ExperimentalTime
class MainView : View("Hello TornadoFX") {
  val notificationShowing = booleanProperty(false)
  var notificationPane: NotificationPane by singleAssign()
  private val notificationQueue: ConcurrentLinkedQueue<NotificationRequest> = ConcurrentLinkedQueue()

  init {
    subscribe<NotificationEvent> {
      notificationQueue.add(it.request)
    }
    thread(true, true, ClassLoader.getPlatformClassLoader(), "Notification Processor") {
      while(true) {
        if(notificationQueue.isNotEmpty()) {
          
        }
      }
    }
  }

  override val root = notificationPane(true, notificationShowing) {
    fitToParentSize()
    notificationPane = this
    content = borderpane {
      fitToParentSize()
      top<MenuBar>()
      center {
        button("Show Notification") {
          action {
            notificationPane.show("Look a notification!")
          }
        }
      }
    }
  }
}

