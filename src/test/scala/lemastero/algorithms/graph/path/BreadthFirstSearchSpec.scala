package lemastero.algorithms.graph.path

import cats.data.{Validated, Xor}
import lemastero.algorithms.graph.{AdjacencyListGraph, Graph}
import org.scalatest.{FunSpec, MustMatchers}

class BreadthFirstSearchSpec
  extends FunSpec
    with MustMatchers {

  describe("factory method") {

    it("returns PathFromEmptyGraph exception when try to construct from empty Graph") {
      newBFSRaw(42, 0) mustBe Validated.Invalid(PathFromEmptyGraph)
    }

    it("returns PathFromNotExistingVertex exception when try to construct from not existing vertex") {
      newBFSRaw(1, 1) mustBe Validated.Invalid(PathFromNotExistingVertex(1, 1))
      newBFSRaw(42, 1) mustBe Validated.Invalid(PathFromNotExistingVertex(42, 1))
    }
  }

  describe("validations") {

    it("getPathTo returns VertexNotFound when given not existing vertex") {
      newBFS(0, 1).getPathTo(1) mustBe Xor.Left(VertexNotFound(1))
    }

    it("existsPathTo returns VertexNotFound when given not existing vertex") {
      newBFS(0, 1).existsPathTo(1) mustBe Xor.Left(VertexNotFound(1))
    }
  }

  describe("DepthFirstSearch for single element graph") {

    it("exists path from root element") {
      val path = newBFS(0, 1)
      assertExistsPathTo(path, 0)
    }

    it("path from root element to itself consist of one step") {
      val path = newBFS(0, 1)
      assertPathTo(path, 0, List(0))
    }

    it("returns list with root element when get root element") {
      val path = newBFS(0, 1)
      assertPathTo(path, 0, List(0))
    }
  }

  describe("DepthFirstSearch for 2 element graph") {

    it("getPathTo returns empty list when given element is not connected") {
      val path = newBFS(0, 2)
      assertPathTo(path, 1, List.empty[Int])
    }

    it("getPathTo returns root and argument when root is adjacent to argument") {
      val graph = newGraph(2, (0, 1))
      val path = newBFS(0, graph)
      assertPathTo(path, 1, List(0, 1))
    }

    it("existsPathTo returns false when argument is not root") {
      val path = newBFS(0, 2)
      assertNotExistsPathTo(path, 1)
    }
  }

  describe("DepthFirstSearch for complex graph") {

    it("existsPathTo returns true when there is obvious edge between 3 elements") {
      val graph = newGraph(3, (0,1), (1,2) )
      val path = newBFS(0, graph)
      assertExistsPathTo(path, 2)
    }

    it("getPathTo returns true when there is obvious edge between 3 elements") {
      val graph = newGraph(3, (0,1), (1,2) )
      val path = newBFS(0, graph)
      assertPathTo(path, 2, List(0, 1, 2))
    }

    it("getPathTo returns proper path when there is edge between 3 elements") {
      val graph = newGraph(3, (1, 2), (1, 0) )
      val path = newBFS(0, graph)
      assertPathTo(path, 2, List(0, 1, 2))
    }

    it("getPathTo returns empty list when no edge between given object and root") {
      val graph = newGraph(3, (1,2))
      val path = newBFS(0, graph)
      assertPathTo(path, 2, List[Int]())
    }

    it("getPathTo traverse first all path then leaves") {
      /*
       0 +- 1 -- 3 -- 4
         |            |
         +- 2 --------+
       */
      val graph = newGraph(13,
        (0, 1), (0, 2),
        (1, 3),
        (3, 4), (2, 4) )

      val path = newBFS(0, graph)

      assertPathTo(path, 4, List[Int](0, 2, 4))
    }

    it("getPathTo properly recognize complex graph") {
      val path = newBFS(
        0,
        newGraph(13,
          (0, 6), (0, 2), (0, 1), (0, 5),
          (6, 4),
          (4, 3), (4, 5),
          (3, 5),
          (7, 8),
          (9, 10), (9, 12), (9, 11),
          (11, 12) ))

      assertExistsPathTo(path, 6, 2, 1, 4, 3, 5)
      assertNotExistsPathTo(path, 7, 8, 9, 10, 11, 12)
      assertPathTo(path, 6, List[Int](0, 6))
      assertPathTo(path, 1, List[Int](0, 1))
      assertPathTo(path, 2, List[Int](0, 2))
      assertPathTo(path, 4, List[Int](0, 6, 4))
      assertPathTo(path, 3, List[Int](0, 5, 3))
    }

    it("getPathTo properly recognize complex graph 2") {
      val graph = newGraph(13,
        (0, 2), (0, 1), (0, 5),
        (2, 4), (2, 3), (2, 1),
        (4, 3),
        (3, 5) )

      val path = newBFS(0, graph)

      assertExistsPathTo(path, 0, 1, 2, 3, 4, 5)

      assertPathTo(path, 2, List[Int](0, 2))
      assertPathTo(path, 4, List[Int](0, 2, 4))
      assertPathTo(path, 3, List(0, 2, 3), List(0, 5, 3))
      assertPathTo(path, 1, List[Int](0, 1))
      assertPathTo(path, 5, List[Int](0, 5))
    }
  }

  private def assertNotExistsPathTo(path: PathFinder, destinations: Int*): Unit =
    destinations.foreach( dest =>
      path.existsPathTo(dest) mustBe Xor.Right(false)
    )

  private def assertExistsPathTo(path: PathFinder, destinations: Int*): Unit =
    destinations.foreach(dest =>
      path.existsPathTo(dest) mustBe Xor.Right(true)
    )

  private def assertPathTo(path: PathFinder, destination: Int, expected: List[Int]): Unit =
    path.getPathTo(destination) mustBe Xor.Right(expected)

  private def assertPathTo(path: PathFinder, destination: Int,
                           expected1: List[Int], expected2: List[Int]): Unit =
    path.getPathTo(destination) must (equal(Xor.Right(expected1)) or equal(Xor.Right(expected2)))

  private def newGraph(numberOfVertices: Int, edges: (Int, Int)*): Graph = {
    val graph = AdjacencyListGraph(numberOfVertices)
    edges.foreach(edge =>
      graph.addEdgeBetween(edge._1, edge._2)
    )
    graph
  }

  private def newBFS(root: Int, numberOfVertices: Int): PathFinder =
    newBFS(root, newGraph(numberOfVertices))

  private def newBFS(root: Int, graph: Graph): PathFinder =
    BreadthFirstSearch(graph, root).getOrElse(null)

  private def newBFSRaw(root: Int, numberOfVertices: Int): Validated[GraphCreateError, BreadthFirstSearch] =
    newBFSRaw(root, newGraph(numberOfVertices))

  private def newBFSRaw(root: Int, graph: Graph): Validated[GraphCreateError, BreadthFirstSearch] =
    BreadthFirstSearch(graph, root)
}
