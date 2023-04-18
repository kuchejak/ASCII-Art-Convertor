package asciiArtApp.convertors.toAscii

import asciiArtApp.convertors.Convertor
import asciiArtApp.models.image.pixels.Pixel

/**
 * Convertor from Pixel to Ascii char. The conversion only works on greyscale pixels (r == g == b)
 * @param sequence Sequence that should be used to convert given pixel to char, at least 3 chars long (bare minimum for
 *                 proper conversion). Sequence is expected to be ordered from "darkest" to "lightest" chars
 */
class PixelToAsciiConvertor(sequence: String) extends Convertor[Pixel, Char] {
  if (sequence == null) throw new IllegalArgumentException("Sequence can not be null")
  private val seqLength = sequence.length
  if (seqLength < 3) throw new IllegalArgumentException("Given sequence is too short")
  private val bucketSize = Math.ceil(256.0 / seqLength).toInt; // how many values correspond to one ascii char (256 all possible values for rgb)

  override def convert(pixel: Pixel): Char = {
    val color = pixel.color
    if (color.getRed != color.getGreen || color.getRed != color.getBlue)
      throw new IllegalArgumentException("Not greyscale")

    val index = color.getRed / bucketSize // corresponds to index in sequence
    sequence(index)
  }
}
