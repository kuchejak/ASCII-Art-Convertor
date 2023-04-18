package asciiArtApp.convertors.toString.specific

import asciiArtApp.convertors.toString.ToStringConvertor
import asciiArtApp.models.image.AsciiImage

/**
 * Convertor that converts Ascii image to text (printable format)
 */
class AsciiImageToStringConvertor extends ToStringConvertor[AsciiImage] {
  override def convert(source: AsciiImage): String = {
    if (source == null) throw new IllegalArgumentException("Can not convert null")
    var result = ""
    source.getElements.foreach(row => result += row.mkString("") + "\n")
    result
  }
}
