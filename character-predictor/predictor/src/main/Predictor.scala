package main

/**
  * Created by blueur on 19/11/16.
  */
class Predictor {
  private val probs = new ProbabilitiesDatabase(3)
  private val alphabet = "abcdefghijklmnopqrstuvwxyz -'"
  private val n = 3

  probs.initialize(???)

  def getNextChar(text: String): String = {
    /* Not enough chars to do a prediction */
    if (text.length < n - 1) {
      return ""
    }

    val last = text.takeRight(2).toLowerCase()

    alphabet.sortBy(c -> {
      probs.getNgramProbabilities(last + c)/probs.getNgramProbabilities(last)
    }).reverse
  }
}

object Main {
  def main(argv: Array[String]): Unit = {
    val predictor = new Predictor()

    var read = ' '
    var text = ""

    while (read != '\r' && read != '\n') {
      read = io.StdIn.readChar()
      text += read

      print("\r%s%s".format(text, predictor.getNextChar(text).take(1)))
    }
  }
}