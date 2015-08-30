package com.klsoft.messaging.server.authentication

import akka.actor.Actor

import scala.collection.mutable
import com.klsoft.messaging.server.structures.UserToken
import com.klsoft.messaging.server.structures.User

class AuthenticationActor extends Actor {
  
  val authentication = new mutable.HashMap[UserToken, User]
  
  override def receive: Receive = {
    case AuthenticationAdd(token, user) =>
      authentication.put(token, user); sender ! true
    case AuthenticationRequest(token) =>
      sender ! authentication.contains(token)
    case AuthenticationRemove(token) =>
      authentication.remove(token); sender ! true
  }

}
