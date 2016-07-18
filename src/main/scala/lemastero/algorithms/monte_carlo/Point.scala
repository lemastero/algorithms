package lemastero.algorithms.monte_carlo

case class Point(x:Double, y:Double) {

  def isInCircle(radius:Double) =
    x*x + y*y <= radius

}
