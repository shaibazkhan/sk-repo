package com.learn.scala.tdd

import org.scalatest.FunSuite
import BinaryGap.findLongestBinaryGap

/**
  * Created by shaibazkhan on 19/10/2017.
  */
class BinaryGapSuite extends FunSuite {

  test("find binary gap in 9") {
    assert(findLongestBinaryGap(9) == 2)
  }

  test("find binary gap in 11") {
    assert(findLongestBinaryGap(11) == 1)
  }

  test("find binary gap in 20") {
    assert(findLongestBinaryGap(20) == 1)
  }

  test("find binary gap in 4") {
    assert(findLongestBinaryGap(4) == 0)
  }

  test("find binary gap in 529") {
    assert(findLongestBinaryGap(529) == 4)
  }
}
