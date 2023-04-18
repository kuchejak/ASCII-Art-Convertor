package importers.file

import importers.Importer

import java.io.File

/**
 * Trait fro importing from file
 * @tparam T What should be loaded
 */
trait FileImporter[T] extends Importer[File, T]