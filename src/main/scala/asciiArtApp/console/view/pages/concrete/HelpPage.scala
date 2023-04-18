package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.view.ConsoleCommands
import asciiArtApp.console.view.pages.TextPage

/**
 * Page with all available commands
 */
class HelpPage extends TextPage {
  override def render(): String = {
    var result = ""

    result += "-----COMMANDS-----\n"
    result += ConsoleCommands.help + "\n"
    result += ConsoleCommands.exit + "\n"
    result += ConsoleCommands.run + " <image> [filter1] [filter2] ... <output>\n"
    result += "\n"
    result += "-----IMAGE (use exactly one of the following)-----\n"
    result += "--" + ConsoleCommands.imageFromFile + " \"relative or absolute path to image\"\n"
    result += "--" + ConsoleCommands.randomImage + "\n"
    result += "\n"
    result += "-----FILTERS-----\n"
    result += "--" + ConsoleCommands.invertFilter + "\n"
    result += "--" + ConsoleCommands.flipXFilter + "\n"
    result += "--" + ConsoleCommands.flipYFilter + "\n"
    result += "--" + ConsoleCommands.brightnessFilter + " <amount>\n"
    result += "\n"
    result += "-----OUTPUT (use at least one of the following)-----\n"
    result += "--" + ConsoleCommands.outputConsole + "\n"
    result += "--" + ConsoleCommands.outputFile + " \"relative or absolute path to output file\"\n"
    result
  }
}
