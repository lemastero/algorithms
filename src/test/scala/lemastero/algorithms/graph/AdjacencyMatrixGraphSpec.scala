package lemastero.algorithms.graph

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.graph.AdjacencyMatrixGraph._

class AdjacencyMatrixGraphSpec extends BaseSpec {

  describe("numberOfVertices") {
    it("remembers number of vertices given at creation") {
      newGraph(3).numberOfVertices mustBe 3
    }
  }

  describe("numberOfEdges") {
    it("remembers number of vertices for new graph") {
      newGraph(3).numberOfVertices mustBe 3
    }

    it("has no edges for new graph") {
      newGraph(3).numberOfEdges mustBe  0
    }

    it("returns 1 when there was two vertices connected") {
      newGraph(3, (0, 1)).numberOfEdges mustBe 1
    }

    it("has 2 edges when we connect two times two vertices") {
      newGraph(3, (0, 1), (1, 2)).numberOfEdges mustBe 2
    }

    it("has 1 edges when we connect two times the same vertices") {
      newGraph(3, (0, 1), (1, 0)).numberOfEdges mustBe 1
    }
  }

  describe("adjacentVertices") {
    it("returns no vertices when there is no edge") {
      val graph = newGraph(2)
      graph.adjacentVertices(0).size mustBe 0
      graph.adjacentVertices(1).size mustBe 0
    }

    it("returns other end of edge when one is added") {
      val graph = newGraph(3, (0,1))

      graph.adjacentVertices(1).size mustBe 1
      graph.adjacentVertices(1).contains(0) mustBe true

      graph.adjacentVertices(0).size mustBe 1
      graph.adjacentVertices(0).contains(1) mustBe true
    }

    it("returns both added edges") {
      val graph = newGraph(3, (0,1), (0,2))

      graph.adjacentVertices(0).size mustBe 2
      graph.adjacentVertices(0).contains(1) mustBe true
      graph.adjacentVertices(0).contains(2) mustBe true
    }
  }
}
