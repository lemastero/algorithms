package lemastero.algorithms.compression

import lemastero.algorithms.tdd.BaseSpec

class RunLengthCodingSpec extends BaseSpec {

  def uncompressed(setElements:Int*): Iterator[Int] =
    setElements.iterator

  val codec = new RunLengthCoding()

  describe("compress") {
    it("handle empty input") {
      val archive = codec.compress(uncompressed())
      assertArchive(archive)
    }

    it("handle 1 as input") {
      val archive = codec.compress(uncompressed(1))
      assertArchive(archive, 1)
    }

    it("handle multiple 1 as input") {
      val archive = codec.compress(uncompressed(1,1,1))
      assertArchive(archive, 3)
    }

    it("handle sequence of 1s 0s") {
      val archive = codec.compress(uncompressed(1,1,0))
      assertArchive(archive, 2,1)
    }

    it("handle sequence of 1s 0s 1s") {
      val archive = codec.compress(uncompressed(1,1,0,1,1,1))
      assertArchive(archive, 2,1,3)
    }

    it("handle sequence of 1s 0s 1s 0s") {
      val archive = codec.compress(uncompressed(1,1,0,1,1,1,0,0))
      assertArchive(archive, 2,1,3,2)
    }

    it("handle sequence of 0s") {
      val archive = codec.compress(uncompressed(0,0))
      assertArchive(archive, 0, 2)
    }

  }

  def assertArchive(archive:List[Int], expected:Int*): Unit = {
    archive mustBe expected.toList
  }
}
