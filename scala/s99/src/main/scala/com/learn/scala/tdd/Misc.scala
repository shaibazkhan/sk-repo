package com.learn.scala.tdd

import com.learn.scala.tdd.EmptyString

/**
  * Created by shaibazkhan on 18/10/2017.
  */
object Misc {

  def main(args: Array[String]): Unit = {

    //println(longestBinaryGap(List(1,0,0)))
    //println(findLongestBinaryGap(20))
    //println (List("Mary", "had", "a", "little", "lamb", "little").indexOf("little"))



    print(2000/1000)
  }

  def findLongestBinaryGap(num: Int): List[Int] = {
    def countZeros(acc: List[Int], running: Int): List[Int] = running match {
      case 0 => acc
      case _ if running%2 == 0 =>  countZeros(0 :: acc, running/2)
      case _ if running%2 == 1 =>  countZeros(1 :: acc, running/2)
      case _ => countZeros(acc, running/2)
    }

    countZeros(List(), num)
  }

  def longestBinaryGap(list: List[Int]): Int = {

    def findLongestGap(list: List[Int], current: List[Int], result: List[Int]): Int = list match {
      case List() => result.max
      case 1 :: xs =>
        if(!list.isEmpty) findLongestGap(xs, List(), current.count(_ == 0) :: result) else findLongestGap(xs, current, result)
      case 0 :: xs => findLongestGap(xs, 0 :: current, result)
    }

    findLongestGap(list, List(), List())
  }

  def patternMatching(): Unit ={
    val s = "-1\n2,3"

    val regexPattern = "^(\\W+)\\n.*".r.findFirstMatchIn(s) match {
      case m@Some(e) => m.get.group(1)
      case None => "[\n,]"
    }

    val pattern = "^//(\\[(.*)\\])".r.findFirstMatchIn(regexPattern) match {
      case m@Some(e) => m.get.group(1)
      case None => regexPattern
    }


    println("-?\\d+".r.findAllIn(s).toList.map(_.toString).map(_.toInt))

    for(m <- "-?\\d+".r.findAllIn(s).toList) println(m)

    println(pattern)
  }

  def extract(s: String): String = {
    "^(\\W+)\\n.*".r.findFirstMatchIn(s) match {
      case m@Some(e) => m.get.group(1)
      case None => "[\n,]"
    }
  }
  //"^\\W\\n(.*)".r.findFirstIn(s).getOrElse(s)

  def operandsEmpty(s: String, s1: String): (String, String) = (s, s1) match {
    case (e @ EmptyString(_), e1 @ EmptyString(_)) => (e, e1)
    case (_, _) => ("Nil", "Nil")
  }

  def extractElementsFrom(s: String): (String, String) = {
    "^(\\W)(\n)(.*)".r.findFirstMatchIn(s) match {
      case v@Some(e) => (v.get.group(3), v.get.group(1))
      case None => (s, "\n|,")
    }
  }
}
