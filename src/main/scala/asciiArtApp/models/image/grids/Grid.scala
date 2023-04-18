package asciiArtApp.models.image.grids

import asciiArtApp.models.image.dimensions.Dimensions2D

/**
 * Class representing grid. Grid is a collection of elements with at least one element.
 * @param elements Elements that should be stored in the grid, all rows must have the same size, otherwise exception is thrown.
 * @tparam T Type of elements that should be stored
 */
case class Grid[T](elements: Iterable[Iterable[T]]) {
  if (elements == null) throw new IllegalArgumentException("Elements can not be null")
  if (elements.isEmpty) throw new IllegalArgumentException("Grid can not be empty")

  private val rowSize = elements.head.size
  if (rowSize == 0) throw new IllegalArgumentException("Grid can not be empty")

  elements.foreach(e => {
    if (e == null) throw new IllegalArgumentException("Element can not be null")
    if (e.size != rowSize) throw new IllegalArgumentException("All rows must have same size")
  })

  /**
   * Returns all elements as list of lists
   * @return
   */
  def getElementsAsList : List[List[T]] = elements.map(e => e.toList).toList

  /**
   * Returns grids dimensions
   */
  def getDimensions: Dimensions2D = Dimensions2D(elements.size, rowSize)

  /**
   * Transforms entire grid with given function
   * @param mapper Function that should be used on all elements
   * @tparam G Mapper return type
   * @return Grid with all elements transformed using given mapper function
   */
  def transform[G](mapper: T => G) : Grid[G] = {
    Grid(elements.map(e => e.map(mapper)))
  }
}
