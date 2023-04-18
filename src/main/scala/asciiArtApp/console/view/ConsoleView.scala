package asciiArtApp.console.view

import asciiArtApp.console.controllers.Controller
import asciiArtApp.console.view.utils.CommandParser
import asciiArtApp.filters.image.pixel.PixelImageFilter

import scala.io.StdIn

class ConsoleView(controller: Controller) {
  private var running = false

  /**
   * Main app loop
   */
  def run(): Unit = {
    controller.showStartMessage()
    running = true
    while(running) {
      val input = StdIn.readLine()
      processInput(input)
    }
  }

  /**
   * Reads input from user and processes it
   */
  private def processInput(command: String): Unit = {
    if (CommandParser.isExit(command)) {
      exit()
    }

    else if (CommandParser.isHelp(command)) {
      controller.showHelp()
    }

    else if (CommandParser.isRun(command)) {
      processRun(command)
    }
  }

  /**
   * Processes run command and its arguments
   */
  private def processRun(command: String): Unit = {
    try {
      val textArgsArray = CommandParser.getRunArgs(command)
      val inputArg = CommandParser.getInputArg(textArgsArray)
      val mixedFilter = CommandParser.getMixedFilter(textArgsArray)
      val multiExporter = CommandParser.getMultiTextExporter(textArgsArray)

      if (CommandParser.isFromFile(inputArg)) {
        val file = CommandParser.getInputFile(inputArg)
        controller.processImageFromFile(file, mixedFilter, multiExporter)
        return
      }
      else if (CommandParser.isRandom(inputArg)) {
        controller.processRandomFromFile(mixedFilter, multiExporter)
        return
      }
      throw new IllegalArgumentException("Invalid input argument")
    }
    catch {
      case e: IllegalArgumentException => controller.showError(e.getMessage)
    }
  }

  /**
   * Ends program
   */
  private def exit(): Unit = {
    running = false
  }
}
