package lemastero.algorithms.graph.path

import cats.data.Xor
import lemastero.algorithms.graph.Graph

/**
  * Preprocess graph to be able to query:
  * - if two vertices are connected
  * - how many components there is
  *
  * Pre-processing is based on Depth First Search algorithm.
  */
case class ConnectedComponents(graph:Graph) {

  private val componentsId: Array[Option[Int]] =
    Array.fill[Option[Int]](graph.numberOfVertices)(None)

  initialize()

  def areConnected(first: Int, second:Int): Xor[VertexNotFound, Boolean] =
    if (first >= graph.numberOfVertices)
      Xor.Left(VertexNotFound(first))
    else if(second >= graph.numberOfVertices)
      Xor.Left(VertexNotFound(second))
    else Xor.Right(componentsId(first) == componentsId(second))

  def numberOfComponents: Int = componentsId.toSet.size

  private def initialize() =
    while (componentsId.exists(_.isEmpty))
      markComponent(componentsId.indexOf(None))

  private def markComponent(componentId: Int) = {
    componentsId(componentId) = Some(componentId)
    markAdjacentVertices(componentId, componentId)
  }

  private def markAdjacentVertices(vertex: Int, componentId:Int): Unit =
    graph.adjacentVertices(vertex)
      .filter(componentsId(_).isEmpty)
      .foreach( notVisited => {
        componentsId(notVisited) = Some(componentId)
        markAdjacentVertices(notVisited, componentId)
      })
}
