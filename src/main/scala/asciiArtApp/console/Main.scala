package asciiArtApp.console

import asciiArtApp.console.controllers.ConsoleController
import asciiArtApp.console.view.ConsoleView
import asciiArtApp.facades.ImageFacade
import exporters.text.specific.StdOutputExporter

object Main extends App {
  // create controller
  val stdOutput = new StdOutputExporter
  val imageFacade = new ImageFacade
  val controller = new ConsoleController(stdOutput, imageFacade)

  // create view
  val view = new ConsoleView(controller)

  // run the app
  view.run()
}
