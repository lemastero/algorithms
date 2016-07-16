// ScalaStyle - static code analysis
// http://www.scalastyle.org/sbt.html
// sbt scalastyle
// sbt test:scalastyle
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

// WartRemover - static code analysis - report FP violations
// https://github.com/puffnfresh/wartremover
// all actions trigger analysis
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "1.0.1")
