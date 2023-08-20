package lemastero.algorithms.graph

import lemastero.algorithms.graph.AdjacencyListGraph._
import lemastero.algorithms.graph.Graph._
import scala.collection.mutable

object AdjacencyListGraph {
  type VertexEdges = mutable.ListBuffer[Int]
  type VerticesEdges = List[VertexEdges]

  def newGraph(numberOfVertices: Int, edges: RawEdge*): AdjacencyListGraph = {
    val adjacent: VerticesEdges =
      verticesNoEdges(numberOfVertices)
    edges.foreach(addEdgeBetween(adjacent, _))
    new AdjacencyListGraph(adjacent)
  }

  private def verticesNoEdges(size: Int): VerticesEdges =
    List.fill(size)(new VertexEdges)

  private def addEdgeBetween(
      listBuffers: VerticesEdges,
      edge: RawEdge
  ): Unit = {
    val (first: Int, second: Int) = edge
    listBuffers(first) += second
    listBuffers(second) += first
  }
}

class AdjacencyListGraph(adjacent: VerticesEdges) extends Graph {

  override def numberOfVertices: Int = adjacent.size

  override def adjacentVertices(vertex: Int): List[Int] =
    adjacent(vertex).toList

  override def numberOfEdges: Int =
    adjacent.map(e => e.size).sum / 2
}
