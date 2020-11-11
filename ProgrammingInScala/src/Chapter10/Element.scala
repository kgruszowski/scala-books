package Chapter10

import Element.elem

abstract class Element {
  def contents: Array[String] // abstract method do not need abstract classifier, no "=" it means no body
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    elem(this1.contents ++ that1.contents)
  }

  def beside(that: Element): Element = {
    val this1 = this heighten that.width
    val that1 = that heighten this.width
    elem(
      for (
        (line1, line2) <- this1.contents zip that1.contents // zip two array into array of elements tuples
      ) yield line1 + line2 // yield means "return value from for"
    )
  }

  def widen(w: Int): Element = {
    if (w <= width) this
    else {
      val left = elem(' ', (w-width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }
  }

  def heighten(h: Int): Element = {
    if (h <= height) this
    else {
      val top = elem(' ', width, (h-height) / 2)
      val bottom = elem(' ',width, h - height - top.height)
      top above this above bottom
    }
  }

  override def toString: String = contents mkString "\n"
}

/**
 * Act as Factory
 */
object Element {
  def elem(contents: Array[String]): Element = new ArrayElement(contents)
  def elem(chr: Char, width: Int, height: Int): Element = new UniformElement(chr, width, height)
  def elem(line: String): Element = new LineElement(line)
}