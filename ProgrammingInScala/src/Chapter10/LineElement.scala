package Chapter10

class LineElement(s: String) extends Element { // the way how to pass things to superclass constructor
  val contents = Array(s)
  override def height = 1
  override def width = s.length
}
