package lemastero.algorithms.graph.path

import cats.data.Xor
import lemastero.algorithms.BaseSpec
import lemastero.algorithms.graph.AdjacencyListGraph

class ConnectedComponentsSpec extends BaseSpec {

  describe("areConnected") {
    val randomHigVertex = 42

    it("returns VertexNotFound when the first vertex do not exist in graph") {
      val cc = ConnectedComponents(AdjacencyListGraph(1))
      cc.areConnected(1, 0) mustBe Xor.left(VertexNotFound(1))
      cc.areConnected(randomHigVertex, 0) mustBe Xor.left(VertexNotFound(randomHigVertex))
    }

    it("returns VertexNotFound when second argument do not exist in graph") {
      val cc = ConnectedComponents(AdjacencyListGraph(1))
      cc.areConnected(0, 1) mustBe Xor.left(VertexNotFound(1))
      cc.areConnected(0, randomHigVertex) mustBe Xor.left(VertexNotFound(randomHigVertex))
    }

    it("returns false for not connected vertices") {
      val cc = ConnectedComponents(AdjacencyListGraph(2))
      cc.areConnected(1, 0) mustBe Xor.Right(false)
    }

    it("returns true for connected vertices") {
      val graph = AdjacencyListGraph(2)
      graph.addEdgeBetween(0, 1)
      val cc = ConnectedComponents(graph)
      cc.areConnected(1, 0) mustBe Xor.Right(true)
    }

    it("returns true for connected vertices with non trivial connection") {
      val graph = AdjacencyListGraph(6)
      graph.addEdgeBetween(0, 1)
      graph.addEdgeBetween(1, 2)
      graph.addEdgeBetween(1, 3)
      graph.addEdgeBetween(2, 4)
      graph.addEdgeBetween(4, 5)
      graph.addEdgeBetween(5, 3)

      val cc = ConnectedComponents(graph)

      cc.areConnected(0, 3) mustBe Xor.Right(true)
    }

    it("returns false for separated connected groups") {
      val graph = AdjacencyListGraph(6)
      graph.addEdgeBetween(0, 1)
      graph.addEdgeBetween(1, 2)
      graph.addEdgeBetween(4, 5)

      val cc = ConnectedComponents(graph)

      cc.areConnected(0, 5) mustBe Xor.Right(false)
      cc.areConnected(4, 5) mustBe Xor.Right(true)
    }
  }

  describe("numberOfComponents") {
    it("returns 0 for empty graph") {
      val cc = ConnectedComponents(AdjacencyListGraph(0))
      cc.numberOfComponents mustBe 0
    }

    it("returns 1 for single element graph") {
      val cc = ConnectedComponents(AdjacencyListGraph(1))
      cc.numberOfComponents mustBe 1
    }

    it("returns 2 for two element graph") {
      val cc = ConnectedComponents(AdjacencyListGraph(1))
      cc.numberOfComponents mustBe 1
    }

    it("returns 1 for connected graph") {
      val graph = AdjacencyListGraph(4)
      graph.addEdgeBetween(0,1)
      graph.addEdgeBetween(1,3)
      graph.addEdgeBetween(2,3)

      val cc = ConnectedComponents(graph)

      cc.numberOfComponents mustBe 1
    }

    it("handle complicated case") {
      val graph = AdjacencyListGraph(8)
      graph.addEdgeBetween(0,1)
      graph.addEdgeBetween(1,3)
      graph.addEdgeBetween(2,3)
      graph.addEdgeBetween(4,5)
      graph.addEdgeBetween(5,6)

      val cc = ConnectedComponents(graph)

      cc.numberOfComponents mustBe 3
    }
  }

}
