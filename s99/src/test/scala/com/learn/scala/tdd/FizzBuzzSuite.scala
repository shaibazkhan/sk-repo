package com.learn.scala.tdd

import org.scalatest.FunSuite
import FizzBuzz._
/**
  * Created by shaibazkhan on 21/10/2017.
  */
class FizzBuzzSuite extends FunSuite {

  test("number multiples of 3 is fizz")  {
    assert(output(9) == "fizz")
  }

  test("number multiples of 5 is buzz") {
    assert(output(20) == "buzz")
  }

  test("number multiples of 15 is fizzbuzz") {
    assert(output(15) == "fizzbuzz")
  }

  test("number not multiples of 3, 5 and 15 equals number itself") {
    assert(output(2) == "2")
  }

  test("range output") {
    assert(rangeOutPut(1, 20) == "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz")
  }

  test("range report from 1 to 8") {
    assert(
      reportOutPut(1, 8) ==
        "1 2 lucky 4 buzz fizz 7 8\nfizz: 1\nbuzz: 1\nlucky: 1\ninteger: 5")
  }

  test("range report from 1 to 20") {
    assert(
      reportOutPut(1, 20) ==
        "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz\nfizz: 4\nbuzz: 3\nfizzbuzz: 1\nlucky: 2\ninteger: 10")
  }
}
