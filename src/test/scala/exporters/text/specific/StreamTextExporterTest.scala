package exporters.text.specific

import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

/**
 * @note Borrowed from lab
 */
class StreamTextExporterTest extends FunSuite {
  test("Write"){
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.export("Ahoj")

    assert(stream.toString("UTF-8") == "Ahoj")
  }
}
