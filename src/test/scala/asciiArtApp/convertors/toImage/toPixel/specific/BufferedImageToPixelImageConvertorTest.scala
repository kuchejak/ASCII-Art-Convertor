package asciiArtApp.convertors.toImage.toPixel.specific

import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.FunSuite

import java.awt.Color
import java.awt.image.BufferedImage

class BufferedImageToPixelImageConvertorTest extends FunSuite {

  test("testConvert") {
    val bufferedImageMock = mock[BufferedImage]
    val width = 5
    val height = 3

    // I don't know how to work with Buffered image, so I'm just mocking the methods I need
    when(bufferedImageMock.getWidth()).thenReturn(width)
    when(bufferedImageMock.getHeight()).thenReturn(height)

    val grid = new Array[Array[Pixel]](height)
    for (h <- 0 until height)
      grid.update(h, new Array[Pixel](width))

    for (i <- 0 until height) {
      for (j <- 0 until width) {
        val intToUse = (j * i) + j + i // some "random" value
        when(bufferedImageMock.getRGB(j, i)).thenReturn(intToUse)
        grid(i).update(j, Pixel(new Color(intToUse)))
      }
    }

    val convertor = new BufferedImageToPixelImageConvertor()
    val actual = convertor.convert(bufferedImageMock)
    val expected = PixelImage(Grid(grid.toList.map(row => row.toList)))

    assert(actual == expected)
  }

  test("testNullConversion") {
    try {
      val convertor = new BufferedImageToPixelImageConvertor()
      convertor.convert(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }




}
