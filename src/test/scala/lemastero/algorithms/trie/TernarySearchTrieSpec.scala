package lemastero.algorithms.trie

import lemastero.algorithms.tdd.BaseSpec
import lemastero.algorithms.trie.TernarySearchTrieSimpleFactory._

class TernarySearchTrieSpec extends BaseSpec {

  describe("Ternary Search Trie") {
    it("put place first element on root node") {
      val trie:StringSymbolTable[Integer] = newTernarySearchTrie
      trie must not be null
    }
  }

  describe("put") {
    it("place first element on root node") {
      val trie = newTernarySearchTrie
      trie.put("foo", 42)
      trie.get("foo") mustBe Some(42)
      trie.get("bar") mustBe None
    }
  }

}
