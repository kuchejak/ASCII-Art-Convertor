package asciiArtApp.facades

import asciiArtApp.convertors.toImage.toAscii.specific.PixelImageToAsciiImageConvertor
import asciiArtApp.convertors.toImage.toPixel.specific.BufferedImageToPixelImageConvertor
import asciiArtApp.convertors.toString.specific.AsciiImageToStringConvertor
import asciiArtApp.filters.image.pixel.mixed.MixedFilter
import asciiArtApp.filters.image.pixel.specific.GreyScaleFilter
import asciiArtApp.generators.image.pixel.specific.RandomPixelImageGenerator
import asciiArtApp.models.image.{AsciiImage, PixelImage}
import asciiArtApp.models.image.grids.Grid
import asciiArtApp.models.image.pixels.Pixel
import exporters.text.multi.MultiTextExporter
import importers.file.image.BufferedImageImporter
import org.mockito.MockitoSugar.{mock, verify, when}
import org.scalatest.FunSuite

import java.awt.image.BufferedImage
import java.io.File

class ImageFacadeTest extends FunSuite {

  test("testProcessRandomImage") {
    val facade = new ImageFacade
    val file = new File("test")
    val pixelImage = PixelImage(Grid(List(List(new Pixel(1, 1, 1)))))
    val asciiImage = AsciiImage(Grid(List(List('1'))))
    val toExport = "1"

    val convertorToAsciiMock = mock[PixelImageToAsciiImageConvertor]
    val convertorToStringMock = mock[AsciiImageToStringConvertor]
    val filterMock = mock[MixedFilter]
    val exporterMock = mock[MultiTextExporter]
    val pixelImageGeneratorMock = mock[RandomPixelImageGenerator]
    val filterGreyMock = mock[GreyScaleFilter]

    when(pixelImageGeneratorMock.generate()).thenReturn(pixelImage)
    when(filterMock.filter(pixelImage)).thenReturn(pixelImage)
    when(convertorToAsciiMock.convert(pixelImage)).thenReturn(asciiImage)
    when(convertorToStringMock.convert(asciiImage)).thenReturn(toExport)
    when(filterGreyMock.filter(pixelImage)).thenReturn(pixelImage)

    facade.randomPixelImageGenerator = pixelImageGeneratorMock
    facade.convertorToAsciiImg = convertorToAsciiMock
    facade.convertorToString = convertorToStringMock
    facade.greyScaleFilter = filterGreyMock

    facade.processRandomImage(filterMock, exporterMock)

    verify(pixelImageGeneratorMock).generate()
    verify(filterMock).filter(pixelImage)
    verify(convertorToAsciiMock).convert(pixelImage)
    verify(convertorToStringMock).convert(asciiImage)
    verify(exporterMock).`export`(toExport)
    verify(filterGreyMock).filter(pixelImage)
  }

  test("testProcessImageFromFile") {
    val facade = new ImageFacade
    val file = new File("test")
    val pixelImage = PixelImage(Grid(List(List(new Pixel(1, 1, 1)))))
    val asciiImage = AsciiImage(Grid(List(List('1'))))
    val toExport = "1"

    val importerMock = mock[BufferedImageImporter]
    val convertorToPixelMock = mock[BufferedImageToPixelImageConvertor]
    val convertorToAsciiMock = mock[PixelImageToAsciiImageConvertor]
    val convertorToStringMock = mock[AsciiImageToStringConvertor]
    val bufferedImageMock = mock[BufferedImage]
    val filterMock = mock[MixedFilter]
    val filterGreyMock = mock[GreyScaleFilter]
    val exporterMock = mock[MultiTextExporter]

    when(importerMock.`import`(file)).thenReturn(bufferedImageMock)
    when(convertorToPixelMock.convert(bufferedImageMock)).thenReturn(pixelImage)
    when(filterMock.filter(pixelImage)).thenReturn(pixelImage)
    when(convertorToAsciiMock.convert(pixelImage)).thenReturn(asciiImage)
    when(convertorToStringMock.convert(asciiImage)).thenReturn(toExport)
    when(filterGreyMock.filter(pixelImage)).thenReturn(pixelImage)

    facade.importer = importerMock
    facade.convertorToPixelImg = convertorToPixelMock
    facade.convertorToAsciiImg = convertorToAsciiMock
    facade.convertorToString = convertorToStringMock
    facade.greyScaleFilter = filterGreyMock

    facade.processImageFromFile(file, filterMock, exporterMock)

    verify(importerMock).`import`(file)
    verify(convertorToPixelMock).convert(bufferedImageMock)
    verify(filterMock).filter(pixelImage)
    verify(convertorToAsciiMock).convert(pixelImage)
    verify(convertorToStringMock).convert(asciiImage)
    verify(exporterMock).`export`(toExport)
    verify(filterGreyMock).filter(pixelImage)
  }

  test("testProcessIllegalFile") {
    val facade = new ImageFacade
    val file = new File("XXX")
    val filter = mock[MixedFilter]
    val exporter = mock[MultiTextExporter]
    try {
      facade.processImageFromFile(file, filter, exporter)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }
}
