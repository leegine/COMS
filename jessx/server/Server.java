package jessx.server;

/***************************************************************/
/*                     SOFTWARE SECTION                        */
/***************************************************************/
/*
 * <p>Name: Jessx</p>
 * <p>Description: Financial Market Simulation Software</p>
 * <p>Licence: GNU General Public License</p>
 * <p>Organisation: EC Lille / USTL</p>
 * <p>Persons involved in the project : group T.E.A.M.</p>
 * <p>More details about this source code at :
 *    http://eleves.ec-lille.fr/~ecoxp03  </p>
 * <p>Current version: 1.0</p>
 */

/***************************************************************/
/*                      LICENCE SECTION                        */
/***************************************************************/
/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/***************************************************************/
/*                       IMPORT SECTION                        */
/***************************************************************/

import java.io.*;

import java.awt.*;
import javax.swing.*;

import org.apache.log4j.*;
import jessx.business.*;
import jessx.server.gui.*;
import jessx.server.net.*;
import jessx.utils.*;
import org.jdom.Element;
import org.jdom.Document;
import java.util.Properties;

/***************************************************************/
/*                   Server CLASS SECTION                      */
/***************************************************************/
/**
 * <p>Title: Server</p>
 * <p>Description: </p>
 * @author Thierry Curtil
 * @version 0.9
 */
public class Server {

  public static int EXPERIMENT_STATE_SETUP = 0;
  public static int EXPERIMENT_STATE_RUNNING = 1;
  public static int EXPERIMENT_STATE_ENDED = 2;

  public static int SERVER_STATE_OFFLINE = 3;
  public static int SERVER_STATE_ONLINE = 4;

  boolean packFrame = true;

  /**
   * The server Frame
   */
  private GeneralServerFrame frame;

  private static int experimentState = 0; // value: EXPERIMENT_STATE_( SETUP | RUNNING | ENDED )
  private static int serverState = 3;     // value: SERVER_STATE_( ONLINE | OFFLINE )


  public static int getExperimentState() {
    return experimentState;
  }

  public static int getServerState() {
    return serverState;
  }

  public static void setServerState(int servState) {
    serverState = servState;
    if (servState == SERVER_STATE_ONLINE) {
      NetworkCore.setServerOnline();
    }
    else {
      /**
       * @todo implementer l'arret d'ecoute du serveur.
       */
      NetworkCore.setServerOffline();
      }
  }


  //Build Application
  public Server(String[] args) {

    BusinessCore.init(); // -todo : non used?
    BusinessCore.setGeneralParameters(new GeneralParameterSetupGui());

    this.experimentState = this.EXPERIMENT_STATE_SETUP;
    this.serverState = this.SERVER_STATE_OFFLINE;

    this.loadServerProperties();

    this.loadJessXModules();

    this.buildFrame();

    try
       {
         if (args.length > 0) {

           System.out.println("Il y a un fichier, chargement...");


           Document xmlDoc = Utils.readXmlFile(args[0]);

           BusinessCore.loadFromXml(xmlDoc.getRootElement(), frame);

         }
       }
     catch(Exception ex)
     {
     }
  }

  /**
   * <p>Loads all known modules, from the following locations: <br />
   * - modules/Operations<br />
   * - modules/Institutions<br />
   * - modules/Assets</p>
   */
  private void loadJessXModules() {
    Utils.logger.debug("Loading all available modules.");


    Utils.loadModules("jessx.business.operations.LimitOrder");
    Utils.loadModules("jessx.business.operations.DeleteOrder");
    Utils.loadModules("jessx.business.operations.MarketOrder");
    Utils.loadModules("jessx.business.operations.BestLimitOrder");

    Utils.loadModules("jessx.business.institutions.OrderMarket");

    Utils.loadModules("jessx.business.assets.Stock");

    Utils.logger.debug("All available modules loaded.");
  }

  private static final FileFilter filter = new FileFilter() {
    public boolean accept(File pathname) {
      if (pathname.isDirectory())return true;
      if (!pathname.isFile())return false;

      String path = pathname.getAbsolutePath();
      if (!path.endsWith(".class"))return false;
      if (path.indexOf('$') >= 0)return false;

      return true;
    }
  };


  /**
   * <p>Builds the main frame, by creating the frame object and placing it at the
   * center of the screen.</p>
   */
  private void buildFrame() {

    Utils.logger.info("Building frame...");
    frame = new jessx.server.gui.GeneralServerFrame();

    //Validate panels where preferred size is set
    if (packFrame) {
      frame.pack();
      Utils.logger.debug("Frame packed.");
    }
    else {
      frame.validate();
      Utils.logger.debug("Frame validated.");
    }
    //Center window
    Utils.logger.debug("Placing frame...");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    Utils.logger.debug("Setting the frame visible.");
    frame.setVisible(true);
  }

  private void loadServerProperties() {
    //String path = System.getProperty("user.dir") + System.getProperty("file.separator") + Constants.SERVER_PROPERTIES_FILE;
    //Utils.logger.debug("Loading server properties from file: " + path);
    //Utils.loadApplicationProperties(path);

    Utils.logger.debug("Sets WaitingPort");
    Utils.SetApplicationProperties("ServerWaitingPort","6290");
  }

  public void startServer() {

  }

  //main method
    public final static String SERVER_LOG_FILE = "./server.log";

  public static void InitLogs() {

    File file = new File(SERVER_LOG_FILE);
    if (file.exists()) file.delete();

    //PropertyConfigurator.configure("log4j.server.properties");

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
    log4jconf.setProperty("log4j.appender.R.File", SERVER_LOG_FILE);

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

    Utils.logger.debug("Logging enabled. Starting logging...");
  }

   public static void main(String[] args) {

     InitLogs();

    try {
      Utils.logger.debug("Getting and setting look and feel...");
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      Utils.logger.error("Error while loading look and feel: " + e.toString());
      e.printStackTrace();
    }

    Utils.logger.debug("Creating core object.");

    Server a = new Server(args);
  }
}
