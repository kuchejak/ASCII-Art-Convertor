package importers.file.image

import importers.file.FileImporter

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class BufferedImageImporter() extends FileImporter[BufferedImage] {
  /**
   * Loads buffered image from file. Returns null if file can be loaded, but can not be transformed to image.
   * @throws IllegalArgumentException if source is null
   * @throws java.io.IOException if source can not be loaded as buffered image
   */
  override def `import`(source: File): BufferedImage = ImageIO.read(source)
}
