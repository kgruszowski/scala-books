package Chapter20

import Chapter20.Currency.US.Dollar

object Currency extends App{

  // first not perfect design
//  abstract class Currency {
//    val amount: Long
//    def designation: String
//
//    override def toString: String = s"$amount $designation"
//
//    def + (that: Currency): Currency = ...
//    def * (x: Double): Currency = ...
//  }

  abstract class CurrencyZone {
    type Currency <: AbstractCurrency // abstract type
    def make(x: Long): Currency // abstract factory method
    val CurrencyUnit: Currency

    object Converter {
      var exchangeRate = Map(
        "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596, "JPY" -> 1.211),
        "EUR" -> Map("USD" -> 1.316, "EUR" -> 1.0, "JPY" -> 1.594),
        "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272, "JPY" -> 1.0)
      )
    }

    abstract class AbstractCurrency {
      val amount: Long
      def designation: String

      override def toString: String = {
        (amount.toDouble / CurrencyUnit.amount.toDouble) formatted(
          "%." + decimals(CurrencyUnit.amount) + "f"
        ) + " " + designation
      }

//      def + (that: Currency): Currency = make(this.amount + that.amount)
      // def + that converts second currency in fly
      def + (that: CurrencyZone#AbstractCurrency): Currency = {
        make(this.amount + from(that).amount)
      }
      def * (x: Double): Currency = make((this.amount * x).toLong)
      def - (that: Currency): Currency = make(this.amount - that.amount)
      def / (that: Currency): Currency = make(this.amount / that.amount)
      def / (x: Double): Currency = make((this.amount / x).toLong)

      // CurrencyZone#AbstractCurrency - formal parameter type,
      def from(other: CurrencyZone#AbstractCurrency): Currency = {
        make(math.round(
          other.amount.toDouble * Converter.exchangeRate(other.designation)(this.designation)
        ))
      }

      private def decimals(n: Long): Int =
        if (n == 1) 0 else 1 + decimals(n / 10)
    }
  }

  object US extends CurrencyZone {
    abstract class Dollar extends AbstractCurrency {
      def designation = "USD"
    }
    type Currency = Dollar
    def make(cents: Long) = new Dollar { val amount = cents }
    val Cent = make(1)
    val Dollar = make(100)
    val CurrencyUnit = Dollar
  }

  object Europe extends CurrencyZone {
    abstract class Euro extends AbstractCurrency {
      def designation = "EUR"
    }
    type Currency = Euro
    def make(cents: Long) = new Euro { val amount = cents }
    val Cent = make(1)
    val Euro = make(100)
    val CurrencyUnit = Euro
  }

  object Japan extends CurrencyZone {
    abstract class Yen extends AbstractCurrency {
      def designation = "JPY"
    }
    type Currency = Yen
    def make(yen: Long) = new Yen { val amount = yen }
    val Yen = make(1)
    val CurrencyUnit = Yen
  }

  println(Japan.Yen from US.Dollar * 100)
  println(US.Cent from Europe.Cent * 100)
  println(US.Dollar from US.Cent * 100)
  println(US.Dollar from Europe.Cent)
  println((US.Dollar * 2) + (US.Dollar * 2) * 2)
  println(Japan.Yen * 100 - Japan.Yen)
  println(Japan.Yen from US.Dollar)
  println(Japan.Yen + US.Dollar)

}
