package Chapter22

object List1 extends App {
  println("hi")
  val a = 1 :: Nil
  println(a)

  class Fruit
  class Apple extends Fruit
  class Orange extends Fruit

  val ap: List[Apple] = new Apple :: Nil
  val c = new Orange :: ap
  println(c)
}
