package lemastero.algorithms.graph.path

import lemastero.algorithms.graph.Graph

/** Breadth First Search (BFS) algorithm.  */
case class BreadthFirstSearch(graph: Graph, root:Int) extends PathFinder {

  private val previousVertex: Array[Option[Int]] =
    Array.fill[Option[Int]](graph.numberOfVertices)(None)

  if(graph.numberOfVertices == 0) throw new PathFromEmptyGraph
  if(root >= graph.numberOfVertices) throw new PathFromNotExistingVertex
  initialize()

  override def existsPathTo(destination: Int): Boolean =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else previousVertex(destination).isDefined

  /** Return the shortest path fro vertex provided as argument
    * to the root vertex */
  override def getPathTo(destination: Int): List[Int] =
    if (destination >= graph.numberOfVertices) throw new VertexNotFound
    else if (previousVertex(destination).isEmpty) List()
    else createPathFor(destination, List[Int]())

  private def createPathFor(destination: Int, soFar:List[Int]): List[Int] =
    if (destination == root) root :: soFar
    else createPathFor(previousVertex(destination).get, destination :: soFar)

  private def initialize() {
    previousVertex(root) = Some(root)
    markPreviousVertices(List(root))
  }

  private def markPreviousVertices(elementsToProcess:List[Int]) {
    if (elementsToProcess.nonEmpty ) {
      val toProcess = graph.adjacentVertices(elementsToProcess.head).filterNot(existsPathTo)
      toProcess.foreach( previousVertex(_) = Some(elementsToProcess.head) )
      markPreviousVertices(elementsToProcess.tail ++ toProcess)
    }
  }
}
