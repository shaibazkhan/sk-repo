package com.learn.scala.tdd

import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * Created by shaibazkhan on 18/10/2017.
  */
class StringSumSuite extends FunSuite with BeforeAndAfter{

  test("operands are empty") {
    assert(new StringSum().sum("", "") == 0)
  }

  test("add zero and number") {
    assert(new StringSum().sum("2", "0") == 2)
  }

  test("add two non-zero numbers") {
    assert(new StringSum().sum("4", "4") == 8)
  }
}
