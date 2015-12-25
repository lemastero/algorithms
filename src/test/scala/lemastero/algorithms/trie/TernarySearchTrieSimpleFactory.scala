package lemastero.algorithms.trie

object TernarySearchTrieSimpleFactory {

  def newTernarySearchTrie: TernarySearchTrie[Integer] =
    new TernarySearchTrie[Integer]()

  def newTernaryTrieNode =
    new TernaryTrieNode[Integer]()

}
