package im.tony.util

import im.tony.models.Todo
import javafx.collections.ObservableList
import tornadofx.observableListOf
import java.util.*

object FakeDatabase {
  private val allTodos_: List<Todo> = mutableListOf()

  val allTodos: ObservableList<Todo>
    get() = observableListOf(allTodos_)

  fun addTodo(todo: Todo) = allTodos.add(todo)

  fun searchTodo(text: String) = allTodos_.filter { it.containsText(text) }

  fun searchTodo(id: UUID) = allTodos_.filter { it.id == id }
}
