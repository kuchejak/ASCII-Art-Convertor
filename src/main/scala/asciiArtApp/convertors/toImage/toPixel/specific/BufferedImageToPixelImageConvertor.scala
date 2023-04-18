package asciiArtApp.convertors.toImage.toPixel.specific

import asciiArtApp.convertors.toImage.toPixel.ToPixelImageConvertor
import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel

import java.awt.Color
import java.awt.image.BufferedImage

/**
 * Convertor for converting BufferedImages (loaded with library) to PixelImages (own model)
 */
class BufferedImageToPixelImageConvertor extends ToPixelImageConvertor[BufferedImage] {
  override def convert(source: BufferedImage): PixelImage = {
    if (source == null) throw new IllegalArgumentException("Can not convert null")
    val width = source.getWidth
    val height = source.getHeight
    val resultArray = new Array[List[Pixel]](height)
    for (i <- 0 until height) {
      val row = new Array[Pixel](width)
      for (j <- 0 until width) {
        row(j) = Pixel(new Color(source.getRGB(j, i)))
      }
      resultArray.update(i, row.toList)
    }
    val pixelGrid = Grid(resultArray.toList)
    PixelImage(pixelGrid)
  }
}
