package im.tony.setup

import tornadofx.*

class BasicTestApp : App(BasicTestView::class) {
  override var scope: Scope = TestScope(this)
  val testScope = scope as TestScope
}
