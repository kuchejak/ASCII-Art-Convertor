package asciiArtApp.convertors.toImage.toAscii.specific

import asciiArtApp.convertors.toAscii.PixelToAsciiConvertor
import asciiArtApp.convertors.toImage.toAscii.ToAsciiImageConvertor
import asciiArtApp.filters.image.pixel.specific.GreyScaleFilter
import asciiArtApp.models.image.{AsciiImage, PixelImage}

/**
 * Convertor from Pixel image to Ascii image.
 * Given pixel image is first greyscaled and then each pixel is converted using given pixel convertor.
 * @param pixelConvertor Pixel convertor that should be used to convert pixels to their corresponding ascii chars. Can not be null.
 */
class PixelImageToAsciiImageConvertor(pixelConvertor : PixelToAsciiConvertor) extends ToAsciiImageConvertor[PixelImage] {
  if (pixelConvertor == null) throw new IllegalArgumentException("Pixel convertor can not be null")

  override def convert(source: PixelImage): AsciiImage = {
    if (source == null) throw new IllegalArgumentException("Can not convert null")
    val greyScaleFilter = new GreyScaleFilter
    val greyscaleSource = greyScaleFilter.filter(source)
    AsciiImage(greyscaleSource.pixelGrid.transform(p => pixelConvertor.convert(p)))
  }
}
