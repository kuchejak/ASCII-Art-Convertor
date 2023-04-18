package asciiArtApp.filters

/**
 * Base trait for filtering something (for example making image grayscale)
 * @tparam T Item, on which given filter should by applied
 */
trait Filter[T] {
  def filter(item: T) : T
}
