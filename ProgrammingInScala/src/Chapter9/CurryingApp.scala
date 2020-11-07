package Chapter9

object CurryingApp extends App {
  println("Curried function assigned to object member " + CurriedSum.second(2))
  println("Invoke curried function " + CurriedSum.curriedSum(1)(2))
  val plusOne = CurriedSum.curriedSum(1)_ // mix curried function with partially applied function
  println("Use placeholder for curried function " + plusOne(10))
  println("Use placeholder for curried function with another argument " + plusOne(100))
}

object CurriedSum {
  def firstFunc(x: Int) = (y: Int) => x + y

  val second = firstFunc(1)

  def curriedSum(x: Int)(y: Int) = x + y
}


