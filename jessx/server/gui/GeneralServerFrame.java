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

import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;

import org.jdom.*;
import org.jdom.output.*;
import jessx.business.*;
import jessx.business.event.*;
import jessx.net.*;
import jessx.server.*;
import jessx.server.net.*;
import jessx.utils.*;
import jessx.utils.gui.*;

/***************************************************************/
/*                  ServerFrame CLASS SECTION                  */
/***************************************************************/

/**
 * <p>Title: ServerFrame</p>
 * <p>Description : This class contains the graphical interface of the<br />
 * server application.<br />
 * @author Thierry Curtil, Christophe Grosjean, Mohamed Amine Hamamouchi, Charles Montez.
 * @todo Essayer d'initialiser les clients depuis le serveur. Partiellement fait maiss ne fonctionne pas complètement.
 * Il faut réactiver l'onglet (initialize clients).
 */

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
 * @author non attribuable
 * @version 1.0
 */
public class GeneralServerFrame extends JFrame implements Constants,PlayerTypeListener, PlayerStateListener, PlayerListener {

  boolean hasAlreadySaved = false;

  JPanel contentPane;
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JTree jTree1;
  private ExperimentSetupTree treeModel;
  private ServerFrame_jTree1_mouseAdapter TreemouseAdapter;

  JPanel jPanel2 = new JPanel();
  JPanel jPanelTimer = new JPanel();
  JSplitPane jSplitPane1 = new JSplitPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JMenuBar menuBar = new JMenuBar();

  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuItemLoad = new JMenuItem();
  JMenuItem jMenuItemSave = new JMenuItem();
  JMenuItem jMenuItemQuickSave = new JMenuItem();
  JMenuItem jMenuItemExit = new JMenuItem();
  JMenuItem jMenuItemClear = new JMenuItem();
  JMenu jMenuExperiment = new JMenu();
  JMenuItem jMenuItemOnline = new JMenuItem();
  JMenuItem jMenuItemBegin = new JMenuItem();
  JMenuItem jMenuItemNewExperiment = new JMenuItem();
  JCheckBoxMenuItem jAutomaticallyContinue = new JCheckBoxMenuItem();


  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpTutorial = new JMenuItem();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JPanel jPanel1 = new JPanel();
  CommunicationGui jPanelCommunication = new CommunicationGui();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  Border border1;
  Border border2;
  JScrollPane jScrollPane3 = new JScrollPane();
  JTable jTable1 = new JTable();
  JComboBox playerTypeComboBox = new JComboBox();
  JButton jButton1 = new JButton();
  JTextField jTextFieldNumberOfPlayers = new JTextField("0");

  ServerTimer timer = new ServerTimer(jButton1,jTextField2);

  TableModelPlayersStatus tableModelPlayersStatus = new TableModelPlayersStatus();
  JMenuItem jMenuItem1 = new JMenuItem();


  public GeneralServerFrame() {
    timer.start();
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    Iterator iter = BusinessCore.getScenario().getPlayerTypes().keySet().iterator();
    while(iter.hasNext()) {
      this.playerTypeComboBox.addItem((String)iter.next());
    }
    BusinessCore.getScenario().addPlayerTypeListener(this);
    NetworkCore.addListener(this);
  }

  /**
   *
   * @param e PlayerTypeEvent
   */
  public void playerTypeModified(PlayerTypeEvent e) {
    if (e.getEvent() == PlayerTypeEvent.PLAYER_ADDED) {
      this.playerTypeComboBox.addItem(e.getPlayerType().getPlayerTypeName());
    }
    else if (e.getEvent() == PlayerTypeEvent.PLAYER_REMOVED) {
      this.playerTypeComboBox.removeItem(e.getPlayerType().getPlayerTypeName());
    }
    if (this.playerTypeComboBox.getItemCount() == 1)
      this.tableModelPlayersStatus.setDefaultPlayerCategory(e.getPlayerType().
          getPlayerTypeName());
  }

  /**
   * This function is called when a playerEvent is send
   * @param e PlayerEvent
   */
  public void playerListModified(PlayerEvent e) {
    if (e.getEvent() == PlayerEvent.PLAYER_ADDED) {
      NetworkCore.getPlayer(e.getPlayerName()).addListener(this);
    }
    this.jTextFieldNumberOfPlayers.setText(""+NetworkCore.getPlayerList().size());
  }


