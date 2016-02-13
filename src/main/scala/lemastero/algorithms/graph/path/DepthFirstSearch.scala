package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph

case class DepthFirstSearch(graph:Graph, root:Int) extends PathFinder {

  if(graph.numberOfVertices == 0) throw new PathFromEmptyGraph
  if(root >= graph.numberOfVertices) throw new PathFromNotExistingVertex

  override def existsPathTo(destination: Int): Boolean = ???

  override def getPathTo(destination: Int): List[Int] = ???

}
