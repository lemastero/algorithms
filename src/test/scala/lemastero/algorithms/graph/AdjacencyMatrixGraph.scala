package lemastero.algorithms.graph

import lemastero.algorithms.graph.AdjacencyMatrixGraph._
import lemastero.algorithms.graph.Graph._

object AdjacencyMatrixGraph {
  type VertexEdges = Array[Boolean]
  type VerticesEdges = List[VertexEdges]

  def newGraph(numberOfVertices: Int, edges: RawEdge*): AdjacencyMatrixGraph = {
    val adjacent = verticesNoEdges(numberOfVertices)
    edges.foreach(edge => addEdgeBetween(adjacent, edge._1, edge._2))
    new AdjacencyMatrixGraph(adjacent)
  }

  private def verticesNoEdges(size: Int): VerticesEdges =
    List.fill(size)(vertexNoEdges(size))

  private def vertexNoEdges(size: Int): VertexEdges =
    new VertexEdges(size)

  private def addEdgeBetween(
      adjacent: VerticesEdges,
      firstVertex: Int,
      secondVertex: Int
  ): Unit = {
    adjacent(firstVertex)(secondVertex) = true
    adjacent(secondVertex)(firstVertex) = true
  }
}

/** Implementation of Graph using matrix with vertices edges
  *
  * It is simple but not efficient. Space cost is square(number of vertices).
  */
class AdjacencyMatrixGraph(adjacent: VerticesEdges) extends Graph {

  override def numberOfVertices: Int = adjacent.size

  override def adjacentVertices(vertex: Int): List[Int] =
    adjacent(vertex).indices
      .filter(adjacent(vertex)(_))
      .toList

  override def numberOfEdges: Int =
    adjacent.map(countEdges).sum / 2

  private def countEdges(edgeFlags: VertexEdges): Int =
    edgeFlags.count(identity)

  override def toString: String =
    adjacent.indices
      .map(vertex =>
        adjacentVertices(vertex).mkString(s"Edges for $vertex: ", " ", "\n")
      )
      .mkString
}
