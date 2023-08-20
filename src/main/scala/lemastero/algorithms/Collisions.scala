package lemastero.algorithms

// https://softwarefoundations.cis.upenn.edu/current/vfa-current/Trie.html
object Collisions {
  val Max = Integer.MAX_VALUE;
  
  def collisions(xs : Seq[Int]): Int = {
    var collisions = 0;
    val a: Array[Int] = Array.fill(Max)(0);
    xs.foreach{ i =>
      if(a(i) != 0)
        collisions += 1;
      a(i)=1;
    }
    collisions;
  }
}
