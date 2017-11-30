package com.learn.scala.tdd

/**
  * Created by shaibazkhan on 21/10/2017.
  */
object FizzBuzz {

  def reportOutPut(start: Int, end: Int): String = {
    val result = rangeOutPut(start, end)
    val resultParts = result.split(" ").toSeq
    val (nums, words) = resultParts.partition(_.forall(Character.isDigit))
    val wordsReport = words.groupBy(k => k).map{ case (k, v) => s"\n$k: ${v.size}"}
    val summary = wordsReport.mkString("") + "\ninteger: " + nums.size

    s"$result$summary"
  }

  def rangeOutPut(start: Int, end: Int): String = rangeOutPutList(start, end).mkString(" ")

  def rangeOutPutList(start: Int, end: Int): List[String] = (start to end).toList.map(output)

  def output(num: Int): String = num match {
    case _ if isNumberContainsThree(num) => "lucky"
    case _ if isMultipleOfFifteen(num) => "fizzbuzz"
    case _ if isMultipleOfThree(num) => "fizz"
    case _ if isMultipleOfFive(num) => "buzz"
    case _ => num.toString
  }

  def isMultipleOfFifteen(num: Int): Boolean = num % 15 == 0
  def isMultipleOfThree(num: Int): Boolean = num % 3 == 0
  def isMultipleOfFive(num: Int): Boolean = num % 5 == 0
  def isNumberContainsThree(num: Int): Boolean = num.toString.contains("3")

  def isWord(s: String): Boolean = s match {
    case "fizz" => true
    case "buzz" => true
    case "fizzbuzz" => true
    case "lucky" => true
    case _ => false
  }
}
