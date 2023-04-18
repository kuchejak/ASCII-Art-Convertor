package asciiArtApp.filters

/**
 * Filter that does nothing to given T
 * @tparam T Item, on which given filter should by applied
 */
class NoFilter[T] extends Filter[T] {
  override def filter(item: T): T = item
}
