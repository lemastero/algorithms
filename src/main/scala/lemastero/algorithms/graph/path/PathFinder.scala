package lemastero.algorithms.graph.path

/**
  * PathFinder is algorithm that preprocess grapf for given vertex.
  * Can answer if exist path between vertices.
  */
trait PathFinder {

  def existsPathTo(destination:Int)

  def getPathTo(destination:Int):List[Integer]

}
