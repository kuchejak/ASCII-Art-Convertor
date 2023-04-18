package asciiArtApp.console.view.pages.concrete

import org.scalatest.FunSuite

class StartPageTest extends FunSuite {

  test("testRender") {
    val page = new StartPage
    assert(page.render() == "Welcome!\nEnter \"help\" for help\n")
  }

}
