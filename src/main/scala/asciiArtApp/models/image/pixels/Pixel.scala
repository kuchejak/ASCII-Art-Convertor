package asciiArtApp.models.image.pixels

import java.awt.Color

case class Pixel(color: Color) {
  if (color == null) throw new IllegalArgumentException("Can not construct pixel from null")
  def this(r: Int, g: Int, b: Int) = this(new Color(r, g, b))
  def isGreyscale: Boolean = {
    color.getRed == color.getBlue && color.getBlue == color.getGreen
  }
}
