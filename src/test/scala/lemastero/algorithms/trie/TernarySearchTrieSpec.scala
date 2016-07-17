package lemastero.algorithms.trie

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.trie.TernarySearchTrieSimpleFactory._

class TernarySearchTrieSpec extends BaseSpec {

  describe("Ternary Search Trie") {
    it("factory method create object") {
      newTernarySearchTrie must not be null
    }
  }

  describe("get returns what put stored") {
    it("place first element on root node") {
      val trie = newTernarySearchTrie
      trie.put("foo", 42)
      trie.get("foo") mustBe Some(42)
      trie.get("bar") mustBe None

      trie.put("fo", 1)
      trie.get("fo") mustBe Some(1)
      trie.get("f") mustBe None

      trie.get("fooz") mustBe None
    }
  }

}
