package asciiArtApp.console.view.pages.concrete

import org.scalatest.FunSuite

class ErrorPageTest extends FunSuite {

  test("testRender") {
    val page = new ErrorPage
    assert(page.render() == "Invalid command entered\nEnter \"help\" for help\n")
  }

}
