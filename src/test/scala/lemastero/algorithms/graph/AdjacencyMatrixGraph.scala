package lemastero.algorithms.graph

import lemastero.algorithms.graph.AdjacencyMatrixGraph._

object AdjacencyMatrixGraph {

  type VertexEdges = Array[Boolean]
  type VerticesEdges = List[VertexEdges]

  def noEdges(count:Int): VerticesEdges =
    List.fill(count)(new VertexEdges(count))

}

/**
  * This is ineficient implementation of Graph that uses
  * matrix that holds connection between each vertices.
  *
  * It is simple but is very space consuming square(number of vertices)
  */
class AdjacencyMatrixGraph(val numberOfVertices:Int) extends Graph {

  val adjacent = noEdges(numberOfVertices)

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
