package com.klsoft.messaging.server.PublishSubscriberApi

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.klsoft.messaging.server.structures.{User, UserObj}
import org.scalatest.{Matchers, WordSpec}

class PublishSubscriberApiTest extends WordSpec with Matchers  with ScalatestRouteTest{
  val api = new PublishSubscriberApi {}

  "PublishSubscriberApi" should {
    "give an user a token and authorize him based on that" in {
      val user = User("michal")

      Get("/users/is-loged/" + UserObj.userWrite(user)) ~> api.usersRoutes ~> check {
        val response = responseAs[String]
        response shouldBe "false"
      }

      Get("/users/login/" + UserObj.userWrite(user)) ~> api.usersRoutes ~> check {
       val response = responseAs[String]
       response should not be ""
      }

      Get("/users/is-loged/" + UserObj.userWrite(user)) ~> api.usersRoutes ~> check {
        val response = responseAs[String]
        response shouldBe "true"
      }

      Get("/users/logout/" + UserObj.userWrite(user)) ~> api.usersRoutes ~> check {
        val response = responseAs[String]
        response should not be ""
      }

      Get("/users/is-loged/" + UserObj.userWrite(user)) ~> api.usersRoutes ~> check {
        val response = responseAs[String]
        response shouldBe "false"
      }
    }
  }
}
