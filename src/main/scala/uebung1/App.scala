package uebung1

import com.sun.net.httpserver.Authenticator.{Failure, Success}
import org.apache.logging.log4j.scala.Logging

import java.util.logging.Logger
import scala.reflect.runtime.universe.Try

object App {

  def main(args: Array[String]): Unit = {



    println("Hier startet das erste Scala Programm!")
    val x= new Rational(2,5)
    val y= new Rational(3,4)
    val z= x.max(y)
    if (z==y) println("Richtiges Ergebnis")
    	else println("Falsches Ergebnis")


    def fib(x: Int): BigInt = {

      def helper(f1: BigInt, f2: BigInt, z: Int): BigInt = { //z ist der berechnungsschritt, letzte fibonacci wenn z=0
        if (z == 0) f2
        else helper(f2, f1 + f2, z - 1)
      }

      helper(0, 1, x)
    }

    println("Fibonacci von 9 lautet: "+fib(8).toLong)

    // wirft Assertion Failed Exception
    assert(z==y)
  }


}
