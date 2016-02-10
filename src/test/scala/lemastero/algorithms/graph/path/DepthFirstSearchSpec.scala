package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.{AdjacencyListGraph, Graph}
import org.scalatest.{FunSpec, MustMatchers}

class DepthFirstSearchSpec extends FunSpec with MustMatchers {

  describe("DepthFirstSearch") {

    it("throws PathFromEmptyGraph exception when try to construct from empty Graph") {
      val graph = AdjacencyListGraph(0)
      intercept[PathFromEmptyGraph] {
        DepthFirstSearch(graph: Graph, 42)
      }
    }
  }

  describe("DepthFirstSearch for single element graph") {

    it("exists path from root element") {
      val graph = AdjacencyListGraph(1)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.existsPathTo(0) mustBe true
    }

    it("path from root element to itself consist of one step") {
      val graph = AdjacencyListGraph(1)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(0) mustBe List(0)
    }

  }

}
