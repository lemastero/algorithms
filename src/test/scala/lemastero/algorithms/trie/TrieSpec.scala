package lemastero.algorithms.trie

import lemastero.algorithms.tdd.BaseSpec


class TrieSpec extends BaseSpec {

  describe("Trie") { // TODO how on earth to test it!
    it("works") {
      val trie = new RWayTrie[Integer]()
      trie.put("sea", 42)

      trie.get("sea") mustBe 42
      trie.get("s") mustBe null
    }
  }


}
