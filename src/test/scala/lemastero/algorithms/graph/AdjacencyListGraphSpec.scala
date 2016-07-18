package lemastero.algorithms.graph

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.graph.AdjacencyListGraph._

class AdjacencyListGraphSpec extends BaseSpec {

  describe("A Graph") {
    it("remembers number of vertices for new graph") {
      newGraph(1).numberOfVertices mustBe 1
    }

    it("has no edges for new graph") {
      newGraph(1).numberOfEdges mustBe 0
    }

    it("has 1 edge when there was two vertices connected") {
      newGraph(2, (0, 1)).numberOfEdges mustBe 1
    }
  }

  describe("adjacentVertices") {
    it("returns no vertices when there is no edge") {
      newGraph(2).adjacentVertices(0).size mustBe 0
      newGraph(2).adjacentVertices(1).size mustBe 0
    }

    it("returns other end of edge when one is added") {
      val graph = newGraph(3, (0, 1))

      graph.adjacentVertices(1).contains(0) mustBe true
      graph.adjacentVertices(0).contains(1) mustBe true
    }
  }
}
