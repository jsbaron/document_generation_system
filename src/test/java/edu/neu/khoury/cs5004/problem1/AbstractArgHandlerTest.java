package edu.neu.khoury.cs5004.problem1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractArgHandlerTest {

  String[] ary1;
  String[] ary2;
  String[] ary3;
  String[] ary4;
  String[] ary5;
  String[] ary6;
  String[] ary7;
  String[] ary8;
  String[] ary9;
  String[] ary10;
  String[] ary11;
  String[] ary12;
  String[] ary13;
  String[] ary14;
  String[] ary15;
  String[] ary16;
  String[] ary17;
  String[] ary18;

  @Before
  public void setUp() throws Exception {
    ary1 = "--email --email-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary2 = "--email --email-template emails.txt --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary3 = "--letter --letter-template letters.txt --csv-file customer.csv --output-dir letters"
        .split(" ");

    ary4 = "--email --letter-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary5 = "--letter --email-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary6 = "--email-template emails --csv-file customer.csv --output-dir emails".split(" ");

    ary7 = "--email --wrong-template emails --csv-file customer.csv --output-dir emails".split(" ");

    ary8 = "--letter --email-template emails.txt --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary9 = "--letter --letter-template emails --csv-file --output-dir emails customer.csv"
        .split(" ");

    ary10 = "--email --noTxt --email-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary11 = "--email --email-template emails.txt --csv-file noCsv --output-dir emails"
        .split(" ");

    ary12 = "--letter --letter-template letter.txt --csv-file customer.csv --output-dir letters"
        .split(" ");

    ary13 = "--email --letter-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary14 = "--letter --email-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary15 = "--email-template emails --csv-file customer.csv --output-dir emails".split(" ");

    ary16 = "--email --wrong-template emails --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary17 = "--letter --email-template emails.txt --csv-file customer.csv --output-dir emails"
        .split(" ");

    ary18 = "--letter --letter-template emails --csv-file --output-dir emails customer.csv"
        .split(" ");


  }

  @Test(expected = IncorrectArgumentFormat.class)
  public void validate() {
    AbstractArgHandler.validate(ary2);
    AbstractArgHandler.validate(ary1);

    AbstractArgHandler.validate(ary3);
    AbstractArgHandler.validate(ary4);
    AbstractArgHandler.validate(ary5);
    AbstractArgHandler.validate(ary6);
    AbstractArgHandler.validate(ary7);
    AbstractArgHandler.validate(ary8);
    AbstractArgHandler.validate(ary9);
    AbstractArgHandler.validate(ary10);
    AbstractArgHandler.validate(ary11);


  }

  @Test(expected = IncorrectArgumentFormat.class)
  public void validate1() {
    AbstractArgHandler.validate(ary3);
    AbstractArgHandler.validate(ary4);
    AbstractArgHandler.validate(ary5);
    AbstractArgHandler.validate(ary6);
    AbstractArgHandler.validate(ary7);
    AbstractArgHandler.validate(ary8);
    AbstractArgHandler.validate(ary9);
    AbstractArgHandler.validate(ary10);
    AbstractArgHandler.validate(ary11);


  }

  @Test(expected = IncorrectArgumentFormat.class)
  public void validate2() {
    AbstractArgHandler.validate(ary5);
    AbstractArgHandler.validate(ary6);
    AbstractArgHandler.validate(ary7);
    AbstractArgHandler.validate(ary8);
    AbstractArgHandler.validate(ary9);
    AbstractArgHandler.validate(ary10);
    AbstractArgHandler.validate(ary11);


  }

  @Test(expected = IncorrectArgumentFormat.class)
  public void validate4() {
    AbstractArgHandler.validate(ary7);
    AbstractArgHandler.validate(ary8);
    AbstractArgHandler.validate(ary9);
    AbstractArgHandler.validate(ary10);
    AbstractArgHandler.validate(ary11);


  }

  @Test(expected = IncorrectArgumentFormat.class)
  public void validate5() {
    AbstractArgHandler.validate(ary9);
    AbstractArgHandler.validate(ary10);
    AbstractArgHandler.validate(ary11);


  }


  @Test
  public void read() {
    AbstractArgHandler.read(ary1);
    AbstractArgHandler.read(ary2);
    Hub hub = new Hub("emails.txt", "emails", "customer.csv", "--emails-template");
    Hub hub2 = new Hub("letters.txt", "letters", "customer.csv", "--letter-template");

    System.out.println(hub2.templateDirectory);

    System.out.println(hub2.outputDirectory);
    System.out.println(hub2.csvFile);
    System.out.println(AbstractArgHandler.read(ary3).templateDirectory);
    System.out.println(AbstractArgHandler.read(ary3).outputDirectory);
    System.out.println(AbstractArgHandler.read(ary3).csvFile);

    assertTrue(hub.templateDirectory.equals(AbstractArgHandler.read(ary2).templateDirectory));
  }

  @Test
  public void read2() {
    AbstractArgHandler.read(ary1);
    AbstractArgHandler.read(ary2);
    Hub hub = new Hub("emails.txt", "emails", "customer.csv","--emails-template");
    Hub hub2 = new Hub("letters.txt", "letters", "customer.csv","--letter-template");

    System.out.println(hub2.templateDirectory);

    System.out.println(hub2.outputDirectory);
    System.out.println(hub2.csvFile);
    System.out.println(AbstractArgHandler.read(ary3).templateDirectory);
    System.out.println(AbstractArgHandler.read(ary3).outputDirectory);
    System.out.println(AbstractArgHandler.read(ary3).csvFile);

    assertEquals(hub2.templateDirectory, AbstractArgHandler.read(ary3).templateDirectory);
  }


}