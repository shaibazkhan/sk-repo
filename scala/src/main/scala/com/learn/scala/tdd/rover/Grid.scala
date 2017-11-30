package com.learn.scala.tdd.rover

/**
  * Created by shaibazkhan on 25/10/2017.
  */
case class Grid(x: Int, y: Int) {

  def nextPosition(current:Position): Position = current match {
    case Position(x, y, North) => Position(x, y+1, North)
    case Position(x, y, East) => Position(x+1, y, East)
    case Position(x, y, West) => Position(x-1, y, West)
    case Position(x, y, South) => Position(x, y-1, South)
  }

}
