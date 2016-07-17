package lemastero.algorithms.graph.path

class PathFromEmptyGraph extends RuntimeException
class PathFromNotExistingVertex extends RuntimeException
sealed case class VertexNotFound(vertex:Int) extends RuntimeException

/**
  * PathFinder is algorithm that pre-process graph for given vertex.
  * After that it can can answer:
  *  - if there is path between given node and starting one
  *  - what is the path between given node and some other node
  */
trait PathFinder {

  def existsPathTo(destination:Int): Boolean

  def getPathTo(destination:Int): List[Int]

}
