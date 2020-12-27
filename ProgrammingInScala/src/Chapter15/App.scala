package Chapter15

object App extends App{
  val expr = new Expr {}
  import expr._

  println(UnOp("-", Number(13)))
  println(expr.simplifyAll(UnOp("-", Number(13))))
  println(UnOp("-", UnOp("-",Number(13)))) // could be simplified to Number(13)
  println(expr.simplifyAll(UnOp("-", UnOp("-",Number(13))))) // pattern match works here
  println(expr.simplifyAll(UnOp("-", UnOp("-",BinOp("+", Number(5), Var("a")))))) // pattern match works here
  println(expr.simplifyAll(UnOp("-", UnOp("-",UnOp("-", UnOp("-",Number(13))))))) // deep pattern match works here
  println(expr.simplifyAdd(BinOp("+", Number(10), Number(10))))
  println(expr.simplifyAdd(BinOp("+", Var("x"), Var("x"))))
}
