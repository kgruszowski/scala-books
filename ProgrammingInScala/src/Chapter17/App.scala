package Chapter17

import scala.collection.mutable
import scala.collection.immutable.{TreeMap, TreeSet}

object App extends App {

  /**
   * Collections - Sequences, Lists, Arrays, ListBuffers, ArrayBuffers, StringOps
   * Sets and Maps
   */
  val seq = Seq(3,4,2,1)
  println(seq)
  val arr = Array(1,2,3,4)
  println(arr.mkString(","))
  var arr1a = Array[Int](5)
  val arr1b = new Array[Int](5)
  println(arr1a.mkString(","))
  println(arr1b.mkString(",")) // note the different result, one is factory method another one is new class
  println(arr(3))

  println("====================")
  /**
   * Sets - bunch of unique values
   */
  val s = Set(1,2,3,4)
  println(s + 5)
  println(s - 2)
  println(s ++ List(6,6,7,8,9,0))
  println(s -- List(1,3))
  println(s & Set(2,3,8,9)) // intersection
  println(s.size)
  println(s.contains(3))

  println("====================")
  /**
   * Maps - key -> value collections
   */
  val map = mutable.Map.empty[String, Int]
  println(map)
  map("age") = 26
  map("height") = 180
  println(map)
  println(map("age"))

  println("====================")
  val nums = Map("i" -> 1, "ii" -> 11)
  println(nums)
  println(nums + ("iii" -> 111))
  println(nums - "ii") // remove key
  println(nums - "iiii") // nothing will happen => key does not exist
  println(nums ++ List("iiii" -> 1111, "j" -> 2))
  val nums2 = nums ++ List("iiii" -> 1111, "j" -> 2)
  println(nums2.keys)
  println(nums2.keySet)
  println(nums2.values)

  println("====================")
  // Ordered Sets and Maps
  val ts = TreeSet(3,4,5,1,3,6,8,2)
  println(ts)
  val tm = TreeMap("omega" -> 99, "alpha" -> 1, "theta" -> 22, "beta" -> 2)
  println(tm)
  println(tm + ("gamma" -> 3))

  println("====================")
  val letters = Set("a","b","c")
  println(letters)
  // letters += "d" - immutable.Set does not support += assignment
  var letters2 = Set("a","b","c")
  letters2 += "d" // you can do a trick and use var instead of val and take advantage of syntactic sugar (a += "d") === (a = a + "d") BUT IT HAS TO BE VAR!
  println(letters2)

  println("====================")
  // conversion
  val colors = List("red", "cyan", "magenta", "black")
  println(colors)
  println(colors to TreeSet)

  println(colors to mutable.TreeSet) // Immutable to mutable set conversion
  println((colors to mutable.TreeSet) to TreeSet) // and the other way back

  println("====================")
  /**
   * Tuples
   */
  val t = ("Kamil", 26, List(1,2,3))
  println(t)
  println(t._2)
  val (name, age, ids) = t // tuple assignment
  println(name)
  println(age)
  println(ids)
  val name1, age1, ids1 = t // tuple assignment, DIFFERENT RESULT!
  println(name1)
  println(age1)
  println(ids1)

  println("====================")
  /**
   * Example
   */
  println(countWords("Hey Hi Hello, I am hello hi all and bye bye bye"))

  def countWords(text: String) = {
    val counts = mutable.Map.empty[String, Int]

    for (rawWord <- text.split("[ ,!.]+")) {
      val word = rawWord.toLowerCase
      val oldCount =
        if (counts.contains(word)) counts(word)
        else 0
      counts += (word -> (oldCount + 1))
    }

    counts
  }
}
