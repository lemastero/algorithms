package lemastero.algorithms.graph.path

object PathFinder {
  class PathFromEmptyGraph extends RuntimeException
  class PathFromNotExistingVertex extends RuntimeException
  class VertexNotFound extends RuntimeException
}

/**
  * PathFinder is algorithm that preprocess grapf for given vertex.
  * Can answer if exist path between vertices.
  */
trait PathFinder {

  def existsPathTo(destination:Int):Boolean

  def getPathTo(destination:Int):List[Int]

}
