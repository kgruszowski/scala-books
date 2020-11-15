package Chapter6

object Rational extends App {
  implicit def intToRational(i: Int): Rational = new Rational(i) // implicit method has to be in the scope
  val r1 = new Rational(10,4)
  val r2 = new Rational(2,3)
  println("a = " + r1)
  println("b = " + r2)
  println("a + b = " + (r1 + r2))
  println("a - b = " + (r1 - r2))
  println("a * b = " + (r1 * r2))
  println("a / b = " + (r1 / r2))
  println("a * b + a = " + (r1 * r2 + r1))
  println()
  println("a + 5 = " + (5 + r1))
  println("a - 5 = " + (r1 - 5))
  println("a * 5 = " + (r1 * 5))
  println("a / 5 = " + (r1 / 5))
  println("a > b = " + (r1 > r2))
  println("a < b = " + (r1 < r2))
}

class Rational(n: Int, d: Int) extends Ordered[Rational] {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numerator: Int = n / g
  val denominator: Int = d / g

  def this(n: Int) = this(n, 1)

  override def toString: String = s"$numerator/$denominator"


  override def compare(that: Rational): Int =
    (this.numerator * that.denominator) - (that.numerator * this.denominator)

  def +(that: Rational): Rational =
    new Rational(numerator * that.denominator + that.numerator * denominator, denominator * that.denominator)

  def +(i: Int): Rational =
    this + new Rational(i)

  def -(that: Rational): Rational =
    new Rational(numerator * that.denominator - that.numerator * denominator, denominator * that.denominator)

  def -(i: Int): Rational =
    this - new Rational(i)

  def *(that: Rational): Rational =
    new Rational(numerator * that.numerator, denominator * that.denominator)

  def *(i: Int): Rational =
    this * new Rational(i)

  def /(that: Rational): Rational =
    new Rational(numerator * that.denominator, denominator * that.numerator)

  def /(i: Int): Rational =
    this / new Rational(i)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}
