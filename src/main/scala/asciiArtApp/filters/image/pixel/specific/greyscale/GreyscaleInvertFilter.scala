package asciiArtApp.filters.image.pixel.specific.greyscale

import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.pixels.Pixel

import java.awt.Color

/**
 * Filter that inverts colors of given image
 */
class GreyscaleInvertFilter extends PixelImageFilter {
  override def filter(item: PixelImage): PixelImage = {
    item.transform(p => invertPixel(p))
  }

  private def invertPixel(pixel: Pixel): Pixel = {
    if (!pixel.isGreyscale) throw new IllegalArgumentException("Input image is not greyscale")
    val invertedValue = Color.WHITE.getRed - pixel.color.getRed // all greyscale colors have R == G == B
    new Pixel(invertedValue, invertedValue, invertedValue)
  }
}
