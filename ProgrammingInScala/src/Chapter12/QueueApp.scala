package Chapter12

import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def get(): Int
  def put(x: Int): Unit // could be also : ()
}

trait Doubling extends IntQueue { // it extends IntQueue so it means it can be mixin only with subclasses of IntQueue
  abstract override def put(x: Int) = super.put(2 * x) // to use super you need to use it along with abstract override
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = super.put(x + 1)
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) = if (x >= 0) super.put(x)
}

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]
  def get() = buf.remove(0)
  def put(x: Int) = buf += x
}

class DoublingQueue extends BasicIntQueue with Doubling {

}

object QueueApp extends App {
  val queue = new BasicIntQueue()
  queue.put(5)
  queue.put(30)
  println(queue.get())

  val doubledQueue = new DoublingQueue()
  doubledQueue.put(5)
  doubledQueue.put(30)
  println(doubledQueue.get())

  /**
   * Stackable modifications
   */

  val notNegativeIncrementedQueue = (new BasicIntQueue with Incrementing with Filtering) //order make a difference -> first Filtering
  notNegativeIncrementedQueue.put(-2)
  notNegativeIncrementedQueue.put(-1)
  notNegativeIncrementedQueue.put(1)
  println(notNegativeIncrementedQueue.get())

  val notNegativeIncrementedQueue2 = (new BasicIntQueue with Filtering with Incrementing) // first Incrementing
  notNegativeIncrementedQueue2.put(-2)
  notNegativeIncrementedQueue2.put(-1)
  notNegativeIncrementedQueue2.put(1)
  println(notNegativeIncrementedQueue2.get())
  println(notNegativeIncrementedQueue2.get())

  val notNegativeIncrementedDoubledQueue = (new BasicIntQueue with Doubling with Filtering with Incrementing)
  notNegativeIncrementedDoubledQueue.put(-2)
  notNegativeIncrementedDoubledQueue.put(1)
  notNegativeIncrementedDoubledQueue.put(2)
  println(notNegativeIncrementedDoubledQueue.get())
  println(notNegativeIncrementedDoubledQueue.get())
}