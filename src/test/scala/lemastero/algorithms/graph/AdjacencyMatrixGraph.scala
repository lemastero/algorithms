package lemastero.algorithms.graph

import scala.collection.mutable

/**
  * This is ineficient implementation of Graph that uses
  * matrix that holds connection between each vertices.
  *
  * It is simple but is very space consuming square(number of vertices)
  */
class AdjacencyMatrixGraph(val numberOfVertices:Int) extends Graph {

  val adjacent = new Array[Array[Boolean]](numberOfVertices)
  for(index <- 0 to numberOfVertices - 1) {
    adjacent(index) = new Array[Boolean](numberOfVertices)
    for(index2 <- 0 to numberOfVertices - 1) {
      adjacent(index)(index2) = false
    }
  }

  override def addEdgeBetween(firstVertex: Int, secondVertex: Int): Unit = {
    adjacent(firstVertex)(secondVertex) = true
    adjacent(secondVertex)(firstVertex) = true
  }

  override def adjacentVertices(vertex: Int): mutable.ListBuffer[Int] = {
    val result = mutable.ListBuffer[Int]()
    for(index2 <- 0 to numberOfVertices - 1) {
      if (adjacent(vertex)(index2) && !result.contains(index2)) {
        result += index2
      }
    }
    return result
  }

  override def numberOfEdges: Int = {
    var result = 0
    for(index <- 0 to numberOfVertices - 1) {
      for(index2 <- 0 to numberOfVertices - 1) {
        if (adjacent(index)(index2)) {
          result += 1
        }
      }
    }
    return result/2
  }

  override def toString: String = {
    val buffer = new java.lang.StringBuilder
    for(index <- 0 to numberOfVertices - 1) {
      buffer.append("Edges for ").append(index).append(": ")
      for(index2 <- 0 to numberOfVertices - 1) {
        if(adjacent(index)(index2))
          buffer.append(index2).append(" ")
      }
      buffer.append("\n")
    }
    buffer.toString
  }
}
