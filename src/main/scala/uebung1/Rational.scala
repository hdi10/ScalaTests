package uebung1


class Rational(val numerator: Int, val denominator: Int) {

  //LoggingApp
  def this(denom: Int) = this(1, denom)

  override def toString: String = s"$num/$denom"

  require(denominator != 0, "Denominator mmuss != 0 sein")

  def num: Int = numerator

  def denom: Int = denominator

  def value: Double = (num.toDouble / denom)

  def max(x: Rational): Rational = {

    if (numerator / denominator < x.num / x.denom) this else x
  }


  def fib(x: Int): BigInt = {

    def helper(f1: BigInt, f2: BigInt, z: Int): BigInt = { //z ist der berechnungsschritt, letzte fibonacci wenn z=0
      if (z == 0) f1
      else helper(f1 + f2, f1, z - 1)
    }

    helper(0, 1, x)
  }

  var thisOne = fib(1220)
  println(
    "bibonacci von 1220 ist " +thisOne.toLong
  )

  var thisOne1 = fib(2105)
  println("fibonacci von 2105 ist "+thisOne1.toLong)


}

