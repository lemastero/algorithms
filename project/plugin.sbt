// sbt dependencyBrowseTree
addDependencyTreePlugin

// https://github.com/orrsella/sbt-stats
// sbt stats
addSbtPlugin("com.orrsella" % "sbt-stats" % "1.0.7")

// search for dependencies with
// publicly disclosed vulnerabilities by OWASP
// sbt check
// addSbtPlugin("net.vonbuchholtz" % "sbt-dependency-check" % "0.1.1")

// WartRemover - static code analysis: https://github.com/puffnfresh/wartremover
// all actions trigger analysis
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "3.1.3")

// PMD:CPD - find duplications: https://github.com/sbt/sbt-cpd
// sbt cpd
// addSbtPlugin("de.johoop" % "cpd4sbt" % "1.0.0")

// Scoverage - code coverage: https://github.com/scoverage/sbt-scoverage)
// sbt coverage test
// sbt coverageReport
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.0.8")

// sbt dependencyUpdates
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")

// sbt scalafmtAll
addSbtPlugin("org.scalameta" % "sbt-scalafmt"  % "2.5.0")
