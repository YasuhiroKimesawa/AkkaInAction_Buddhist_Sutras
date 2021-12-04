package jp.pilgrim

import akka.actor.ActorSystem
import akka.testkit.{TestActorRef, TestKit}
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatest.matchers.must.Matchers

class SilentActor01Test extends TestKit(ActorSystem("testSystem"))
  with AnyWordSpecLike with Matchers with StopSystemAfterAll {
  // WordSpecLike/MustMatchers は3.2で廃止(https://www.scalatest.org/release_notes/3.2.0)

  // SilentActor
  "A Silent Actor" must {
    "change state when it receives a message, shingle threaded" in {
      import jp.pilgrim.SilentActor._

      val silentActor = TestActorRef[SilentActor]
      silentActor ! SilentMessage("wisper")
      // underlyingActor・・・内部アクター
      silentActor.underlyingActor.state must (contain("wisper"))
    }

    "change state when it receives a message, multi-threaded" in {
      fail("not implemented yet")
    }
  }
}
