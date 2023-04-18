package asciiArtApp.filters.image.pixel.specific.greyscale

import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.scalatest.FunSuite

import java.awt.Color

class GreyscaleInvertFilterTest extends FunSuite {

  val filter = new GreyscaleInvertFilter

  test("testFilterBasic") {
    val origImage = PixelImage(
      Grid(
        List(
          List(new Pixel(255, 255, 255), new Pixel(0, 0, 0)),
          List(new Pixel(50, 50, 50), new Pixel(150, 150, 150)),
        )
      )
    )

    val expectedImage = PixelImage(
      Grid(
        List(
          List(new Pixel(0, 0, 0), new Pixel(255, 255, 255)),
          List(new Pixel(255 - 50, 255 - 50, 255 - 50), new Pixel(255 - 150, 255 - 150, 255 -150)),
        )
      )
    )

  assert(filter.filter(origImage) == expectedImage)
  }

  test("testFilterAll") {
    for (c <- 0 to 255) {
      val origImage = PixelImage(
        Grid(
          List(
            List(new Pixel(255 - c, 255 - c, 255 - c), new Pixel(c, c, c)),
          )
        )
      )

      val expectedImage = PixelImage(
        Grid(
          List(
            List(new Pixel(c, c, c), new Pixel(255 - c, 255 - c, 255 - c)),
          )
        )
      )

      assert(filter.filter(origImage) == expectedImage)
    }
  }

  test("testNotGreyscale") {
    val origImage = PixelImage(
      Grid(
        List(
          List(new Pixel(255, 255, 255), new Pixel(0, 0, 0)),
          List(new Pixel(50, 50, 50), new Pixel(150, 200, 150)),
        )
      )
    )
    try {
      filter.filter(origImage)
      assert(false)
    }
    catch {
      case e: IllegalArgumentException =>
    }

  }
}
