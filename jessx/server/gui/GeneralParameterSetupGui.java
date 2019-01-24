package jessx.server.gui;

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
/*         GeneralParameterSetupGui CLASS SECTION              */
/***************************************************************/
/**
 * <p>Title : GeneralParameterSetupGui</p>
 * <p>Description : </p>
 * @author Thierry Curtil, Christophe Grosjean
 * @version 1.0
 */

public class GeneralParameterSetupGui extends JPanel implements DisplayableNode, GeneralParameters {
  String workingDirectory = new String(System.getProperty("user.dir") + "\\");
  String loggingFileName = new String("New Experiment Log");
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JSpinner jSpinner1 = new JSpinner();
  JLabel jLabelVersion = new JLabel("Version : ");
  JTextField jTextFieldVersion = new JTextField();
  //JLabel jLabel6 = new JLabel();
  //JCheckBox jCheckBox1 = new JCheckBox();
  JLabel jLabel7 = new JLabel();
  JSpinner jSpinner2 = new JSpinner();
  JLabel jLabel8 = new JLabel();
  JSpinner jSpinner3 = new JSpinner();
  private Vector listeners = new Vector();

  /**
   * Ask this panel to appear and warn the user if it can't.
   */
  public GeneralParameterSetupGui() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addChangeGeneralParametersListener(
      ChangeGeneralParametersListener listener) {
    this.listeners.add(listener);
  }

  public JPanel getGeneralParameterSetupGui() {
    return this;
  }

  /**
   * Return this panel.
   * @return JPanel
   */
  public JPanel getPanel() {
    return this;
  }

  /**
   * Return the string General Parameters.
   * @return String
   */
  public String toString() {
    return "General Parameters";
  }

  /**
   * Return the working directory.
   * @return String
   */
  public String getWorkingDirectory() {
    return this.workingDirectory;
  }

  /**
   * Replace the current working directory by one specified by the user.
   * @param cwd String
   */
  public void setWorkingDirectory(String cwd) {
    this.workingDirectory = cwd;
  }

  /**
   * Return the name of the setup file.
   * @return String
   */
  public String getSetupFileName() {
    return this.jTextField2.getText();
  }

  /**
   * Replace the current name of the setup file by one specified by the user.
   * @param setupFileName String
   */
  public void setSetupFileName(String setupFileName) {
    this.jTextField2.setText(setupFileName);
  }
  /**
   * Return the version of the XML Log.
   * @return String
   */
  public String getXMLVersion() {
   return jTextFieldVersion.getText();
 }
 /**Used to update the version of XML with the current version of the Server
  * @param xmlVersion String
  */
 public void setXMLVersion(String xmlVersion){
   jTextFieldVersion.setText(xmlVersion);
  }

  /**
   * Return the name of the logging file.
   * @return String
   */
  public String getLoggingFileName() {
    return this.loggingFileName;
  }

  /**
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
  public int getPeriodCount() {
    return new Integer(this.jSpinner1.getValue().toString()).intValue();
  }

  /**
   * Replace the current number of periods of the session by one specified by
   * the user.
   * @param periodCount int
   */
  public void setPeriodCount(int periodCount) {
    this.jSpinner1.setValue(new Integer(periodCount));
  }

  /**
   * Return if it is allowed to join after setup.
   * @return boolean
   */
  public boolean getAfterSetupJoiningAllowed() {
    return false;//this.jCheckBox1.isSelected();
  }

  /**
   * Allow or not to join after setup.
   * @param allowed boolean
   */
  public void setAfterSetupJoiningAllowed(boolean allowed) {
    //this.jCheckBox1.setSelected(allowed);
  }

  /**
   * Return the interest rate of the session.
   * @return float
   */
  public float getInterestRate() {
    return Float.parseFloat(jSpinner3.getValue().toString());
  }

  /**
   * Replace the current interest rate of the session by one specified by the
   * user.
   * @param rate float
   */
  public void setInterestRate(float rate) {
    this.jSpinner3.setValue(new Float(rate));
  }

