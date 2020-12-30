package Chapter16

object MergeSort extends App{
  def mergeSort[T](less: (T, T) => Boolean) (xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(mergeSort(less)(ys), mergeSort(less)(zs))
    }
  }

  // take advantage of currying
  val intSort = mergeSort((x: Int, y: Int) => x < y) _
  val reverseIntSort = mergeSort((x: Int, y: Int) => x > y) _

  println(intSort(List(4,1,6,3,2,0,1,1,9)))
  println(reverseIntSort(List(4,1,6,3,2,0,1,1,9)))
}
