package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph

/**
  * Breadth First Search (BFS) algorithm.
  *
  * Allows traversing and searching paths in graph data structures.
  * BFS pre-process graph starting from arbitrary node.
  * After pre-processing clients of BFS can query:
  * - if there is path between given node and starting one
  * - what is the path between given node and some other node
  */
case class BreadthFirstSearch(graph: Graph, root:Int) extends PathFinder {

  private val previousVertex: Array[Option[Int]] =
    Array.fill[Option[Int]](graph.numberOfVertices)(None)

  if(graph.numberOfVertices == 0) throw new PathFromEmptyGraph
  if(root >= graph.numberOfVertices) throw new PathFromNotExistingVertex

  initialize

  override def existsPathTo(destination: Int): Boolean =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else previousVertex(destination).isDefined

  override def getPathTo(destination: Int): List[Int] =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else if (previousVertex(destination).isEmpty) List()
    else createPathFor(destination, List[Int]())

  private def createPathFor(destination: Int, soFar:List[Int]): List[Int] =
    if (destination == root) root :: soFar
    else createPathFor(previousVertex(destination).get, destination :: soFar)

  private def initialize = {
    previousVertex(root) = Some(root)
    markPrevious(root)
    this
  }

  private def markPrevious(previous:Int): Unit =
    graph
      .adjacentVertices(previous)
      .filterNot( existsPathTo )
      .foreach( notVisited => {
        previousVertex(notVisited) = Some(previous)
        markPrevious(notVisited)
      })

}
