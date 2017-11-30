package com.learn.scala.tdd

import org.scalatest.{FlatSpec, Matchers}
import DigitalToBerlinConverter._
/**
  * Created by shaibazkhan on 25/10/2017.
  */
class DigitalToBerlinConverterSpec extends FlatSpec with Matchers {

  "berlin clock's top seconds light" should "be off" in {
    topClock(1) should be ("O")
  }

  it should "blink every two seconds" in {
    topClock(2) should be ("Y")
  }

  "berlin clock's top hours at midnight" should "be off" in {
    topHours(0) should be ("OOOO")
  }

  it should "lit up at first 5 hour" in {
    topHours(5) should be ("ROOO")
  }

  it should "lit up then every five hours" in {
    topHours(23) should be ("RRRR")
  }

  "berlin clock's second hours light after midnight" should "be off" in {
    bottomHours(0) should be ("OOOO")
  }

  it should "then lit up at first hour" in {
    bottomHours(1) should be ("ROOO")
  }

  it should "then lit up at second hour" in {
    bottomHours(2) should be ("RROO")
  }

  "berlin clock's five minutes row after midnight" should "be off" in {
    topMinutes(0) should be ("OOOOOOOOOOO")
  }

  "berlin clock's every five minutes" should "be off" in {
    topMinutes(10) should be ("YYOOOOOOOOO")
  }

  it should "lit up quarter lamp quarter past hour" in {
    topMinutes(15) should be ("YYROOOOOOOO")
  }

  it should "lit up quarters lamp at every quarter past an hour" in {
    topMinutes(35) should be ("YYRYYRYOOOO")
  }
}
