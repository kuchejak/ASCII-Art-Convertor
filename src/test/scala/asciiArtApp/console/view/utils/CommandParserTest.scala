package asciiArtApp.console.view.utils

import asciiArtApp.console.view.ConsoleCommands
import asciiArtApp.filters.image.pixel.default.PixelNoFilter
import asciiArtApp.filters.image.pixel.specific.greyscale.{GreyscaleBrightnessFilter, GreyscaleInvertFilter}
import asciiArtApp.filters.image.pixel.specific.{Axis, FlipFilter}
import exporters.text.specific.{FileOutputExporter, StdOutputExporter}
import org.scalatest.FunSuite

import java.io.File

class CommandParserTest extends FunSuite {

  val parser: CommandParser.type = CommandParser

  test("testIsHelp") {
    assert(parser.isHelp(ConsoleCommands.help))
    assert(!parser.isHelp("hel"))
    assert(!parser.isHelp("he"))
    assert(!parser.isHelp("h"))
    assert(!parser.isHelp(""))
    assert(!parser.isHelp("helphelp"))
  }

  test("testGetRunArgsValid") {
    val runCommand = "run --image \"path\" --image-random --flip x --flip y --invert --brightness +23 --output-file \"path\" --output-console --smthnrnd"
    val args = List("image \"path\"", "image-random", "flip x", "flip y", "invert", "brightness +23", "output-file \"path\"", "output-console", "smthnrnd")
    val converted = parser.getRunArgs(runCommand).toList
    assert(args == converted)
  }

