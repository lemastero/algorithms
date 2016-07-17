
// search for dependencies with
// publicly disclosed vulnerabilities by OWASP
// https://github.com/orrsella/sbt-stats
// sbt check
addSbtPlugin("net.vonbuchholtz" % "sbt-dependency-check" % "0.1.1")

// ScalaStyle - static code analysis
// http://www.scalastyle.org/sbt.html
// sbt scalastyle
// sbt test:scalastyle
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.8.0")

// WartRemover - static code analysis - report FP violations
// https://github.com/puffnfresh/wartremover
// all actions trigger analysis
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "1.0.1")

// PMD:CPD - find dulications
// sbt cpd
addSbtPlugin("de.johoop" % "cpd4sbt" % "1.2.0")

// Scoverage - code coverage: https://github.com/scoverage/sbt-scoverage)
// sbt coverage test
// sbt coverageReport
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")

// itegration with service Coverall
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.1.0")
