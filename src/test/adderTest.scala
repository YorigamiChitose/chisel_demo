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

// class adderTest_32 extends AnyFreeSpec with Matchers {
//   "Adder test(32)" in {
//     simulate(new adder(32)) { dut =>
//       for (i <- 0 until 10) {
//         val a = BigInt(test.getRandomNum(32), 2)
//         val b = BigInt(test.getRandomNum(32), 2)
//         val c = (a + b)
//         dut.io.in_a.poke(a.U)
//         dut.io.in_b.poke(b.U)
//         dut.io.out.expect(c.U(31, 0))
//       }
//     }
//   }
// }

// class adderTest_16 extends AnyFreeSpec with Matchers {
//   "Adder test(16)" in {
//     simulate(new adder(16)) { dut =>
//       for (i <- 0 until 10) {
//         val a = BigInt(test.getRandomNum(16), 2)
//         val b = BigInt(test.getRandomNum(16), 2)
//         val c = (a + b)
//         dut.io.in_a.poke(a.U)
//         dut.io.in_b.poke(b.U)
//         dut.io.out.expect(c.U(15, 0))
//       }
//     }
//   }
// }

// class adderTest_8 extends AnyFreeSpec with Matchers {
//   "Adder test(8)" in {
//     simulate(new adder(8)) { dut =>
//       for (i <- 0 until 10) {
//         val a = BigInt(test.getRandomNum(8), 2)
//         val b = BigInt(test.getRandomNum(8), 2)
//         val c = (a + b)
//         dut.io.in_a.poke(a.U)
//         dut.io.in_b.poke(b.U)
//         dut.io.out.expect(c.U(7, 0))
//       }
//     }
//   }
// }
