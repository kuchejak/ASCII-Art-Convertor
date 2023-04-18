package asciiArtApp.generators

/**
 * Base trait for generating something (for example random image)
 * @tparam T Type which should be generated
 */
trait Generator[T] {
  def generate() : T
}
