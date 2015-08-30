package com.klsoft.messaging.server.structures

case class User(name: String)

object UserObj /*extends json.DefaultJsonProtocol*/ {
  //implicit val userRead = jsonFormat1(User.apply)

  def userWrite(user: User): String = s"User?name=${user.name}"
}