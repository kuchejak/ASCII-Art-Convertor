package asciiArtApp.convertors.toAscii.tables

import asciiArtApp.convertors.toAscii.PixelToAsciiConvertor

/**
 * Pixel to Ascii char convertor that uses "standard" converting sequence
 */
object StandardTable extends PixelToAsciiConvertor("""$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:,"^`'. """)
