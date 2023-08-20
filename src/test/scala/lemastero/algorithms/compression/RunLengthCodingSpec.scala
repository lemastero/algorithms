package lemastero.algorithms.compression

import lemastero.algorithms.BaseSpec

class RunLengthCodingSpec extends BaseSpec {

  def createSequence(setElements: Int*): Iterator[Int] =
    setElements.iterator

  val codec = new RunLengthCoding()

  describe("compress") {
    it("handle empty input") {
      assertCompress(createSequence())
    }

    it("handle 1 as input") {
      assertCompress(createSequence(1), 1)
    }

    it("handle multiple 1 as input") {
      assertCompress(createSequence(1, 1, 1), 3)
    }

    it("handle sequence of 1s 0s") {
      assertCompress(createSequence(1, 1, 0), 2, 1)
    }

    it("handle sequence of 1s 0s 1s") {
      assertCompress(createSequence(1, 1, 0, 1, 1, 1), 2, 1, 3)
    }

    it("handle sequence of 1s 0s 1s 0s") {
      assertCompress(createSequence(1, 1, 0, 1, 1, 1, 0, 0), 2, 1, 3, 2)
    }

    it("handle sequence of 0s") {
      assertCompress(createSequence(0, 0), 0, 2)
    }
  }

  def assertCompress(input: Iterator[Int], array: Int*) = {
    val archived = codec.compress(input)
    assertOutputEquals(archived, array.toList)
  }

  def assertDecompress(input: Iterator[Int], array: Int*) = {
    val archived = codec.decompress(input)
    assertOutputEquals(archived, array.toList)
  }

  def assertOutputEquals(archive: List[Int], expected: List[Int]) =
    archive mustBe expected

  describe("decompress") {
    it("handles empty input") {
      assertDecompress(createSequence())
    }

    it("handles single element input") {
      assertDecompress(createSequence(2), 1, 1)
    }

    it("handles 1s and 0s compressed") {
      assertDecompress(createSequence(1, 2), 1, 0, 0)
    }

    it("handles 1s and 0s 1s compressed") {
      assertDecompress(createSequence(1, 3, 2), 1, 0, 0, 0, 1, 1)
    }

    it("handles 0s at beginning") {
      assertDecompress(createSequence(0, 2), 0, 0)
    }
  }
}
