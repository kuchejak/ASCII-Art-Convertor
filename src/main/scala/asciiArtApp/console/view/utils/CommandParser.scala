package asciiArtApp.console.view.utils

import asciiArtApp.console.view.ConsoleCommands
import asciiArtApp.filters.image.pixel.PixelImageFilter
import asciiArtApp.filters.image.pixel.default.PixelNoFilter
import asciiArtApp.filters.image.pixel.mixed.MixedFilter
import asciiArtApp.filters.image.pixel.specific.{Axis, FlipFilter}
import asciiArtApp.filters.image.pixel.specific.greyscale.{GreyscaleBrightnessFilter, GreyscaleInvertFilter}
import exporters.text.TextExporter
import exporters.text.multi.MultiTextExporter
import exporters.text.specific.{FileOutputExporter, StdOutputExporter}

import java.io.File

object CommandParser {

  //region Basic checks on commands
  /**
   * Checks if given command is help command
   * @param command Command to be tested
   * @return True if command is help command
   */
  def isHelp(command: String): Boolean = {
    command == ConsoleCommands.help
  }

  /**
   * Checks if given command is exit command
   * @param command Command to be tested
   * @return True if command is exit command
   */
  def isExit(command: String): Boolean = {
    command == ConsoleCommands.exit
  }

  /**
   * Checks if given command is run command
   * @param command Command to be tested
   * @return True if command is run command
   */
  def isRun(command: String): Boolean = {
    command.startsWith(ConsoleCommands.run + " ") || command.equals(ConsoleCommands.run)
  }
  //endregion

  //region Run parsing
  /**
   * Parses given run command and returns array of arguments``
   * @param runCommand Run command to be parsed
   * @return Arguments, without "--"
   * @throws IllegalArgumentException if parsing was not successful
   */
  def getRunArgs(runCommand: String) : Array[String] = {
    val argRegex = (ConsoleCommands.run + " --?(.*)").r

    runCommand match {
      case argRegex(args) => args.split(" --") ;
      case _ => throw new IllegalArgumentException("Parsing of run command was not successful")
    }
  }
  //endregion

  //region Input argument methods
  /**
   * Checks input argument and return true if image should be imported from file
   * @param inputArg Argument to check
   */
  def isFromFile(inputArg: String): Boolean = {
    inputArg.startsWith(ConsoleCommands.imageFromFile) && !inputArg.startsWith(ConsoleCommands.randomImage)
  }

  /**
   * Extracts input parameter and returns it as File, does not verify that arg is valid
   * @param inputArg Valid file input argument to be parsed
   * @return File corresponding to input argument
   * @throws IllegalArgumentException if reading from given file is not possible
   */
  def getInputFile(inputArg: String): File = {
    val param = getParameterFromArg(inputArg)
    val file = new File(param.replaceAll("\"", ""))
    if (!file.canRead) throw new IllegalArgumentException("Can not read image from file \"" + param + "\"" )
    file
  }

  /**
   * Check input argument and return true if image should be randomly generated
   * @param inputArg Argument to check
   */
  def isRandom(inputArg: String): Boolean = {
    inputArg.equals(ConsoleCommands.randomImage)
  }

  /**
   * Extracts input argument from all arguments + checks that input argument is valid and only one
   * @param textArgs Run arguments
   * @return Input argument as text
   * @throws IllegalArgumentException If no valid input argument is found, of if multiple image arguments are found
   */
  def getInputArg(textArgs: Array[String]): String = {
    val imageArgs = textArgs.filter(matchInput)
    verifyImageArgs(imageArgs)
    imageArgs.head // only one element
  }

  /**
   * Checks that there is exactly one image argument
   * @param inputArgs All run image arguments
   */
  private def verifyImageArgs(inputArgs: Array[String]): Unit = {
    if (inputArgs.length > 1) {
      throw new IllegalArgumentException("Too many image arguments")
    }
    if (inputArgs.length == 0) {
      throw new IllegalArgumentException("No image arguments")
    }
  }

  /**
   * Simple string matcher that check if given string is image argument
   * @param arg Argument to check
   * @return True if given argument is image argument
   */
  private def matchInput(arg: String) : Boolean = {
    arg.startsWith(ConsoleCommands.imageFromFile) || arg.startsWith(ConsoleCommands.randomImage)
  }
  //endregion

  //region Filter argument methods
  /**
   * Extracts filter arguments from run command arguments and converts them to MixedFilter
   * @param textArgsArray Run command arguments
   * @throws IllegalArgumentException if any problem with parsing occurs
   */
  def getMixedFilter(textArgsArray: Array[String]): MixedFilter =  {
    val filterArgs = textArgsArray.filter(a => !matchOutput(a) && !matchInput(a))
    verifyFilterArgs(filterArgs)
    convertFilterArgsToFilter(filterArgs)
  }

  /**
   * Verifies that all input arguments are valid (from ConsoleCommands), but does not check their parameters
   * @param filterArgs Argument that should be verified
   */
  private def verifyFilterArgs(filterArgs: Array[String]): Unit = {
    filterArgs.foreach(f => {
      if (f.startsWith(ConsoleCommands.invertFilter)) return
      if (f.startsWith(ConsoleCommands.flipXFilter)) return
      if (f.startsWith(ConsoleCommands.flipYFilter)) return
      if (f.startsWith(ConsoleCommands.brightnessFilter)) return
      throw new IllegalArgumentException("Unknown filter: --" + f)
    })
  }

