package jp.pilgrim

import akka.actor.{ActorSystem, Props}
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatest.matchers.must.Matchers

class SilentActor01Test extends TestKit(ActorSystem("testSystem"))
  with AnyWordSpecLike with Matchers with StopSystemAfterAll {
  // WordSpecLike/MustMatchers は3.2で廃止(https://www.scalatest.org/release_notes/3.2.0)

  // SilentActor
  "A Silent Actor" must {
    "change state when it receives a message, shingle threaded" in {
      import SilentActor._

      val silentActor = TestActorRef[SilentActor]
      silentActor ! SilentMessage("whisper1")
      // underlyingActor・・・内部アクター
      silentActor.underlyingActor.state must (contain("whisper1"))
    }

    "change state when it receives a message, multi-threaded" in {
      import SilentActor._

      val silentActor = system.actorOf(Props[SilentActor], "S3")
      silentActor ! SilentMessage("whisper1")
      silentActor ! SilentMessage("whisper2")
      silentActor ! GetState(testActor) // testActorはTestKit
      expectMsg(Vector("whisper1", "whisper2"))
    }
  }
}
