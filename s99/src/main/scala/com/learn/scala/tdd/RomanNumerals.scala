package com.learn.scala.tdd

/**
  * Created by shaibazkhan on 22/10/2017.
  */
object RomanNumerals {

  val numerals: Map[Int, String] = Map(50 -> "L", 40 -> "XL", 10 -> "X", 9 -> "IX", 5 -> "V",  4 -> "IV", 1 -> "I")
  val keys = numerals.keysIterator.toList sortWith(_ > _)

  def convert(num: Int): String = keys find(num >= _) match {
    case Some(k) => numerals(k) + convert(num-k)
    case None => ""
  }
}
