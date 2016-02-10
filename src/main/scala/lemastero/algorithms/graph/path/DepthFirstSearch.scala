package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph
import lemastero.algorithms.graph.path.PathFinder.{VertexNotFound, PathFromNotExistingVertex}

/**
  * Depth First Search implementation of Path finder for graph.
  */
case class DepthFirstSearch(graph: Graph, root:Int) extends PathFinder {

  private val previousVertex:Array[Option[Int]] = Array.fill[Option[Int]](graph.numberOfVertices)(None)
  private val visitedVertices:Array[Boolean] = Array.fill[Boolean](graph.numberOfVertices)(false)

  if(graph.numberOfVertices == 0) throw new PathFinder.PathFromEmptyGraph
  if(root >= graph.numberOfVertices) throw new PathFromNotExistingVertex

  previousVertex(root) = Some(root)
  preprocessDfs(root)

  private def preprocessDfs(previous:Int): Unit = {
    visitedVertices(previous) = true
    graph
      .adjacentVertices(previous)
      .filter( adjacent => ! visitedVertices(adjacent))
      .foreach( notVisited => {
        previousVertex(notVisited) = Some(previous)
        preprocessDfs(notVisited)
      })
  }

  override def existsPathTo(destination: Int): Boolean =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else previousVertex(destination).isDefined

  override def getPathTo(destination: Int): List[Int] =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else if (previousVertex(destination).isDefined) createPathFor(destination)
    else List()

  private def createPathFor(destination: Int): List[Int] =
    if (destination == root) List(root)
    else root :: List(destination)

}
