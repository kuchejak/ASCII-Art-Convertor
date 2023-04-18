package asciiArtApp.models.image

import asciiArtApp.models.image.dimensions.Dimensions2D

/**
 * Trait representing image created from type T
 * @tparam T Type from which given image is constructed, for example Pixel
 */
trait Image[T] {
  /**
   * Returns dimensions corresponding to given image.
   */
  def getDimensions: Dimensions2D

  /*
   * Transforms image using given mapper (applies it to each element)
   */
  def transform(mapper: T => T): Image[T]

  /**
   * Returns 2D list of elements corresponding to given Image
   */
  def getElements: List[List[T]]
}
