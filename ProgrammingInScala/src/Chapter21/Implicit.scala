package Chapter21

object Implicit extends App{
  implicit def bool2Int(x: Boolean): Int = if (x) 1 else 0

  val a = 12
  val x = false
  // cannot perform + on Boolean, but before it fails with error compiler tries to convert it with implicit conversion
  val implicit1 = a + x
  println(implicit1)

  /*
    Example of implicit class
   */
  val rect = 2 x 4 // uses implicit conversion for RectangleMaker, Int has no "x" method so compiler search for something that does and it finds RectangleMaker
  println(rect)
  val implicitRectMaker2: () = "test" x 2 // here is RectangleMaker2 applied

  case class Rectangle(width: Int, height: Int)

  /* Rich wrapper for rectangle
    it allows to construct class by invoking "width x height"
    WARNING: implicit cannot be used with case classes and it requires class to have constructor with only one parameter
   */
  implicit class RectangleMaker(width: Any) {
    def x(height: Int) = println(width)
  }

  /* Cannot define second implicit class with same constructor up to Scala 2.7
    implicit class RectangleMaker2(width: Int) {
      def x(height: Int) = Rectangle(height, width)
    }
   */

  implicit class RectangleMaker2(width: Int) {
    def x(height: Int) = Rectangle(height, width)
  }

  /**
   * @TODO: understand implicit parameters...
   */
}
