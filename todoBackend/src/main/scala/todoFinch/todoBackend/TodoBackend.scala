package todofinch.todobackend

import java.util.UUID
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Duration, Future, JavaTimer}
import todofinch.model.Todo
import todofinch.infra.db.mysqlDb.TodoRepositoryOnSql

object TodoBackend extends App {
  // val router = RoutingService.byPathObject[Request] {
  //   case Root / "todos" => getAllTodosService
  //   case Root / "todos"/ "create" => addTodoSerivce
  //   case _ => blackHole
  // }
  val todoEndpoint = new TodoEndpoint("localhost")

  //val serviceWithTimeout = timeoutFilter.andThen(todoService)
  //val server = Http.serve(":8081", todoService)
  val server = Http.serve(":8081", todoEndpoint.getTodosEndpoint.toService)

  Await.ready(server)
}