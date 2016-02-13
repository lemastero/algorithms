package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph

/**
  * Depth First Search (DFS) algorithm.
  *
  * Allows traversing and searchiong paths in graph data structures.
  * DFS preprocess graph starting from arbitrary node.
  * Then clients can query DFS to find path between given vertex
  * and the initial one.
  *
  * Theseus could use it if Ariadne gave him a ball of thread
  * _and_ something to mark in which path he already went.
  *
  * Algorithm was investigated by Charles Pierre Tremaux
  * in context of solving mazes.
  */
case class DepthFirstSearch(graph: Graph, root:Int) extends PathFinder {

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
