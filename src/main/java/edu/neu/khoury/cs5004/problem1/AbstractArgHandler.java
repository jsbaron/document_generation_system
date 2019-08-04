package edu.neu.khoury.cs5004.problem1;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An Abstract ArgHandler class.
 */
public abstract class AbstractArgHandler {

  private static final String EMAIL_ERROR_STRING = "Error: --email provided but no "
      + "--email-template was given.\n";

  private static final String LETTER_ERROR_STRING = "Error: --letter provided but no "
      + "--email-template was given.\n";

  private static final Integer NUM_ARGS = 7;

  private static final String EMAIL = "--email";

  private static final String EMAIL_TEMPLATE = "--email-template";

  private static final String LETTER = "--letter";

  private static final String LETTER_TEMPLATE = "--letter-template";

  private static final String OUTPUT = "--output-dir";

  private static final String CSV = "--csv-file";

  private static final String BAD_DIR = "Directory does not exist.";


  /**
   * Given a string array from the cli, validates that the arguments are correct, stops program and
   * instructs user as to proper use of program.
   *
   * @param args command line interface arguments.
   */
  public static void validate(String[] args) throws IncorrectArgumentFormat {

    List<String> argList = new ArrayList<>(Arrays.asList(args));
    if (argList.size() != NUM_ARGS) {
      throw new IncorrectArgumentFormat(AbstractArgHandler.instructions());
    }

    if (!argList.contains(EMAIL) && !argList.contains(LETTER)) {
      throw new IncorrectArgumentFormat(AbstractArgHandler.instructions());
    }

    if (!AbstractArgHandler.noEmailMismatch(argList)) {
      throw new IncorrectArgumentFormat(EMAIL_ERROR_STRING
          +
          AbstractArgHandler.instructions());
    }

    if (!AbstractArgHandler.noLetterMismatch(argList)) {
      throw new IncorrectArgumentFormat(LETTER_ERROR_STRING
          +
          AbstractArgHandler.instructions());

    }

    if (!AbstractArgHandler.hasCsv(argList)) {
      throw new IncorrectArgumentFormat(AbstractArgHandler.instructions());
    }

    if (!AbstractArgHandler.hasOutput(argList)) {
      throw new IncorrectArgumentFormat(AbstractArgHandler.instructions());
    }

    if (!AbstractArgHandler.hasTxt(argList)) {
      throw new IncorrectArgumentFormat(AbstractArgHandler.instructions());
    }

    if (!AbstractArgHandler.isDirectory(argList)) {
      throw new IncorrectArgumentFormat(BAD_DIR + AbstractArgHandler.instructions());
    }

  }

  /**
   * Reads strings and returns a Hub object with strings placed in appropriate fields.
   *
   * @param args args
   * @return hub Hub
   */
  public static Hub read(String[] args) {
    List<String> argList = new ArrayList<>(Arrays.asList(args));
    String outputDir = "";
    String csv = "";
    String templateDir = "";
    String templateType = "";

    for (String arg : args) {
      switch (arg) {
        case OUTPUT:
          outputDir = (argList).get(argList.indexOf(arg) + 1);
          break;

        case EMAIL_TEMPLATE:
          templateDir = (argList).get(argList.indexOf(arg) + 1);
          templateType = EMAIL_TEMPLATE;
          break;
        case LETTER_TEMPLATE:
          templateDir = (argList).get(argList.indexOf(arg) + 1);
          templateType = LETTER_TEMPLATE;
          break;
        case CSV:
          csv = (argList).get(argList.indexOf(arg) + 1);
          break;
        default:
          break;
      }
    }

    return new Hub(templateDir, outputDir, csv, templateType);
  }


  /**
   * Ascertains whether csv file exists.
   *
   * @param argList List of Strings
   * @return true or false
   */
  private static Boolean hasCsv(List<String> argList) {
    for (String arg : argList) {
      if (arg.equals(CSV)) {
        return (argList).get(argList.indexOf(arg) + 1).matches(".+(\\.csv)");
      }
    }
    return false;
  }

  private static Boolean hasTxt(List<String> argList) {
    for (String arg : argList) {
      if (arg.equals(EMAIL_TEMPLATE) || arg.equals(LETTER_TEMPLATE)) {
        return (argList).get(argList.indexOf(arg) + 1).matches(".+(\\.txt)");
      }
    }
    return false;
  }

  private static Boolean hasOutput(List<String> argList) {
    for (String arg : argList) {
      if (arg.equals(OUTPUT)) {
        return (argList).get(argList.indexOf(arg) + 1).matches(".+");
      }
    }
    return false;
  }


  private static Boolean noEmailMismatch(List<String> argList) {
    if (argList.contains(EMAIL)) {
      return !argList.contains(LETTER_TEMPLATE) && argList.contains(EMAIL_TEMPLATE);
    }

    return true;

  }


  /**
   * Given an argList ests for letter mismatch.
   *
   * @param argList arglist
   * @return Boolean true or false
   */
  private static Boolean noLetterMismatch(List<String> argList) {
    if (argList.contains(LETTER)) {
      return !argList.contains(EMAIL_TEMPLATE) && argList.contains(LETTER_TEMPLATE);
    }
    return true;
  }


  /**
   * Tests whether output directory is legitimate.
   *
   * @param argList List of strings
   * @return Boolean true or false
   */
  private static Boolean isDirectory(List<String> argList) {
    String output = "";
    for (String arg : argList) {
      if (arg.equals(OUTPUT)) {
        output = (argList).get(argList.indexOf(arg) + 1);
      }
    }
    File file = new File(output);
    return file.isDirectory();
  }


  /**
   * Returns the instructions on how to use the program.
   *
   * @return String instructions.
   */
  private static String instructions() {
    return "Usage:\n"
        + " --email only generate email messages\n"
        + " --email-template <file> accept a filename that holds the\n"
        + "email template.\n"
        + " Required if --email is used\n"
        + " --letter only generate letters\n"
        + " --letter-template <file> accept a filename that holds\n"
        + "the email template.\n"
        + " Required if --letter is used\n"
        + " --output-dir <path> accept the name of a folder, all\n"
        + "output is placed in this folder\n"
        + " --csv-file <path> accept the name of the csv file to\n"
        + "process\n"
        + "Examples:\n"
        + " --email --email-template email-template.txt --output-dir\n"
        + "emails --csv-file customer.csv\n"
        + " --letter --letter-template letter-template.txt --outputdir\n"
        + "letters --csv-file customer.csv";
  }

}
