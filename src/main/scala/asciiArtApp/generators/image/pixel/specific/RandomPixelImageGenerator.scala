package asciiArtApp.generators.image.pixel.specific

import asciiArtApp.generators.image.pixel.PixelImageGenerator
import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel

import java.awt.Color
import scala.util.Random

/**
 * Generator that generates random pixel image
 */
class RandomPixelImageGenerator(rnd: Random = new Random()) extends PixelImageGenerator {
  override def generate(): PixelImage = {
    val height = rnd.nextInt(399) + 1 // random number between 1 and 400
    val width = rnd.nextInt(399) + 1

    val gridArg = new Array[List[Pixel]](height)
    for (i <- 0 until height) {
      gridArg.update(i, getRowOfRandomPixel(width).toList)
    }
    PixelImage(Grid(gridArg))
  }

  private def getRowOfRandomPixel(width: Int) : Array[Pixel] = {
    val arr = new Array[Pixel](width)
    for (i <- 0 until width) {
      arr.update(i, createRandomPixel())
    }
    arr
  }

  private def createRandomPixel() : Pixel = {
    val r = rnd.nextInt(256)
    val g = rnd.nextInt(256)
    val b = rnd.nextInt(256)
    new Pixel(r, g, b)
  }
}
