package com.learn.scala.tdd.rover

import org.scalatest.{Matchers, FlatSpec}


class RoverSpec extends FlatSpec with Matchers {

  val GRID_10_X_10: Grid = new Grid(10, 10)

  val INITIAL_POSITION: Position = Position(0, 0, North)

  trait context {
    val rover = Rover(GRID_10_X_10, INITIAL_POSITION)
  }

  "rover" should "have initial position" in new context {
    rover.position should be (INITIAL_POSITION)
  }

  it should "move one point in the same direction" in new context {
    rover execute("M")
    rover.position should be (Position(0, 1, North))
  }

  it should "move few points in same direction" in new context {
    rover execute("MMM")
    rover.position should be (Position(0, 3, North))
  }

  it should "turn right" in new context {
    rover execute("R")

    rover.position should be (Position(0, 0, East))
  }

  it should "turn right and move one point" in new context {
    rover execute("RM")

    rover.position should be (Position(1, 0, East))
  }

  it should "turn left" in new context {
    rover execute("L")

    rover.position should be (Position(0, 0, West))
  }

  it should "turn right twice" in new context {
    rover execute("RR")

    rover.position should be (Position(0, 0, South))
  }

  it should "do few turns left and right" in new context {
    rover execute("RRLLR")

    rover.position should be (Position(0, 0, East))
  }

  it should "do full right and left circle" in new context {
    rover execute("RRRRLLLL")

    rover.position should be (Position(0, 0, North))
  }

  it should "turn left and move few points" in new context {
    override val rover = Rover(GRID_10_X_10, Position(5, 5, West))

    rover execute("M")

    rover.position should be (Position(4, 5, West))
  }

  it should "move one point towards south" in new context {
    override val rover = Rover(GRID_10_X_10, Position(5, 5, South))

    rover execute("M")

    rover.position should be (Position(5, 4, South))
  }
}
