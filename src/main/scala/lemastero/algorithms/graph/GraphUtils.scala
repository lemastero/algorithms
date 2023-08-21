package lemastero.algorithms.graph

sealed class MarkColor(val other: MarkColor)
case object Red extends MarkColor(White)
case object White extends MarkColor(Red)

object GraphUtils {

  def isBipartite(graph: Graph): Boolean =
    if (graph.numberOfVertices > 1)
      isBipartiteNonTrivialGraph(graph)
    else true

  private def isBipartiteNonTrivialGraph(graph: Graph): Boolean = {
    val colors: Array[Option[MarkColor]] =
      Array.fill(graph.numberOfVertices)(None)

    def colorVertex(root: Int, color: MarkColor): Boolean = {
      colors(root) = Some(color)

      graph
        .adjacentVertices(root)
        .filterNot(colors(_).contains(color.other))
        .foreach { v =>
          if (colors(v).contains(color))
            return false
          else {
            colors(v) = Some(color.other)
            colorVertex(v, color)
          }
        }
      true
    }

    colorVertex(0, Red)
  }
}
