package jp.pilgrim

import akka.actor.Actor

class SilentActor extends Actor{
  def receive {
    case msg =>
  }
}
