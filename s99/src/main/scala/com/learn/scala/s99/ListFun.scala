package com.learn.scala.s99

import javax.xml.transform.Result

import scala.collection.immutable

/**
  * Created by shaibazkhan on 16/10/2017.
  *
  * This singleton object contains implementation (ignoring performance facts) of methods
  * found in List class. Attempt has been made to not use high-order functions available
  * on List class. Idea is to learn (pattern matching on List) and have some fun!
  */
object ListFun {

  def main(args: Array[String]) {
    println("Last element=" + findLast(List(1, 1, 2, 3, 5, 8)))
    println("Penultimate element=" + penultimate(List(1, 1, 2, 3, 5, 8)))

    nth(2, List(1, 1, 2, 3, 5, 8)) match {
      case Some(x) => println("Nth element=" + x)
      case None => println("Nothing found")
    }

    println("List's length=" + length(List(1, 1, 2, 3, 5, 8)))
    println("Reverse list=" + reverse(List(1, 1, 2, 3, 5, 8)))
    println("is palindrome?=" + isPalindrome(List(1, 2, 3, 2, 1)))
    println("flattened List=" + flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
    println("compressed list=" + compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
    println("packed list=" + pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
    println("run-length encoding="+ encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
    println("run-lenght encoding modifies=" + encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
    println("decoded list=" + decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))
    println("duplicate list=" + duplicate(List('a, 'b, 'c, 'c, 'd)))
    println("duplicate N times=" + duplicateN(3, List('a, 'b, 'c, 'c, 'd)))
    println("drop nth element=" + drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
    println("split=" + split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }
  /**
    * Find the last element of a list.
    */
  def findLast(values: List[Int]):Int = {

    def searchLast(head: Int, list: List[Int]):Int = list match{
      case List() => head
      case x :: Nil => searchLast(x, Nil)
      case x :: xs => searchLast(xs.head, xs.tail)
    }

    searchLast(values.head, values.tail)
  }
  /**
    *
    * Find the last but one element of a list.
    */
  def penultimate[T](values: List[T]): T = {

    def searchPenultimate[T](head: T, remaining: List[T]): T = remaining match {
      case x :: _ :: Nil => x
      case x :: xs => searchPenultimate(xs.head, xs)
    }
    searchPenultimate(values.head, values.tail)
  }

  /**
    *
    * Find the Kth element of a list.
    */
  def nth[T](k: Int, values: List[T]): Option[T] = (k, values) match{
      case (n, List()) => None
      case (0, x :: _) => Some(x)
      case (n, x :: xs) => nth(k - 1, xs)
  }

  /**
    * Find the number of elements of a list.
    */
  def length[T](values: List[T]): Int = {

    def countElements(k: Int, elements: List[T]): Int = elements match{
      case List() => k
      case x :: xs => countElements(k+1, xs)
    }

    countElements(1, values.tail)
  }

  /**
    * Reverse a list.
    */
  def reverse[T](values: List[T]): List[T] = {

    def doReverse[T](values: List[T], temp: List[T]): List[T] = values match {
      case List() => temp
      case x :: xs => doReverse(xs, x :: temp)
    }

    doReverse(values, Nil)
  }

  /**
    *  Find out whether a list is a palindrome.
    */
  def isPalindrome[T](ints: List[T]): Boolean = ints match{
    case x :: Nil => true
    case x :: xs => if(x != xs.last) false else isPalindrome(formList(xs, List()))
  }

  private def formList[T](elements: List[T], current: List[T]): List[T] = elements match {
    case x :: Nil => current
    case x :: xs => formList(xs, x :: current)
  }


  /**
    * Flatten a nested list structure.
    */
  def flatten(list: List[Any]): List[Any] = list match{
      case List() => Nil
      case (x: List[Any]) :: tail => flatten(x) ::: flatten(tail)
      case x :: xs => x :: flatten(xs)
  }

  /**
    * Eliminate consecutive duplicates of list elements.
    */
  def compress(symbols: List[Symbol]): List[Symbol] =  symbols match {
      case List() => Nil
      case x :: xs =>
        if(x != xs.headOption.getOrElse(Nil))
          x  :: compress(xs)
        else compress(xs)
  }

  /**
    * Pack consecutive duplicates of list elements into sublists.
    * scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    * res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
    *
    */
  def pack[A](symbols: List[A]): List[List[A]] = {

    def doPack[A](symbols: List[A], temp: List[A], current: List[List[A]]): List[List[A]] = symbols match {
      case List() => current
      case x :: xs =>
        if(x != xs.headOption.getOrElse(Nil))
          doPack(xs, Nil, (x :: temp) :: current)
        else doPack(xs, x :: temp, current)
    }
    reverse(doPack(symbols, Nil, Nil))
  }

  /**
    * Run-length encoding of a list
    * scala> encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    * res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
    */

  def encode(list: List[Symbol]) : List[(Int, Symbol)] = {
    def doEncode(list: List[Symbol], current: List[(Int, Symbol)], headCount: Int): List[(Int, Symbol)] = list match {
      case List() => current
      case x :: xs =>
        if(x != xs.headOption.getOrElse(Nil))
          doEncode(xs, (headCount, x) :: current, 1) else doEncode(xs, current, headCount+1)
    }

    reverse(doEncode(list, List(), 1))
  }

  /**
    * Modified run-length encoding.
    * scala> encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    * res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
    */
  def encodeModified(symbols: List[Any]): List[Any] = {
    def element(x: Any, headCount: Int): Any = if(headCount > 1) (headCount, x) else x
    def doEncodeModified(symbols: List[Any], current: List[Any], headCount: Int): List[Any] = symbols match {
      case List() => reverse(current)
      case x :: xs =>
        if(x != xs.headOption.getOrElse(Nil))
          doEncodeModified(xs, element(x, headCount) :: current, 1)
        else doEncodeModified(xs, current, headCount+1)
    }
    doEncodeModified(symbols, Nil, 1)
  }

  /**
    * Decode a run-length encoded list.
    * scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
    * res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
    */
  def decode(list: List[(Int, Symbol)]): List[Symbol] = {
    def doDecode(list: List[(Int, Symbol)], result: List[Symbol], count: Int): List[Symbol] = (count, list) match {
      case (0, List()) => reverse(result)
      case (0, x :: xs) => doDecode(xs, x._2 :: result, x._1 - 1)
      case (_, _) => doDecode(list, result.head :: result, count - 1)
    }
    doDecode(list, Nil, 0)
  }

  /**
    *
    * scala> duplicate(List('a, 'b, 'c, 'c, 'd))
    * res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
    */
  def duplicate(symbols: List[Symbol]): List[Symbol] = {
    def doDuplicate(symbols: List[Symbol], result: List[Symbol]): List[Symbol] = symbols match{
      case List() => reverse(result)
      case x :: xs => doDuplicate(xs, x :: x :: result)
    }
    doDuplicate(symbols, Nil)
  }

  /**
    * Duplicate the elements of a list a given number of times.
    * scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
    * res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
    */
  def duplicateN(times: Int, symbols: List[Symbol]): List[Symbol] = {
    def nTimesDuplicate(symbols: List[Symbol], result: List[Symbol]): List[Symbol] = symbols match{
      case List() => result
      case x :: xs => nTimesDuplicate(xs,  result ::: (for(i <- 1 to times) yield x).toList)// or List.fill(times)(x)

    }
    nTimesDuplicate(symbols, Nil)
  }

  /**
    * Drop every Nth element from a list.
    * scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    * res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
    */
  def drop(index : Int, symbols: List[Symbol]): List[Symbol] = {

    def dropAt(symbols: List[Symbol], count: Int, result: List[Symbol]) : List[Symbol] = (count, symbols) match {
      case (_, List()) => reverse(result)
      case (1, x :: xs) => dropAt(xs, index, result)
      case (_, x :: xs) => dropAt(xs, count-1, x :: result)
    }

    dropAt(symbols, index, Nil)
  }

  /**
    * Split a list into two parts.
    * The length of the first part is given. Use a Tuple for your result.
    *
    * scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    * res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    */
  def split(length: Int, symbols: List[Symbol]): (List[Symbol], List[Symbol]) = {

    def splitAt(symbols: List[Symbol],
                count: Int,
                result: List[Symbol]): (List[Symbol], List[Symbol]) = (count, symbols) match{
      case (_, List())  => (reverse(result), Nil)
      case (0, xs) => (reverse(result), xs)
      case (_, x :: xs) => splitAt(xs, count - 1, x :: result)
    }
    splitAt(symbols, length, Nil)
  }
}