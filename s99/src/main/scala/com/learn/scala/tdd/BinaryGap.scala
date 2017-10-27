package com.learn.scala.tdd

/**
  * Created by shaibazkhan on 19/10/2017.
  */
object BinaryGap {

  def findLongestBinaryGap(num: Int): Int = {

    def checkList(list: List[Int]): Boolean = !list.isEmpty && list.last == 1

    def bin(list: List[Int], binaries: List[Int]): List[Int] = if(checkList(list)) list.count(_ == 0) :: binaries else binaries

    def countZeros(acc: List[Int], running: Int, binaries: List[Int]): Int = running match {
      case 0 => if(!binaries.isEmpty) binaries.max else 0
      case _ if running%2 == 0 =>  countZeros(0 :: acc, running/2, binaries)
      case _ if running%2 == 1 =>
          countZeros(1 :: Nil, running/2, bin(acc, binaries))
    }

    countZeros(List(), num, List())
  }
}
