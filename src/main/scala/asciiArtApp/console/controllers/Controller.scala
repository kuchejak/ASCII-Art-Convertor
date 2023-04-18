package asciiArtApp.console.controllers

import asciiArtApp.filters.image.pixel.mixed.MixedFilter
import exporters.text.multi.MultiTextExporter

import java.io.File

trait Controller {
  /**
   * Shows error page with specified problem
   */
  def showError(problem: String): Unit

  /**
   * Shows error page
   */
  def showError(): Unit

  /**
   * Shows help to user
   */
  def showHelp(): Unit

  /**
   * Shows start message to user
   */
  def showStartMessage(): Unit

  /**
   * Loads image from given file, applies given filter and outputs result using given exporter
   * @param file Filter from which image should be loaded
   * @param filter Filter which should be applied
   * @param exporter Exporter which should be used to output result
   * @throws IllegalArgumentException if image can not be loaded from given file
   */
  def processImageFromFile(file: File, filter: MixedFilter, exporter: MultiTextExporter): Unit

  /**
   * Generates random image, applies given filter and outputs result using given exporter
   * @param filter Filter which should be applied
   * @param exporter Exporter which should be used to output result
   */
  def processRandomFromFile(filter: MixedFilter, exporter: MultiTextExporter): Unit
}
