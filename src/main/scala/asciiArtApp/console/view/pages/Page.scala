package asciiArtApp.console.view.pages

/**
 * @note Borrowed from lab
 */
trait Page[T] {
  /**
   * Renders the content of the page in a datatype T
   */
  def render() : T
}
