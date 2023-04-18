package asciiArtApp.convertors.toString

import asciiArtApp.convertors.Convertor

/**
 * Convertor that convert type S to string
 * @tparam S Something that should be converted
 */
trait ToStringConvertor[S] extends Convertor[S, String]
