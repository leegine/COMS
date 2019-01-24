package jessx.automate;

/**
 * <p>Titre : <Classe Principale Automate/p>
 *
 * <p>Description : </p>
 *
 * <p>Copyright : Copyright (c) 2005, Licence GPL, </p>
 *
 * <p>Société : Ec-lille, USTL</p>
 *
 * @ MARKET - JessX
 * @author Guillaume Tromp, Etienne Broutin
 * @version 1.0
 */


/***************************************************************/
/*                       IMPORT SECTION                        */
/***************************************************************/

import java.io.*;

import java.awt.*;
import javax.swing.*;

import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//import org.apache.log4j.*;
import jessx.business.*;
import jessx.utils.*;
import org.apache.log4j.PropertyConfigurator;
import jessx.net.Initialisation;
import java.util.Iterator;
import jessx.server.Server;
import java.util.Vector;
import jessx.net.ExpUpdate;
import jessx.server.gui.MessageTimer;
import jessx.server.net.ExperimentManager;
import jessx.server.net.NetworkCore;
import java.util.Properties;


/***************************************************************/
/*                 Automate CLASS SECTION                  */
/***************************************************************/
/**
 *
 * <p>Titre : </p>
 *
 * <p>Description : </p>
 *
 * <p>Copyright : Copyright (c) 2005, Licence GPL, </p>
 *
 * <p>Société : Ec-lille, USTL</p>
 *
 * @author Guillaume Tromp, Etienne Broutin
 * @version 1.0
 */

/**

 */

public class Automate {

    private final String Params_Serveur[] = {
      "auto.xml", "automatique", "sortie.xml"};

  //Initialize the current date
  GregorianCalendar calendar = new java.util.GregorianCalendar();
  Timer t3, t4, t33;
  private jessx.server.gui.GeneralServerFrame frame;

  int hour = calendar.get(calendar.HOUR_OF_DAY);
  int minutes = calendar.get(calendar.MINUTE);

  public final String AUTOMATE_LOG_FILE = "./automate.log";

  private void InitLogs() {

    File file = new File(AUTOMATE_LOG_FILE);

    if (file.exists()) {
      file.delete();
    }

    // Fill in a Property object
      Properties log4jconf = new Properties();

    // Use two appenders, one to log to console, another to log to a file
      log4jconf.setProperty("log4j.rootCategory", "debug, stdout, R");

    // Print only messages of priority DEBUG or higher for your category
      log4jconf.setProperty("log4j.category.your.category.name", "DEBUG");

    // Specifically inherit the priority level
      log4jconf.setProperty("log4j.category.your.category.name", "INHERITED");

    // First appender writes to console
      log4jconf.setProperty("log4j.appender.stdout",
                            "org.apache.log4j.ConsoleAppender");
      log4jconf.setProperty("log4j.appender.stdout.layout",
                            "org.apache.log4j.PatternLayout");

    // Pattern to output the caller's file name and line number.
      log4jconf.setProperty("log4j.appender.stdout.layout.ConversionPattern",
                            "%5p [%t] (%F:%L) - %m%n");

    // Second appender writes to a file
      log4jconf.setProperty("log4j.appender.R",
                            "org.apache.log4j.RollingFileAppender");
      log4jconf.setProperty("log4j.appender.R.File", AUTOMATE_LOG_FILE);

    // Control the maximum log file size
      log4jconf.setProperty("log4j.appender.R.MaxFileSize", "5000KB");

    // Archive log files (one backup file here)
      log4jconf.setProperty("log4j.appender.R.MaxBackupIndex", "1");

      log4jconf.setProperty("log4j.appender.R.layout",
                            "org.apache.log4j.PatternLayout");
      log4jconf.setProperty("log4j.appender.R.layout.ConversionPattern",
                            "%r [%p] %m  [%t] (%F:%L) \r\n");

    // Set this configuration
    PropertyConfigurator.configure(log4jconf);

  }

  public Automate() {

          Date sessionBeginning = new Date();
          //Launching of the automate
          System.out.println("Lancement de l'automate ...");

          jessx.automate.generator.generator gegen = new jessx.automate.
              generator.generator();

          gegen.Generate();

          gegen.Save("auto.xml");

          System.out.println("Fin de l'execution de l'automate ...");

          InitLogs();

          Utils.logger.debug("Logging enabled. Starting logging...");
          try {
            Utils.logger.debug("Getting and setting look and feel...");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          }
          catch (Exception e) {
            Utils.logger.error("Error while loading look and feel: " +
                               e.toString());
            e.printStackTrace();
          }

          //Creation and launching of a server with the parameters of the file auto.xml
          jessx.server.Server a = new jessx.server.Server(Params_Serveur);


          //Hosting of a session and launching of it when clients are connected
          t33 = new Timer();
          t33.schedule(new HostSession(), 10000, 10000);

          t3 = new Timer();
          t3.schedule(new LaunchSession(),10000 + 15000, 20000);

          //End the session btw 0 and 60 sec after the session is finished
          t4 = new Timer();
          t4.schedule(new EndSession(),1*60000, 20000);

  }

  /**
   It "host a session" which means players could connect to the game and wait for it to be launched
   */
  class HostSession
      extends TimerTask {
    public void run() {
      System.out.println("Host a Session");
      Server.setServerState(Server.SERVER_STATE_ONLINE);
      t33.cancel();
    }
  }


  /**
  This class launch a session, that means players will be connected to the game and could play together
   */
  class LaunchSession
      extends TimerTask {
    public void run() {
      if (NetworkCore.getPlayerList().size() > 0)
      System.out.println("Launch a session");

      Iterator iterPlayer = jessx.server.net.NetworkCore.getPlayerList().keySet().
          iterator();
      while (iterPlayer.hasNext()) {
        jessx.server.net.NetworkCore.getPlayer( (String) iterPlayer.next()).
            send(new
                 Initialisation());
      }

      if (jessx.server.net.NetworkCore.getExperimentManager().beginExperiment(
          frame)) {
        new MessageTimer( (Vector) BusinessCore.getScenario().
                         getListInformation().clone()).start();

      }

      t3.cancel();
    }
  }


  /**
   This class end a session by shutting down the automate
   */
  class EndSession
      extends TimerTask {
    public void run() {
      if (      NetworkCore.getExperimentManager().getExperimentState() ==
               ExperimentManager.EXP_OFF)
      System.exit(0);

      t4.cancel();
    }
  }

  public static void main(String[] args) {
    Automate automate = new Automate();
  }

}