  /**
   * Return the duration of a period of the session.
   * @return int
   */
  public int getPeriodDuration() {
    return new Integer(this.jSpinner2.getValue().toString()).intValue();
  }

  /**
   * Replace the duration of a period of the session by one specified by the
   * user.
   * @param duration int
   */
  public void setPeriodDuration(int duration) {

    this.jSpinner2.setValue(new Integer(duration));
  }

  /**
   * Add a change listener for the number of periods.
   * @param listener ChangeListener
   */
  public void addPeriodCountChangeListener(ChangeListener listener) {
    this.jSpinner1.addChangeListener(listener);
  }

  /**
   * Add a change listener for the duration of a period.
   * @param listener ChangeListener
   */
  public void addPeriodDurationChangeListener(ChangeListener listener) {
    this.jSpinner2.addChangeListener(listener);
  }

  /**
   * Remove a change listener for the number of periods.
   * @param listener ChangeListener
   */
  public void removePeriodCountChangeListener(ChangeListener listener) {
    this.jSpinner1.removeChangeListener(listener);
  }

  /**
   * Remove a change listener for the duration of a period.
   * @param listener ChangeListener
   */
  public void removePeriodDurationChangeListener(ChangeListener listener) {
    this.jSpinner2.removeChangeListener(listener);
  }

  /**
   * Initialize this panel.
   */
  public void initializeGeneralParameters() {
    this.jTextField2.setText("Title of the Experiment"); //Setup Parameters Experiment Name
    jSpinner3.setValue(new Integer(0)); //Interest rate
    jSpinner1.setValue(new Integer(1)); //Number of Periods
    this.jTextField2.setEditable(true); //Setup Parameters File Name
    this.jSpinner3.setEnabled(true); //Interest rate
    this.jSpinner1.setEnabled(true); //Number of Periods
    this.jSpinner1.setName("PeriodCountSpinner");
    this.jSpinner2.setName("PeriodDurationSpinner");
  }

  /**
   * Prevent this panel from being edited.
   */
  public void setUneditable() {
    this.jTextField2.setEditable(false); //Setup Parameters File Name
    this.jSpinner3.setEnabled(false); //Interest rate
    this.jSpinner1.setEnabled(false); //Number of Periods
    this.jSpinner2.setEnabled(false); //Period Length
    //this.jCheckBox1.setEnabled(false); //Joining the experiment is allowed
  }

  /**
   * Allow this panel to be edited.
   */
  public void setEditable() {
    this.jTextField2.setEditable(true); //Setup Parameters File Name
    this.jSpinner3.setEnabled(true); //Interest rate
    this.jSpinner1.setEnabled(true); //Number of Periods
    this.jSpinner2.setEnabled(true); //Period Length
    //this.jCheckBox1.setEnabled(false); //Joining the experiment is allowed (to be reviewed)
  }

  public JSpinner getPeriodCountSpinner() {
    return this.jSpinner1;
  }

  public JSpinner getPeriodDurationSpinner() {
    return this.jSpinner2;
  }

