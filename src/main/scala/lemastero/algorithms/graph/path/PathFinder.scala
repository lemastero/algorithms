package lemastero.algorithms.graph.path

sealed trait GraphCreateError
final case object PathFromEmptyGraph extends GraphCreateError
final case class PathFromNotExistingVertex(invalidVertex: Int, maxVertex: Int)
    extends GraphCreateError

final case class VertexNotFound(vertex: Int) extends RuntimeException

/** PathFinder is algorithm that pre-process graph for given vertex. After that
  * it can can answer:
  *   - if there is path between given node and starting one
  *   - what is the path between given node and some other node
  */
trait PathFinder {

  def existsPathTo(destination: Int): Either[VertexNotFound, Boolean]

  def getPathTo(destination: Int): Either[VertexNotFound, List[Int]]
}
