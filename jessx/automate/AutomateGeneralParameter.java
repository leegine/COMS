package jessx.automate;

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

import java.util.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import org.jdom.*;
import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.Constants;
import jessx.utils.Constants;

/***************************************************************/
/*         AutomateGeneralParameter CLASS SECTION              */
/***************************************************************/

/**
 * <p>Titre : </p>
 *
 * <p>Description : </p>
 *
 * <p>Copyright : Copyright (c) 2005, Licence GPL, </p>
 *
 * <p>Société : Ec-lille, USTL</p>
 *
 * @author Guillaume Tromp
 * @version 1.0
 */
public class AutomateGeneralParameter implements GeneralParameters {

  String workingDirectory = new String(System.getProperty("user.dir") + "\\");
  String loggingFileName = new String("New Experiment Log");
  String setupFileName= new String("Setup File Name");
  private Vector listeners = new Vector();
  private int periodCount;
  private float interestRate;
  private int periodDuration;
  private boolean joiningAfterStartup;

  //All this first fonctions are here to make the class non abstract
  public JPanel getGeneralParameterSetupGui() {

    return null;
  }

  public void addPeriodCountChangeListener(ChangeListener listener) {
  }

  public void removePeriodCountChangeListener(ChangeListener listener) {
  }

  public void addPeriodDurationChangeListener(ChangeListener listener) {
  }

  public void removePeriodDurationChangeListener(ChangeListener listener) {
  }

  public String getXMLVersion() {
    return "";
  }

  public void setXMLVersion(String xmlVersion) {
  }

  public int getPeriodCount() {
    return periodCount;
  }

  public void setPeriodCount(int _periodCount) {
    periodCount=_periodCount;
  }

  public JSpinner getPeriodCountSpinner() {
    return null;
  }

  public JSpinner getPeriodDurationSpinner() {
    return null;
  }

  public float getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(float rate) {
    interestRate=rate;
  }

  public int getPeriodDuration() {
    return periodDuration;
  }

  public void setPeriodDuration(int duration) {
    periodDuration=duration;
  }

  public void initializeGeneralParameters() {
  }

  public void addChangeGeneralParametersListener(
    ChangeGeneralParametersListener listener) {
  this.listeners.add(listener);
}

  public String toString() {
    return "General Parameters";
  }

  public String getWorkingDirectory() {
    return this.workingDirectory;
  }

  public void setWorkingDirectory(String cwd) {
    this.workingDirectory = cwd;
  }

  public String getSetupFileName() {
    return this.setupFileName;
  }

  /**
   * Replace the current name of the setup file by one specified by the user.
   * @param setupFileName String
   */

  public void setSetupFileName(String setupFileName) {
    this.setupFileName= setupFileName;
  }

  /**
   * Return the name of the logging file.
   * @return String
   */

  public String getLoggingFileName() {
    return this.loggingFileName;
  }

  /**
   *
   * Replace the current name of the logging file by one specified by the
   * user.
   * @param name String
   */

  public void setLoggingFileName(String name) {
    this.loggingFileName = name;
  }

  /**
   * Return the number of periods of the session.
   * @return int
   */

  public boolean getAfterSetupJoiningAllowed() {
    return joiningAfterStartup;
  }

  /**
   * Allow or not to join after setup.
   * @param allowed boolean
   */
  public void setAfterSetupJoiningAllowed(boolean allowed) {
    joiningAfterStartup=allowed;
  }

  /**
   * Return the interest rate of the session.
   * @return float
   */

  public void saveToXml(Element node) {
    node.addContent(new Element("WorkingDirectory").setText(this.
        getWorkingDirectory()))
        .addContent(new Element("SetupFileName").setText(this.getSetupFileName()))
        .addContent(new Element("XMLVersion").setText(Constants.VERSION))
        .addContent(new Element("LoggingFileName").setText(this.
        getLoggingFileName()))
        .addContent(new Element("PeriodNumber").setText(Integer.toString(this.
        getPeriodCount())))
        .addContent(new Element("PeriodDuration").setText(Integer.toString(this.
        getPeriodDuration())))
        .addContent(new Element("InterestRate").setText(Float.toString(this.
        getInterestRate())))
        .addContent(new Element("JoiningAfterStartup").setText(Boolean.toString(this.
        getAfterSetupJoiningAllowed())));
    setXMLVersion(Constants.VERSION);
  }

  /**
   * Load the general parameters of the session.
   * @param node Element
   */
  public void loadFromXml(Element node) {
    this.setWorkingDirectory(node.getChild("WorkingDirectory").getText());
    this.setSetupFileName(node.getChild("SetupFileName").getText());
    if ( node.getChild("XMLVersion") != null) {
      setXMLVersion(node.getChild("XMLVersion").getText());
    }
      else setXMLVersion("This XML file has no version");


    this.setLoggingFileName(node.getChild("LoggingFileName").getText());
    this.setPeriodCount(Integer.parseInt(node.getChild("PeriodNumber").getText()));
    this.setPeriodDuration(Integer.parseInt(node.getChild("PeriodDuration").
                                            getText()));
    this.setInterestRate(Float.parseFloat(node.getChild("InterestRate").getText()));
    this.setAfterSetupJoiningAllowed(Boolean.getBoolean(node.getChild(
        "JoiningAfterStartup").getText()));
  }

  }
