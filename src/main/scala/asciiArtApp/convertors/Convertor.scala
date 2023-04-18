package asciiArtApp.convertors

/**
 * Base trait for converting something to something else
 * @tparam S Something that should be converted
 * @tparam T Desired result of the conversion
 */
trait Convertor[S,T] {
  def convert(source: S) : T
}
