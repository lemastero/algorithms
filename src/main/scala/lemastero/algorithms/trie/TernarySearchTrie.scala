package lemastero.algorithms.trie

/** Ternary Search Trie implementation.
  *
  * Store characters and values in nodes. Each node has 3 children: smaller
  * (lfet), equal (middle), larger (right)
  */
class TernarySearchTrie[Value] extends StringSymbolTable[Value] {

  private val root = new TernaryTrieNode[Value]()

  override def put(key: String, value: Value): Unit = root.put(key, value)

  override def get(key: String): Option[Value] = root.get(key)
}

case class TernaryTrieNode[Value](
    var key: Option[Char] = None,
    var value: Option[Value] = None,
    var left: Option[TernaryTrieNode[Value]] = None,
    var middle: Option[TernaryTrieNode[Value]] = None,
    var right: Option[TernaryTrieNode[Value]] = None
) {

  def put(key: String, value: Value): Unit = {
    if (noValueYet) this.key = Some(key.head)

    if (isForCurrent(key))
      if (isLastKeyCharacter(key))
        this.value = Some(value)
      else getMiddle.put(key.substring(1), value)
    else if (isForLeft(key)) getLeft.put(key, value)
    else getRight.put(key, value)
  }

  def get(key: String): Option[Value] =
    if (isForCurrent(key)) {
      if (isLastKeyCharacter(key)) value
      else if (middle.isDefined) middle.get.get(key.substring(1))
      else None
    } else if (isForLeft(key))
      if (left.isDefined) left.get.get(key) else None
    else if (right.isDefined) right.get.get(key)
    else None

  private def noValueYet = this.key.isEmpty
  private def isForCurrent(key: String) = this.key.contains(key.head)
  private def isLastKeyCharacter(key: String) = key.length == 1

  private def isForLeft(key: String): Boolean =
    if (this.key.isDefined) this.key.get > key.head
    else false

  private def getMiddle: TernaryTrieNode[Value] = {
    if (middle.isEmpty)
      middle = Some(new TernaryTrieNode[Value]())
    middle.get
  }

  private def getRight: TernaryTrieNode[Value] = {
    if (right.isEmpty)
      right = Some(new TernaryTrieNode[Value]())
    right.get
  }

  private def getLeft: TernaryTrieNode[Value] = {
    if (left.isEmpty)
      left = Some(new TernaryTrieNode[Value]())
    left.get
  }
}
