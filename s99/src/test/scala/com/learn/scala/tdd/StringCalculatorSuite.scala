package com.learn.scala.tdd

import org.scalatest.FunSuite

/**
  * Created by shaibazkhan on 18/10/2017.
  */
class StringCalculatorSuite extends FunSuite {

  test("empty string") {
    assert(new StringCalculator().add("") == 0)
  }

  test("add a number") {
    assert(new StringCalculator().add("1") == 1)
  }

  test("add numbers with delimiters") {
    assert(new StringCalculator().add("1,2") == 3)
  }

  test("add numbers with new line delimiters") {
    assert(new StringCalculator().add("1\n2,3") == 6)
  }

  test("numbers bigger than 1000 ignored") {
    assert(new StringCalculator().add("1000,2") == 2)
  }

  test("add numbers with different delimiters") {
    assert(new StringCalculator().add(";\n1;2") == 3)
  }

  test("delimiters can be of any length") {
    assert(new StringCalculator().add("//[###]\n1###2###3") == 6)
  }

  test("allow multiple delimiters") {
    assert(new StringCalculator().add("//[#][%]\n1#2%3") == 6)
  }

  test("negative numbers not allowed") {
    val thrown = intercept[IllegalArgumentException] {
      new StringCalculator().add("//;\n1;2p-4")
    }
    assert(!thrown.getMessage.isEmpty)
  }
}
