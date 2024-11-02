package test.Example

import main.Example.adder
import scala.util.Random

import chisel3._
import chisel3.simulator.EphemeralSimulator._
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

object test {
  def getRandomNum(length: Int): String = {
    val random = new Random()
    return ((1 to length).map(_ => random.nextInt(2)).mkString)
  }
}

class adderTest_64 extends AnyFreeSpec with Matchers {
  "Adder test(64)" in {
    simulate(new adder(64)) { dut =>
      for (i <- 0 until 10) {
        val a = BigInt(test.getRandomNum(64), 2)
        val b = BigInt(test.getRandomNum(64), 2)
        val c = (a + b)
        dut.io.in_a.poke(a.U)
        dut.io.in_b.poke(b.U)
        dut.io.out.expect(c.U(63, 0))
      }
    }
  }
}
