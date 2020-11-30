package im.tony.fragments

import im.tony.util.*
import javafx.animation.Interpolator
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.util.Duration
import org.controlsfx.glyphfont.FontAwesome
import tornadofx.*

typealias ModalAction = () -> Unit
typealias StyleSetter = InlineCss.() -> Unit
typealias BodySetter = VBox.() -> Node

val Fragment.defaultCancelAction: ModalAction
  get() = { this.close() }

class AcceptCancelModal : Fragment("Some Modal") {
  private var cancelAction: ModalAction = defaultCancelAction
  private var acceptAction: ModalAction = defaultCancelAction
  private var message: String = "Yes or No?"

  private var windowStyler: StyleSetter? = null
  private var messageStyler: StyleSetter? = null
  private var acceptButtonStyler: StyleSetter? = null
  private var cancelButtonStyler: StyleSetter? = null

  @Suppress("MemberVisibilityCanBePrivate")
  var preferredSize: Vector2D = Vector2D(300.0, 150.0)

  fun withWindowStyle(styleSetter: StyleSetter): AcceptCancelModal {
    windowStyler = styleSetter
    return this
  }

  fun withAcceptAction(act: ModalAction, acceptButtonStyle: StyleSetter? = null): AcceptCancelModal {
    acceptAction = act
    if(acceptButtonStyle != null) acceptButtonStyler = acceptButtonStyle
    return this
  }

  fun withAcceptButtonStyle(styleSetter: StyleSetter): AcceptCancelModal {
    acceptButtonStyler = styleSetter
    return this
  }

  fun withCancelAction(act: ModalAction, cancelButtonStyle: StyleSetter? = null): AcceptCancelModal {
    cancelAction = act
    if(cancelButtonStyle != null) cancelButtonStyler = cancelButtonStyle
    return this
  }

  fun withCancelButtonStyle(styleSetter: StyleSetter): AcceptCancelModal {
    cancelButtonStyler = styleSetter
    return this
  }

  fun withMessage(msg: String, msgStyle: StyleSetter? = null): AcceptCancelModal {
    message = msg
    if(msgStyle != null) messageStyler = msgStyle
    return this
  }

  fun withMessageStyle(styleSetter: StyleSetter): AcceptCancelModal {
    messageStyler = styleSetter
    return this
  }

  fun with(
    message: String,
    acceptAction: ModalAction,
    cancelAction: ModalAction = defaultCancelAction,
    windowStyler: StyleSetter? = null,
    messageStyler: StyleSetter? = null,
    acceptButtonStyle: StyleSetter? = null,
    cancelButtonStyle: StyleSetter? = null,
  ): AcceptCancelModal {
    this.message = message
    this.acceptAction = acceptAction
    this.cancelAction = cancelAction
    this.windowStyler = windowStyler
    this.messageStyler = messageStyler
    this.acceptButtonStyler = acceptButtonStyle
    this.cancelButtonStyler = cancelButtonStyle
    return this
  }

  override val root = vbox(5) {
    val (prefX, prefY) = preferredSize
    setPrefSize(prefX, prefY)
    style(true) { windowStyler?.invoke(this) }

    label {
      prefHeight = prefY * 0.8
      text = message
      font = FontHelper.LatoRegular14
      alignment = Pos.CENTER
      textAlignment = TextAlignment.CENTER
      style(true) { messageStyler?.invoke(this) }
    }

    hbox(2, Pos.BOTTOM_CENTER) {
      prefHeight = prefX * 0.2
      spacer(Priority.SOMETIMES)
      button("", IconHelper.get(FontAwesome.Glyph.CLOSE)) {
        action { cancelAction() }
        style(true) {
          this.endMargin = 6.px
          cancelButtonStyler?.invoke(this)
        }
      }
      spacer(Priority.SOMETIMES)
      button("", IconHelper.get(FontAwesome.Glyph.CHECK_SQUARE)){
        action { acceptAction() }
        style(true) {
          this.startMargin = 6.px
          acceptButtonStyler?.invoke(this)
        }
      }
      spacer(Priority.SOMETIMES)
    }
  }.apply {
    this.opacity = 0.0
    this.fade(Duration.seconds(0.5), 1.0, Interpolator.EASE_IN, reversed = false, play = true)
  }

  companion object {
    fun create(
      message: String,
      acceptAction: ModalAction,
      cancelAction: ModalAction? = null,
      windowStyler: StyleSetter? = null,
      messageStyler: StyleSetter? = null,
      acceptButtonStyle: StyleSetter? = null,
      cancelButtonStyle: StyleSetter? = null,
    ) = find<AcceptCancelModal>().apply {
      this.with(
        message,
        acceptAction,
        cancelAction ?: this.defaultCancelAction,
        windowStyler,
        messageStyler,
        acceptButtonStyle,
        cancelButtonStyle
      )
    }

    fun createAsModal(
      message: String,
      acceptAction: ModalAction,
      cancelAction: ModalAction? = null,
      windowStyler: StyleSetter? = null,
      messageStyler: StyleSetter? = null,
      acceptButtonStyle: StyleSetter? = null,
      cancelButtonStyle: StyleSetter? = null,
    ) = find<AcceptCancelModal>().apply {
      this.with(
        message,
        acceptAction,
        cancelAction ?: this.defaultCancelAction,
        windowStyler,
        messageStyler,
        acceptButtonStyle,
        cancelButtonStyle
      ).openModal()
    }
  }
}
