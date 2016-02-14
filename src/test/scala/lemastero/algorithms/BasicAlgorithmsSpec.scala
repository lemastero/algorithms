package lemastero.algorithms

import lemastero.algorithms.tdd.BaseSpec
import lemastero.algorithms.BasicAlgorithms._

class BasicAlgorithmsSpec extends BaseSpec {

  describe("BasicAlgorithms") {
    describe("Greater Common Divisor") {
      it("of number and 1 is 1") {
        gcd(42,1) mustBe 1
      }

      it("gcd of 12 and 8 is ") {
        gcd(12,8) mustBe 4
      }

      it("gcd of 1071 and 462 is 7") {
        gcd(12,8) mustBe 4
      }
    }
  }
}
