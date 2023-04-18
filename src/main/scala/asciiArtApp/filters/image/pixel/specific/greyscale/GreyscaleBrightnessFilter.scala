package asciiArtApp.filters.image.pixel.specific.greyscale

import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.pixels.Pixel

/**
 * Filter that changes brightness of each pixel in given image
 * @param amount How much should brightness change
 */
class GreyscaleBrightnessFilter(val amount: Int) extends PixelImageFilter {
  override def filter(item: PixelImage): PixelImage = {
    item.transform(p => calculateNewPixel(p))
  }

  private def calculateNewPixel(pixel: Pixel): Pixel = {
    if (!pixel.isGreyscale) throw new IllegalArgumentException("Input image is not greyscale")
    val colorValue = pixel.color.getRed // same for all, because it is greyscale
    val newColorValue = verifyColorValue(colorValue + amount)
    new Pixel(newColorValue, newColorValue, newColorValue)
  }

  private def verifyColorValue(value: Int) : Int = {
    if (value < 0) return 0
    if (value > 255) return 255
    value
  }
}
