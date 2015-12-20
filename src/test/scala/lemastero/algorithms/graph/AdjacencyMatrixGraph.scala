package lemastero.algorithms.graph

import scala.collection.mutable
import lemastero.algorithms.graph.AdjacencyMatrixGraph._

object AdjacencyMatrixGraph {

  type VertexEdges = Array[Boolean]
  type VerticesEdges = Array[VertexEdges]

  def noEdges(count:Int): VerticesEdges =
    Array.fill(count)(new VertexEdges(count))

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

  override def adjacentVertices(vertex: Int): mutable.ListBuffer[Int] = {
    val result = mutable.ListBuffer[Int]()
    for(index2 <- adjacent(vertex).indices) {
      if (adjacent(vertex)(index2) && !result.contains(index2)) {
        result += index2
      }
    }
    result
  }

  override def numberOfEdges: Int =
    adjacent.map( countEdges ).sum / 2

  private def countEdges(edges:VertexEdges): Int =
    edges.count(_ == true)

  override def toString: String = {
    val buffer = new java.lang.StringBuilder
    for(index <- adjacent.indices) {
      buffer.append("Edges for ").append(index).append(": ")
      for(index2 <- adjacent(index).indices) {
        if(adjacent(index)(index2))
          buffer.append(index2).append(" ")
      }
      buffer.append("\n")
    }
    buffer.toString
  }
}
