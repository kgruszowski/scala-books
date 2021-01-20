package Chapter20

object Ch20App extends App{

  object LazyDemo {
    val x = { println("init x"); "x value"}
    lazy val y = { println("init y"); "y value"}
  }

  LazyDemo // it will print out "init x" but not "init y"
  println("--------")
  println(LazyDemo.x)
  println("--------")
  println(LazyDemo.y) // "init y" will be deferred until here
  println(LazyDemo.y) // but it will be performed only once, now it is stored in object

  class Food
  class Grass extends Food
  class Fish extends Food

  abstract class Animal {
    type SuitableFood <: Food // upper bound
    def eat(food: SuitableFood): Unit
  }

  class Cow extends Animal {
    type SuitableFood = Grass
    override def eat(food: SuitableFood): Unit = { println("Cow eats grass") }
  }

//  class Bear extends Animal {
//    type SuitableFood = Fish
//    override def eat(food: Fish): Unit = { println("Bear eats fish") }
//  }

//  val cow: Animal = new Cow()
//  cow.eat(new Grass)
  // cow.eat(new Fish) - it won't work
  // cow.eat(new Food) - either this
  //val bear: Animal  = new Bear()
  //bear.eat(new bear.SuitableFood)
}
