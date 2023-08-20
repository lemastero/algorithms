package lemastero.algorithms

import lemastero.algorithms.Collisions.collisions

class CollisionsSpec extends BaseSpec {

    describe("BasicAlgorithms") {
      it("recognize no collision") {
        collisions(List(1, 2)) mustBe 0
      }
      it("recognize single collision") {
        collisions( List(3, 1, 4, 1, 5, 9, 2, 6) ) mustBe 1
      }
      it("recognize multiple collisions") {
        collisions(List(3, 1, 1, 1, 3)) mustBe 3
      }
      it("handle neg numbers") {
        collisions(List(-3)) mustBe 0
      }
    }
}