  /**
   * Converts already parsed filter arguments to corresponding MixedFilter
   * @throws IllegalArgumentException When filters are not properly parsed or their arguments are wrong
   * @param filterArgs Parsed filter args
   * @return Corresponding MixedFilter
   */
  private def convertFilterArgsToFilter(filterArgs: Array[String]) : MixedFilter = {
    if (filterArgs.length == 0) return new MixedFilter(List(new PixelNoFilter))
    new MixedFilter(filterArgs.map(convertFilterArgToFilter))
  }

  /**
   * Converts already parsed filter argument to its corresponding PixelImageFilter
   * @throws IllegalArgumentException When filter parameters are wrong or filter is not properly parsed
   * @param filterArg Parsed filter arg
   * @return Corresponding PixelImageFilter
   */
  def convertFilterArgToFilter(filterArg: String) : PixelImageFilter = {
    val param = getParameterFromArg(filterArg)
    val justFilter = filterArg.split(" ").head

    if (justFilter.equals(ConsoleCommands.invertFilter)) {
      if (param != "") throw new IllegalArgumentException("Invert filter does not take parameters")
      return new GreyscaleInvertFilter
    }

    if (justFilter.equals(ConsoleCommands.flipFilter)) {
      if (param == "x")
        return new FlipFilter(Axis.X)

      if (param == "y")
        return new FlipFilter(Axis.Y)

      throw new IllegalArgumentException("Invalid flip filter parameter \"" + param + "\"")
    }

    if (justFilter.equals(ConsoleCommands.brightnessFilter)) {
      try {
        return new GreyscaleBrightnessFilter(param.toInt)
      }
      catch {
        case _: NumberFormatException => throw new IllegalArgumentException("Invalid brightness filter parameter \"" + param + "\"");
      }
    }
    // This should in theory never happen, because argument passed to this function should already be parsed
    throw new IllegalArgumentException("Error occurred while parsing filters, make sure filters are inputted correctly")
  }
  //endregion

  //region Output argument methods
  /**
   * Extracts output arguments from run command arguments and converts them to MultiTextExporter
   * @param textArgsArray Run command arguments
   * @throws IllegalArgumentException if any problem with parsing occurs
   */
  def getMultiTextExporter(textArgsArray: Array[String]): MultiTextExporter = {
    val outputArgs = textArgsArray.filter(matchOutput)
    verifyOutputArgs(outputArgs)
    convertOutputArgsToMultiTextExporter(outputArgs)
  }

  /**
   * Creates new MultiTextExporter corresponding to given output arguments
   * @param outputArgs Output arguments that should be converted to MultiTextExporter
   */
  private def convertOutputArgsToMultiTextExporter(outputArgs: Array[String]): MultiTextExporter = {
    new MultiTextExporter(outputArgs.map(convertOutputArgToTextExporter))
  }

  /**
   * Creates new TextExporter corresponding to given output argument
   */
  def convertOutputArgToTextExporter(outputArg: String): TextExporter = {
    val param = getParameterFromArg(outputArg)
    val justFilter = outputArg.split(" ").head
    if (justFilter == ConsoleCommands.outputFile) {
      if (param == "") throw new IllegalArgumentException("No file specified for file output")
      val noQuotesParam = param.replaceAll("\"", "")
      val file = new File(noQuotesParam)
      try {
        return new FileOutputExporter(file)
      }
      catch {
        case _: java.io.IOException => throw new IllegalArgumentException("Could not create file output stream, make sure that " + param + " is valid path for output")
      }
    }
    else if (outputArg.equals(ConsoleCommands.outputConsole)) {
      if (param != "") throw new IllegalArgumentException("Console output does not take parameters, but got param \"" + param + "\"")
      return new StdOutputExporter
    }
    throw new IllegalArgumentException("Unknown output argument \"" + outputArg + "\"")
  }

  /**
   * Simple check if given argument is output argument
   */
  private def matchOutput(arg: String) : Boolean = {
    arg.startsWith(ConsoleCommands.outputFile) || arg.startsWith(ConsoleCommands.outputConsole)
  }

  /**
   * Verifies that there is at least one output argument
   */
  private def verifyOutputArgs(outputArgs: Array[String]): Unit = {
    if (outputArgs.length == 0) {
      throw new IllegalArgumentException("No output specified")
    }
  }
  //endregion

  //region Utility
  /**
   * Returns property from argument with parameter string
   * @param argWithParams Argument with parameter, for example --image "test"
   * @return Parameter, for example "test"
   */
  def getParameterFromArg(argWithParams: String): String = {
    val index = argWithParams.indexOf(" ") + 1 // substring begins with char at given index, we dont want space so +1
    if (index == 0) return "" // space not found -> no params
    if (index >= argWithParams.length) return "" // space is last char and last char only
    argWithParams.substring(index)
  }
  //endregion
}
