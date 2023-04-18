package asciiArtApp.filters.image.pixel.specific

import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.filters.image.pixel.specific.Axis.Axis
import asciiArtApp.models.image.PixelImage
import asciiArtApp.models.image.grids.Grid

/**
 * Filter that flips given image on given axis
 * @param axis Axis that should be used to flip the image
 */
class FlipFilter(val axis: Axis) extends PixelImageFilter {
  if (axis == null) throw new IllegalArgumentException("Axis can not be null")

  override def filter(item: PixelImage): PixelImage = {
    val elements = item.getElements
    if (axis == Axis.Y) {
      val grid = Grid(elements.map(row => row.reverse))
      PixelImage(grid)
    }
    else if (axis == Axis.X) {
      val grid = Grid(elements.reverse)
      PixelImage(grid)
    }
    else {
      throw new IllegalArgumentException
    }
  }
}

