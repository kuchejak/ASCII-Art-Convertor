package exporters.text.multi

import exporters.text.specific.{FileOutputExporter, StdOutputExporter, StreamTextExporter}
import org.mockito.MockitoSugar.{mock, verify}
import org.scalatest.FunSuite

class MultiTextExporterTest extends FunSuite {
  test("TestExport") {
    val mockFile = mock[FileOutputExporter]
    val mockStd = mock[StdOutputExporter]
    val mockStream = mock[StreamTextExporter]

    val exporter = new MultiTextExporter(List(mockFile, mockStd, mockStream))

    val expStr = "abc"
    exporter.`export`(expStr)

    verify(mockFile).`export`(expStr)
    verify(mockStd).`export`(expStr)
    verify(mockStream).`export`(expStr)
  }

  test("TestNull") {
    try {
      new MultiTextExporter(null)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }
}
