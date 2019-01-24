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

import org.jdom.*;
import org.jdom.input.*;
import jessx.utils.*;
import jessx.utils.gui.*;

/***************************************************************/
/*     ConnectionsPlayersServerGenericGui CLASS SECTION        */
/***************************************************************/
/**
 * <p>Title : ConnectionsPlayersServerGenericGui</p>
 * <p>Description : /p>
 * @author Thierry Curtil, Clement Plaignaud, Christophe Grosjean
 * @version 1.0
 */

public class ConnectionsPlayersServerGenericGui
    extends JPanel implements DisplayableNode {
  JPanel jPanelPassword = new JPanel();
  JPanel jPanelList = new JPanel();

  GridBagLayout gridBagLayout = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();

  JScrollPane jScrollPane1 = new JScrollPane();
  TableModelListOfParticipants tableModelParticipants = new
      TableModelListOfParticipants();
  JTable jTable1 = new JTable(tableModelParticipants);

  JButton jButtonSuppress = new JButton();
  JButton jButtonAcquire = new JButton();
  JButton jButtonSave = new JButton();
  JButton jButtonLoad = new JButton();

  JCheckBox jCheckBoxConnection = new JCheckBox();
  private JTextField jTextFieldPassword = new JTextField("");
  private JCheckBox jCheckBoxPassword = new JCheckBox();
  JLabel jLabel1 = new JLabel();

  Border border = BorderFactory.createEtchedBorder(Color.white,
      new Color(156, 156, 158));
  Border borderList = new TitledBorder(border, "List of participants");
  Border border1 = BorderFactory.createEtchedBorder(Color.white,
      new Color(156, 156, 158));
  Border border2 = new TitledBorder(border1, "Global password for the session");
  Border border3 = BorderFactory.createEtchedBorder(Color.white,
      new Color(156, 156, 158));
  Border border4 = new TitledBorder(border3, "List of allowed participants");
  Border border5 = BorderFactory.createEmptyBorder();
  Border border6 = BorderFactory.createLineBorder(Color.white, 2);
  Border border7 = BorderFactory.createLineBorder(new Color(127, 157, 185), 2);
  Border border8 = BorderFactory.createLineBorder(new Color(127, 157, 185), 2);
  Border border9 = BorderFactory.createLineBorder(new Color(127, 157, 185), 1);
  Border border10 = BorderFactory.createEmptyBorder();

  /**
   * Allow the panel to be displayed or warn the user if it can't.
   */
  public ConnectionsPlayersServerGenericGui() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Display this panel.
   * @throws Exception
   */
  private void jbInit() throws Exception {
    this.setLayout(gridBagLayout);

    jPanelList.setLayout(gridBagLayout2);
    jPanelPassword.setLayout(gridBagLayout);

    jPanelList.setBorder(border4);
    jPanelPassword.setBorder(border2);


    jButtonSuppress.setText("Suppress the list");
    jButtonAcquire.setText("Acquire a new list");
    jButtonLoad.setText("Load a list");
    jButtonSave.setText("Save the list");
    jButtonSuppress.addMouseListener(new
                                     CurrentPlayersServerGenericGui_jButtonSuppress_mouseAdapter(this));
    jButtonAcquire.addMouseListener(new
                                    CurrentPlayersServerGenericGui_jButtonAcquire_mouseAdapter(this));
    jButtonSave.addMouseListener(new
                                 CurrentPlayersServerGenericGui_jButton1_mouseAdapter(this));
    jButtonLoad.addMouseListener(new
                                 CurrentPlayersServerGenericGui_jButton2_mouseAdapter(this));

    jCheckBoxConnection.setText("Use the list to allow the connection of the clients");
    jCheckBoxConnection.addActionListener(new
        CurrentPlayersServerGenericGui_jCheckBox1_actionAdapter(this));
    jCheckBoxPassword.setText("Check the connections with this password");
    jCheckBoxPassword.addActionListener(new
        CurrentPlayersServerGenericGui_jCheckBoxPassword_actionAdapter(this));
    jCheckBoxPassword.setSelected(false);
    jLabel1.setText("Password for the session :");
    jTextFieldPassword.addActionListener(new
        CurrentPlayersServerGenericGui_jTextFieldPassword_actionAdapter(this));
    jScrollPane1.setBorder(border9);
    jTable1.setBorder(border10);
    jPanelPassword.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.WEST,
                                                GridBagConstraints.NONE,
                                                new Insets(5, 5, 5, 5), 0, 0));
    this.add(jPanelPassword, new GridBagConstraints(0, 0, 1, 1, 0.2, 0.0
                                             , GridBagConstraints.CENTER,
                                             GridBagConstraints.BOTH,
                                             new Insets(2, 5, 2, 5), 0, 0));
    jScrollPane1.getViewport().add(jTable1);
    jPanelList.add(jButtonSuppress,
                   new GridBagConstraints(3, 0, 1, 1, 0.25, 0.0
                                          , GridBagConstraints.CENTER,
                                          GridBagConstraints.NONE,
                                          new Insets(0, 0, 0, 0), 0, 0));
    jPanelList.add(jButtonLoad, new GridBagConstraints(2, 0, 1, 1, 0.25, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanelList.add(jButtonSave, new GridBagConstraints(1, 0, 1, 1, 0.25, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanelList.add(jButtonAcquire, new GridBagConstraints(0, 0, 1, 1, 0.25, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanelList.add(jCheckBoxConnection,
                   new GridBagConstraints(0, 1, 4, 1, 0.0, 0.0
                                          , GridBagConstraints.CENTER,
                                          GridBagConstraints.HORIZONTAL,
                                          new Insets(0, 0, 0, 0), 0, 0));
    this.add(jPanelList, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.8
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.BOTH,
                                                new Insets(2, 5, 2, 5), 0, 0));
    jPanelList.add(jScrollPane1, new GridBagConstraints(0, 3, 4, 1, 0.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanelPassword.add(jTextFieldPassword,
                       new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(5, 5, 5, 5), 0, 0));
    jPanelPassword.add(jCheckBoxPassword,
                       new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
                                              , GridBagConstraints.WEST,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(5, 5, 5, 5), 0, 0));
    jTable1.getColumnModel().getColumn(2).setCellRenderer(new
        JButtonRenderer());
    jTable1.getColumnModel().getColumn(2).setCellEditor(new
        JButtonEdition(tableModelParticipants));
  }

  /**
   * Allow the panel to be editable.
   */
  public void setEditable() {
  }

  /**
   * Prevent the panel from being edited.
   */
  public void setUneditable() {
  }

  /**
   * Return the string Current players.
   * @return String
   */
  public String toString() {
    return "Connection players";
  }

  /**
   * Return this panel.
   * @return JPanel
   */
  public JPanel getPanel() {
    return this;
  }

  public void jButtonAcquire_mouseClicked(MouseEvent e) {
    Iterator iter = jessx.server.net.NetworkCore.getPlayerList().keySet().iterator();
    if (tableModelParticipants.getListParticipants().size() == 0 ||
        (JOptionPane.showConfirmDialog(null,
        "Do you really want to suppress the previous list?",
                                       "Warning",
                                       JOptionPane.OK_CANCEL_OPTION) ==
         JOptionPane.OK_OPTION)) {
      tableModelParticipants.removeAll();
      while (iter.hasNext()) {
        String key = (String) iter.next();
        tableModelParticipants.addRow(key,jessx.server.net.NetworkCore.getPlayer(key).getPassword());
      }
    }
    jessx.business.BusinessCore.getScenario().setlistOfParticipants(this.tableModelParticipants.getListParticipants());
  }

  public void jButtonSuppress_mouseClicked(MouseEvent e) {
    tableModelParticipants.removeAll();
    jessx.business.BusinessCore.getScenario().setlistOfParticipants(this.tableModelParticipants.getListParticipants());
  }

  /**
   * Save the list of participants to a XML file.
   * @param e MouseEvent
   */
  public void jButton1_mouseClicked(MouseEvent e) {
    new FileChooserSave(createParticipantsXmlDocument(),this,"JessX Server","xml");
  }

  private Document createParticipantsXmlDocument() {
    Document doc = new Document();
    Element experiment = new Element("JessXParticipants");
    Iterator iter = tableModelParticipants.getListParticipants().iterator();
    while (iter.hasNext()) {
      String participantAndPassword[] = (String[]) iter.next();
      Element player = new Element("player");
      player.setAttribute("Name", participantAndPassword[0]);
      player.setAttribute("Password", participantAndPassword[1]);
      experiment.addContent(player);
    }
    doc.setRootElement(experiment);
    return doc;
  }

  public void jButton2_mouseClicked(MouseEvent e) {
    JFileChooser chooser = Utils.newFileChooser(null, "","xml files","xml");
    chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
    if (chooser.showOpenDialog(this) == chooser.APPROVE_OPTION) {
      File file = chooser.getSelectedFile();
      if (file.exists() == false) {
        JOptionPane.showMessageDialog(this, "No file selected.", "Error",
                                      JOptionPane.WARNING_MESSAGE);

        return;
      }
      if (!file.getName().endsWith("xml")) {
        JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                      "Error", JOptionPane.WARNING_MESSAGE);
        return;
      }
      Document xmlLog;
      SAXBuilder sax = new SAXBuilder();
      try {
        xmlLog = sax.build(file);
        Element root = xmlLog.getRootElement();
        Iterator iter = root.getChildren("player").iterator();
        tableModelParticipants.removeAll();
        while (iter.hasNext()) {
          Element player = (Element) iter.next();
          tableModelParticipants.addRow(player.getAttributeValue("Name"),player.getAttributeValue("Password"));
        }
        jessx.business.BusinessCore.getScenario().setlistOfParticipants(this.tableModelParticipants.getListParticipants());
      }
      catch (JDOMException ex) {
        jessx.business.BusinessCore.getScenario().setlistOfParticipants(this.tableModelParticipants.getListParticipants());
        JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                      "Error", JOptionPane.WARNING_MESSAGE);
        return;
      }
      catch (IOException ex) {

        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "The file you choose is incorrect.",
                                      "Error", JOptionPane.WARNING_MESSAGE);
        return;
      }

    }
  }

  public void jCheckBox1_actionPerformed(ActionEvent e) {
    jessx.business.BusinessCore.getScenario().setlistOfParticipantsUsed(this.jCheckBoxConnection.isSelected());
    if (jCheckBoxConnection.isSelected()) {
      jessx.business.BusinessCore.getScenario().setlistOfParticipants(this.tableModelParticipants.getListParticipants());
    }
  }

  public void jCheckBoxPassword_actionPerformed(ActionEvent e) {
    jessx.business.BusinessCore.getScenario().setPasswordUsed(this.jCheckBoxPassword.isSelected());
    if (this.jCheckBoxPassword.isSelected()){
      jessx.business.BusinessCore.getScenario().setPassword(this.jTextFieldPassword.getText());
    }
  }

  public void jTextFieldPassword_actionPerformed(ActionEvent e) {
    jessx.business.BusinessCore.getScenario().setPassword(this.jTextFieldPassword.getText());
  }

}

