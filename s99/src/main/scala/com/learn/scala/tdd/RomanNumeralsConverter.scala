package com.learn.scala.tdd

/**
  * Created by shaibazkhan on 23/10/2017.
  */
object RomanNumeralsConverter {

  val numerals = Map('I' -> 1, 'V' -> 5, 'X' -> 10, 'L' -> 50, 'C' -> 100, 'D' -> 500, 'M' -> 1000)

  def convertToDecimal(s: String): Int = toDecimal(s.toUpperCase.map(numerals).toList, 0, 0)

  def deduction(last: Int, curr: Int): Int = if (last < curr) -2*last else 0

  def toDecimal(numbers: List[Int], sum: Int, last: Int): Int = numbers match {
    case x :: xs =>
      if(sum > 0 && last < x)
        toDecimal(xs, sum + x + deduction(last, x), x)
      else
        toDecimal(xs, sum+x, x)
    case Nil => sum
  }
}
