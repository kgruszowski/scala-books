package Chapter13

class Rocket {
  // Class can access all private methods and fields of the companion object
  import Rocket.fuel

  private def canGoHomeAgain = fuel > 20
}

// Companion object
object Rocket {
  private def fuel = 10

  def chooseStrategy(rocket: Rocket) = {
    if (rocket.canGoHomeAgain) // Companion object can also access all private methods and field of the class
      goHome()
    else
      pickStar()
  }

  def goHome() = {}

  def pickStar() = {}
}
