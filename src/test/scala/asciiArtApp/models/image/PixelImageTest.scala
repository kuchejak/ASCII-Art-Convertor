package asciiArtApp.models.image

import asciiArtApp.models.image.dimensions.Dimensions2D
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.mockito.MockitoSugar.{mock, verify, when}
import org.scalatest.FunSuite

class PixelImageTest extends FunSuite {

  test("testPixelGridNull") {
    try {
      PixelImage(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetDimensions") {
    val mockGrid = mock[Grid[Pixel]]
    val dim2D = Dimensions2D(1, 1)
    when(mockGrid.getDimensions).thenReturn(dim2D)
    val pixelImage = PixelImage(mockGrid)
    assert(pixelImage.getDimensions == dim2D)
    verify(mockGrid).getDimensions
  }

  test("testTransform") {
    val mockGrid = mock[Grid[Pixel]]
    val func: Pixel => Pixel = _ => new Pixel(1, 1, 1)
    when(mockGrid.transform(func)).thenReturn(mockGrid)
    val pixelImage = PixelImage(mockGrid)
    pixelImage.transform(func)
    verify(mockGrid).transform(func)
  }
}
