package im.tony.fragments

import im.tony.util.IconHelper
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.layout.VBox
import org.controlsfx.glyphfont.FontAwesome
import tornadofx.*

typealias ModalAction = () -> Unit
typealias StyleSetter = InlineCss.() -> Unit
typealias BodySetter = VBox.() -> Node

val Fragment.defaultCancelAction: ModalAction
  get() = { this.close() }

class AcceptCancelModal : Fragment("Some Modal", IconHelper.FA.create(FontAwesome.Glyph.EXCLAMATION_CIRCLE)) {
  private var cancelAction: ModalAction = defaultCancelAction
  private var acceptAction: ModalAction = defaultCancelAction
  private var message: String = "Yes or No?"

  private var windowStyler: StyleSetter? = null
  private var messageStyler: StyleSetter? = null
  private var acceptButtonStyler: StyleSetter? = null
  private var cancelButtonStyler: StyleSetter? = null

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
    style(true) { windowStyler?.invoke(this) }

    label {
      text = message
      style(true) { messageStyler?.invoke(this) }
    }

    hbox(2, Pos.BOTTOM_CENTER) {
      button("", IconHelper.get(FontAwesome.Glyph.CLOSE)) {
        action { cancelAction() }
        style(true) {  }
      }

      button("", IconHelper.get(FontAwesome.Glyph.CHECK_SQUARE)){
        action { acceptAction() }
        style(true) { acceptButtonStyler?.invoke(this) }
      }
    }
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
