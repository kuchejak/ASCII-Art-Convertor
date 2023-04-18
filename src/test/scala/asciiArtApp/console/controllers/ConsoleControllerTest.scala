package asciiArtApp.console.controllers

import asciiArtApp.console.view.pages.concrete.{ErrorPage, ErrorPageWithProblem, HelpPage, StartPage}
import asciiArtApp.facades.ImageFacade
import asciiArtApp.filters.image.pixel.default.PixelNoFilter
import asciiArtApp.filters.image.pixel.mixed.MixedFilter
import exporters.text.multi.MultiTextExporter
import exporters.text.specific.{StdOutputExporter, StreamTextExporter}
import org.mockito.MockitoSugar.{mock, verify}
import org.scalatest.FunSuite

import java.io.{ByteArrayOutputStream, File}

class ConsoleControllerTest extends FunSuite {

  test("testProcessRandomFromFile") {
    val mockFacade = mock[ImageFacade]
    val controller = new ConsoleController(null, mockFacade) // exporting is not expected in this method call
    val noFilter = new PixelNoFilter
    val mixedFilter = new MixedFilter(List(noFilter))
    val multiExporter = new MultiTextExporter(List(new StdOutputExporter))
    controller.processRandomFromFile(mixedFilter, multiExporter)
    verify(mockFacade).processRandomImage(mixedFilter, multiExporter)
  }

  test("testShowError") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(exporter, null) // facade should not be used in this method call
    controller.showError()
    val text = stream.toString("UTF-8")
    assert(text == new ErrorPage().render())
  }

  test("testShowErrorWithProblem") {
    val problem = "Test 123"
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(exporter, null) // facade should not be used in this method call
    controller.showError(problem)
    val text = stream.toString("UTF-8")
    assert(text == new ErrorPageWithProblem(problem).render())
  }

  test("testShowHelp") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(exporter, null) // facade should not be used in this method call
    controller.showHelp()
    val text = stream.toString("UTF-8")
    assert(text == new HelpPage().render())
  }

  test("testProcessImageFromFile") {
    val mockFacade = mock[ImageFacade]
    val controller = new ConsoleController(null, mockFacade) // exporting is not expected in this method call

    val noFilter = new PixelNoFilter
    val mixedFilter = new MixedFilter(List(noFilter))
    val multiExporter = new MultiTextExporter(List(new StdOutputExporter))
    val file = new File("Test")
    controller.processImageFromFile(file, mixedFilter, multiExporter)
    verify(mockFacade).processImageFromFile(file, mixedFilter, multiExporter)
  }

  test("testShowStartMessage") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)
    val controller = new ConsoleController(exporter, null) // facade should not be used in this method call
    controller.showStartMessage()
    val text = stream.toString("UTF-8")
    assert(text == new StartPage().render())
  }
}
