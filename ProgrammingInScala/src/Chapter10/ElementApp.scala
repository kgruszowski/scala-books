package Chapter10
import Element.elem

object ElementApp extends App {
  val header =elem("Table of students")
  val u1 = elem(' ', 1, 3)
  val c1 = elem(Array("name", "age", "city"))
  val c2 = elem(Array("Kamil", "26", "Cracow"))

  println(header above (c1 beside u1 beside c2))
}
