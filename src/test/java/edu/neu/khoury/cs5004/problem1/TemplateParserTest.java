package edu.neu.khoury.cs5004.problem1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TemplateParserTest {


  ITemplateParser parser;
  @Before
  public void setUp() throws Exception {
    parser = new TemplateParser("/Users/joshuabaron/Documents/NEU/cs5004/Team_repo_Josh_Baron_Eric_Egan/Assignment8/src/main/java/edu/neu/khoury/cs5004/problem1/emailtemplate.txt");
  }

  @Test
  public void read() throws Exception{
    assertEquals(parser.parse().getTemplateString(), "From:insuranceCompany@ic.com\n" +
        "To:[[email]]\n" +
        "Subject: Insurance company – information about recent data breach\n" +
        "Dear [[first_name]] [[last_name]],\n" +
        "As you may have heard or read, last month we learned that criminals forced their way " +
        "into our systems, \n" +
        "and stole information about our customers. Late last week, as part of our ongoing " +
        "investigation, \n" +  "we learned that the taken information includes names, mailing " +
        "addresses, phone numbers or email addresses.\n" + " \n" +
        "I am writing to make you aware that your name, mailing address, phone number or email address may have been \n" +
        "taken during the intrusion. \n" +
        "\n" +
        "I am truly sorry this incident occurred, and I sincerely regret any inconvenience it may cause you. \n" +
        "\n" +
        "Because we value you as a customer, and because your trust is important to us, our company is offering you one \n" +
        "year of free credit monitoring through Experian’s ProtectMyID product, which includes identity theft insurance \n" +
        "where available. You will receive more information about this offer via regular mail.\n" +
        "\n" +
        "You can find additional information and FAQs about this incident at our website. If you have further questions, \n" +
        "you may call us at 866-852-8680. \n" +
      "\n" +
        "Thank you for your patience and your loyalty. \n" +
        "Sincerely,\n" +
        "Insurance Company CEO\n");
  }

  @Test
  public void parse() throws Exception {
    assertEquals(new HashSet<>(Arrays.asList("email", "last_name", "first_name")), parser.parse().getFields());
  }
}