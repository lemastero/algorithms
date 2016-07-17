package lemastero.algorithms.trie

import lemastero.algorithms.BaseSpec

class RWayTrieSpec extends BaseSpec {

  describe("Empty R-way Trie") {

    it("has null in root and its children") {
      val trie = new RWayTrie[Integer]()
      trie.root.value mustBe None
      trie.root.children.count(_ != null) mustBe 0
    }

    it("does not find any values") {
      val trie = new RWayTrie[Integer]()
      trie.get("foo") mustBe None
    }
  }

  describe("put") {
    it("with 1 character key create single node") {
      val trie = new RWayTrie[Integer]()
      trie.put("a", 3)

      trie.root.children('a').value mustBe Some(3)
      trie.root.children.count(_ != null) mustBe 1
      trie.root.children('a').children.count(_ != null) mustBe 0
    }

    it("with 3 character key create three chained node") {
      val trie = new RWayTrie[Integer]()
      trie.put("bar", 4)

      trie.root.children('b').value mustBe None
      trie.root.children('b').children('a').value mustBe None
      trie.root.children('b').children('a').children('r').value mustBe Some(4)

      trie.root.children.count(_ != null) mustBe 1
      trie.root.children('b').children.count(_ != null) mustBe 1
      trie.root.children('b').children('a').children.count(_ != null) mustBe 1
      trie.root.children('b').children('a').children('r').children.count(_ != null) mustBe 0
    }

    it("with 2 character key create appriopriate nodes") {
      val trie = new RWayTrie[Integer]()
      trie.put("ba", 12)
      trie.put("pa", 100)

      trie.root.children('b').value mustBe None
      trie.root.children('b').children('a').value mustBe Some(12)
      trie.root.children('p').children('a').value mustBe Some(100)
      trie.root.children('p').value mustBe None

      trie.root.children.count(_ != null) mustBe 2
      trie.root.children('b').children.count(_ != null) mustBe 1
      trie.root.children('p').children.count(_ != null) mustBe 1
      trie.root.children('b').children('a').children.count(_ != null) mustBe 0
      trie.root.children('p').children('a').children.count(_ != null) mustBe 0
    }

    it("with substring") {
      val trie = new RWayTrie[Integer]()
      trie.put("sear", 5)
      trie.put("sea", 6)

      trie.root.children('s').children('e').children('a').children('r').value mustBe Some(5)
      trie.root.children('s').children('e').children('a').value mustBe Some(6)

      trie.root.children('s').children('e').value mustBe None
      trie.root.children('s').value mustBe None

      trie.root.children('s').children('e').children('a').children('r').children.count(_ != null) mustBe 0
      trie.root.children('s').children('e').children('a').children.count(_ != null) mustBe 1
      trie.root.children('s').children('e').children.count(_ != null) mustBe 1
      trie.root.children('s').children.count(_ != null) mustBe 1
    }
  }

  describe("get") {
    it("returns stored previously value") {
      val trie = new RWayTrie[Integer]()
      trie.put("sea", 42)
      trie.get("sea") mustBe Some(42)
    }

    it("does not return values that were not stored") {
      val trie = new RWayTrie[Integer]()
      trie.put("foobar", 42)

      trie.get("fooba") mustBe None
      trie.get("foob") mustBe None
      trie.get("oobar") mustBe None
      trie.get("foobaR") mustBe None
      trie.get("Foobar") mustBe None
    }
  }

}
