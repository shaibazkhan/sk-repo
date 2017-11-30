package com.learn.scala.tdd

/**
  * Created by shaibazkhan on 25/10/2017.
  */
object DigitalToBerlinConverter {

  def topHours(hours: Int): String = displayTime(hours / 5)

  def bottomHours(hours: Int): String = displayTime(hours % 5)

  def topMinutes(minutes: Int): String = {
    val times = minutes / 5

    ("Y" * times + "O" * (11-times)).replace("YYY", "YYR")
  }

  def displayTime(times: Int): String = "R" * times + "O" * (4 - times)

  def topClock(second: Int): String = if(second % 2 == 0) "Y" else "O"

}
