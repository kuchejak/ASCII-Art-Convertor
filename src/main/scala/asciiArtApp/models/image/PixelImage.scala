package asciiArtApp.models.image
import asciiArtApp.models.image.dimensions.Dimensions2D
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel

/**
 * Image created from Pixels
 */
case class PixelImage(pixelGrid: Grid[Pixel]) extends Image[Pixel] {
  if (pixelGrid == null) throw new IllegalArgumentException("Can not create Pixel Image from null")

  override def getDimensions: Dimensions2D = pixelGrid.getDimensions

  override def transform(mapper: Pixel => Pixel): PixelImage = {
    PixelImage(pixelGrid.transform(mapper))
  }

  override def getElements: List[List[Pixel]] = pixelGrid.getElementsAsList
}
