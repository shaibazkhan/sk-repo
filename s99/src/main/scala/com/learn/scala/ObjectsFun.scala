package com.learn.scala

class MyElement(name:String) {
  private val content: String = name
  var description:String = _

  val whoMe = "I am the "+content+" class"
}

class SubElement extends MyElement("sub-element") {
  override val whoMe = "hee hee"
}

object ObjectsFun {

  def main(args:Array[String]) = println(element.whoMe)

  private def element:MyElement = new SubElement

  private def printMe(value: String) = println(value)
}