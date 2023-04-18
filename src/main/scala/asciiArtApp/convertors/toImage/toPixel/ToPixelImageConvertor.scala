package asciiArtApp.convertors.toImage.toPixel

import asciiArtApp.convertors.Convertor
import asciiArtApp.models.image.PixelImage

/**
 * Convertor that converts type S to PixelImage
 * @tparam S Something that should be converted
 */
trait ToPixelImageConvertor[S] extends Convertor[S, PixelImage]
