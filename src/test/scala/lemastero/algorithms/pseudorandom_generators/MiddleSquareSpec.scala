package lemastero.algorithms.pseudorandom_generators

import org.scalatest.{MustMatchers, FunSuite}

class MiddleSquareSpec extends FunSuite with MustMatchers {

  test("MiddleSquare return square without edges") {
    MiddleSquare(1111).nextInt._1 mustBe MiddleSquare(2343)
    MiddleSquare(1111).nextInt._2 mustBe 2343

    MiddleSquare(1111).nextInt._1.nextInt._2 mustBe 4896
  }

}


