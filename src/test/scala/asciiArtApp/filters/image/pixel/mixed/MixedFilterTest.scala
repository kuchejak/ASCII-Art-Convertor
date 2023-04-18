package asciiArtApp.filters.image.pixel.mixed

import asciiArtApp.filters.image.pixel.default.PixelNoFilter
import asciiArtApp.filters.image.pixel.specific.FlipFilter
import asciiArtApp.filters.image.pixel.specific.greyscale.{GreyscaleBrightnessFilter, GreyscaleInvertFilter}
import asciiArtApp.models.image.PixelImage
import org.mockito.MockitoSugar.{mock, verify, when}
import org.scalatest.FunSuite
import helpers.data.PixelImages

class MixedFilterTest extends FunSuite {

  test("testFiltersAreNull") {
    try {
      new MixedFilter(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testFilter") {
    val mockFlipFilter1 = mock[FlipFilter]
    val mockFlipFilter2 = mock[FlipFilter]
    val mockBrightnessFilter = mock[GreyscaleBrightnessFilter]
    val mockInvertFilter = mock[GreyscaleInvertFilter]
    val mockNoFilter = mock[PixelNoFilter]
    val mockMixed = mock[MixedFilter]

    val img = PixelImages.pixelImage1
    val mixedFilter = new MixedFilter(
      List(
        mockFlipFilter1,
        mockFlipFilter2,
        mockBrightnessFilter,
        mockInvertFilter,
        mockNoFilter,
        mockMixed
      )
    )

    when(mockFlipFilter1.filter(img)).thenReturn(img)
    when(mockFlipFilter2.filter(img)).thenReturn(img)
    when(mockBrightnessFilter.filter(img)).thenReturn(img)
    when(mockInvertFilter.filter(img)).thenReturn(img)
    when(mockNoFilter.filter(img)).thenReturn(img)
    when(mockMixed.filter(img)).thenReturn(img)

    mixedFilter.filter(img)

    verify(mockFlipFilter1).filter(img)
    verify(mockFlipFilter2).filter(img)
    verify(mockBrightnessFilter).filter(img)
    verify(mockInvertFilter).filter(img)
    verify(mockNoFilter).filter(img)
    verify(mockMixed).filter(img)
  }

}
