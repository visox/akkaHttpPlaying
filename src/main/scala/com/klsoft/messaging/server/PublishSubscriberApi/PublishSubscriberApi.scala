package com.klsoft.messaging.server.PublishSubscriberApi

import akka.actor.{ Props, ActorSystem }
import akka.http.scaladsl.server.Directives._
import com.klsoft.messaging.server.authentication.{ AuthenticationRequest, AuthenticationRemove, AuthenticationAdd, AuthenticationActor }
import com.klsoft.messaging.server.utils.BearerTokenGenerator
import com.klsoft.messaging.server.structures.User
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.util.{ Success, Failure }

trait PublishSubscriberApi {
  implicit private val system = ActorSystem("PublishSubscriberApi")
  implicit private val ec = system.dispatcher
  private val authenticatorRef = system.actorOf(Props[AuthenticationActor], "AuthenticationActor")
  implicit val timeout: Timeout = 10 second
  type AuthenticationResult = Boolean

  val usersRoutes =
    get {
      pathPrefix("users") {
        pathPrefix("logout") {

          parameters('name).as(User) { user ⇒
            val token = BearerTokenGenerator.getUserToken(user)
            val res = (authenticatorRef ? AuthenticationRemove(token)).mapTo[AuthenticationResult]

            onComplete(res) {
              case Success(_)  ⇒ complete(token.token)
              case Failure(ex) ⇒ failWith(ex)
            }

          }
        } ~
          pathPrefix("login") {
            parameters('name).as(User) { user ⇒
              val token = BearerTokenGenerator.getUserToken(user)
              val res = (authenticatorRef ? AuthenticationAdd(token, user)).mapTo[AuthenticationResult]

              onComplete(res) {
                case Success(_)  ⇒ complete(token.token)
                case Failure(ex) ⇒ failWith(ex)
              }
            }
          } ~
          pathPrefix("is-loged") {
            parameters('name).as(User) { user ⇒
              val token = BearerTokenGenerator.getUserToken(user)
              val res = (authenticatorRef ? AuthenticationRequest(token)).mapTo[AuthenticationResult]

              onComplete(res) {
                case Success(result) ⇒ complete(result.toString)
                case Failure(ex)     ⇒ failWith(ex)
              }
            }
          }
      }
    }
}
