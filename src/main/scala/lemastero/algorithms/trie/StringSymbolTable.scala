package lemastero.algorithms.trie

trait StringSymbolTable[Value] {

  def put(key: String, value: Value): Unit

  def get(key: String): Option[Value]
}
