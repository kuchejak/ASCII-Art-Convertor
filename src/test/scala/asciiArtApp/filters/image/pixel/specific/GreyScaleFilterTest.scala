package asciiArtApp.filters.image.pixel.specific

import asciiArtApp.models.image.pixels.Pixel
import org.scalatest.FunSuite
import helpers.data.PixelImages

import java.awt.Color

class GreyScaleFilterTest extends FunSuite {

  val filter = new GreyScaleFilter

  test("testFilter1") {
    val img = PixelImages.pixelImage1
    val actual = filter.filter(img)
    val expected = img.transform(p => calculateGreyPixel(p))
    assert(actual == expected)
  }

  test("testFilter2") {
    val img = PixelImages.pixelImage2
    val actual = filter.filter(img)
    val expected = img.transform(p => calculateGreyPixel(p))
    assert(actual == expected)
  }

  private def calculateGreyPixel(pixel: Pixel) : Pixel = {
    val color = pixel.color
    val c = ((0.3 * color.getRed) + (0.59 * color.getGreen) + (0.11 * color.getBlue)).toInt
    new Pixel(c, c, c)
  }
}