  test("testGetRunArgsJustRun") {
    try {
      val runCommand = "run"
      parser.getRunArgs(runCommand)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetRunArgsNoRun") {
    try {
      val runCommand = "--image \"path\" --image-random --flip x --flip y --invert --brightness +23 --output-file \"path\" --output-console --smthnrnd"
      parser.getRunArgs(runCommand)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetInputArgImageWithPath") {
    val args = List("image \"path\"", "flip x", "flip y", "invert", "brightness +23", "output-file \"path\"", "output-console", "smthnrnd")
    assert(parser.getInputArg(args.toArray) == "image \"path\"")
  }

  test("testGetInputArgImageRandom") {
    val args = List("image-random", "flip x", "flip y", "invert", "brightness +23", "output-file \"path\"", "output-console", "smthnrnd")
    assert(parser.getInputArg(args.toArray) == "image-random")
  }

  test("testGetInputArgTooManyInputs") {
    val args = List("image \"path\"", "image-random", "flip x", "flip y", "invert", "brightness +23", "output-file \"path\"", "output-console", "smthnrnd")
    try {
      parser.getInputArg(args.toArray)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetInputArgNone") {
    val args = List("flip x", "flip y", "invert", "brightness +23", "output-file \"path\"", "output-console", "smthnrnd")
    try {
      parser.getInputArg(args.toArray)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testIsExit") {
    assert(parser.isExit(ConsoleCommands.exit))
    assert(!parser.isExit("exi"))
    assert(!parser.isExit("ex"))
    assert(!parser.isExit("e"))
    assert(!parser.isExit(""))
    assert(!parser.isExit("exite"))
  }

  test("testIsFromFile") {
    assert(parser.isFromFile(ConsoleCommands.imageFromFile))
    assert(parser.isFromFile(ConsoleCommands.imageFromFile + "\"path\""))
    assert(!parser.isFromFile(ConsoleCommands.randomImage))
    assert(!parser.isFromFile(""))
    assert(!parser.isFromFile("sfsdf"))
  }

  test("testGetParameterFromArg") {
    val arg1 = "\"this could be some path\""
    val arg2 = "some random stuff"
    val arg3 = ""
    assert(parser.getParameterFromArg(ConsoleCommands.imageFromFile + " " + arg1) == arg1)
    assert(parser.getParameterFromArg(ConsoleCommands.imageFromFile + " " + arg2) == arg2)
    assert(parser.getParameterFromArg(ConsoleCommands.imageFromFile + " " + arg3) == arg3)

    assert(parser.getParameterFromArg(ConsoleCommands.brightnessFilter + " " + arg1) == arg1)
    assert(parser.getParameterFromArg(ConsoleCommands.brightnessFilter + " " + arg2) == arg2)
    assert(parser.getParameterFromArg(ConsoleCommands.brightnessFilter + " " + arg3) == arg3)
  }

  test("testGetMultiTextExporter") {
    val args = List("image \"path\"", "flip x", "flip y", "invert", "brightness +23", "output-file \"path\"", "output-console", "smthnrnd").toArray
    val actual = parser.getMultiTextExporter(args)
    val expectedFile = new File("path")

    // check that correct exporter was created
    assert(actual.textExporters.head.isInstanceOf[FileOutputExporter])
    assert(actual.textExporters.head.asInstanceOf[FileOutputExporter].file == expectedFile)
    assert(actual.textExporters(1).isInstanceOf[StdOutputExporter])
  }

  test("testGetMultiTextExporterNoOutput") {
    val args = List("image \"path\"", "flip x", "flip y", "invert", "brightness +23", "smthnrnd").toArray
    try {
      val actual = parser.getMultiTextExporter(args)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testIsRun") {
    assert(parser.isRun(ConsoleCommands.run))
    assert(parser.isRun(ConsoleCommands.run + " "))
    assert(parser.isRun(ConsoleCommands.run + " abc"))
    assert(!parser.isRun("ru"))
    assert(!parser.isRun("r"))
    assert(!parser.isRun(""))
    assert(!parser.isRun("runr"))
  }

  test("testConvertOutputArgToTextExporterConsole") {
    var exporter = parser.convertOutputArgToTextExporter(ConsoleCommands.outputConsole)
    assert(exporter.isInstanceOf[StdOutputExporter])
  }

  test("testConvertOutputArgToTextExporterFileQuotes") {
    var exporter = parser.convertOutputArgToTextExporter(ConsoleCommands.outputFile + " \"abc\"")
    assert(exporter.isInstanceOf[FileOutputExporter])
    assert(exporter.asInstanceOf[FileOutputExporter].file == new File("abc"))
  }

  test("testConvertOutputArgToTextExporterFileNoQuotes") {
    var exporter = parser.convertOutputArgToTextExporter(ConsoleCommands.outputFile + " abc")
    assert(exporter.isInstanceOf[FileOutputExporter])
    assert(exporter.asInstanceOf[FileOutputExporter].file == new File("abc"))
  }

  test("testConvertOutputArgToTextExporterNotValidOutputArg") {
    try {
      parser.convertOutputArgToTextExporter("output-consoler")
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testConvertOutputArgToTextExporterNotValidConsoleArg") {
    try {
      parser.convertOutputArgToTextExporter(ConsoleCommands.outputConsole + " fileArg")
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testConvertOutputArgToTextExporterEmptyStr") {
    try {
      parser.convertOutputArgToTextExporter("")
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testConvertFilterArgToFilterInvert") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.invertFilter)
    assert(filter.isInstanceOf[GreyscaleInvertFilter])
  }

  test("testConvertFilterArgToFilterFlipY") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.flipYFilter)
    assert(filter.isInstanceOf[FlipFilter])
    assert(filter.asInstanceOf[FlipFilter].axis == Axis.Y)
  }

  test("testConvertFilterArgToFilterFlipX") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.flipXFilter)
    assert(filter.isInstanceOf[FlipFilter])
    assert(filter.asInstanceOf[FlipFilter].axis == Axis.X)
  }

  test("testConvertFilterArgToFilterBrightnessPlus") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.brightnessFilter + " +10")
    assert(filter.isInstanceOf[GreyscaleBrightnessFilter])
    assert(filter.asInstanceOf[GreyscaleBrightnessFilter].amount == 10)
  }

  test("testConvertFilterArgToFilterBrightnessNoSign") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.brightnessFilter + " 10")
    assert(filter.isInstanceOf[GreyscaleBrightnessFilter])
    assert(filter.asInstanceOf[GreyscaleBrightnessFilter].amount == 10)
  }

