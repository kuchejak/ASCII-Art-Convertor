package importers

import org.scalatest.FunSuite
import importers.file.image.BufferedImageImporter

import java.io.File

class BufferedImageImporterTest extends FunSuite {
  test("Test image importing") {
    val importer = new BufferedImageImporter
    val file = new File("src/test/scala/testFiles/Pepe.png")
    val imported = importer.`import`(file)
    assert(imported != null)
    assert(imported.getWidth() == 230)
    assert(imported.getHeight() == 219)
  }

  test("Import invalid") {
    try {
      val importer = new BufferedImageImporter
      val file = new File("akdfhsdfjihasdfkjsadfk")
      importer.`import`(file)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
      case _: javax.imageio.IIOException =>
    }
  }

  test("Bad format") {
    val importer = new BufferedImageImporter
    val file = new File("src/test/scala/testFiles/Dog.mp4")
    val imported = importer.`import`(file)
    assert(imported == null)
  }
}
