package asciiArtApp.filters.image.pixel.default

import asciiArtApp.filters.NoFilter
import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.models.image.PixelImage

/**
 * Filter that does nothing
 */
class PixelNoFilter extends NoFilter[PixelImage] with PixelImageFilter
