package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.{AdjacencyListGraph, Graph}
import org.scalatest.{FunSpec, MustMatchers}

class DepthFirstSearchSpec extends FunSpec with MustMatchers {

  describe("factory method") {

    it("throws PathFromEmptyGraph exception when try to construct from empty Graph") {
      val graph = AdjacencyListGraph(0)
      intercept[PathFinder.PathFromEmptyGraph] {
        DepthFirstSearch(graph: Graph, 42)
      }
    }

    it("throws PathFromNotExistingVertex exception when try to construct from not existing vertex") {
      val graph = AdjacencyListGraph(1)
      intercept[PathFinder.PathFromNotExistingVertex] {
        DepthFirstSearch(graph: Graph, 1)
      }
    }
  }

  describe("validations") {

    it("getPathTo throws VertexNotFound when given not existing vertex") {
      val graph = AdjacencyListGraph(1)
      val path = DepthFirstSearch(graph: Graph, 0)
      intercept[PathFinder.VertexNotFound] {
        path.getPathTo(1)
      }
    }

    it("existsPathTo throws VertexNotFound when given not existing vertex") {
      val graph = AdjacencyListGraph(1)
      val path = DepthFirstSearch(graph: Graph, 0)
      intercept[PathFinder.VertexNotFound] {
        path.existsPathTo(1)
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

    it("returns list with root element when get root element") {
      val graph = AdjacencyListGraph(1)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(0) mustBe List(0)
    }

  }

  describe("DepthFirstSearch for 2 element graph") {

    it("getPathTo returns empty list when given element is not connected") {
      val graph = AdjacencyListGraph(2)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(1) mustBe List.empty[Int]
    }

    it("getPathTo returns root and argument when root is adjacent to argument") {
      val graph = AdjacencyListGraph(2)
      graph.addEdgeBetween(0, 1)

      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(1) mustBe List(0, 1)
    }

    it("existsPathTo returns false when argument is not root") {
      val graph = AdjacencyListGraph(2)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.existsPathTo(1) mustBe false
    }

  }

  describe("DepthFirstSearch for complex graph") {

    it("existsPathTo returns true when there is obvious edge between 3 elements") {
      val graph = AdjacencyListGraph(3)
      graph.addEdgeBetween(0,1)
      graph.addEdgeBetween(1,2)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.existsPathTo(2) mustBe true
    }

    it("getPathTo returns true when there is obvious edge between 3 elements") {
      val graph = AdjacencyListGraph(3)
      graph.addEdgeBetween(0,1)
      graph.addEdgeBetween(1,2)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    it("getPathTo returns proper path when there is edge between 3 elements") {
      val graph = AdjacencyListGraph(3)
      graph.addEdgeBetween(1, 2)
      graph.addEdgeBetween(1, 0)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(2) mustBe List(0, 1, 2)
    }

    it("getPathTo returns empty list when no edge between given object and root") {
      val graph = AdjacencyListGraph(3)
      graph.addEdgeBetween(1,2)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(2) mustBe List[Int]()
    }

    it("getPathTo properly recognize complex graph") {
      val graph = AdjacencyListGraph(13)
      graph.addEdgeBetween(0,6)
      graph.addEdgeBetween(0,2)
      graph.addEdgeBetween(0,1)
      graph.addEdgeBetween(0,5)

      graph.addEdgeBetween(6,4)
      graph.addEdgeBetween(4,3)
      graph.addEdgeBetween(4,5)

      graph.addEdgeBetween(3,5)

      graph.addEdgeBetween(7,8)

      graph.addEdgeBetween(9,10)
      graph.addEdgeBetween(9,12)
      graph.addEdgeBetween(9,11)

      graph.addEdgeBetween(11,12)
      val path = DepthFirstSearch(graph: Graph, 0)

      path.existsPathTo(6) mustBe true
      path.existsPathTo(2) mustBe true
      path.existsPathTo(1) mustBe true
      path.existsPathTo(4) mustBe true
      path.existsPathTo(3) mustBe true
      path.existsPathTo(5) mustBe true

      path.existsPathTo(7) mustBe false
      path.existsPathTo(8) mustBe false
      path.existsPathTo(9) mustBe false
      path.existsPathTo(10) mustBe false
      path.existsPathTo(11) mustBe false
      path.existsPathTo(12) mustBe false

      path.getPathTo(6) mustBe List[Int](0, 6)
      path.getPathTo(1) mustBe List[Int](0, 1)
      path.getPathTo(2) mustBe List[Int](0, 2)
      path.getPathTo(4) mustBe List[Int](0, 6, 4)
      path.getPathTo(3) mustBe List[Int](0, 6, 4, 3)
    }

  }

}



