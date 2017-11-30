package com.learn.scala.tdd

import RomanNumeralsConverter._
import org.scalatest.FunSuite

/**
  * Created by shaibazkhan on 23/10/2017.
  */
class RomanNumeralsConverterSuite extends FunSuite {

  test("convert I") {
    assert(convertToDecimal("I") == 1)
  }

  test("convert II") {
    assert(convertToDecimal("II") == 2)
  }

  test("convert III") {
    assert(convertToDecimal("III") == 3)
  }

  test("convert V") {
    assert(convertToDecimal("V") == 5)
  }

  test("convert L") {
    assert(convertToDecimal("L") == 50)
  }

  test("convert IV") {
    assert(convertToDecimal("IV") == 4)
  }

  test("convert XL") {
    assert(convertToDecimal("XL") == 40)
  }

}
