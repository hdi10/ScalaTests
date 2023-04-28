package test
import org.scalatest.funsuite.AnyFunSuite
import uebung1.Rational

class RationalTest extends AnyFunSuite {

  test("Rational Inititalisation 1") {
    val x = new Rational(1, 2)
    assert(x.value === 0.5)
  }

  test("Rational Inititalisation 2") {
    val x = new Rational(1, 2)
    assertResult("1/2") {
      x.toString
    }
  }

  test("Test requirement (denominator!=0)") {
    intercept[IllegalArgumentException] {
      new Rational(1, 0)
    }
  }

  test("numbers below 10 that are multiplies ") {
    //val x = new Rational(20,1);

    def multiple1(x: Integer): Integer = {
      if (x <= 0) 0
      else if ((x % 3 == 0) || (x % 5 == 0)) x + multiple1(x - 1) // geht auch x-1
      else multiple1(x - 1)
    }

    val x = multiple1(10)

    assertResult(23) {
      x.toString
    }
  }

  test("sqrt im nährungsverfahren selbst definert 1. Variante") {

    def sqrt(x: Double): Double = {

      def iter_sqrt(x: Double, estimate: Double): Double = {
        if (isGoodEnough(x, estimate)) estimate
        else iter_sqrt(x, improve(x, estimate))
      }

      def isGoodEnough(x: Double, estimate: Double): Boolean =
        ((estimate * estimate - x).abs < 0.000001)

      def improve(x: Double, estimate: Double): Double =
        (x / estimate + estimate) / 2


      iter_sqrt(x, 1)
    }

    val x = sqrt(36)
    assertResult(6) {
      x.toString
    }

  }

  test("sqrt im nährungsverfahren selbst definert 2. Variante nested") {
    def sqrt(x: Double): Double = {

      def iter_sqrt(x: Double, estimate: Double): Double =
        if (isGoodEnough(x, estimate)) estimate
        else iter_sqrt(x, improve(x, estimate))

      def isGoodEnough(x: Double, estimate: Double): Boolean =
        ((estimate * estimate - x).abs < 0.000001)

      def improve(x: Double, estimate: Double): Double =
        (x / estimate + estimate) / 2

      iter_sqrt(x, 1)
    }

    val x = sqrt(36)
    assertResult(6) {
      x.toString
    }

  }

  test("sqrt im nährungsverfahren selbst definert 3. und effizienteste Variante") {
    def sqrt(x: Double): Double = {

      def iter_sqrt(estimate: Double): Double = {
        def isGoodEnough: Boolean = ((estimate * estimate - x).abs < 0.000001)

        def improve: Double = (x / estimate + estimate) / 2

        if (isGoodEnough) estimate
        else iter_sqrt(improve)
      }

      iter_sqrt(1)
    }

    val x = sqrt(9)
    assertResult(3) {
      x.toString
    }

  }

  test("is this number a prime number?") {

    def isPrime(x: Int): Boolean = {
      val endValue = (math.sqrt(x).toInt + 1)

      def prime_helper(curVal: Int): Boolean = {
        if (curVal == x) true
        else if (x % curVal == 0) false
        else prime_helper(curVal + 1)
      }

      if (x == 1) false
      prime_helper(2)

    }

    var my = isPrime(23);
    var yours = isPrime(21);
    assert(my)
    assert(!yours)

  }

  test("calculation prime of the 1001 prime number") {

    def isPrime(x: Int): Boolean = {
      val endValue = (math.sqrt(x).toInt + 1)

      def prime_helper(curVal: Int): Boolean = {
        if (curVal == x) true
        else if (x % curVal == 0) false
        else prime_helper(curVal + 1)
      }

      if (x == 1) false
      prime_helper(2)

    }

    /*def calcNthPrim(n: Int): Int = {
      def calcHelper(n: Int, nr: Int): Long = {
        val p = isPrime(nr)
        if (n <= 1 && p) nr
        else if (p) calcHelper(n - 1, nr + 1)
        else calcHelper(n, nr + 1)

      }

      calcHelper(n, 2)
    }*/

    var my = isPrime(23);
    assert(my);

  }


  test("Fibonacci Zahl von 100 berechnen, hier noch keine effiziente Lösung") {

    def fibo(x: Int): BigInt = {
      if (x == 0) 0
      else if (x == 1) 1
      else fibo(x - 1) + fibo(x - 2)
    }


    var thisOne = fibo(5)
    var thisOne1 = fibo(8)
    assertResult(5) {
      thisOne.toInt
    }
    assertResult(21) {
      thisOne1.toInt
    }

  }

  test("Fibonacci Zahl von 100 berechnen, hier effizienter gestaltete Lösung") {

    def fib(x: Int): BigInt = {

      def helper(f1: BigInt, f2: BigInt, z: Int): BigInt = { //z ist der berechnungsschritt, letzte fibonacci wenn z=0
        if (z == 0) f2
        else helper(f2, f1 + f2, z - 1)
      }

      helper(0, 1, x)
    }

    var thisOne = fib(12)
    assertResult(144) {
      thisOne.toInt
    }
  }


  test("Fibonacci Zahl von 100 berechnen, Variante aus UE Muster") {

    def fib(x: Int): BigInt = {

      def helper(f1: BigInt, f2: BigInt, z: Int): BigInt = { //z ist der berechnungsschritt, letzte fibonacci wenn z=0
        if (z == 0) f1
        else helper(f1 + f2, f1, z - 1)
      }

      helper(0, 1, x)
    }

    var thisOne = fib(12)
    assertResult(144) {
      thisOne.toInt
    }

    var thisOne1 = fib(31)
    assertResult(1346269) {
      thisOne1.toInt
    }
  }

  test("tupel für UE3 1)") {

    def convert(convertTo: String, temp: Double): (String, Double) = {
      //return --> //(String) neue Einheit // (Double) neuer Wert

      convertTo match {
        case "Fahrenheit" => (convertTo, temp * 1.8 + 32)
        case "Reamure" => (convertTo, temp * 0.8)
        case "Kelvins" => (convertTo, temp + 273.15)
      }


    }

  }


  /*test("tupel für UE3 2)") {

    def convert2(change: (String, Double)): (String, Double) = {
      //return --> //(String) neue Einheit // (Double) neuer Wert

      change match {
        //besteht aus einer konstanten und einer variablen
        case ("Fahrenheit", temp) => (change, temp * 1.8 + 32)
        case ("Reamure", temp) => convert2(convertTo, temp * 0.8)
        case ("Kelvins", temp) => (convert._1, temp + 273.15)
      }


    }*/
}
