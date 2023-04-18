package asciiArtApp.console.view.pages.concrete

import asciiArtApp.console.view.ConsoleCommands
import asciiArtApp.console.view.pages.TextPage

/**
 * Error page with specific problem
 * @param problem Problem that should be communicated to the user
 */
class ErrorPageWithProblem(problem: String) extends TextPage {
  override def render(): String = {
    var result = ""
    result += problem + "\n"
    result += "Use " + ConsoleCommands.help + " for list of supported commands\n"
    result
  }
}
