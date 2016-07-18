package lemastero.algorithms.monte_carlo

import scala.util.Random

case class RandomPointFactory(radius:Int) {

  private val random = new Random()

  def randomPoint() =
    new Point(randomSmallerThan, randomSmallerThan)

  private def randomSmallerThan: Double =
    (2.0 * random.nextDouble() - 1.0) * radius

}
