package lemastero.algorithms.graph

import lemastero.algorithms.BaseSpec

class GraphBipartiteSpec extends BaseSpec {

  describe("trivial graphs") {
    it("empty graph is bipartite") {
      GraphUtils.isBipartite( AdjacencyListGraph(0) ) mustBe true
    }

    it("single element graph is bipartite") {
      GraphUtils.isBipartite( AdjacencyListGraph(1) ) mustBe true
    }

    it("three elements graph with cycle is not bipartite") {
      val graph = AdjacencyListGraph(3)
      graph.addEdgeBetween(0, 1)
      graph.addEdgeBetween(1, 2)
      graph.addEdgeBetween(0, 1)
      GraphUtils.isBipartite(graph) mustBe false
    }

    it("detect complex example of bipartype graph") {
      val graph = AdjacencyListGraph(7)
      graph.addEdgeBetween(0, 1)
      graph.addEdgeBetween(0, 2)
      graph.addEdgeBetween(0, 6)
      graph.addEdgeBetween(0, 5)
      graph.addEdgeBetween(1, 3)
      graph.addEdgeBetween(2, 3)
      graph.addEdgeBetween(2, 4)
      graph.addEdgeBetween(6, 4)
      graph.addEdgeBetween(5, 4)
      GraphUtils.isBipartite(graph) mustBe true
    }
  }


}
