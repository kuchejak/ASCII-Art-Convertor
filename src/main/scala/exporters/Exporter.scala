package exporters

trait Exporter[T] {
  /**
   * Exports something somewhere
   * @note Borrowed from lab
   * @param item The item to export
   */
  def export(item: T): Unit
}
