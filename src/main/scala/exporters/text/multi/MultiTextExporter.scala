package exporters.text.multi

import exporters.text.TextExporter

/**
 * Combines multiple text exporters, method export will export given item to every text exporter specified.
 */
class MultiTextExporter(val textExporters: Seq[TextExporter]) extends TextExporter {
  if (textExporters == null) throw new IllegalArgumentException("Can not construct MultiTextExporter from null")
  override def export(item: String): Unit = {
    textExporters.foreach(te => te.export(item))
  }
}
