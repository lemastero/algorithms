package lemastero.algorithms.pseudorandom_generators

/**
  * Middle-square method, generate random 4 digit number
  *
  * John von Neumann, 1946 algorithm for pseudorandom number generator.
  * Some sequences repeat themselves very quickly: eg: 0000
  */
case class MiddleSquare(seed: Int) extends PRNG {

  def nextInt: (MiddleSquare, Int) = {
    val newSeed = "%06d".format( seed * seed / 100 ).drop(2).toInt
    ( new MiddleSquare(newSeed), newSeed )
  }

}
