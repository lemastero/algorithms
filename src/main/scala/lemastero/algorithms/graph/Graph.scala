package lemastero.algorithms.graph

trait Graph {

  def addEdgeBetween(firstVertex:Int, secondVertex:Int): Unit

  def adjacentVertices(vertex:Int): List[Int]

  def numberOfVertices: Int

  def numberOfEdges: Int

}
