package jp.pilgrim

import akka.actor.{Actor, ActorRef}

object SilentActor{
  case class SilentMessage(data: String)
  case class GetState(receiver: ActorRef)
}

class SilentActor extends Actor{
  import SilentActor._

  var internalState = Vector[String]()

  def receive = {
    case SilentMessage(data: String) =>
      internalState = internalState :+ data
    case GetState(receiver: ActorRef) =>
      receiver ! internalState
  }

  def state = internalState
}
