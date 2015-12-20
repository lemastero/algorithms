package lemastero.algorithms.trie

/**
  * Implemented R-way Trie
  * in Scala
  * https://class.coursera.org/algs4partII-006/lecture/34
  */
private class CrossRoads[Value >: Null] {

  val NumberOfPathsStartingFromCrossRoad:Int = 256; // TODO printable ASCII is between 32 and 126
  // https://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters

  private[trie] var signValue:Value = null;
  private[trie] var roads:Array[CrossRoads[Value]] = new Array[CrossRoads[Value]](NumberOfPathsStartingFromCrossRoad);

  override def toString():String = {
    var result:StringBuilder = new StringBuilder("(")
      .append(signValue).append(": ");
    for(i <- 0 to roads.length - 1)
      if(roads(i) != null)
        result.append(i.toChar).append(": ").append(roads(i).toString()).append(", ")
    result.append(")")
    return result.toString()
  }
}

class RWayTrie[Value >: Null] extends StringSymbolTable[Value] {

  private var startOfPath:CrossRoads[Value] = new CrossRoads[Value]();

  override def put(key:String, value:Value):Unit = {
    startOfPath = putSignAtTheEndOfPath(startOfPath, key, value, 0);
  }

  private def putSignAtTheEndOfPath(currentStage:CrossRoads[Value], fullPath:String, signToSet:Value, stageMark:Int): CrossRoads[Value] = {
    var currentStageToVisit = currentStage;
    if(currentStage == null)
      currentStageToVisit = new CrossRoads();

    if(isEndOfPath(fullPath, stageMark)) {
      currentStageToVisit.signValue = signToSet;
      return currentStageToVisit;
    }

    var c:Char = pickRoad(fullPath, stageMark);
    currentStageToVisit.roads(c) = putSignAtTheEndOfPath(currentStageToVisit.roads(c), fullPath, signToSet, stageMark+1);
    return currentStageToVisit;
  }

  override def get(key:String):Value = {
    var x:CrossRoads[Value] = findNodeByKeyFromPath(startOfPath, key, 0);
    if(x == null) return null;
    return x.signValue
  }

  val noResults = null
  private def findNodeByKeyFromPath(path:CrossRoads[Value], key:String, nextStep:Int):CrossRoads[Value] = {
    if(path == null) {
      return noResults
    };

    if(isEndOfPath(key, nextStep)) {
      return path
    };

    var c:Char = pickRoad(key, nextStep);
    return findNodeByKeyFromPath(path.roads(c), key, nextStep+1);
  }

  def pickRoad(key: String, nextStep: Int): Char = {
    key.charAt(nextStep)
  }

  private def isEndOfPath(key: String, nextStep: Int): Boolean = {
    nextStep == key.length
  }

  // TODO implement delete

  override def toString():String = {
    "Trie: " + startOfPath.toString()
  }

}
