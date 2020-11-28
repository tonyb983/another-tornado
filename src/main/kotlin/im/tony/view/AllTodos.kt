package im.tony.view

import im.tony.util.FakeDatabase
import tornadofx.*

class AllTodos : View("My View") {
  override val root = borderpane {
    listview(FakeDatabase.allTodos)
    bottom<MenuBar>()
  }
}
