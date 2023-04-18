package asciiArtApp.facades

import asciiArtApp.convertors.toAscii.tables.StandardTable
import asciiArtApp.convertors.toImage.toAscii.specific.PixelImageToAsciiImageConvertor
import asciiArtApp.convertors.toImage.toPixel.specific.BufferedImageToPixelImageConvertor
import asciiArtApp.convertors.toString.specific.AsciiImageToStringConvertor
import asciiArtApp.filters.image.pixel.mixed.MixedFilter
import asciiArtApp.filters.image.pixel.specific.GreyScaleFilter
import asciiArtApp.generators.image.pixel.specific.RandomPixelImageGenerator
import asciiArtApp.models.image.PixelImage
import exporters.text.multi.MultiTextExporter
import importers.file.image.BufferedImageImporter

import java.io.File

/**
 * Facade that covers basic image flow throughout the app
 */
class ImageFacade {

  // setters are needed for mocking
  var importer = new BufferedImageImporter
  var convertorToPixelImg = new BufferedImageToPixelImageConvertor
  var convertorToAsciiImg = new PixelImageToAsciiImageConvertor(StandardTable)
  var convertorToString = new AsciiImageToStringConvertor
  var randomPixelImageGenerator = new RandomPixelImageGenerator()
  var greyScaleFilter = new GreyScaleFilter

  /**
   * Loads image from given file, applies given filter and outputs result using given exporter
   * @param file Filter from which image should be loaded
   * @param filter Filter which should be applied
   * @param exporter Exporter which should be used to output result
   * @throws IllegalArgumentException if image can not be loaded from given file
   */
  def processImageFromFile( file: File, filter: MixedFilter, exporter: MultiTextExporter): Unit = {
    try {
      val bufferedImage = importer.`import`(file)
      if (bufferedImage == null) throw new IllegalArgumentException("Image could not be loaded from given file")
      val pixelImage = convertorToPixelImg.convert(bufferedImage)
      processImage(pixelImage, filter, exporter)
    }
    catch {
      case _: java.io.IOException => throw new IllegalArgumentException("Image could not be loaded from file " + file.getPath)
    }
  }

  /**
   * Generates random image, applies given filter and outputs result using given exporter
   * @param filter Filter which should be applied
   * @param exporter Exporter which should be used to output result
   */
  def processRandomImage(filter: MixedFilter, exporter: MultiTextExporter): Unit = {
    processImage(randomPixelImageGenerator.generate(), filter, exporter)
  }

  private def processImage(image: PixelImage, filter: MixedFilter, exporter: MultiTextExporter): Unit = {
    val greyscaleImage = greyScaleFilter.filter(image)
    val filteredImage = filter.filter(greyscaleImage)
    val asciiImage = convertorToAsciiImg.convert(filteredImage)
    exporter.`export`(convertorToString.convert(asciiImage))
  }
}
