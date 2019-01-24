package jessx.analysis.gui;

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
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.jdom.*;
import org.jdom.input.*;
import jessx.analysis.*;
import jessx.analysis.tools.*;
import jessx.utils.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

/***************************************************************/
/*                  CoreFrame CLASS SECTION                    */
/***************************************************************/

/**
 * <p>Title: CoreFrame</p>
 * <p>Description: This class is the frame of the analysis module.
 * Its purpose is to give the user a graphical interface to choose which
 * module he wants to analyse his data with, launch the analysis process and
 * display results.</p>
 * @author Thierry Curtil ; Mohamed Amine Hamamouchi; Christophe Grosjean
 * @version 1.0
 * v0.8: upgrade the interface so that long tools name are not troncated in
 * checkbox.
 * v0.9: Add a tab level: one where new analysis create a tab. And in this tab pane,
 * there should be a tab for each used tool.
 * v1.0: an about box with a link to a tutorial explaining how to add / program
 * analysis modules.
 */

public class CoreFrame
    extends JFrame implements Constants {

  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JLabel statusBar = new JLabel();
  JTabbedPane jTabbedPane = new JTabbedPane();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JMenuItem jMenuItemExport = new JMenuItem();
  JMenuItem jMenuItemAnalyse = new JMenuItem();
  JPanel jPanel2 = new JPanel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();

  /**
   * This hashmap contains all dynamically added checkboxes (one for each found
   * tool)
   */
  private HashMap checkboxList = new HashMap();

  /** Construct the frame
   */

  public CoreFrame() {
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      AnalysisToolsCore.logger.debug("Frame initialization...");
      jbInit();
    }
    catch (Exception e) {
      AnalysisToolsCore.logger.fatal(
          "Something went wrong during frame initialisation: " + e.toString());
      e.printStackTrace();
    }
  }

  /**Component initialization
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {
    this.setIconImage(new ImageIcon("./images/logo_JessX_small.GIF").getImage());

    AnalysisToolsCore.logger.debug("Content pane initialisation...");
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(gridBagLayout1);
    this.setSize(new Dimension(400, 300));
    this.setTitle("JessX Analyzer "+Constants.VERSION);
    statusBar.setText(" ");

    AnalysisToolsCore.logger.debug("Menu items initialisation");
    jMenuFile.setMnemonic(KeyEvent.VK_F);
    jMenuFile.setText("File");
    jMenuFileExit.setMnemonic(KeyEvent.VK_X);
    jMenuFileExit.setText("Exit");
    jMenuFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,Event.ALT_MASK));
    jMenuFileExit.addActionListener(new CoreFrame_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setMnemonic(KeyEvent.VK_H);
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setMnemonic(KeyEvent.VK_A);
    jMenuHelpAbout.setText("About JessX...");
    jMenuHelpAbout.addActionListener(new CoreFrame_jMenuHelpAbout_ActionAdapter(this));
    jMenuItemAnalyse.setMnemonic(KeyEvent.VK_O);
    jMenuItemAnalyse.setText("Open results...");
    jMenuItemAnalyse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
        Event.CTRL_MASK));
    jMenuItemAnalyse.setToolTipText(
        "Click here to analyse the results of the experiment.");
    jMenuItemAnalyse.addActionListener(new
                                       CoreFrame_jMenuItemAnalyse_actionAdapter(this));
    jMenuItemExport.setMnemonic(KeyEvent.VK_E);
    jMenuItemExport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
        Event.CTRL_MASK));
    jMenuItemExport.setText("Export results...");
    jMenuItemExport.setToolTipText(
        "Click here to export the results of the experiment.");
    jMenuItemExport.addActionListener(new
                                      CoreFrame_jMenuItemExport_actionAdapter(this));
    jPanel2.setLayout(gridBagLayout2);
    jPanel2.setMinimumSize(new Dimension(100, 0));
    jPanel2.setToolTipText("Choose an option.");
    jTabbedPane.setTabPlacement(JTabbedPane.LEFT);

    jMenuFile.add(jMenuItemAnalyse);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuItemExport);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(statusBar, new GridBagConstraints(0, 2, 2, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(0, 0, 0, 0), 0, 0));
    contentPane.add(jTabbedPane, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 3, 3, 3), 0, 0));
    contentPane.add(jPanel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0
        , GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
        new Insets(0, 0, 0, 0), 0, 0));

    AnalysisToolsCore.logger.debug(
        "Initiating about box with available modules...");
    Enumeration analysisToolsIterator = AnalysisToolCreator.analyseFactories.
        keys();
    int toolNum = 0;
    while (analysisToolsIterator.hasMoreElements()) {
      String key = (String) analysisToolsIterator.nextElement();
      AnalysisTool tempTool = null;
      try {

        tempTool = AnalysisToolCreator.createTool(key);
        JCheckBox jCheckBox = new JCheckBox(tempTool.getToolName());
        jCheckBox.setToolTipText(tempTool.getToolDescription());
        AnalysisToolsCore.logger.debug("Adding " + tempTool.getToolName() +
                                       " tool to interface.");
        jCheckBox.setSelected(true);
        this.checkboxList.put(tempTool.getToolName(), jCheckBox);
        jPanel2.add(jCheckBox,
                    new GridBagConstraints(0, toolNum, 1, 1, 0.0, 0.0
                                           , GridBagConstraints.NORTHWEST,
                                           GridBagConstraints.NONE,
                                           new Insets(3, 3, 3, 3), 0, 0));
        AnalysisToolsCore.logger.debug("following tool added to interface: " +
                                       tempTool.getToolName());

        toolNum++;
      }
      catch (AnalysisToolNotCreatedException ex1) {
        ex1.printStackTrace();
      }
    }
    jPanel2.setBorder( (Border)  new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(156, 156, 158)), "Available modules"));
  }

  /**
   * This function enables to close the analyzer
   * In the menu : File | Exit action performed
   * @param e ActionEvent which is send to close the system
   */
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    AnalysisToolsCore.logger.debug("Exiting program on user prompt. (menu|exit)");
      if (JOptionPane.showConfirmDialog(this,
                                      "Do you really want to quit JessX Analyzer?",
                                      "JessX Analyzer",
                                      JOptionPane.OK_CANCEL_OPTION,
                                      JOptionPane.OK_CANCEL_OPTION) ==
        JOptionPane.OK_OPTION)
      System.exit(0);
  }

  /**
   * This function display the About box with some explanations (authors of each tool...)
   * In the menu : Help | About action performed
   * @param e ActionEvent which is send to display the About box
   */

  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    AnalysisAboutBox dlg = new AnalysisAboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }

  void jMenuItemAnalyse_actionPerformed(ActionEvent e) {

    //builds a File Chooser
    AnalysisToolsCore.logger.info("Beginning analyse process.");
    AnalysisToolsCore.logger.debug("Asking user a file to analyse.");
    JFileChooser chooser = Utils.newFileChooser(new String("."),
                                                new String(""),
                                                LOG_DIALOG_FILTER_DESCRIPTION,
                                                LOG_DIALOG_FILTER_EXTENSION);

    //calls it
    int returnVal = chooser.showOpenDialog(this);
    File file;

    //some file selected ?
    if (returnVal == JFileChooser.APPROVE_OPTION) {

      //get the selected file
      AnalysisToolsCore.logger.info("A file was selected: " +
                                    chooser.getSelectedFile());
      file = chooser.getSelectedFile();
      if (file.exists() == false) {
        AnalysisToolsCore.logger.info(
            "File selected doesn't exist. Stopping analyse process.");
        JOptionPane.showMessageDialog(this, "No file selected.", "Error",
                                      JOptionPane.WARNING_MESSAGE);
        return;
      }
      if (file.getName().endsWith("xml") == false) {
        AnalysisToolsCore.logger.info("File choosen is incorrect(" +
                                      file.getName() +
                                      "). Stopping analyse process.");
        JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                      "Error", JOptionPane.WARNING_MESSAGE);
        return;
      }

    }
    else {
      AnalysisToolsCore.logger.info(
          "No file selected. Stopping analyse process.");
      return;
    }

    // Creating a tabbed pane for the tools to add some panels...
    JTabbedPane tempTabbedPane = new JTabbedPane(JTabbedPane.TOP);

    Document xmlLog;
    SAXBuilder sax = new SAXBuilder();
    try {
      AnalysisToolsCore.logger.debug("Reading the xml file...");
      xmlLog = sax.build(file);
    }
    catch (JDOMException ex) {
      AnalysisToolsCore.logger.fatal("Something went wrong during reading: " +
                                     ex.toString() +
                                     ". Stopping analyse process.");
      JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                    "Error", JOptionPane.WARNING_MESSAGE);
      return;
    }
    catch (IOException ex) {
      AnalysisToolsCore.logger.fatal("Something went wrong during reading: " +
                                     ex.toString() +
                                     ". Stopping analyse process.");
      ex.printStackTrace();
      return;
    }

    AnalysisToolsCore.logger.debug("Got the xml document.");
    AnalysisToolsCore.logger.debug("Preparing analysis tools...");
    Enumeration analysisToolsIterator = AnalysisToolCreator.analyseFactories.
        keys();
    try {
      while (analysisToolsIterator.hasMoreElements()) {
        String key = (String) analysisToolsIterator.nextElement();
        AnalysisTool tempTool = null;
        try {
          tempTool = AnalysisToolCreator.createTool(key);
          if ( ( (JCheckBox)this.checkboxList.get(tempTool.getToolName())).
              isSelected()) {
            tempTool.setDocument(xmlLog);
            AnalysisToolsCore.logger.debug(
                "Analysing the xml with the following tool: " +
                tempTool.getToolName());
            tempTabbedPane.add(tempTool.drawGraph(), tempTool.getToolName());
          }
        }
        catch (AnalysisToolNotCreatedException ex1) {
          AnalysisToolsCore.logger.fatal(
              "Something went wrong when trying to use to following tool: " +
              key + ": " + ex1.toString());
          ex1.printStackTrace();
        }
      }
      // Adding a new tabbed pane for this analysis.
      this.jTabbedPane.add(tempTabbedPane,
                           "Analysis " + (this.jTabbedPane.getTabCount() + 1));
      this.jTabbedPane.setSelectedIndex(this.jTabbedPane.getTabCount() - 1);
    }
    catch (Exception ex1) {
      JOptionPane.showMessageDialog(this, "File incorrect for those analysis.",
                                    "error", JOptionPane.WARNING_MESSAGE);
    }
  }

  /**
   * This function enables to export data save in a suitable XML document.
   * First you choose an XML document in a JFileChooser and the function calls
   * the class XMLExportation which uses the document and create a new file
   * with the new datas.
   * In the menu : File | Export Results
   * @see XMLExportation
   * @param e ActionEvent which is send to start the process
   */
  void jMenuItemExport_actionPerformed(ActionEvent e) {

    //builds a File Chooser
    AnalysisToolsCore.logger.info("Beginning exportation");
    AnalysisToolsCore.logger.debug("Asking user a file to export");
    JFileChooser chooser = Utils.newFileChooser(new String("."),
                                                new String(""),
                                                LOG_DIALOG_FILTER_DESCRIPTION,
                                                LOG_DIALOG_FILTER_EXTENSION);

    //calls it
    int returnVal = chooser.showOpenDialog(this);
    File file;

    //some file selected ?
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      //get the selected file
      AnalysisToolsCore.logger.info("A file was selected: " +
                                    chooser.getSelectedFile());
      file = chooser.getSelectedFile();
      //check if it's a suitable file or not
      if (file.exists() == false) {
        AnalysisToolsCore.logger.info(
            "File selected doesn't exist. Stopping analyse process.");
        JOptionPane.showMessageDialog(this, "No file selected.", "Error",
                                      JOptionPane.WARNING_MESSAGE);

        return;
      }
      //check if it's a suitable file or not
      if (file.getName().endsWith("xml") == false) {
        AnalysisToolsCore.logger.info("File choosen is incorrect(" +
                                      file.getName() +
                                      "). Stopping analyse process.");
        JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                      "Error", JOptionPane.WARNING_MESSAGE);
        return;
      }

    }

    else {
      AnalysisToolsCore.logger.info(
          "No file selected. Stopping analyse process.");
      return;
    }

    Document xmlLog;
    SAXBuilder sax = new SAXBuilder();
    try {
      AnalysisToolsCore.logger.debug("Reading the xml file...");
      xmlLog = sax.build(file);
      AnalysisToolsCore.logger.debug("Got the xml document.");
      new XMLExportation(xmlLog, this);
    }
    catch (JDOMException ex) {
      AnalysisToolsCore.logger.error("Something went wrong during reading: " +
                                     ex.toString() +
                                     ". Stopping analyse process.");
      JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                    "Error", JOptionPane.WARNING_MESSAGE);
      return;
    }
    catch (IOException ex) {
      AnalysisToolsCore.logger.error("Something went wrong during reading: " +
                                     ex.toString() +
                                     ". Stopping analyse process.");
      ex.printStackTrace();
      JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                    "Error", JOptionPane.WARNING_MESSAGE);
      return;
    }
  }
}

/***************************************************************/
/*                   EVENT CLASSES SECTION                     */
/***************************************************************/

class CoreFrame_jMenuFileExit_ActionAdapter
    implements ActionListener {
  CoreFrame adaptee;

  CoreFrame_jMenuFileExit_ActionAdapter(CoreFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class CoreFrame_jMenuHelpAbout_ActionAdapter
    implements ActionListener {
  CoreFrame adaptee;

  CoreFrame_jMenuHelpAbout_ActionAdapter(CoreFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class CoreFrame_jMenuItemAnalyse_actionAdapter
    implements java.awt.event.ActionListener {
  CoreFrame adaptee;

  CoreFrame_jMenuItemAnalyse_actionAdapter(CoreFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemAnalyse_actionPerformed(e);
  }
}

class CoreFrame_jMenuItemExport_actionAdapter
    implements ActionListener {
  CoreFrame adaptee;
  CoreFrame_jMenuItemExport_actionAdapter(CoreFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemExport_actionPerformed(e);
  }
}
