package asciiArtApp.models.image
import asciiArtApp.models.image.dimensions.Dimensions2D
import asciiArtApp.models.image.grids.Grid

/**
 * Image created from Chars
 */
case class AsciiImage(asciiGrid: Grid[Char]) extends Image[Char] {
  if (asciiGrid == null) throw new IllegalArgumentException("Can not construct Ascii image from null")

  override def getDimensions: Dimensions2D = asciiGrid.getDimensions

  override def transform(mapper: Char => Char): AsciiImage = {
    AsciiImage(asciiGrid.transform(mapper))
  }

  override def getElements: List[List[Char]] = asciiGrid.getElementsAsList
}
