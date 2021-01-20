package Chapter19

object QueueApp extends App {
  val q = new Queue[Int](1,2,3,4,5,6,7)
  val q1 = q.enqueue(2.0)

  println(q)
  println(q.head)
  println(q1)
  println(q1.head)
  println(q1.tail.head)
}

// Not efficient
class SlowAppendQueue[T](elems: List[T]) {

  def head = elems.head

  def tail = new SlowAppendQueue(elems.tail)

  // take time proportional to size of the elems
  def enqueue(x: T) = new SlowAppendQueue(elems ::: List(x))
}

// Not efficient
// smele => elems reversed
class SlowHeadQueue[T](smele: List[T]) {

  // time proportional to size of the smele
  def head = smele.last

  def tail = new SlowHeadQueue(smele.init)

  // constant time
  def enqueue(x: T) = new SlowHeadQueue(x :: smele)
}

// +T means covariant type so T and its subclasses
class Queue[+T] private( // private makes constructor private - it's accessible only from class body or companion objects
                private[this] var leading: List[T],
                private[this] var trailing: List[T],
              ) {

  // First approach to handle private constructor
  def this(elems: T*) = this(elems.toList, Nil)

  private def mirror() =
    if (leading.isEmpty)
      while (trailing.nonEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }

  def head: T = {
    mirror()
    leading.head
  }

  def tail: Queue[T] = {
    mirror()
    new Queue(leading.tail, trailing)
  }

  // type parameter U, "U >: T" defines T as the lower bound for U so it's required U to be supertype of T
  def enqueue[U >: T](x: U) = new Queue[U](leading, x :: trailing) // what the heck? :o
}

object Queue {
  // Second approach to handle private constructor, it can be invoked as Queue.apply(1,2,3) or Queue(1,2,3)
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}