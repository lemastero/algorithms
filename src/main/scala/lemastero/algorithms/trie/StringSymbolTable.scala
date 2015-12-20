package lemastero.algorithms.trie


trait StringSymbolTable[Value >: Null] {

  def put(key: String, value: Value): Unit

  def get(key: String): Value
}
