package asciiArtApp.models.image.pixels

import org.scalatest.FunSuite

class PixelTest extends FunSuite {

  test("testIsGreyscale") {
    var pixel = new Pixel(1, 1, 1)
    assert(pixel.isGreyscale)

    pixel = new Pixel(1, 1, 2)
    assert(!pixel.isGreyscale)

    pixel = new Pixel(1, 2, 1)
    assert(!pixel.isGreyscale)

    pixel = new Pixel(2, 1, 1)
    assert(!pixel.isGreyscale)
  }

  test("testNullColor") {
    try {
      Pixel(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }
}
