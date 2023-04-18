package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.view.ConsoleCommands
import asciiArtApp.console.view.pages.TextPage

/**
 * Generic error page signaling input of invalid command
 */
class ErrorPage extends TextPage {
  override def render(): String = {
    var result = ""

    result += "Invalid command entered\n"
    result += "Enter \""+ ConsoleCommands.help +"\" for help\n"
    result
  }

}
