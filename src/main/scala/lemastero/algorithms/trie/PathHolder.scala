package lemastero.algorithms.trie

object PathHolder {

  implicit class Path(val stringPath:String) extends AnyVal {

    def pick(stage: Int): Char =
      stringPath.charAt(stage)

    def isEnd(stage: Int): Boolean =
      stage == stringPath.length
  }
}
