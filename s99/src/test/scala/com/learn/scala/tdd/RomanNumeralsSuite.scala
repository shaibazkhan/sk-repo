package com.learn.scala.tdd

import org.scalatest.FunSuite
import RomanNumerals.convert
/**
  * Created by shaibazkhan on 22/10/2017.
  */
class RomanNumeralsSuite extends FunSuite {

  test("convert 1") {
    assert(convert(1) == "I")
  }

  test("convert 2") {
    assert(convert(2) == "II")
  }

  test("convert 3") {
    assert(convert(3) == "III")
  }

  test("convert 4") {
    assert(convert(4) == "IV")
  }

  test("convert 6") {
    assert(convert(6) == "VI")
  }

  test("convert 9") {
    assert(convert(9) == "IX")
  }

  test("convert 30") {
    assert(convert(30) == "XXX")
  }

  test("convert 41") {
    assert(convert(41) == "XLI")
  }

  test("convert 89") {
    assert(convert(89) == "LXXXIX")
  }
}
