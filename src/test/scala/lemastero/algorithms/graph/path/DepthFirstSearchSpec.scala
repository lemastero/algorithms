package lemastero.algorithms.graph.path

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.graph.{Graph, AdjacencyListGraph}

class DepthFirstSearchSpec extends BaseSpec {

  describe("factory method") {

    it("throws PathFromNotExistingVertex exception when try to construct from not existing vertex") {
      intercept[PathFromNotExistingVertex] {
        DepthFirstSearch(AdjacencyListGraph(1), 1)
      }
    }

    it("throws PathFromEmptyGraph exception when try to construct from empty Graph") {
      intercept[PathFromEmptyGraph] {
        DepthFirstSearch(AdjacencyListGraph(0), 42)
      }
    }
  }

}
