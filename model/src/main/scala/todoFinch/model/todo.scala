package todofinch.model

import io.circe._

case class Todo(id: Int, title: String, completed: Boolean, user_id: Int)

object Todo {
  def apply(id: Int, title: String, completed: Boolean, user_id: Int): Todo = {
    Todo(id: Int, title: String, completed: Boolean, user_id: Int)
  }
  implicit val encoder: Encoder[Todo] = 
    Encoder.forProduct4("id", "title", "completed", "user_id") {
      todo: Todo =>
        { (todo.id, todo.title, todo.completed, todo.user_id) }
    }
  implicit val decoder: Decoder[Todo] =
    Decoder.forProduct4("id", "title", "completed", "user_id")(
      (id: Int, title: String, completed: Boolean, user_id: Int) =>
        apply(id, title, completed, user_id)
    )
}