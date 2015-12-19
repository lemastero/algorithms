package lemastero.algorithms.graph

import scala.collection.mutable

trait Graph {

  def addEdgeBetween(firstVertex:Int, secondVertex:Int): Unit

  def adjacentVertices(vertex:Int): mutable.ListBuffer[Int]

  def numberOfVertices: Int

  def numberOfEdges: Int

}
