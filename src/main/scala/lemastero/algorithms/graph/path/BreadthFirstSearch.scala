package lemastero.algorithms.graph.path

import cats.data.Validated
import lemastero.algorithms.graph.Graph

object BreadthFirstSearch {

  def apply(
      graph: Graph,
      root: Int
  ): Validated[GraphCreateError, BreadthFirstSearch] =
    if (graph.numberOfVertices == 0)
      Validated.Invalid(PathFromEmptyGraph)
    else if (root >= graph.numberOfVertices)
      Validated.Invalid(PathFromNotExistingVertex(root, graph.numberOfVertices))
    else Validated.Valid(new BreadthFirstSearch(graph, root))
}

/** Breadth First Search (BFS) algorithm.
  *
  * Breadth First Search differs from Depth First Search by using queue to
  * enforce processing siblings before children.
  */
class BreadthFirstSearch(graph: Graph, root: Int) extends PathFinder {

  private val previousVertex: Array[Option[Int]] =
    Array.fill[Option[Int]](graph.numberOfVertices)(None)

  initialize()

  override def existsPathTo(destination: Int): Either[VertexNotFound, Boolean] =
    if (destination >= graph.numberOfVertices) Left(VertexNotFound(destination))
    else Right(previousVertex(destination).isDefined)

  /** Return the shortest path fro vertex provided as argument to the root
    * vertex
    */
  override def getPathTo(destination: Int): Either[VertexNotFound, List[Int]] =
    if (destination >= graph.numberOfVertices) Left(VertexNotFound(destination))
    else if (previousVertex(destination).isEmpty) Right(List())
    else Right(createPathFor(destination, List[Int]()))

  private def createPathFor(destination: Int, soFar: List[Int]): List[Int] =
    if (destination == root) root :: soFar
    else
      previousVertex(destination) match {
        case Some(dest) => createPathFor(dest, destination :: soFar)
        case None       => List()
      }

  private def initialize(): Unit = {
    previousVertex(root) = Some(root)
    markPreviousVertices(List(root))
  }

  private def markPreviousVertices(elementsToProcess: List[Int]): Unit =
    if (elementsToProcess.nonEmpty) {
      val toProcess = graph
        .adjacentVertices(elementsToProcess.head)
        .filterNot(existsPathTo(_).getOrElse(false))
      toProcess.foreach(previousVertex(_) = Some(elementsToProcess.head))
      markPreviousVertices(elementsToProcess.tail ++ toProcess)
    }
}
