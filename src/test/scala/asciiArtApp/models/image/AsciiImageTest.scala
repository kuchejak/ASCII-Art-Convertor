package asciiArtApp.models.image

import asciiArtApp.models.image.dimensions.Dimensions2D
import asciiArtApp.models.image.grids.Grid
import org.mockito.MockitoSugar.{mock, verify, when}
import org.scalatest.FunSuite

class AsciiImageTest extends FunSuite {

  test("testGridNull") {
    try {
      AsciiImage(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetDimensions") {
    val mockGrid = mock[Grid[Char]]
    val dim2D = Dimensions2D(1, 1)
    when(mockGrid.getDimensions).thenReturn(dim2D)
    val asciiImage = AsciiImage(mockGrid)
    assert(asciiImage.getDimensions == dim2D)
    verify(mockGrid).getDimensions
  }

  test("testTransform") {
    val mockGrid = mock[Grid[Char]]
    val func: Char => Char = _ => '1'
    when(mockGrid.transform(func)).thenReturn(mockGrid)
    val asciiImage = AsciiImage(mockGrid)
    assert(asciiImage.transform(func) == asciiImage)
    verify(mockGrid).transform(func)
  }

}
