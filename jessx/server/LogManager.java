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

import javax.swing.*;

import org.jdom.*;
import jessx.business.*;
import jessx.server.net.*;
import jessx.server.net.event.*;
import jessx.utils.*;

/***************************************************************/
/*                  LogManager CLASS SECTION                   */
/***************************************************************/
/**
 * <p>Title : LogManager</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class LogManager implements ExperimentStateListener {

  private Document experimentDoc;
  private Element currentPeriodNode;
  private Element experimentLogNode;

  /**
   * Call this method before any call to changeOfPeriod() or log(). You don't
   * need (and should not) call changeOfPeriod just after initing the logging:
   * it's ready for the first period.
   */
  private void initLogging() {
    this.experimentLogNode = new Element("Experiment");
    Element setupNode = new Element("Setup");
    BusinessCore.saveToXml(setupNode);
    experimentLogNode.addContent(setupNode);
    this.experimentDoc = new Document(this.experimentLogNode);

    currentPeriodNode = new Element("Period");
    currentPeriodNode.setAttribute("num", "0");
    this.experimentLogNode.addContent(currentPeriodNode);

  }

  private void changeOfPeriod() {
    if (Integer.parseInt(currentPeriodNode.getAttributeValue("num")) != NetworkCore.getExperimentManager().getPeriodNum()) {
      currentPeriodNode = new Element("Period");
      currentPeriodNode.setAttribute("num",Integer.toString(NetworkCore.getExperimentManager().getPeriodNum()));
      this.experimentLogNode.addContent(currentPeriodNode);
    }
  }

  public void log(Element toLog) {
    this.currentPeriodNode.addContent(toLog);
  }

  public void logPortfoliosendOfPeriod(Element portfolios) {
    this.experimentLogNode.addContent(portfolios);
  }

  public LogManager() {
    NetworkCore.getExperimentManager().addExperimentStateListener(this);
  }

  public void saveLogToFile() {
    // - getting the filename where to save the xml doc: a concatenation of the
    // - working directory and the filename given in the general parameters panel

    String pwd = BusinessCore.getGeneralParameters().getWorkingDirectory();
    String fileName = BusinessCore.getGeneralParameters().getLoggingFileName();

    if ((pwd == null) || (pwd.equalsIgnoreCase("")) ||
        (fileName == null) || (fileName.equalsIgnoreCase(""))) {
      Utils.logger.warn("The logging fileName or the working directory are not defined. [logging to log.xml]");
      return;
    }

    // if the working directory does not end with the file separator, we add it.
    if (!pwd.substring(pwd.length()-1).equals(System.getProperty("file.separator"))) {
      pwd += System.getProperty("file.separator");
    }

    // check if the fileName ends with .xml, and add it if not
    if ((fileName.length() < 5) || (!fileName.substring(fileName.length() - 4).equalsIgnoreCase(".xml"))) {
      fileName += ".xml";
    }

    try {
      Utils.logger.debug("Saving xml setup file to : " + pwd + fileName);
      Utils.saveXmlDocument(pwd + fileName, experimentDoc);
    }
    catch (Exception ex) {
      Utils.logger.error("Error while saving xml document. " + ex.toString());
      JOptionPane.showConfirmDialog(null,"Error occured during writing of the xml document:\n" + ex.toString(),"Error: unable to write xml document",JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

  public void experimentStateChanged(int newState) {
    if (newState != ExperimentManager.EXP_OFF) {
      if ( (this.experimentLogNode == null) ||
          (NetworkCore.getExperimentManager().getPeriodNum() == -1)) {
        this.initLogging();
      }

      if ((NetworkCore.getExperimentManager().getPeriodNum() != -1)) {
        this.changeOfPeriod();
      }
    }
  }
}
