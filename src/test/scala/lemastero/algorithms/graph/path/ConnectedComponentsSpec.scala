package lemastero.algorithms.graph.path

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.graph.AdjacencyListGraph._

class ConnectedComponentsSpec extends BaseSpec {

  describe("areConnected") {
    val randomHigVertex = 42

    it("returns VertexNotFound when the first vertex do not exist in graph") {
      val cc = ConnectedComponents(newGraph(1))
      cc.areConnected(1, 0) mustBe Left(VertexNotFound(1))
      cc.areConnected(randomHigVertex, 0) mustBe Left(VertexNotFound(randomHigVertex))
    }

    it("returns VertexNotFound when second argument do not exist in graph") {
      val cc = ConnectedComponents(newGraph(1))
      cc.areConnected(0, 1) mustBe Left(VertexNotFound(1))
      cc.areConnected(0, randomHigVertex) mustBe Left(VertexNotFound(randomHigVertex))
    }

    it("returns false for not connected vertices") {
      val cc = ConnectedComponents(newGraph(2))
      cc.areConnected(1, 0) mustBe Right(false)
    }

    it("returns true for connected vertices") {
      val cc = ConnectedComponents(newGraph(2, (0, 1)))
      cc.areConnected(1, 0) mustBe Right(true)
    }

    it("returns true for connected vertices with non trivial connection") {
      val graph = newGraph(6, (0, 1), (1, 2), (1, 3), (2, 4), (4, 5), (5, 3))
      val cc = ConnectedComponents(graph)

      cc.areConnected(0, 3) mustBe Right(true)
    }

    it("returns false for separated connected groups") {
      val graph = newGraph(6, (0, 1), (1, 2), (4, 5))

      val cc = ConnectedComponents(graph)

      cc.areConnected(0, 5) mustBe Right(false)
      cc.areConnected(4, 5) mustBe Right(true)
    }
  }

  describe("numberOfComponents") {
    it("returns 0 for empty graph") {
      val cc = ConnectedComponents(newGraph(0))
      cc.numberOfComponents mustBe 0
    }

    it("returns 1 for single element graph") {
      val cc = ConnectedComponents(newGraph(1))
      cc.numberOfComponents mustBe 1
    }

    it("returns 2 for two element graph") {
      val cc = ConnectedComponents(newGraph(1))
      cc.numberOfComponents mustBe 1
    }

    it("returns 1 for connected graph") {
      val graph = newGraph(4, (0,1), (1,3), (2,3))
      val cc = ConnectedComponents(graph)
      cc.numberOfComponents mustBe 1
    }

    it("handle complicated case") {
      val graph = newGraph(8, (0,1), (1,3), (2,3), (4,5), (5,6))
      val cc = ConnectedComponents(graph)

      cc.numberOfComponents mustBe 3
    }
  }
}
