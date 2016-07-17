package lemastero.algorithms.trie

import lemastero.algorithms.BaseSpec
import lemastero.algorithms.trie.TernarySearchTrieSimpleFactory._

class TernaryTrieNodeSpec extends BaseSpec {

  describe("put") {
    it("stores given value for single character string") {
      val trie = newTernaryTrieNode
      trie.put("a", 42)
      assertNodeContent(trie, 'a', 42)
    }

    it("replaces previously stored value") {
      val node = newTernaryTrieNode
      node.put("a", -1)
      node.put("a", 42)
      assertNodeContent(node, 'a', 42)
    }

    it("places bigger key on the right") {
      val node = newTernaryTrieNode
      node.put("p", 3)
      node.put("z", 4)

      assertNodeContent(node, 'p', 3)
      assertNodeContent(node.right, 'z', 4)
    }

    it("places smaller key on the left") {
      val node = newTernaryTrieNode
      node.put("p", 3)
      node.put("a", 4)

      assertNodeContent(node, 'p', 3)
      assertNodeContent(node.left, 'a', 4)
    }

    it("places multi character key") {
      val node = newTernaryTrieNode
      node.put("fo", 42)

      assertNodeContent(node, 'f', None)
      assertNodeContent(node.middle, 'o', 42)
    }
  }

  describe("get") {

    it("returnes None for non stored value") {
      val node = newTernaryTrieNode
      assertGet(node, "a", None)
    }

    it("returns stored value") {
      val node = newTernaryTrieNode
      node.put("a", 42)
      assertGet(node, "a", 42)
    }

    it("returns stored value on multicharacter key") {
      val node = newTernaryTrieNode
      node.put("foo", 42)
      node.get("foo") mustBe Some(42)
    }

    it("returns value stored on the right") {
      val node = newTernaryTrieNode
      node.put("p", 3)
      node.put("z", 4)

      assertGet(node, "p", 3)
      assertGet(node, "z", 4)
    }


    it("returns value stored on the left") {
      val node = newTernaryTrieNode
      node.put("p", 3)
      node.put("a", 4)

      assertGet(node, "p", 3)
      assertGet(node, "a", 4)
    }

    it("places multi character key") {
      val node = newTernaryTrieNode
      node.put("fo", 42)

      assertNodeContent(node, 'f', None)
      assertNodeContent(node.middle, 'o', 42)
    }
  }

  def assertGet(node: TernaryTrieNode[Integer], key:String, value:Int): Unit =
    assertGet(node, key, Some(value))

  def assertGet(node: TernaryTrieNode[Integer], key:String, value:Option[Int]): Unit =
    node.get(key) mustBe value

  def assertNodeContent(node: Option[TernaryTrieNode[Integer]], key:Char, value:Int): Unit = {
    node must not be None
    assertNodeContent(node.orNull, key, value)
  }

  def assertNodeContent(node: TernaryTrieNode[Integer], key:Char, value:Option[Int]): Unit = {
    assertNodeKey(node, key)
    node.value mustBe value
  }

  def assertNodeContent(node: TernaryTrieNode[Integer], key:Char, value:Int): Unit = {
    assertNodeKey(node, key)
    node.value mustBe Some(value)
  }

  def assertNodeKey(node: Option[TernaryTrieNode[Integer]], key:Char): Unit =
    assertNodeKey(node.orNull, key)

  def assertNodeKey(node: TernaryTrieNode[Integer], key:Char): Unit =
    node.key mustBe Some(key)

}

