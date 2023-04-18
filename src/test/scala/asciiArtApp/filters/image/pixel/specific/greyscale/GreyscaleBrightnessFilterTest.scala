package asciiArtApp.filters.image.pixel.specific.greyscale

import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.scalatest.FunSuite

import java.awt.Color

class GreyscaleBrightnessFilterTest extends FunSuite {

  test("testNotGreyscale") {
    val filter = new GreyscaleBrightnessFilter(5)
    val grid = new Grid[Pixel](List(List(Pixel(new Color(1, 1, 1)), Pixel(new Color(1, 2, 3)))))
    val image = PixelImage(grid)

    try {
      filter.filter(image)
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testFilter") {

    val v1 = 0
    val v2 = 100
    val v3 = 200
    val v4 = 255

    val color1 = new Color(v1, v1, v1)
    val color2 = new Color(v2, v2, v2)
    val color3 = new Color(v3, v3, v3)
    val color4 = new Color(v4, v4, v4)

    val grid = new Grid[Pixel](List(
      List(Pixel(color1), Pixel(color2)),
      List(Pixel(color3), Pixel(color4))
    ))

    val originalImage = PixelImage(grid)

    for (i <- -255 until 500) {
      val filter = new GreyscaleBrightnessFilter(i)
      verify(originalImage, filter)
    }
  }

  private def verify(originalImage: PixelImage, filter: GreyscaleBrightnessFilter): Unit = {
    val amount = filter.amount
    val changedImage = filter.filter(originalImage)

    assert(originalImage.getDimensions == changedImage.getDimensions, "Different dimensions")

    val origElems = originalImage.getElements
    val changedElems = changedImage.getElements
    val dim = originalImage.getDimensions

    for (i <- 0 until dim.height) {
      for (j <- 0 until dim.width) {
        assert(changedElems(i)(j) == calculatePixel(origElems(i)(j), amount))
      }
    }
  }

  private def calculatePixel(pixel: Pixel, amount: Int): Pixel = {
    var value = pixel.color.getRed + amount
    if (value > 255) value = 255
    if (value < 0) value = 0
    Pixel(new Color(value, value, value))
  }
}
