package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.{AdjacencyListGraph, Graph}
import org.scalatest.{FunSpec, MustMatchers}

class DepthFirstSearchSpec extends FunSpec with MustMatchers {

  describe("DepthFirstSearch") {

    it("can be build from graph") {
      val graph = AdjacencyListGraph(0)
      intercept[PathFromEmptyGraph] {
        DepthFirstSearch(graph: Graph, 42)
      }
    }
  }

}
