package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.view.ConsoleCommands
import asciiArtApp.console.view.pages.TextPage

/**
 * First page to be displayed, contain greeting and help information
 */
class StartPage extends TextPage {
  override def render(): String = {
    var result = ""

    result += "Welcome!\n"
    result += "Enter \""+ ConsoleCommands.help +"\" for help\n"
    result
  }

}
