package lemastero.algorithms.trie

/**
  * Implemented R-way Trie
  * in Scala
  * https://class.coursera.org/algs4partII-006/lecture/34
  */
private class Node {
  val R:Int = 256; // TODO printable ASCII is between 32 and 126
  // https://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters

  private[trie] var value:Any = null;
  private[trie] var next:Array[Node] = new Array[Node](R);

  override def toString():String = {
    var result:StringBuilder = new StringBuilder("(")
      .append(value).append(": ");
    for(i <- 0 to next.length - 1)
      if(next(i) != null)
        result.append(i.toChar).append(": ").append(next(i).toString()).append(", ")
    result.append(")")
    return result.toString()
  }
}

class Trie[Value >: Null] {
  private var root:Node = new Node();

  def put(key:String, value:Value):Unit = {
    root = put(root, key, value, 0);
  }

  private def put(xArg:Node, key:String, value:Value, d:Int): Node = {
    var x = xArg;
    if(x == null)
      x = new Node();

    if(d == key.length()) {
      x.value = value;
      return x;
    }
    var c:Char = key.charAt(d);
    println("char: " + c)
    x.next(c) = put(x.next(c), key, value, d+1);
    return x;
  }

  def get(key:String):Value = {
    var x:Node = get(root, key, 0);
    if(x == null) return null;
    return x.value.asInstanceOf[Value]
  }

  private def get(x:Node, key:String, d:Int):Node = {
    if(x == null) return null;
    if(d == key.length) return x;
    var c:Char = key.charAt(d);
    return get(x.next(c), key, d+1);
  }

  // TODO implement delete

  override def toString():String = {
    "Trie: " + root.toString()
  }

}
