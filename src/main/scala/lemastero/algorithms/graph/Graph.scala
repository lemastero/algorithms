package lemastero.algorithms.graph

object Graph {
  type RawEdge = (Int, Int)
}

trait Graph {
  def adjacentVertices(vertex: Int): List[Int]
  def numberOfVertices: Int
  def numberOfEdges: Int
}
