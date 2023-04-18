package exporters.text.specific

import java.io.{File, FileOutputStream}

/**
 * @note Borrowed from lab
 */
class FileOutputExporter(val file: File) extends StreamTextExporter(new FileOutputStream(file))
