package lemastero.algorithms.graph


case class AdjacencyListGraph(numberOfVertices:Int) extends Graph {

  override def addEdgeBetween(firstVertex: Int, secondVertex: Int): Unit = ???

  override def adjacentVertices(vertex: Int): List[Int] = ???

  override def numberOfEdges: Int = ???
}
