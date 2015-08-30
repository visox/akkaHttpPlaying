package com.klsoft.messaging.server.utils

import com.typesafe.config.ConfigFactory

class Config {
  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")

  val httpInterface = httpConfig.getString("interface")
  val httpPort = httpConfig.getInt("port")
}