package asciiArtApp.filters.image.pixel.specific

import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.pixels.Pixel

import java.awt.Color

/**
 * Filter that "greyscales" given image
 */
class GreyScaleFilter extends PixelImageFilter {
  override def filter(item: PixelImage): PixelImage = {
    item.transform(p => {
      val greyVersion = calculateGrey(p.color)
      Pixel(new Color(greyVersion, greyVersion, greyVersion))
    })
  }

  private def calculateGrey(color: Color) : Int = {
    ((0.3 * color.getRed) + (0.59 * color.getGreen) + (0.11 * color.getBlue)).toInt
  }
}
