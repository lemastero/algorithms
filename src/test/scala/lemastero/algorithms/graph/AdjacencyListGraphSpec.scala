package lemastero.algorithms.graph

import org.scalatest.{MustMatchers, FunSpec}

class AdjacencyListGraphSpec extends FunSpec with MustMatchers {

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

}
