package lemastero.algorithms.graph

import lemastero.algorithms.BaseSpec

class AdjacencyListGraphSpec extends BaseSpec {

  describe("A Graph") {

    it("can be created") {
      val graph:Graph = AdjacencyListGraph(0)
    }

    it("remembers number of vertices for new graph") {
      val graph:Graph = AdjacencyListGraph(1)
      graph.numberOfVertices mustBe 1
    }

    it("has no edges for new graph") {
      val graph = AdjacencyListGraph(1)
      graph.numberOfEdges mustBe  0
    }

    it("has 1 edge when there was two vertices connected") {
      val graph = AdjacencyListGraph(2)
      graph.addEdgeBetween(0, 1)
      graph.numberOfEdges mustBe 1
    }

  }

  describe("adjacentVertices") {

    it("returns no vertices when there is no edge") {
      val graph = AdjacencyListGraph(2)
      graph.adjacentVertices(0).size mustBe 0
      graph.adjacentVertices(1).size mustBe 0
    }

    it("returns other end of edge when one is added") {
      val graph = AdjacencyListGraph(3)
      graph.addEdgeBetween(0, 1)

      graph.adjacentVertices(1).contains(0) mustBe true
      graph.adjacentVertices(0).contains(1) mustBe true
    }
  }

}
