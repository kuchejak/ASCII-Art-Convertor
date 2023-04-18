package importers

/**
 * Trait for importing something
 * @tparam S Source from which T should be loaded
 * @tparam T What should be loaded
 */
trait Importer[S, T] {
  def `import`(source: S) : T
}