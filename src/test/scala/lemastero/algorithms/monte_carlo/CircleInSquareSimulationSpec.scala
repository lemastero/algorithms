package lemastero.algorithms.monte_carlo

import org.scalatest.{MustMatchers, FunSpec}

class CircleInSquareSimulationSpec extends FunSpec with MustMatchers {

  // 3156
  // 4000
  describe("runs simulation given number of times") {
    val simulation = CircleInSquareSimulation(4)
    (simulation.run(2) >= 0) mustBe true
  }

}
