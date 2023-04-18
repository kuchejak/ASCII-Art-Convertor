package asciiArtApp.convertors.toImage.toAscii

import asciiArtApp.convertors.Convertor
import asciiArtApp.models.image.AsciiImage

/**
 * Convertor which converts type S to AsciiImage
 * @tparam S Something that should be converted
 */
trait ToAsciiImageConvertor[S] extends Convertor[S, AsciiImage]
