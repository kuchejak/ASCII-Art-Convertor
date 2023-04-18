package helpers.data

import asciiArtApp.models.image.{AsciiImage, PixelImage}
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel

object AsciiImages {

  val asciiImage1: AsciiImage = AsciiImage(
    Grid(
      List(
        List('1', '2', '3'),
        List('4', '5', '6'),
        List('7', '8', '9'),
        List('0', '1', '2'),
      )
    )
  )

  val asciiImage2: AsciiImage = AsciiImage(
    Grid(
      List(
        List('1')
      )
    )
  )
}
