package lemastero.algorithms.compression

import scala.collection.mutable

class RunLengthCoding {

  def decompress(ints: Iterator[Int]):List[Int] = {
    val result = mutable.ListBuffer[Int]()
    var value = 1
    while(ints.hasNext) {
      for(i <- 1 to ints.next())
        result.append(value)
      value = if(value == 1) 0 else 1
    }
    result.toList
  }


  def compress(uncompressed:Iterator[Int]): List[Int]  =
    if(uncompressed.isEmpty) List()
    else compressNonEmpty(uncompressed)

  private def compressNonEmpty(uncompressed:Iterator[Int]): List[Int] = {
    val result = mutable.ListBuffer[Int]()
    var setCount: Int = 0
    var unsetCount: Int = 0
    while (uncompressed.hasNext) {
      if (uncompressed.next() == 1) {
        setCount = setCount + 1
        if (unsetCount > 0) {
          result.append(unsetCount)
          unsetCount = 0
        }
      } else {
        if (setCount > 0) {
          result.append(setCount)
          setCount = 0
        }
        unsetCount = unsetCount + 1
      }
    }
    if (setCount > 0 || result.isEmpty) {
      result.append(setCount)
      setCount = 0
    }
    if (unsetCount > 0) {
      result.append(unsetCount)
      unsetCount = 0
    }
    result.toList
  }
}
