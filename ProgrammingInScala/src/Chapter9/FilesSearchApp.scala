package Chapter9

import java.io.File

/**
 * Simple file search that demonstrates how to use function values
 */
object FilesMatcher {
  private def filesHere = (new File("./public/Chapter9/")).listFiles

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName)) yield file

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))

  def filesContain(query: String) =
    filesMatching(_.contains(query))

  def filesRegex(query: String) =
    filesMatching(_.matches(query))
}

object FilesSearchApp extends App{
  println("=== Files ending ===")
  FilesMatcher.filesEnding(".log").foreach(println)
  println("=== Files contains ===")
  FilesMatcher.filesContain("te").foreach(println)
  println("=== Files matches ===")
  FilesMatcher.filesRegex(".*[0-9]+.*").foreach(println)
}


