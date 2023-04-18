package asciiArtApp.generators.image.pixel.specific

import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.FunSuite

import scala.util.Random

class RandomPixelImageGeneratorTest extends FunSuite {

  test("testGenerate") {
    val mockRandom = mock[Random]
    val dim = 3
    when(mockRandom.nextInt(399)).thenReturn(dim - 1)
    when(mockRandom.nextInt(256)).thenReturn(1)

    val generator = new RandomPixelImageGenerator(mockRandom)
    val actual = generator.generate()
    val expectedGrid = Grid(
      List(
        List(new Pixel(1, 1, 1), new Pixel(1, 1, 1), new Pixel(1, 1, 1)),
        List(new Pixel(1, 1, 1), new Pixel(1, 1, 1), new Pixel(1, 1, 1)),
        List(new Pixel(1, 1, 1), new Pixel(1, 1, 1), new Pixel(1, 1, 1))
      )
    )
    val expected = PixelImage(expectedGrid)
    assert(actual == expected)

    verify(mockRandom, times(2)).nextInt(399)
    verify(mockRandom, times(dim * dim * 3)).nextInt(256)
  }

}
