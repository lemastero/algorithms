package lemastero.algorithms.graph

import lemastero.algorithms.graph.AdjacencyMatrixGraph._

object AdjacencyMatrixGraph {

  type VertexEdges = Array[Boolean]
  type VerticesEdges = List[VertexEdges]

  def verticesNoEdges(size:Int): VerticesEdges =
    List.fill(size)( vertexNoEdges(size) )

  private def vertexNoEdges(size: Int): VertexEdges =
    new VertexEdges(size)
}

/**
  * Implementation of Graph using matrix with vertices edges
  *
  * It is simple but not efficient. Space cost is square(number of vertices).
  */
class AdjacencyMatrixGraph(val numberOfVertices:Int) extends Graph {

  val adjacent:VerticesEdges = verticesNoEdges(numberOfVertices)

  override def addEdgeBetween(firstVertex: Int, secondVertex: Int): Unit = {
    adjacent(firstVertex)(secondVertex) = true
    adjacent(secondVertex)(firstVertex) = true
  }

  override def adjacentVertices(vertex: Int): List[Int] =
    adjacent(vertex).indices
      .filter( adjacent(vertex)(_) )
      .toList

  override def numberOfEdges: Int =
    adjacent.map( countEdges ).sum / 2

  private def countEdges(edgeFlags:VertexEdges): Int =
    edgeFlags.count(_ == true)

  override def toString: String =
    adjacent.indices
      .map(
        vertex => adjacentVertices(vertex).mkString(s"Edges for $vertex: ", " " , "\n") )
      .mkString
}
