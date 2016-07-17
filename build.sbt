name := "algorithms"

version := "0.0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.typelevel" %% "cats-core" % "0.6.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % Test

wartremoverWarnings ++= Warts.unsafe

enablePlugins(CopyPasteDetector)
