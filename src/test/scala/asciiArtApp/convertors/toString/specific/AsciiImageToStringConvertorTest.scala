package asciiArtApp.convertors.toString.specific

import org.scalatest.FunSuite
import helpers.data.AsciiImages

class AsciiImageToStringConvertorTest extends FunSuite {

  val convertor = new AsciiImageToStringConvertor

  test("testConvert") {
    val img = AsciiImages.asciiImage1
    val actual = convertor.convert(img)
    val expected = "123\n456\n789\n012\n"
    assert(actual == expected)
  }

  test("testConvert2") {
    val img = AsciiImages.asciiImage2
    val actual = convertor.convert(img)
    val expected = "1\n"
    assert(actual == expected)
  }

  test("testConvertNull") {
    try {
      convertor.convert(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }
}
