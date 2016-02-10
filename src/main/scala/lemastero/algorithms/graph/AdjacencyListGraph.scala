package lemastero.algorithms.graph

import scala.collection.mutable

case class AdjacencyListGraph(numberOfVertices:Int) extends Graph {

  private val adjacents:List[mutable.ListBuffer[Int]] = List.fill(numberOfVertices)(new mutable.ListBuffer[Int])

  override def addEdgeBetween(firstVertex: Int, secondVertex: Int): Unit = {
    adjacents(firstVertex) += secondVertex
    adjacents(secondVertex) += firstVertex
  }

  override def adjacentVertices(vertex: Int): List[Int] =
    adjacents(vertex).toList

  override def numberOfEdges: Int =
    adjacents.map(e => e.size).sum / 2
}
