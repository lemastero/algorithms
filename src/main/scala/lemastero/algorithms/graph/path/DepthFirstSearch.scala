package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph
import lemastero.algorithms.graph.path.PathFinder.{VertexNotFound, PathFromNotExistingVertex}

/**
  * Depth First Search implementation of Path finder for graph.
  */
case class DepthFirstSearch(graph: Graph, root:Int) extends PathFinder {

  private val previousVertex:Array[Option[Int]] = Array.fill[Option[Int]](graph.numberOfVertices)(None)

  if(graph.numberOfVertices == 0) throw new PathFinder.PathFromEmptyGraph
  if(root >= graph.numberOfVertices) throw new PathFromNotExistingVertex

  previousVertex(root) = Some(root)
  graph.adjacentVertices(root).foreach(preprocessDfs)

  private def preprocessDfs(each: Int): Unit = {
    previousVertex(each) = Some(root)
  }

  override def existsPathTo(destination: Int): Boolean =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else true

  override def getPathTo(destination: Int): List[Int] =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else if (previousVertex(destination).isEmpty) List()
    else if (destination == root) List(root)
    else root :: List(destination)

}
