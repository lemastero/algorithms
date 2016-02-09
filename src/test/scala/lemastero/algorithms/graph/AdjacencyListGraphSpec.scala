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

  }

}
