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

  }

  describe("getPathTo") {

    it("returns list with root element when get root element") {
      val graph = AdjacencyListGraph(1)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(0) mustBe List(0)
    }

    it("returns empty list when given element is not connected") {
      val graph = AdjacencyListGraph(2)
      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(1) mustBe List.empty[Int]
    }

    it("returns root and argument when root is adjacent to argument") {
      val graph = AdjacencyListGraph(2)
      graph.addEdgeBetween(0, 1)

      val path = DepthFirstSearch(graph: Graph, 0)
      path.getPathTo(1) mustBe List(0, 1)
    }

  }

}
