package asciiArtApp.convertors.toAscii

import asciiArtApp.models.image.pixels.Pixel
import org.scalatest.FunSuite

import java.awt.Color
import scala.util.control.Breaks.{break, breakable}

class PixelToAsciiConvertorTest extends FunSuite {

  test("testShortSequence1") {
    try {
      val convertor = new PixelToAsciiConvertor("1")
      assert(false)
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testShortSequence2") {
    try {
      val convertor = new PixelToAsciiConvertor("")
      assert(false)
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testNullSequence") {
    try {
      val convertor = new PixelToAsciiConvertor(null)
      assert(false)
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testNotGreyScale") {
    val testSequence = "123"
    val convertor = new PixelToAsciiConvertor(testSequence)
    val pixel = Pixel(new Color(13, 14, 15))

    try {
      convertor.convert(pixel)
      assert(false)
    }
    catch {
      case e: IllegalArgumentException =>
    }
  }

  test("testConvert") {
    testSeq("123")
    testSeq("12345")
    testSeq("123456")
    testSeq("1234567")
  }

  // Tests every possible greyscale color and its conversion to ascii
  private def testSeq(seq: String): Unit = {
    val convertor = new PixelToAsciiConvertor(seq)
    val bucketSize = Math.ceil(256.0 / seq.length).toInt

    for (i <- 0 until seq.length) {
      breakable {
        for (j <- 0 until bucketSize) {
          val value = j + (i * bucketSize)
          if (value > 255) break
          val color = new Color(value, value, value)
          val conversion = convertor.convert(Pixel(color))
          assert(conversion == seq(i), "Assert failed for seq " + seq + ", value " + value + ", was converted to " + conversion + ", instead of " + seq(i))
        }
      }
    }
  }
}
