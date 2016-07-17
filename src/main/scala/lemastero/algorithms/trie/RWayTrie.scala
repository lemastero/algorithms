package lemastero.algorithms.trie

import lemastero.algorithms.trie.PathHolder.Path

/**
  * R-way Trie
  *
  * Implementation based on:
  * https://class.coursera.org/algs4partII-006/lecture/34
  */
private class TrieNode[Value >: Null] {

  val NumberOfChildren: Int = 256 // printable ASCII is between 32 and 126

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
      currentChildToVisit.children(index) = putValue(currentChildToVisit.children(index), currentIndex + 1)
      currentChildToVisit
    }

    root = putValue(root, 0)
  }

  override def get(key:String): Option[Value] = {

    def getNode(currentChild:Option[TrieNode[Value]], step:Int): Option[TrieNode[Value]] =
      currentChild.flatMap(
         child =>
           if( key.isEnd(step) )
             Option(child)
           else
             getNode(Option(child.children(key.pick(step))), step + 1)
      )

    val foundNode = getNode(Option(root), 0)
    if(foundNode.isEmpty) None
    else Option(foundNode.get.value)
  }

  // TODO implement delete

}