/***************************************************************/
/*                   EVENT CLASSES SECTION                     */
/***************************************************************/
class CurrentPlayersServerGenericGui_jCheckBoxPassword_actionAdapter
    implements ActionListener {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jCheckBoxPassword_actionAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBoxPassword_actionPerformed(e);
  }
}

class CurrentPlayersServerGenericGui_jCheckBox1_actionAdapter
    implements ActionListener {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jCheckBox1_actionAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBox1_actionPerformed(e);
  }
}

class CurrentPlayersServerGenericGui_jTextFieldPassword_actionAdapter
    implements ActionListener {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jTextFieldPassword_actionAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jTextFieldPassword_actionPerformed(e);
  }
}

class CurrentPlayersServerGenericGui_jButton2_mouseAdapter
    extends MouseAdapter {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jButton2_mouseAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jButton2_mouseClicked(e);
  }
}

class CurrentPlayersServerGenericGui_jButton1_mouseAdapter
    extends MouseAdapter {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jButton1_mouseAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jButton1_mouseClicked(e);
  }
}

class CurrentPlayersServerGenericGui_jButtonAcquire_mouseAdapter
    extends MouseAdapter {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jButtonAcquire_mouseAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jButtonAcquire_mouseClicked(e);
  }
}

class CurrentPlayersServerGenericGui_jButtonSuppress_mouseAdapter
    extends MouseAdapter {
  private ConnectionsPlayersServerGenericGui adaptee;
  CurrentPlayersServerGenericGui_jButtonSuppress_mouseAdapter(
      ConnectionsPlayersServerGenericGui adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jButtonSuppress_mouseClicked(e);
  }
}

/***************************************************************/
/*              JButtonEdition Classe SECTION                  */
/***************************************************************/

class JButtonEdition
    extends AbstractCellEditor implements TableCellEditor,
    ActionListener {
  JButton button;
  TableModelListOfParticipants tableModelParticipants1;

  /**
   * Add a button to the table.
   * @param tableModelParticipants TableModelListOfParticipants
   */
  public JButtonEdition(TableModelListOfParticipants tableModelParticipants) {
    button = new JButton();
    tableModelParticipants1 = tableModelParticipants;

  }

  /**
   * Execute the method which
   * allow to remove a row from the table.
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    //"e.getActionCommand()" contains the row where the button "delete" was clicked
    tableModelParticipants1.deleteRow(Integer.parseInt(e.getActionCommand().
        toString()));
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
