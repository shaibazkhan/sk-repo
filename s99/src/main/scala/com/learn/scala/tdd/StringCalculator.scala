package com.learn.scala.tdd

/**
  * Created by shaibazkhan on 18/10/2017.
  */
class StringCalculator {

  def add(s: String): Int = if(s.isEmpty) 0 else sumOf(s)

  def sumOf(s: String): Int = if(s.length == 1) s.toInt else sumNumbersWithin(s)

  def sumNumbersWithin(s: String): Int = findNumbers(s).sum

  def findNumbers(s: String): List[Int] = {
    val numbers: List[Int] = "-?\\d+".r.findAllIn(s).toList.map(_.toInt).filter(_ < 1000)

    val negatives = numbers.filter(_ < 0)
    require(numbers.filter(_ < 0).length <= 0, String.format("negative numbers not alloed %s", negatives))

    numbers
  }

}
