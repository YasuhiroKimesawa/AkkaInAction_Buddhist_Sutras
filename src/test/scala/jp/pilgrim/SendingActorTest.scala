package jp.pilgrim

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

import scala.util.Random

class SendingActorTest extends TestKit(ActorSystem("testsystem"))
  with AnyWordSpecLike with Matchers with StopSystemAfterAll {

  "A Sending Actor" must {
    "send a message to another actor when it has finished processing" in {
      import SendingActor._

      val props = SendingActor.props(testActor)
      val sendingActor = system.actorOf(props, "sendingActor")

      val size = 1000
      val maxInclusive = 100000

      def randomEvents() = (0 until size).map( _ => Event(Random.nextInt(maxInclusive))).toVector

      val unsorted = randomEvents()
      val sortEvents = SortEvents(unsorted)
      sendingActor ! sortEvents

      expectMsgPF(){
        case SortedEvents(events) =>
          events.size must be(size)
          unsorted.sortBy(_.id) must be(events)
      }

    }
  }
}
