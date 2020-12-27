package Chapter15

sealed abstract class Expr{ // sealed class can be extended only by classes in the same file!
  case class Var (name: String) extends Expr
  case class Number (num: Double) extends Expr
  case class UnOp (operator: String, arg: Expr) extends Expr
  case class BinOp (operator: String, left: Expr, right: Expr) extends Expr

  def simplifyAll(expr: Expr): Expr = expr match { // it acts like an expression so {} for simplifyTop can be omitted
    case UnOp("-", UnOp("-", e)) => simplifyAll(e) // constructor pattern
    case BinOp("+", e, Number(0)) => simplifyAll(e)
    case BinOp("*", e, Number(1)) => simplifyAll(e)
    case UnOp("-", Number(e)) => Number(-e)
    // case "+" - constant pattern (cannot be used here, since expr is Expr)
    // case List(0, _, _) - sequence pattern (fixed length)
    // case List(0, _*) - sequence pattern (variable length)
    // case (a, b, c) - tuple pattern
    // case s: String - type class
    case _ => expr // wildcard pattern
  }

  def simplifyAdd(expr: Expr): Expr = expr match {
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) // pattern guard, cannot be coded as Bin(+, x, x) it has to be linear, variable appears only once!
    case _ => expr
  }
}