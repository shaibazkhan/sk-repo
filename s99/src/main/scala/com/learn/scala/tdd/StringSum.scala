package com.learn.scala.tdd


object EmptyString {
  def unapply(s: String): Option[String] = if (s == null || s.isEmpty) None else Some(s)
}
/**
  * Created by shaibazkhan on 18/10/2017.
  */
class StringSum {

  def sum(s: String, s1: String): Int = if (operandsEmpty(s, s1)) 0 else s.toInt + s1.toInt

  def operandsEmpty(s: String, s1: String): Boolean = (s, s1) match {
    case (EmptyString(s), EmptyString(s1)) => false
    case (_, _) => true
  }
}
