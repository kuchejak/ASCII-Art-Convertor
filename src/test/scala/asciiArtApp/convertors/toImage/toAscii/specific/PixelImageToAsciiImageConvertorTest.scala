package asciiArtApp.convertors.toImage.toAscii.specific

import asciiArtApp.convertors.toAscii.PixelToAsciiConvertor
import asciiArtApp.convertors.toAscii.tables.StandardTable
import asciiArtApp.models.image.AsciiImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.FunSuite
import helpers.data.PixelImages

class PixelImageToAsciiImageConvertorTest extends FunSuite {

  test("testConvert") {
    val pixelImage = PixelImages.pixelImage1
    val convertor = new PixelImageToAsciiImageConvertor(new PixelToAsciiConvertor("123"))
    val result = convertor.convert(pixelImage)
    val charGrid = Grid(
      List(
        List('1', '2', '2'),
        List('1', '1', '1'),
        List('1', '1', '2'),
        List('1', '1', '2')
      )
    )
    val expected = AsciiImage(charGrid)
    assert(result == expected)
  }

  test("testConvertPixelConvertorUsage") {
    val mockPixelConvertor = mock[PixelToAsciiConvertor]
    when(mockPixelConvertor.convert(any())).thenReturn('0')
    val pixelImage = PixelImages.pixelImage1
    val convertor = new PixelImageToAsciiImageConvertor(mockPixelConvertor)
    val result = convertor.convert(pixelImage)
    result.getElements.flatten.foreach(ch => {
      if (ch != '0') assert(false, "Convertor not used")
    })
    val nbrOfElements = pixelImage.getElements.flatten.size
    verify(mockPixelConvertor, times(nbrOfElements)).convert(any())
  }

  test("testConvertorFromNull") {
    try {
      val convertor = new PixelImageToAsciiImageConvertor(StandardTable)
      convertor.convert(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testPixelConvertorNull") {
    try {
      val convertor = new PixelImageToAsciiImageConvertor(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }


}