  void jbInit() throws Exception {
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    this.setIconImage(new ImageIcon("./images/logo_JessX_small.GIF").getImage());

    border1 = BorderFactory.createLineBorder(Color.black, 1);
    border2 = BorderFactory.createLineBorder(Color.black, 1);
    this.setResizable(true);
    this.setTitle("JessX server "+Constants.VERSION+" - "+"New Experiment");

    this.contentPane = (JPanel)this.getContentPane();
    this.contentPane.setLayout(gridBagLayout2);
    this.contentPane.setBorder(null);
    contentPane.setEnabled(true);
    contentPane.setMinimumSize(new Dimension(640, 480));
    contentPane.setPreferredSize(new Dimension(800, 600));
    this.jTable1 = new JTable(tableModelPlayersStatus);
    TableColumn playerTypeColumn = this.jTable1.getColumnModel().getColumn(1);
    playerTypeColumn.setCellEditor(new DefaultCellEditor(this.playerTypeComboBox));
    this.jTable1.getColumnModel().getColumn(3).setCellRenderer(new JLabelRenderer());
    this.jTable1.getColumnModel().getColumn(4).setCellRenderer(new JLabelRenderer());

    this.jTable1.getColumnModel().getColumn(5).setCellRenderer(new JButtonRenderer());
    this.jTable1.getColumnModel().getColumn(5).setCellEditor(new JButtonEditor2(tableModelPlayersStatus,this));


    this.jTable1.setCellSelectionEnabled(false);
    this.jTable1.setRowSelectionAllowed(false);
    this.jTable1.setColumnSelectionAllowed(false);

    treeModel = new ExperimentSetupTree(new DefaultMutableTreeNode(
        "Experiment"));
    this.jTree1 = new JTree(treeModel);

    this.jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

    jMenuFile.setText("File");
    jMenuFile.setMnemonic(KeyEvent.VK_F);
    jMenuItemLoad.setText("Open an experiment...");
    jMenuItemLoad.setMnemonic(KeyEvent.VK_O);
    jMenuItemLoad.addActionListener(new ServerFrame_jMenuItemLoad_actionAdapter(this));
    jMenuItemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));

    jMenuItemSave.setText("Save the experiment As...");
    jMenuItemSave.setMnemonic(KeyEvent.VK_A);
    jMenuItemSave.addActionListener(new ServerFrame_jMenuItemSave_actionAdapter(this));

    jMenuItemQuickSave.setText("Save the experiment");
    jMenuItemQuickSave.setMnemonic(KeyEvent.VK_S);
    jMenuItemQuickSave.addActionListener(new ServerFrame_jMenuItemQuickSave_actionAdapter(this));
    jMenuItemQuickSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));

    jMenuItemExit.setText("Exit");
    jMenuItemExit.setMnemonic(KeyEvent.VK_X);
    jMenuItemExit.addActionListener(new ServerFrame_jMenuItemExit_actionAdapter(this));
    jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK));

    jMenuItemClear.setText("New experiment");
    jMenuItemClear.setMnemonic(KeyEvent.VK_N);
    jMenuItemClear.addActionListener(new ServerFrame_jMenuItemClear_actionAdapter(this));
    jMenuItemClear.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));

    menuBar.setMargin(new Insets(3, 3, 3, 3));

    jMenuExperiment.setText("Experiment");
    jMenuExperiment.setMnemonic(KeyEvent.VK_E);
    jMenuItemOnline.setText("Host a session");
    jMenuItemOnline.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_H, Event.CTRL_MASK));
    jMenuItemOnline.setMnemonic(KeyEvent.VK_H);
    jMenuItemOnline.addActionListener(new ServerFrame_jMenuItemOnline_actionAdapter(this));
    jMenuItemBegin.setText("Begin the session");
    jMenuItemBegin.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
    jMenuItemBegin.setMnemonic(KeyEvent.VK_B);
    jMenuItemBegin.addActionListener(new ServerFrame_jMenuItemBegin_actionAdapter(this));
    jMenuItemBegin.setEnabled(false);
    jAutomaticallyContinue.setText("Start next period automatically");
    jAutomaticallyContinue.setMnemonic(KeyEvent.VK_S);
    jAutomaticallyContinue.setSelected(true);
    jMenuItemNewExperiment.setText("Initialize Clients");
    jMenuItemNewExperiment.setMnemonic(KeyEvent.VK_I);
    jMenuItemNewExperiment.addActionListener(new ServerFrame_jMenuItemNewExperiment_actionAdapter(this));

    jMenuHelp.setText("Help");
    jMenuHelp.setMnemonic(KeyEvent.VK_H);
    jMenuHelpAbout.setText("About JessX...");
    jMenuHelpTutorial.setText("Tutorial");
    jMenuHelpAbout.setMnemonic(KeyEvent.VK_A);
    jMenuHelpTutorial.setMnemonic(KeyEvent.VK_T);
    jMenuHelpAbout.addActionListener(new
                                     ServerFrame_jMenuHelpAbout_actionAdapter(this));
    jMenuHelpTutorial.addActionListener(new
        ServerFrame_jMenuHelpTutorial_actionAdapter(this));
    jPanel1.setLayout(gridBagLayout3);
    jLabel1.setText("Server ip / Version java :");
    jTextField1.setBorder(border2);
    jTextField1.setDoubleBuffered(false);
    jTextField1.setPreferredSize(new Dimension(2, 20));
    jTextField1.setEditable(false);
    jTextField1.setMargin(new Insets(2, 10, 2, 10));
    jTextField1.setText(NetworkCore.getConnectionPoint().getIpAddressAndJavaVersion());
    jLabel2.setText("Experiment Status : ");
    jLabel3.setText("Connected players :");
    jTextField2.setEnabled(true);
    jTextField2.setText("Experiment OFF");
    jTextField2.setBorder(border2);
    jTextField2.setPreferredSize(new Dimension(74, 20));
    jTextField2.setEditable(false);
    jTextField2.setMargin(new Insets(2, 10, 2, 10));
    jButton1.setEnabled(false);
    jButton1.addActionListener(new ServerFrame_jButton1_actionAdapter(this));
    jButton1.setText("Experiment off");
    jTextFieldNumberOfPlayers.setEnabled(true);
    jTextFieldNumberOfPlayers.setBorder(border2);
    jTextFieldNumberOfPlayers.setMargin(new Insets(2, 10, 2, 10));
    jTextFieldNumberOfPlayers.setPreferredSize(new Dimension(74, 20));
    jTextFieldNumberOfPlayers.setEditable(false);

    TreemouseAdapter = new ServerFrame_jTree1_mouseAdapter(this.jTree1,
        this.jPanel2);
    jTree1.addMouseListener(TreemouseAdapter);
    jMenuItem1.setText("Get client logs");
    jMenuItem1.setMnemonic(KeyEvent.VK_G);
    jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
    jMenuItem1.addActionListener(new ServerFrame_jMenuItem1_actionAdapter(this));
    jSplitPane1.add(jScrollPane2, JSplitPane.LEFT);
    jSplitPane1.add(jPanel2, JSplitPane.RIGHT);
    jSplitPane1.setDividerLocation(200);

    jTabbedPane1.add(jSplitPane1, "Settings");

    jTabbedPane1.add(jPanelCommunication, "Chat");

    jTabbedPane1.add(jPanel1, "Server Status");

    jPanel1.add(jLabel1,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(6, 6, 3, 3), 0, 0));
    jPanel1.add(jTextField1,     new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(6, 3, 3, 6), 0, 0));
    jPanel1.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE, new Insets(3, 6, 3, 3), 0, 0));

    jPanel1.add(jTextField2,    new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 6), 0, 0));
    jPanel1.add(jLabel3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,  new Insets(3, 6, 3, 3), 0, 0));
    jPanel1.add(jTextFieldNumberOfPlayers,    new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(3, 3, 3, 6), 0, 0));
    jPanel1.add(jScrollPane3,    new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 6, 3, 6), 0, 0));

    jScrollPane3.getViewport().add(jTable1, null);

    jScrollPane2.getViewport().add(jTree1, null);


    contentPane.add(jTabbedPane1,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    jPanelTimer.add(jButton1,   new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(3, 6, 6, 6), 0, 0));
    contentPane.add(jPanelTimer,  new GridBagConstraints(0, 1, 0, 0, 0, 0
                ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
    menuBar.add(jMenuFile);
    menuBar.add(jMenuExperiment);

    menuBar.add(jMenuHelp);

    jMenuFile.add(jMenuItemClear);
    jMenuFile.add(jMenuItemLoad);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuItemQuickSave);
    jMenuFile.add(jMenuItemSave);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuItemExit);

    jMenuExperiment.add(jMenuItemOnline);
    jMenuExperiment.add(jMenuItemBegin);

    //jMenuExperiment.add(this.jMenuItemNewExperiment);
    jMenuExperiment.addSeparator();
    jMenuExperiment.add(jMenuItem1);
    jMenuExperiment.addSeparator();
    jMenuExperiment.add(jAutomaticallyContinue);

    jMenuHelp.add(jMenuHelpTutorial);
    jMenuHelp.addSeparator();
    jMenuHelp.add(jMenuHelpAbout);

    this.setJMenuBar(menuBar);
  }

  /**
   *
   * @param login String
   */
  public void playerStateChanged(String login) {
    if (NetworkCore.getPlayer(login).getPlayerStatus() ==
        Player.CLIENT_STATUS_CONNECTED||NetworkCore.getPlayerList().size()!= 1) {
      Iterator iter = NetworkCore.getPlayerList().keySet().iterator();
      boolean allPlayerReady = true;
      while (iter.hasNext() && allPlayerReady) {
        allPlayerReady =
            allPlayerReady &&
            (NetworkCore.getPlayer( (String) iter.next()).getPlayerState() ==
             Player.CLIENT_READY);
      }

      if (allPlayerReady &&
          (NetworkCore.getExperimentManager().getPeriodNum() == -1)) {
        NetworkCore.getExperimentManager().beginNewPeriod();
      }

      if (allPlayerReady &&
          (NetworkCore.getExperimentManager().getExperimentState() ==
           ExperimentManager.EXP_ON_PER_OFF)) {
        this.jButton1.setText("Begin next period");
        this.jButton1.setEnabled(true);
        if (jAutomaticallyContinue.isSelected()) {
          jButton1.doClick(0);
        }
      }
    }
  }

  //***********************************************************//
  //*******       ServerFrame interface methods         *******//
  //***********************************************************//

  /**
   * This method will display a message into the logging textBox on the interface.
   * @param message String the message
   */
  public void displayInfoMessage(String message) {
 //   this.jTextArea1.append(message + "\n");
  }


  //***********************************************************//

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      Quit_actionPerformed(null);
    }
  }

  void Quit_actionPerformed(ActionEvent e) {
    if (JOptionPane.showConfirmDialog(this,
                                      "Do you really want to quit JessX Server?",
                                      "JessX Server",
                                      JOptionPane.OK_CANCEL_OPTION,
                                      JOptionPane.OK_CANCEL_OPTION) ==
        JOptionPane.OK_OPTION)
      System.exit(0);
  }

  /**Method called when a Save of the Setup Parameters File is tried
   * @param e ActionEvent
   * @version 1.0
   */
  void jMenuItemSave_actionPerformed(ActionEvent e) {
    // - getting the filename where to save the xml doc: a concatenation of the
    // - working directory and the filename given in the general parameters panel

    try {
      FileChooserSave fileChooserSave = new FileChooserSave(createExperimentSetupXmlDocument(), this,"JessX Server", "xml");
      if ( fileChooserSave.getAnswer() ==
          FileChooserSave.SAVE_DONE) {
        JOptionPane.showMessageDialog(this,
                                      "The file has been saved correctly.",
                                      "Save", JOptionPane.INFORMATION_MESSAGE);

        BusinessCore.getGeneralParameters().setWorkingDirectory(fileChooserSave.getDirectoryName());
        int i=1;
        String name=fileChooserSave.getFileName() + " Log "+i+".xml";
        while (new File(name).exists()) {
          i++;
          name=fileChooserSave.getFileName() + " Log "+i+".xml";

        }
        BusinessCore.getGeneralParameters().setLoggingFileName(name);
        this.setTitle("JessX server "+Constants.VERSION+" - " + fileChooserSave.getDirectoryName() + "\\" + fileChooserSave.getFileName() + ".xml");
        hasAlreadySaved = true;
      }
    }
    catch (Exception ex) {
      Utils.logger.error("Error while saving xml document. " + ex.toString());
      JOptionPane.showConfirmDialog(this,"Error occured during writing of the xml document:\n" + ex.toString(),"Error: unable to write xml document",JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

  /**Method called when a Save of the Setup Parameters File is tried
   * @param e ActionEvent
   */
  void jMenuItemQuickSave_actionPerformed(ActionEvent e) {
    // - getting the filename where to save the xml doc: a concatenation of the
    // - working directory and the filename given in the general parameters panel

    if (hasAlreadySaved){
      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
      try {
        sortie.output(createExperimentSetupXmlDocument(),
                      new FileOutputStream(new
                                           File(BusinessCore.
                                                getGeneralParameters().
                                                getWorkingDirectory() + "\\" +
                                                BusinessCore.
                                                getGeneralParameters().
                                                getLoggingFileName().substring(
            0,
            BusinessCore.getGeneralParameters().getLoggingFileName().lastIndexOf(" Log")) +
                                             ".xml")));
        JOptionPane.showMessageDialog(this,
                                      "The file has been saved correctly.",
                                      "Save", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (IOException ex) {
        System.out.print("Error when the file is created...\n" + ex.toString());
      }
    }

    else {
      jMenuItemSave_actionPerformed(null);
    }
  }
  /**
   *
   * @param xmlDoc Document
   */
  private void loadXmlDocument(Document xmlDoc) {
    Element experiment = xmlDoc.getRootElement();
    BusinessCore.loadFromXml(experiment,this);
  }
  /**
   *
   * @return Document
   */
  private Document createExperimentSetupXmlDocument() {
    Document doc = new Document();
    Element experiment = new Element("JessXSetup");
    BusinessCore.saveToXml(experiment);
    doc.setRootElement(experiment);
    return doc;
  }
  /**
   *
   * @param e ActionEvent
   */
  void jMenuItemLoad_actionPerformed(ActionEvent e) {
    JFileChooser chooser = Utils.newFileChooser(null,"","xml files","xml");
    int option = chooser.showOpenDialog(this);
    if (option == JFileChooser.CANCEL_OPTION) {
      return;
    }
    Document xmlDoc;
    jMenuItemClear_actionPerformed(e); // A cleaning in of the previous parameters is launched
    try {
      jMenuItemClear_actionPerformed(e);
      xmlDoc = Utils.readXmlFile(chooser.getSelectedFile().getAbsolutePath());
      System.out.println(chooser.getSelectedFile().getAbsolutePath());
      this.loadXmlDocument(xmlDoc);
      int i = 1;
      String name = chooser.getSelectedFile().getName().substring(0,
          chooser.getSelectedFile().getName().length() - 4) + " Log " +
          i+".xml";
      while (new File(name).exists()) {
        i++;
        name = chooser.getSelectedFile().getName().substring(0,
          chooser.getSelectedFile().getName().length() - 4) + " Log " +
          i+".xml";
      }

      BusinessCore.getGeneralParameters().setLoggingFileName(name);
      BusinessCore.getGeneralParameters().setWorkingDirectory(chooser.getSelectedFile().getParent());
      this.setTitle("JessX server "+Constants.VERSION+" - " + chooser.getSelectedFile().getAbsolutePath());
      hasAlreadySaved = true;
    }
    catch (Exception ex) {
      Utils.logger.error("Error reading the xml file: " + ex.toString());
      JOptionPane.showMessageDialog(this,"The file you choose is incorrect.","Error",JOptionPane.WARNING_MESSAGE);
      return;
    }
    TreemouseAdapter.resetDisplay();
  }

  /**
   *
   * @param e ActionEvent
   */
  void jMenuItemClear_actionPerformed(ActionEvent e) {
    Utils.logger.debug("Removing assets...");

    this.setTitle("JessX server "+Constants.VERSION+" - New Experiment");
    hasAlreadySaved = false;

    Vector iterAsset = new Vector(BusinessCore.getAssets().keySet());
    for(int i =0; i < iterAsset.size(); i++) {
      BusinessCore.removeAsset(BusinessCore.getAsset(iterAsset.elementAt(i).toString()));
    }
    Utils.logger.debug("Removing assets...");
    Vector iterPT = new Vector(BusinessCore.getScenario().getPlayerTypes().keySet());
    for(int i =0; i < iterPT.size(); i++) {
      BusinessCore.getScenario().removePlayerType(BusinessCore.getScenario().getPlayerType(iterPT.elementAt(i).toString()));
    }
    BusinessCore.getGeneralParameters().initializeGeneralParameters();
    treeModel.messagesServerGenericGui.removeAllInfo();
    TreemouseAdapter.resetDisplay();
  }

  /**
   *
   * @param e ActionEvent
   */
  void jMenuItemOnline_actionPerformed(ActionEvent e) {
    Server.setServerState(Server.SERVER_STATE_ONLINE);

    jMenuItemOnline.setEnabled(false);
    jMenuItemBegin.setEnabled(true);

  }

  /**
   *
   * @param e ActionEvent
   */
  void jMenuItemNewExperiment_actionPerformed(ActionEvent e) {
    Iterator iterPlayer = NetworkCore.getPlayerList().keySet().iterator();
    while (iterPlayer.hasNext()) {
      NetworkCore.getPlayer( (String) iterPlayer.next()).send(new
          Initialisation());
    }
  }

  /**
   *
   * @param e ActionEvent
   */
  void jMenuItemBegin_actionPerformed(ActionEvent e) {
    if (NetworkCore.getExperimentManager().beginExperiment(this)) {
      new MessageTimer((Vector) BusinessCore.getScenario().getListInformation().clone() ).start();
      jMenuItemBegin.setEnabled(false);
      jMenuItemSave.setEnabled(false);
      jMenuItemQuickSave.setEnabled(false);
      jMenuItemLoad.setEnabled(false);
      jMenuItemClear.setEnabled(false);
      treeModel.setAllUneditable();
      TreemouseAdapter.refreshDisplay();
    }
  }

  /**
   *
   * @param e ActionEvent
   */
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    ServerAboutBox dlg = new ServerAboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  /**
   *
   * @param e ActionEvent
   */
  public void jMenuHelpTutorial_actionPerformed(ActionEvent e) {
    String directory = System.getProperty("user.dir");
    File file = new File(directory +System.getProperty("file.separator")+"tutoriel"+System.getProperty("file.separator")+
                         "help.html");
    try {
      Process p= Runtime.getRuntime().exec(
          "rundll32 SHELL32.DLL,ShellExec_RunDLL " + file.getAbsolutePath());
      p.waitFor();
    }
    catch (IOException ex1) {
      JOptionPane.showConfirmDialog(this,
                                      "An error occured with the tutorial.\nGo to our web site to see it.",
                                      "JessX Server",
                                      JOptionPane.OK_OPTION,
                                      JOptionPane.OK_OPTION);
      Utils.logger.error("ERROR with File help");
    }
    catch (InterruptedException ex) {
      JOptionPane.showConfirmDialog(this,
                                      "An error occured with the tutorial.\nGo to our web site to see it.",
                                      "JessX Server",
                                      JOptionPane.OK_OPTION,
                                      JOptionPane.OK_OPTION);
      Utils.logger.error("ERROR with File help");    }
  }

  /**
   *
   * @param e ActionEvent
   */
  void jButton1_actionPerformed(ActionEvent e) {
    jButton1.setEnabled(false);
    if (NetworkCore.getExperimentManager().getExperimentState() == ExperimentManager.EXP_ON_PER_OFF) {
      NetworkCore.getExperimentManager().beginNewPeriod();
    }

    else {
     // timer.closeServerTimer();
      jButton1.setEnabled(false);
      jButton1.setText("Experiment off");
      jMenuItemBegin.setEnabled(true);
      jMenuItemSave.setEnabled(true);
      jMenuItemQuickSave.setEnabled(true);
      jMenuItemLoad.setEnabled(true);
      jMenuItemClear.setEnabled(true);
      treeModel.setAllEditable();
      TreemouseAdapter.refreshDisplay();
    }
  }

  /**
   *
   * @param e ActionEvent
   */
  void jMenuItem1_actionPerformed(ActionEvent e) {
    new Thread(new Runnable() {
      public void run() {
        (new LogGetter(24456)).getLogs(new ProgressMonitor(null, "Chargement des logs ...", "Demarrage", 1, NetworkCore.getPlayerList().size()));
      }
    }).start();

  }
}

/***************************************************************/
/*                   EVENT CLASSES SECTION                     */
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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemSave_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemSave_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemSave_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemQuickSave_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemQuickSave_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }

  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemQuickSave_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemExit_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemExit_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }

  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.Quit_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemLoad_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemLoad_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemLoad_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemClear_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemClear_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemClear_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jTree1_mouseAdapter
    extends MouseAdapter {

  private JTree tree;
  public JPanel displayPanel;

  /**
   *
   * @param tree JTree
   * @param displayPanel JPanel
   */
  public ServerFrame_jTree1_mouseAdapter(JTree tree, JPanel displayPanel) {
    this.tree = tree;
    this.displayPanel = displayPanel;
    this.displayPanel.setLayout(new GridBagLayout());
  }

  void resetDisplay() {
    this.displayNode((DisplayableNode)((ExperimentSetupTree)this.tree.getModel()).getExperimentRootNode().getUserObject());
  }

  void displayNode(DisplayableNode node) {
    this.displayPanel.removeAll();
    this.displayPanel.add(node.getPanel(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
          ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
      this.displayPanel.revalidate();
      this.displayPanel.repaint();
  }

  /**
   *
   * @param e MouseEvent
   */
  public void mouseClicked(MouseEvent e) {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    if (node == null) return;
    if (node.getUserObject() instanceof DisplayableNode) {
      displayNode((DisplayableNode)node.getUserObject());
    }
    else {
      Utils.logger.warn("Tree node is not a DisplayableNode. Don't know what to do with him...");
    }
  }

  /**
   *
   */
  public void refreshDisplay() {
    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
    if (node == null) return;
    if (node.getUserObject() instanceof DisplayableNode) {
      displayNode((DisplayableNode)node.getUserObject());
    }
    else {
      Utils.logger.warn("Tree node is not a DisplayableNode. Don't know what to do with him...");
    }

  }

}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemOnline_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemOnline_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }

  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemOnline_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemBegin_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemBegin_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }

  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemBegin_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItemNewExperiment_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItemNewExperiment_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItemNewExperiment_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuHelpAbout_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuHelpAbout_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuHelpTutorial_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuHelpTutorial_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpTutorial_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jButton1_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

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
 * @author non attribuable
 * @version 1.0
 */
class ServerFrame_jMenuItem1_actionAdapter implements java.awt.event.ActionListener {
  GeneralServerFrame adaptee;

  ServerFrame_jMenuItem1_actionAdapter(GeneralServerFrame adaptee) {
    this.adaptee = adaptee;
  }
  /**
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}


/***************************************************************/
/*               JButtonEditor2 CLASSES SECTION                */
/***************************************************************/
class JButtonEditor2
    extends AbstractCellEditor implements TableCellEditor, ActionListener {
  JButton button;
  PlayersTypeServerGenericGui playersTypeServerGenericGui1;
  TableModelPlayersStatus tableModelPlayersStatus;
  JFrame parentFrame;

  /**
   * Add a button to the table.
   *
   * @param tableModelPlayers TableModelPlayersStatus
   * @param parentFrame JFrame
   */
  public JButtonEditor2(TableModelPlayersStatus tableModelPlayers,JFrame parentFrame) {
    button = new JButton();
    tableModelPlayersStatus = tableModelPlayers;
    this.parentFrame=parentFrame;
  }

  /**
   * Execute the method which
   * allows to remove a row from the table.
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    //"e.getActionCommand()" contains the row where the button "delete" was clicked

    String playerToRemove = new String( (String) tableModelPlayersStatus.
                                       getValueAt(
                                           Integer.parseInt(e.getActionCommand().
        toString()), 0));

    Utils.logger.warn("Player removed" + playerToRemove);
    if (JOptionPane.showConfirmDialog(parentFrame,
                                      "Do you really want to delete this client?",
                                      "JessX Client",
                                      JOptionPane.OK_CANCEL_OPTION,
                                      JOptionPane.OK_CANCEL_OPTION) ==
        JOptionPane.OK_OPTION)
      NetworkCore.removePlayer(playerToRemove);
    Utils.logger.warn("A Player has been deleted :" + playerToRemove);
  }

  /**
   * Return the button.
   * @return Object
   */
  public Object getCellEditorValue() {
    return button;
  }

  /**
   * Return the button.
   * @param table JTable
   * @param jbutton Object
   * @param isSelected boolean
   * @param row int
   * @param column int
   * @return Component
   */
  public Component getTableCellEditorComponent(JTable table, Object jbutton,
                                               boolean isSelected, int row,
                                               int column) {
    button = (JButton) jbutton;
    if (button != null) {
      button.setActionCommand(Integer.toString(row));
      button.addActionListener(this);
    }
    return button;
  }
}
