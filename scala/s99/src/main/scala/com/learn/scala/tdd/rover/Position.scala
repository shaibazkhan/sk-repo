package com.learn.scala.tdd.rover

/**
  * Created by shaibazkhan on 25/10/2017.
  */
case class Position(x: Int, y: Int, direction: Direction) {

  def left = Position(x, y, Directions.directionsFor(direction.left))

  def right = Position(x, y, Directions.directionsFor(direction.right))
}

