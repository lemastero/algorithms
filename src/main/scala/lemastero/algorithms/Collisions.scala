package lemastero.algorithms

// https://softwarefoundations.cis.upenn.edu/current/vfa-current/Trie.html
// replace array[Integer.MAX_VALUE * 2] with map
object Collisions {
  def collisions(xs : Seq[Int]): Int = {
    var collisions = 0;
    var a: Map[Int, Boolean] = Map()
    xs.foreach{ i =>
      if(a.contains(i))
        collisions += 1;
      a = a + (i -> true)
    }
    collisions;
  }
}
