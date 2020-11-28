package im.tony.models

import javafx.collections.ObservableList
import tornadofx.observableListOf
import java.time.Instant
import java.util.UUID

data class Todo(
  val id: UUID = UUID.randomUUID(),
  val title: String,
  val content: String,
  val created: Instant = Instant.now(),
  val tags: ObservableList<String> = observableListOf()
) {
  fun containsText(text: String) =
    title.contains(text, true) ||
    content.contains(text, true) ||
    tags.any { it.contains(text, true) }
}
