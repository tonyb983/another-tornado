package im.tony.styles

import im.tony.util.asOpaque
import im.tony.util.asTransparent
import javafx.geometry.Pos
import javafx.scene.effect.Effect
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*
import kotlin.random.Random

class Styles : Stylesheet() {
  init {
    heading {
      textFill = mainColor
      fontSize = 20.px
      fontWeight = FontWeight.BOLD
    }

    button {
      padding = box(10.px, 20.px)
      fontWeight = FontWeight.BOLD
    }

    val flat = mixin {
      backgroundInsets += box(0.px)
      borderColor += box(Color.DARKGRAY)
    }

    s(button, textInput) {
      +flat
    }

    stdButton and button {
      padding = box(10.px)
      fontSize = 15.px
      textAlignment = TextAlignment.CENTER
    }

    label and heading {
      padding = box(20.px)
      fontSize = 20.px
      fontWeight = FontWeight.BOLD
    }

    centered {
      alignment = Pos.CENTER
      textAlignment = TextAlignment.CENTER
    }

    coolBtn {
      this.padding = box(15.px, 30.px)
      this.fontSize = 20.px
      this.fontWeight = FontWeight.BLACK
      this.borderWidth += box(1.px)
      this.borderStyle += BorderStrokeStyle.SOLID
      this.borderColor = multi(box(c(0, 0, 0, 0.0)))
      this.borderRadius += box(15.px)
      this.prefWidth = 120.px
      this.backgroundColor += c(0, 0, 0, 0.0)

      and(hover) {
        this.borderColor = multi(box(c(0, 0, 0, 1.0)))
      }
    }

    coolBlueBtn {
      coloredButton(coolBlueBtn, Color.MIDNIGHTBLUE)
    }

    coolBrownBtn {
      coloredButton(coolBrownBtn, Color.ROSYBROWN)
    }
  }

  private fun coloredButton(rule: CssRule, color: Color): CssSelection {
    return rule {
      this.padding = box(5.px, 5.px)
      this.fontSize = 20.px
      this.fontWeight = FontWeight.BLACK
      this.textFill = color
      this.borderWidth = multi(box(2.px))
      this.borderStyle = multi(BorderStrokeStyle.SOLID)
      this.borderColor = multi(box(color))
      this.borderRadius = multi(box(7.px))
      this.prefWidth = 100.px
      this.backgroundColor = multi(color.asTransparent())
      this.backgroundRadius = multi(box(10.px))

      and(hover) {
        this.textFill = Color.ANTIQUEWHITE
        //this.borderWidth = multi(box(2.px))
        //this.borderStyle = multi(BorderStrokeStyle.SOLID)
        this.borderColor = multi(box(color))
        //this.borderRadius = multi(box(10.px))
        this.backgroundColor = multi(color)
        //this.backgroundRadius = multi(box(10.px))
      }

      and(pressed) {
        this.textFill = Color.ANTIQUEWHITE
        //this.borderWidth = multi(box(2.px))
        //this.borderStyle = multi(BorderStrokeStyle.SOLID)
        this.borderColor = multi(box(color.desaturate()))
        //this.borderRadius = multi(box(10.px))
        this.backgroundColor = multi(color.desaturate())
        //this.backgroundRadius = multi(box(10.px))
      }
    }
  }

  companion object {
    val heading by cssclass()
    val stdButton by cssclass()
    val centered by cssclass()
    val coolBtn by cssclass()
    val coolBlueBtn by cssclass()
    val coolBrownBtn by cssclass()

    // Define colors
    val mainColor = c("#bdbd22")
  }
}

/*
$purple : #512DA8;
$blue : #1976D2;
$l_orange : #FBC02D;
$brick : #FF5722;

@mixin buttonBG($bg) {
  border: 2px solid $bg;
  color: $bg;
  &:hover {
    background-color: $bg;
    transition: all 0.3s ease-in-out;
  }
}

.container{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  flex-wrap: wrap;
  height: 97vh;
  .skewBtn{
    width: 180px;
    height: 80px;
    outline: none;
    cursor: pointer;
    background: none;
    transition: .5s;
    font-size: 24px;
    border-radius: 5px;
    font-family: "Lato",sans-serif;
    &:hover{
      border: none;
      color: white;
      font-size: 28px;
      transform:scale(1.1);
    }
  }
}

/* Buttons */
.purple{
  @include buttonBG($purple);
}
.blue{
  @include buttonBG($blue);
}
.lorange{
  @include buttonBG($l_orange);
}
.brick{
  @include buttonBG($brick);
}

 */
