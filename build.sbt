name := "messaging"

version := "1.0"

scalaVersion := "2.11.7"

val akkaVersion = "2.3.12"
val akkaHttpVersion = "1.0"

val scalaTestVersion  = "2.2.5"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"                                % scalaTestVersion % Test,
  "org.mockito" % "mockito-core"                                % "1.10.19" % Test,
  "com.typesafe.akka" %% "akka-actor"                           % akkaVersion,
  "com.typesafe.akka" %% "akka-stream-experimental"             % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-experimental"               % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit-experimental"       % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit"                         % akkaVersion % Test
)