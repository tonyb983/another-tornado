package im.tony.fragments

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.embed.swing.JFXPanel
import javafx.scene.layout.VBox
import javafx.util.converter.NumberStringConverter
import org.testfx.api.FxToolkit
import tornadofx.*

private class TestView : View() {
  override val root = VBox()
}

private class TestFrag : Fragment() {
  override val root = VBox()
}

class AcceptCancelModalTests : DescribeSpec({
  describe("AcceptCancelModal Tests.") {
    beforeEach {
      FxToolkit.registerPrimaryStage()
    }

    it("Can actually test TornadoFX views.") {
      //JFXPanel()
      val view = TestView()

      withClue("TestView should not be null.") {
        view.shouldNotBeNull()
      }

      val tf1 = view.textfield(SimpleStringProperty("something"))
      val tf2 = view.textfield(SimpleIntegerProperty(101), NumberStringConverter())
      val tf3 = view.textfield { bind(SimpleIntegerProperty(102)) }

      tf1.isVisible.shouldBeTrue()
      tf2.isVisible.shouldBeTrue()
      tf3.isVisible.shouldBeTrue()
    }

    it("Can actually test TornadoFX fragments.") {
      //JFXPanel()
      val frag = TestFrag()

      withClue("TestFrag should not be null.") {
        frag.shouldNotBeNull()
      }

      val tf1 = frag.textfield(SimpleStringProperty("something"))
      val tf2 = frag.textfield(SimpleIntegerProperty(101), NumberStringConverter())
      val tf3 = frag.textfield { bind(SimpleIntegerProperty(102)) }

      tf1.isVisible.shouldBeTrue()
      tf2.isVisible.shouldBeTrue()
      tf3.isVisible.shouldBeTrue()
    }

    xit("Can be created with zero config.") {
      val acm = AcceptCancelModal()

      withClue("AcceptCancelModal should not be null.") {
        acm.shouldNotBeNull()
      }
      val children = acm.getChildList()
      withClue("AcceptCancelModal should not be null.") {
        children.shouldNotBeNull()
      }

      children!!.shouldNotBeEmpty()

      println("AcceptCancelModal has ${children.size} children.")
    }
  }
})
