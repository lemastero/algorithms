package lemastero.algorithms.graph

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.graph.AdjacencyListGraph._

class GraphBipartiteSpec extends BaseSpec {

  describe("trivial graphs") {
    it("empty graph is bipartite") {
      GraphUtils.isBipartite(newGraph(0)) mustBe true
    }

    it("single element graph is bipartite") {
      GraphUtils.isBipartite(newGraph(1)) mustBe true
    }

    it("three elements graph with cycle is not bipartite") {
      val graph = newGraph(3, (0, 1), (1, 2), (0, 1))
      GraphUtils.isBipartite(graph) mustBe false
    }

    it("detect complex example of bipartype graph") {
      val graph = newGraph(
        7,
        (0, 1),
        (0, 2),
        (0, 6),
        (0, 5),
        (1, 3),
        (2, 3),
        (2, 4),
        (6, 4),
        (5, 4)
      )
      GraphUtils.isBipartite(graph) mustBe true
    }
  }
}
