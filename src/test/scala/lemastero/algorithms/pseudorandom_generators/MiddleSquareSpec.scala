package lemastero.algorithms.pseudorandom_generators

import lemastero.algorithms.BaseSpec

class MiddleSquareSpec extends BaseSpec {

  it("MiddleSquare return square without edges") {
    MiddleSquare(1111).nextInt._1 mustBe MiddleSquare(2343)
    MiddleSquare(1111).nextInt._2 mustBe 2343

    MiddleSquare(1111).nextInt._1.nextInt._2 mustBe 4896
  }
}


