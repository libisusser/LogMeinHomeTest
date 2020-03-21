
package wepPages;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import utilites.base;
import utilites.commonOps;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class sendMail

{

   public static void sendingMail(String subject, String sendfilefullpath) 
   
   {     
      // Recipient's email ID needs to be mentioned.
      String to = "libisusser@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "libisusser@gmail.com";

      // Get  properties
      Properties props = new Properties();

      // Setup mail server
	  final String username = "tessapicks@gmail.com";
	  final String password = "milydyrs3";
		
      props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.host", "smtp.gmail.com");
	  props.put("mail.smtp.port", "587");

	  // get a Session instance
	  Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });


      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Create the message part 
         BodyPart messageBodyPart = new MimeBodyPart();

         // Fill the message
         messageBodyPart.setText("This is the report we got:\n");
         
         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
		 File f = new File(sendfilefullpath);
		 String filenameOnly = f.getName();
         DataSource source = new FileDataSource(sendfilefullpath);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filenameOnly);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart );

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }




}
