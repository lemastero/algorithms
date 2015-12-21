package lemastero.algorithms.trie

import lemastero.algorithms.trie.PathHolder.Path

/**
  * R-way Trie
  *
  * Implementation based on:
  * https://class.coursera.org/algs4partII-006/lecture/34
  */
private class TrieNode[Value >: Null] {

  val NumberOfChildren:Int = 256 // TODO printable ASCII is between 32 and 126

  private[trie] var value:Value = null
  private[trie] val children:Array[TrieNode[Value]] =
    new Array[TrieNode[Value]](NumberOfChildren)

  override def toString:String = {
    val result = new StringBuilder("(")
      .append(value).append(" [")

    for(i <- children.indices)
      if(children(i) != null)
        result.append(i.toChar).append(": ").append(children(i)).append(", ")
    result.append("])")
    result.toString()
  }
}



class RWayTrie[Value >: Null] extends StringSymbolTable[Value] {

  private[trie] var root:TrieNode[Value] = new TrieNode[Value]()

  override def put(stringPath:String, newValue:Value):Unit = {
    def putValue(currentChild: TrieNode[Value], currentIndex: Int): TrieNode[Value] = {
      var currentChildToVisit = currentChild
      if(currentChild == null)
        currentChildToVisit = new TrieNode[Value]()

      if( stringPath.isEnd(currentIndex) ) {
        currentChildToVisit.value = newValue
        return currentChildToVisit
      }

      val index = stringPath.pick(currentIndex)
      currentChildToVisit.children(index) = putValue(currentChildToVisit.children(index), currentIndex+1)
      currentChildToVisit
    }

    root = putValue(root, 0) // TODO removing left side of assignment do not break tests
  }

  override def get(key:String): Value = {

    def getNode(currentChild:TrieNode[Value], step:Int):TrieNode[Value] =
      if(currentChild == null)
        null
      else if( key.isEnd(step) )
        currentChild
      else
        getNode(currentChild.children(key.pick(step)), step + 1)

    val foundNode:TrieNode[Value] = getNode(root, 0)
    if(foundNode == null) return null
    foundNode.value
  }

  // TODO implement delete

}
