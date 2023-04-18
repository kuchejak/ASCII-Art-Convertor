package helpers.data

import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel

object PixelImages {
  val pixelImage1: PixelImage = PixelImage(
    Grid(
      List(
        List(new Pixel(1, 2, 3), new Pixel(42, 234, 12), new Pixel(23, 231, 22)),
        List(new Pixel(11, 6, 3), new Pixel(42, 23, 12), new Pixel(23, 0, 22)),
        List(new Pixel(1, 11, 3), new Pixel(42, 34, 12), new Pixel(23, 231, 75)),
        List(new Pixel(34, 14, 3), new Pixel(42, 11, 12), new Pixel(23, 161, 22)),
      )
    )
  )

  val pixelImage2: PixelImage = PixelImage(
    Grid(
      List(
        List(new Pixel(1, 1, 1))
      )
    )
  )


}
