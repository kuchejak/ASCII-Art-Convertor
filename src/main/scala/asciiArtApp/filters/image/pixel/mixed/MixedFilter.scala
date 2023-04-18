package asciiArtApp.filters.image.pixel.mixed

import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.models.image.PixelImage

/**
 * Filter that combines multiple filters to one
 * @param filters Filters that should be combined
 */
class MixedFilter(val filters: Seq[PixelImageFilter]) extends PixelImageFilter {
  if (filters == null) throw new IllegalArgumentException("Filters can not be null")
  override def filter(item: PixelImage): PixelImage = {
    filters.foldLeft(item)((acc, filter) => filter.filter(acc))
  }
}
