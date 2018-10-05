package todofinch.todobackend

import io.finch._
import io.finch.syntax._
import io.finch.circe._
import io.circe.generic.auto._
import com.twitter.finagle.Http
import com.twitter.finagle.http.Response
import todofinch.model.Todo
import todofinch.infra.db.mysqlDb.TodoRepositoryOnSql

class TodoEndpoint(url: String) {
  private[this] val root = path("todos")

  val todoRepo = new TodoRepositoryOnSql()

  def genResponse(){
    val res = Response()
    res.setContentType("application/xml")
  }
  
  val getTodosEndpoint: Endpoint[Seq[Todo]] = get("todos") {
    val res = todoRepo.getAllTodos
    println(res)
    Ok(todoRepo.getAllTodos)
  }
  def apply(url:String) = getTodosEndpoint
}