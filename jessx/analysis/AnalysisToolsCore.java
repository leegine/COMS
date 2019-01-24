package jessx.analysis;

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
import jessx.analysis.gui.*;
import jessx.utils.*;

/***************************************************************/
/*                AnalysisToolCore CLASS SECTION               */
/***************************************************************/

/**
 * <p>Title: AnalysisToolCore</p>
 * <p>Description: This class contains the main class for the analysis package.
 * It instantiate all component of the core module: the frame, etc. And
 * it looks first for analysis module, and makes them register.</p>
 * @author Thierry Curtil
 * @version 1.0
 */
public class AnalysisToolsCore {

  /////////////////////////////////
  // package accessible fields
  boolean packFrame = false;

  public static Logger logger = Logger.getLogger(AnalysisToolsCore.class.
                                                 getName());

  private AnalysisStartingPopup popupFrame;

  //Construct the application
  public AnalysisToolsCore() {
    logger.info("Core initialisation...");

    this.showStartingPopup();

    //this.popupFrame.jLabel_progression.setText("Loading modules...");
    this.loadModules();

    //this.popupFrame.jLabel_progression.setText("Building frame...");
    logger.debug("Building frame...");
    CoreFrame frame = new CoreFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
      logger.debug("frame packed.");
    }
    else {
      frame.validate();
      logger.debug("frame validated.");
    }
    // Center the window
    logger.debug("Placing the window at the center of the screen.");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation( (screenSize.width - frameSize.width) / 2,
                      (screenSize.height - frameSize.height) / 2);

    frame.setVisible(true);
    popupFrame.setVisible(false);
  }

  public void showStartingPopup() {
    popupFrame = new AnalysisStartingPopup();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout

    popupFrame.pack();

    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = popupFrame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    popupFrame.setLocation( (screenSize.width - frameSize.width) / 2,
                           (screenSize.height - frameSize.height) / 2);
    popupFrame.setVisible(true);

  }

  public void loadModules() {
    String fileSep = System.getProperty("file.separator");
    logger.info("Looking for analysis modules in: " +
                System.getProperty("user.dir") + fileSep + "AnalysisTools" +
                fileSep);
    File analysisToolFolder = new File("." + fileSep + "AnalysisTools" +
                                       fileSep);
    if (analysisToolFolder != null) {
      String[] fileList = analysisToolFolder.list();
      if (fileList != null) {

        for (int i = 0; i < fileList.length; i++) {
          try {

            String fileName = "file:" + System.getProperty("user.dir") +
                fileSep
                + "AnalysisTools" + fileSep + fileList[i];
            if (fileList[i].endsWith(".jar")) {
              logger.debug("good file .jar");
              logger.debug("loading: " + fileName);
              JarClassLoader extLoader = new JarClassLoader(fileName);

              String className = "jessx.analysis.tools." +
                  fileList[i].substring(0, fileList[i].length() - 4);
              logger.debug("loading class: " + className);
              Class tempClass = extLoader.findClass(className);
              tempClass.newInstance();
            }
            else {
              logger.debug("bad file not .jar :" + fileList[i]);
            }
          }
          catch (Exception ex1) {
            logger.debug("Error while loading analysis module: " + ex1.toString());
            ex1.printStackTrace();
          }
        }
      }
    }
    logger.info("Finished loading modules.");
  }

  //Main method
  public static void main(String[] args) throws ClassNotFoundException {

    PropertyConfigurator.configure("log4j.analysis.properties");

    logger.info("Analyzer launched...");
    logger.debug("Logging parameters read.");
    try {
      logger.debug("Getting look and feel...");
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      logger.fatal("Error: " + e.toString());
      e.printStackTrace();
    }
    logger.debug(" done.");

    logger.debug("Creating the core object...");
    new AnalysisToolsCore();
  }
}
