package todofinch.todobackend

import io.finch._
import com.twitter.finagle.Http
import todofinagle.infra.db.mysqlDb.TodoRepositoryOnSql

class TodoEndpoint(url: String) {
  private[this] val root = path("todos")

  val todoRepo = new TodoRepositoryOnSql()
  
  val getTodosEndpoint: Endpoint[List[Todo]] = get(root) {
    todoRepo.getAllTodos().map(Ok)
  }
  val todoEndpoint = getTodosEndpoint.map
}