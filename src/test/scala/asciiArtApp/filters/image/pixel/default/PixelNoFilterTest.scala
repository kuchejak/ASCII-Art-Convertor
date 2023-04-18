package asciiArtApp.filters.image.pixel.default

import org.scalatest.FunSuite
import helpers.data.PixelImages

class PixelNoFilterTest extends FunSuite {

  val filter = new PixelNoFilter

  test("testNoFilter") {
    assert(PixelImages.pixelImage1 == filter.filter(PixelImages.pixelImage1))
    assert(PixelImages.pixelImage2 == filter.filter(PixelImages.pixelImage2))
  }
}
