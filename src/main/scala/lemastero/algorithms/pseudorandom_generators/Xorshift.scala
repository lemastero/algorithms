package lemastero.algorithms.pseudorandom_generators

/** Xorshift algoritym George Marsaglia, 2003
  *
  * https://en.wikipedia.org/wiki/Xorshift http://xoroshiro.di.unimi.it/
  */
case class Xorshift(seed: Int) {

  def nextInt: (Int, Xorshift) = {
    val x1 = seed ^ (seed << 21)
    val x2 = x1 ^ (x1 >>> 35)
    val newSeed = x2 ^ (x2 << 4)
    (newSeed, Xorshift(newSeed))
  }
}
