package lemastero.algorithms.trie

object PathHolder {

  implicit class Path(val stringPath:String) extends AnyVal {

    def pick(stage: Int) =
      stringPath.charAt(stage)

    def isEnd(stage: Int) =
      stage == stringPath.length

  }
}