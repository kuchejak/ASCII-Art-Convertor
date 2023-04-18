package asciiArtApp.console.controllers

import asciiArtApp.console.view.pages.TextPage
import asciiArtApp.console.view.pages.concrete.{
  ErrorPage,
  ErrorPageWithProblem,
  HelpPage,
  StartPage
}
import asciiArtApp.facades.ImageFacade
import asciiArtApp.filters.image.pixel.mixed.MixedFilter
import exporters.text.TextExporter
import exporters.text.multi.MultiTextExporter

import java.io.File

class ConsoleController(textExporter: TextExporter, facade: ImageFacade)
    extends Controller {

  private def render(renderer: TextPage): Unit =
    textExporter.export(renderer.render())

  override def showHelp(): Unit =
    render(new HelpPage)

  override def showStartMessage(): Unit =
    render(new StartPage)

  override def showError(): Unit =
    render(new ErrorPage)

  override def showError(problem: String): Unit =
    render(new ErrorPageWithProblem(problem))

  override def processImageFromFile(file: File, filter: MixedFilter, exporter: MultiTextExporter): Unit =
    facade.processImageFromFile(file, filter, exporter)

  override def processRandomFromFile( filter: MixedFilter, exporter: MultiTextExporter): Unit =
    facade.processRandomImage(filter, exporter)
}