  test("testConvertFilterArgToFilterBrightnessMinus") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.brightnessFilter + " -10")
    assert(filter.isInstanceOf[GreyscaleBrightnessFilter])
    assert(filter.asInstanceOf[GreyscaleBrightnessFilter].amount == -10)
  }

  test("testConvertFilterArgToFilterBrightnessZero") {
    var filter = parser.convertFilterArgToFilter(ConsoleCommands.brightnessFilter + " 0")
    assert(filter.isInstanceOf[GreyscaleBrightnessFilter])
    assert(filter.asInstanceOf[GreyscaleBrightnessFilter].amount == 0)
  }

  test("testConvertFilterArgToFilterInvalidArguments") {
    filterArgExpectIllegalArgEx(ConsoleCommands.invertFilter + "a")
    filterArgExpectIllegalArgEx(ConsoleCommands.invertFilter + " someArg")
    filterArgExpectIllegalArgEx(ConsoleCommands.flipFilter + " R")
    filterArgExpectIllegalArgEx(ConsoleCommands.flipFilter + "r X")
    filterArgExpectIllegalArgEx(ConsoleCommands.flipXFilter + " X")
    filterArgExpectIllegalArgEx(ConsoleCommands.flipYFilter + " Y")
    filterArgExpectIllegalArgEx(ConsoleCommands.brightnessFilter + "r")
    filterArgExpectIllegalArgEx(ConsoleCommands.brightnessFilter + " 10 10")
    filterArgExpectIllegalArgEx(ConsoleCommands.brightnessFilter + " 1o")
    filterArgExpectIllegalArgEx(ConsoleCommands.brightnessFilter)
  }

  private def filterArgExpectIllegalArgEx(FilterArg: String): Unit = {
    try {
      parser.convertFilterArgToFilter(FilterArg)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetMixedFilterValid") {
    val args = List(
      ConsoleCommands.flipXFilter,
      ConsoleCommands.flipYFilter,
      ConsoleCommands.invertFilter,
      ConsoleCommands.brightnessFilter + " 23")
    val mixed = parser.getMixedFilter(args.toArray)

    assert(mixed.filters.head.isInstanceOf[FlipFilter])
    assert(mixed.filters.head.asInstanceOf[FlipFilter].axis == Axis.X)

    assert(mixed.filters(1).isInstanceOf[FlipFilter])
    assert(mixed.filters(1).asInstanceOf[FlipFilter].axis == Axis.Y)

    assert(mixed.filters(2).isInstanceOf[GreyscaleInvertFilter])

    assert(mixed.filters(3).isInstanceOf[GreyscaleBrightnessFilter])
    assert(mixed.filters(3).asInstanceOf[GreyscaleBrightnessFilter].amount == 23)
  }

  test("testGetMixedFilterEmpty") {
    val args = List()
    val mixed = parser.getMixedFilter(args.toArray)
    assert(mixed.filters.head.isInstanceOf[PixelNoFilter])
  }

  test("testGetMixedFilterInvalidArguments") {
    var args = List(
      ConsoleCommands.flipXFilter,
      ConsoleCommands.flipYFilter + " abc",
      ConsoleCommands.invertFilter,
      ConsoleCommands.brightnessFilter + " 23")
    getMixedFilterExpectIllegalArgEx(args)

    args = List(
      ConsoleCommands.flipXFilter + " abc",
      ConsoleCommands.flipYFilter,
      ConsoleCommands.invertFilter,
      ConsoleCommands.brightnessFilter + " 23")
    getMixedFilterExpectIllegalArgEx(args)

    args = List(
      ConsoleCommands.flipXFilter,
      ConsoleCommands.flipYFilter,
      ConsoleCommands.invertFilter + " a",
      ConsoleCommands.brightnessFilter + " 23")
    getMixedFilterExpectIllegalArgEx(args)

    args = List(
      ConsoleCommands.flipXFilter,
      ConsoleCommands.flipYFilter,
      ConsoleCommands.invertFilter,
      ConsoleCommands.brightnessFilter + " 23 23")
    getMixedFilterExpectIllegalArgEx(args)

    args = List(
      ConsoleCommands.flipXFilter,
      ConsoleCommands.flipYFilter,
      ConsoleCommands.invertFilter,
      ConsoleCommands.brightnessFilter)
    getMixedFilterExpectIllegalArgEx(args)
  }

  private def getMixedFilterExpectIllegalArgEx(args: List[String]): Unit = {
    try {
      parser.getMixedFilter(args.toArray)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testGetInputFileNoQuotes") {
    var file = parser.getInputFile(ConsoleCommands.imageFromFile + " abc")
    assert(file == new File("abc"))
  }

  test("testGetInputFileQuotes") {
    var file = parser.getInputFile(ConsoleCommands.imageFromFile + " \"abc\"")
    assert(file == new File("abc"))
  }

  test("testGetInputFileNoParam") {
    try {
      parser.getInputFile(ConsoleCommands.imageFromFile)
      assert(false, "Exception expected")
    }
    catch {
      case _: IllegalArgumentException =>
    }
  }

  test("testIsRandom") {
    assert(parser.isRandom(ConsoleCommands.randomImage))
    assert(!parser.isRandom(ConsoleCommands.randomImage + "smthg"))
    assert(!parser.isRandom(""))
    assert(!parser.isRandom("image"))
    assert(!parser.isRandom(ConsoleCommands.imageFromFile))
  }


}
