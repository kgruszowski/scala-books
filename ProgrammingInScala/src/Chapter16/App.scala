package Chapter16

object App extends App{
  val l = List(1,2,3,4)
  println(l)
  val l2 = 1 :: 2 :: 3:: 4 :: Nil
  val l22 = 1 :: (2 :: (3 :: (4 :: Nil))) // same form as above
  println(l2)
  println(l22)
  println(11 :: l2)
  val a :: b :: rest = l2 // pattern matching
  println(a)
  println(b)
  println(rest)
  val l3 = List('a','b','c','d')
  println(l2 ::: l3)
  println(append(l2,l3))
  println(l2.length) // quite expensive operation since it has to traverse the whole list
  println(l2.reverse)
  println(l2 take 3) // returns list without n element
  println(l2 drop 2) // returns list without n first elements
  println(l2 splitAt 3)
  val l4 = l :: l2 :: Nil
  println(l4.flatten)
  println(l3.indices zip l3)
  println(l3.zipWithIndex)
  val arr = l3.toArray
  println(arr.mkString(","))
  println(arr.toList)
  val l5 = List(1,2,3,4,5,-1,-3,-2,2,4,5)
  println(l5 span (_ > 0)) // divide into two lists
  println(l5 forall (_ > -10)) // return true when all satisfy condition
  println(l.foldLeft(0.1)(_ / _)) // fold operation left-leaning tree 0.1 / 1 / 2 / 3 / 4
  println(l.foldRight(0.1)(_ / _)) // fold operation right-learning tree 1 / (2 / (3 / (4 / 0.1)))
  println(l4)
  // same output different performance
  println(l4.foldLeft(List[Int]())(_ ::: _)) // slower since concatenate List() ::: l1 ::: l2 so it has to copy head of every sublist
  println(l4.foldRight(List[Int]())(_ ::: _)) // faster since concatenate l1 ::: (l2 ::: List())
  println(l5 sortWith(_ < _))
  println(List.fill(10)("a"))
  println(List.fill(2,10)("a")) //2d list

  // concatenation by hand
  def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List() => ys
    case x :: xs1 => x :: append(xs1, ys)
  }
}
