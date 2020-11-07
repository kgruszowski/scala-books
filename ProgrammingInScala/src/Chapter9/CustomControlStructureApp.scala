package Chapter9

import java.io.{File, IOError, PrintWriter}

object CustomControlStructureApp extends App{
  def twice(op: Double => Double, x: Double) = op(op(x))

  println("add one twice " + twice(_ + 1, 10))
  println("add ten twice " + twice(_ + 10, 10))
  println("multiple by two twice " + twice(_ * 2, 10))

  def withPrintWriter(file: File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  withPrintWriter(
    new File("./public/Chapter9/date.log"),
    writer => writer.println(new java.util.Date)
  )

  def withPrintWriter2(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  val file = new File("./public/Chapter9/date2.log")

  // curried function, function can be called by () or {}
  withPrintWriter2(file) { writer =>
    writer.println(new java.util.Date)
  }

  /**
   * By-name  parameters
   */
  var assertionEnabled = true

  def standardAssert(predicate: Boolean) =
    if (assertionEnabled && !predicate)
      throw new AssertionError()

  def functionAssert(predicate: () => Boolean) =
    if (assertionEnabled && !predicate())
      throw new AssertionError()

  def byNameAssert(predicate: => Boolean) =
    if (assertionEnabled && !predicate)
      throw new AssertionError()

  standardAssert(100 > 50) // will execute 100 > 50 and then pass it to the assertion function
  functionAssert(() => 100 > 50) // will postpone execution of 100 > 50 until assert body call it, unconcise format
  byNameAssert(100 > 50) // the same as above but in more concise format

  assertionEnabled = false
  functionAssert(() => 1 > 0 / 0) // for sure it cannot be calculated!
  byNameAssert(1 > 0 / 0) // see no error again
  standardAssert(1 > 0 / 0) // ooopsies!
}
