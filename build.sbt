name := "algorithms"

version := "0.0.1"

scalaVersion := "2.13.11"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.10.0",
  "org.scalatest" %% "scalatest" % "3.2.16" % Test
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8"
)

//wartremoverWarnings ++= Warts.all
