package com.learn.scala.tdd.rover

/**
  * Created by shaibazkhan on 25/10/2017.
  */
sealed abstract class Direction(currentDirection:String, leftDirection: String, rightDirection: String) {

  def current = currentDirection

  def left = leftDirection

  def right = rightDirection
}

object North extends Direction("N", "W", "E")
object East extends Direction("E", "N", "S")
object West extends Direction("W", "S", "N")
object South extends Direction("S", "E", "W")


object Directions {

  val directions: List[Direction] = List(North, East, South, West)

  def directionsFor(s: String): Direction = directions.find(_.current == s).get
}