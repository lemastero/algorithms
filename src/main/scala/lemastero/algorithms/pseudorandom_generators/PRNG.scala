package lemastero.algorithms.pseudorandom_generators

/** Pseudo random number generator */
trait PRNG {
  def nextInt: (PRNG, Int)
}
