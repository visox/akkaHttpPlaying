package com.klsoft.messaging.client

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

object Client {
  implicit val system = ActorSystem("Messaging-server")
  implicit val mat = ActorMaterializer()
}
