package Chapter8

import scala.io.Source

object LongLines {
  def processFile(filename: String, width: Int) = {

    def processLine(line: String) = {
      if (line.length > width)
        println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for (line <- source.getLines())
      processLine(line)

    source.close()
  }
}

object LongLinesApp extends App {
  LongLines.processFile("./public/Chapter8/test.txt", 10)
}
