package com.learn.scala.tdd.rover

/**
  * Created by shaibazkhan on 25/10/2017.
  */
class Rover(grid: Grid, initialPosition: Position) {

  var currentPosition = initialPosition

  def execute(commands: String) =
    commands.split("").foreach(cmd => cmd match {
      case "M" => currentPosition = grid.nextPosition(currentPosition)
      case "R" => currentPosition = currentPosition.right
      case "L" => currentPosition = currentPosition.left
    })

  def position = currentPosition
}

object Rover {
  def apply(grid: Grid, position: Position): Rover = new Rover(grid, position)
}
