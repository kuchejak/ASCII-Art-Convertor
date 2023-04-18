package asciiArtApp.console.view.pages.concrete

import org.scalatest.FunSuite

class ErrorPageWithProblemTest extends FunSuite {

  test("testRender") {
    val problem = "Test123"
    val page = new ErrorPageWithProblem(problem)
    assert(page.render() == problem + "\nUse help for list of supported commands\n")
  }

}