  /**
   * Display this panel.
   * @throws Exception
   */
  private void jbInit() throws Exception {
    this.setLayout(gridBagLayout1);
    jLabel2.setText("Experiment Title : ");
    jLabel5.setText("Number of Periods : ");
    //jLabel6.setText("After Startup : ");
    //jLabel6.setEnabled(false);
    //jCheckBox1.setText("Joining the experiment is allowed");
    //jCheckBox1.setEnabled(false);
    //SpinnerNumberModel(int value, int minimum, int maximum, int stepSize)
    jSpinner1.setModel(new SpinnerNumberModel(1, 1, 100, 1));
    jSpinner3.setModel(new SpinnerNumberModel(0, 0, 100, 0.01));
    jLabel7.setText("Period length (sec) :");
    jSpinner2.setValue(new Integer(1));
    jSpinner2.setModel(new SpinnerNumberModel(1, 1, 3600, 1));
    jLabel8.setText("Interest rate ( % ) :");
    this.initializeGeneralParameters();
    this.add(jLabel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.EAST,
                                             GridBagConstraints.NONE,
                                             new Insets(3, 6, 3, 3), 0, 0));
    this.add(jTextField2, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(3, 3, 3, 6), 0, 0));
    this.add(jLabel5, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.EAST,
                                             GridBagConstraints.NONE,
                                             new Insets(6, 6, 3, 3), 0, 0));
    this.add(jSpinner1, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(6, 3, 3, 6), 50, 0));
    /**this.add(jLabel6, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.EAST,
                                             GridBagConstraints.NONE,
                                             new Insets(6, 6, 6, 3), 0, 0));
    this.add(jCheckBox1, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(6, 3, 6, 6), 0, 0));
     */
    this.add(jLabel7, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.EAST,
                                             GridBagConstraints.NONE,
                                             new Insets(3, 3, 3, 6), 0, 0));
    this.add(jSpinner2, new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(3, 3, 3, 6), 34, 0));
    this.add(jLabel8, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
                                             , GridBagConstraints.EAST,
                                             GridBagConstraints.NONE,
                                             new Insets(3, 3, 6, 6), 0, 0));
    this.add(jSpinner3, new GridBagConstraints(1, 6, 1, 1, 1.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(3, 3, 6, 6), 50, 0));
    this.add(jLabelVersion, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                                   , GridBagConstraints.EAST,
                                                   GridBagConstraints.NONE,
                                                   new Insets(3, 6, 3, 3), 0, 0));
    jTextFieldVersion.setEditable(false);
    jTextFieldVersion.setText(Constants.VERSION);
    this.add(jTextFieldVersion, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.HORIZONTAL,
                                                 new Insets(3, 3, 3, 6), 0, 0));
    jSpinner1.addChangeListener(new
                                GeneralParameterSetupGui_jSpinner1_changeAdapter(this));
    jSpinner2.addChangeListener(new
                                GeneralParameterSetupGui_jSpinner2_changeAdapter(this));
  }

  /**
   * Save the general parameters of the session.
   * @param node Element
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

  private void firejSpinner1StateChanged() {
    for (int i = 0; i < listeners.size(); i++) {
      ( (ChangeGeneralParametersListener)this.listeners.elementAt(i)).
          generalParametersChanged(new ChangeGeneralParametersEvent(this.
          jSpinner1));
    }
  }

  private void firejSpinner2StateChanged() {
    for (int i = 0; i < listeners.size(); i++) {
      ( (ChangeGeneralParametersListener)this.listeners.elementAt(i)).
          generalParametersChanged(new ChangeGeneralParametersEvent(this.
          jSpinner2));
    }
  }

  public void jSpinner1_stateChanged(ChangeEvent e) {
    firejSpinner1StateChanged();
  }

  public void jSpinner2_stateChanged(ChangeEvent e) {
      firejSpinner2StateChanged();
    }
}

  /***************************************************************/
  /*                   EVENT CLASSES SECTION                     */
  /***************************************************************/
  class GeneralParameterSetupGui_jSpinner1_changeAdapter
      implements ChangeListener {
    private GeneralParameterSetupGui adaptee;
    GeneralParameterSetupGui_jSpinner1_changeAdapter(GeneralParameterSetupGui
        adaptee) {
      this.adaptee = adaptee;
    }

    public void stateChanged(ChangeEvent e) {
      adaptee.jSpinner1_stateChanged(e);
    }
  }

  class GeneralParameterSetupGui_jSpinner2_changeAdapter
      implements ChangeListener {
    private GeneralParameterSetupGui adaptee;
    GeneralParameterSetupGui_jSpinner2_changeAdapter(GeneralParameterSetupGui
        adaptee) {
      this.adaptee = adaptee;
    }

    public void stateChanged(ChangeEvent e) {
      adaptee.jSpinner2_stateChanged(e);
    }
  }

