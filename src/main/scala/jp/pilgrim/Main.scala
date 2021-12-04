package jp.pilgrim

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

object Main extends App with RequestTimeout{
  val config = ConfigFactory.load()
  var host = config.getString("http.host")
  var port = config.getInt("http.port")

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher


}

trait RequestTimeout {

}