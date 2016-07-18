package lemastero.algorithms.monte_carlo

case class CircleInSquareSimulation(radius:Int) {

  val radiusSquare:Double = radius * radius
  val pointFactory = RandomPointFactory(radius)

  def run(times:Int): Int =
    (0 until times)
      .map( _ => pointFactory.randomPoint() )
      .count( _.isInCircle(radiusSquare) )

}
