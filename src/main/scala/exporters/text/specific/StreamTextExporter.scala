package exporters.text.specific

import exporters.text.TextExporter

import java.io.OutputStream

/**
 * @note Borrowed from lab
 */
class StreamTextExporter(outputStream: OutputStream) extends TextExporter
{
  private var closed = false

  protected def exportToStream(text: String): Unit ={

    if (closed)
      throw new Exception("The stream is already closed")

    outputStream.write(text.getBytes("UTF-8"))
    outputStream.flush()
  }

  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def export(item: String): Unit = exportToStream(item)
}
