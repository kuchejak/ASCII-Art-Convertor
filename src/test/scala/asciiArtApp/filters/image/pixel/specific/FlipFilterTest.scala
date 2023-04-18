package asciiArtApp.filters.image.pixel.specific

import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.scalatest.FunSuite
import helpers.data.PixelImages

class FlipFilterTest extends FunSuite {


  test("test2FlipIdentity") {
    val filterX = new FlipFilter(Axis.X)
    val filterY = new FlipFilter(Axis.Y)

    assert(PixelImages.pixelImage1 == filterX.filter(filterX.filter(PixelImages.pixelImage1)))
    assert(PixelImages.pixelImage2 == filterX.filter(filterX.filter(PixelImages.pixelImage2)))

    assert(PixelImages.pixelImage1 == filterY.filter(filterY.filter(PixelImages.pixelImage1)))
    assert(PixelImages.pixelImage2 == filterY.filter(filterY.filter(PixelImages.pixelImage2)))

  }

  test("testFlipYOdd") {
    val filterY = new FlipFilter(Axis.Y)

    val origOddClm: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(1, 2, 3), new Pixel(42, 234, 12), new Pixel(23, 231, 22)),
          List(new Pixel(11, 6, 3), new Pixel(42, 23, 12), new Pixel(23, 0, 22)),
          List(new Pixel(1, 11, 3), new Pixel(42, 34, 12), new Pixel(23, 231, 75)),
          List(new Pixel(34, 14, 3), new Pixel(42, 11, 12), new Pixel(23, 161, 22)),
        )
      )
    )

    val expectedOddClm: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(23, 231, 22), new Pixel(42, 234, 12), new Pixel(1, 2, 3)),
          List(new Pixel(23, 0, 22), new Pixel(42, 23, 12), new Pixel(11, 6, 3)),
          List(new Pixel(23, 231, 75), new Pixel(42, 34, 12), new Pixel(1, 11, 3)),
          List(new Pixel(23, 161, 22), new Pixel(42, 11, 12), new Pixel(34, 14, 3)),
        )
      )
    )
    assert(filterY.filter(origOddClm) == expectedOddClm)
  }

  test("testFlipYEven") {
    val filterY = new FlipFilter(Axis.Y)
    // even columns
    val origEvenClm: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(1, 2, 3), new Pixel(23, 231, 22)),
          List(new Pixel(11, 6, 3), new Pixel(23, 0, 22)),
          List(new Pixel(1, 11, 3), new Pixel(23, 231, 75)),
          List(new Pixel(34, 14, 3), new Pixel(23, 161, 22)),
        )
      )
    )

    val expectedEvenClm: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(23, 231, 22), new Pixel(1, 2, 3)),
          List(new Pixel(23, 0, 22), new Pixel(11, 6, 3)),
          List(new Pixel(23, 231, 75), new Pixel(1, 11, 3)),
          List(new Pixel(23, 161, 22), new Pixel(34, 14, 3)),
        )
      )
    )

    assert(filterY.filter(origEvenClm) == expectedEvenClm)
  }

  test("flipXOdd") {
    val filterX = new FlipFilter(Axis.X)

    // odd rows
    val origOddRows: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(1, 2, 3), new Pixel(42, 234, 12), new Pixel(23, 231, 22)),
          List(new Pixel(1, 11, 3), new Pixel(42, 34, 12), new Pixel(23, 231, 75)),
          List(new Pixel(34, 14, 3), new Pixel(42, 11, 12), new Pixel(23, 161, 22)),
        )
      )
    )

    // odd rows
    val expectedOddRows: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(34, 14, 3), new Pixel(42, 11, 12), new Pixel(23, 161, 22)),
          List(new Pixel(1, 11, 3), new Pixel(42, 34, 12), new Pixel(23, 231, 75)),
          List(new Pixel(1, 2, 3), new Pixel(42, 234, 12), new Pixel(23, 231, 22)),
        )
      )
    )
    assert(filterX.filter(origOddRows) == expectedOddRows)
  }

  test("flipXEven") {
    val filterX = new FlipFilter(Axis.X)
    // even rows
    val origEvenRows: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(1, 2, 3), new Pixel(42, 234, 12), new Pixel(23, 231, 22)),
          List(new Pixel(11, 6, 3), new Pixel(42, 23, 12), new Pixel(23, 0, 22)),
          List(new Pixel(1, 11, 3), new Pixel(42, 34, 12), new Pixel(23, 231, 75)),
          List(new Pixel(34, 14, 3), new Pixel(42, 11, 12), new Pixel(23, 161, 22)),
        )
      )
    )

    val expectedEvenRows: PixelImage = PixelImage(
      Grid(
        List(
          List(new Pixel(34, 14, 3), new Pixel(42, 11, 12), new Pixel(23, 161, 22)),
          List(new Pixel(1, 11, 3), new Pixel(42, 34, 12), new Pixel(23, 231, 75)),
          List(new Pixel(11, 6, 3), new Pixel(42, 23, 12), new Pixel(23, 0, 22)),
          List(new Pixel(1, 2, 3), new Pixel(42, 234, 12), new Pixel(23, 231, 22)),
        )
      )
    )

    assert(filterX.filter(origEvenRows) == expectedEvenRows)
  }

  test("testNullAxis") {
    try {
      new FlipFilter(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }
}
