package asciiArtApp.console.view.pages.concrete

import org.scalatest.FunSuite

class HelpPageTest extends FunSuite {

  test("testRender") {
    val page = new HelpPage
    assert(page.render() == "-----COMMANDS-----\n" +
      "help\n" +
      "exit\n" +
      "run <image> [filter1] [filter2] ... <output>\n\n" +
      "-----IMAGE (use exactly one of the following)-----\n" +
      "--image \"relative or absolute path to image\"\n" +
      "--image-random\n\n" +
      "-----FILTERS-----\n" +
      "--invert\n" +
      "--flip x\n" +
      "--flip y\n" +
      "--brightness <amount>\n\n" +
      "-----OUTPUT (use at least one of the following)-----\n" +
      "--output-console\n" +
      "--output-file \"relative or absolute path to output file\"\n")
  }

}
