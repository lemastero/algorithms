package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph

/**
  * Depth First Search implementation of Path finder for graph.
  */
case class DepthFirstSearch(graph: Graph, root:Int) extends PathFinder {

  if(graph.numberOfVertices == 0) throw new PathFromEmptyGraph

  override def existsPathTo(destination: Int): Boolean = true

  override def getPathTo(destination: Int): List[Integer] = List(0)

}
