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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*        PlayersTypeServerGenericGui CLASS SECTION            */
/***************************************************************/
/**
 * <p>Title : PlayersTypeServerGenericGui</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class PlayersTypeServerGenericGui
    extends JPanel implements DisplayableNode, ActionListener, PlayerTypeListener {

  ExperimentSetupTree treeModel;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPopupMenu popup;

// constructeur
  /**
   * Allow this panel to be displayed or warn the user if it can't.
   * @param treeModel JessXExperimentSetupTree
   */
  public PlayersTypeServerGenericGui(ExperimentSetupTree treeModel) {
    super();
    this.treeModel = treeModel;
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    BusinessCore.getScenario().addPlayerTypeListener(this);
  }

  /**
   * Allow this panel to be editable.
   */
  public void setEditable() {
    jTable1.setEnabled(true);
    popup.getComponent(0).setEnabled(true);
    popup.getComponent(1).setEnabled(true);
  }

  /**
   * Prevent this panel from being edited.
   */
  public void setUneditable() {
    jTable1.setEnabled(false);
    popup.getComponent(0).setEnabled(false);
    popup.getComponent(1).setEnabled(false);
  }

  /**
   * Return this panel.
   * @return JPanel
   */
  public JPanel getPanel() {
    return this;
  }

  /**
   * Return the string Player's Category.
   * @return String
   */
  public String toString() {
    return "Player's Category";
  }

  /**
   * Display this panel.
   * @throws Exception
   */
  private void jbInit() throws Exception {

    jTable1 = new JTable(new JessXTableModel(new Object[] {"Player type"}, 0));

    popup = new JPopupMenu();
    JMenuItem menuItem = new JMenuItem("Add a new player type");
    menuItem.setActionCommand("addPlayerType");
    menuItem.addActionListener(this);

    jTable1.setRowSelectionAllowed(false);
    popup.add(menuItem);
    menuItem = new JMenuItem("Remove a player type");
    menuItem.setActionCommand("removePlayerType");
    menuItem.addActionListener(this);
    popup.add(menuItem);

    //Add listener to components that can bring up popup menus.
    MouseListener popupListener = new PopupListener(popup);
    jScrollPane1.addMouseListener(popupListener);
    jTable1.addMouseListener(popupListener);

    this.setLayout(gridBagLayout1);
    this.add(jScrollPane1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                                                  , GridBagConstraints.CENTER,
                                                  GridBagConstraints.BOTH,
                                                  new Insets(0, 0, 0, 0), 0, 0));
    jScrollPane1.getViewport().add(jTable1, null);
  }

  /**
   * Verify if the user has clicked on an item and execute the method
   * associated with the action asked by the user.
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JMenuItem) {
      if ( ( (JMenuItem) e.getSource()).getActionCommand().equalsIgnoreCase(
          "addPlayerType")) {
        this.addPlayerTypeWithPopup();
      }
      else if ( ( (JMenuItem) e.getSource()).getActionCommand().
               equalsIgnoreCase("removePlayerType")) {
        this.removePlayerTypeWithPopup();
      }
    }
  }

  /**
   * Verify if the user has asked to edit the table and execute the method
   * associated with the action asked by the user.
   * @param e PlayerTypeEvent
   */
  public void playerTypeModified(PlayerTypeEvent e) {
    if (e.getEvent() == PlayerTypeEvent.PLAYER_ADDED) {
      ( (JessXTableModel)this.jTable1.getModel()).addRow(new Object[] {e.
          getPlayerType().getPlayerTypeName()});
    }
    else if (e.getEvent() == PlayerTypeEvent.PLAYER_REMOVED) {
      //new Object[] {e.getPlayerType().getPlayerTypeName();
      int row = 0;
      for (int i = 0; i < this.jTable1.getRowCount(); i++) {
        if (this.jTable1.getValueAt(i,
                                    0).toString().equalsIgnoreCase(e.
            getPlayerType().getPlayerTypeName())) {
          ( (JessXTableModel)this.jTable1.getModel()).removeRow(i);
        }
      }
    }
  }
  /**
   * Display a popup which allow the user to specify a type of player to add.
   */
  private void addPlayerTypeWithPopup() {

    JLabel jLabelPlayerTypeName = new JLabel("Player's type :");
    JTextField jTextFieldPlayerTypeName = new JTextField("");

    JPanel jPanel = new JPanel(new GridLayout(1, 2));

    jPanel.add(jLabelPlayerTypeName);
    jPanel.add(jTextFieldPlayerTypeName);
    int answer;
    boolean wrongName;
    do {
      wrongName = false;
      answer = JOptionPane.showConfirmDialog(this.jScrollPane1,
                                             jPanel, "Add a player type.",
                                             JOptionPane.OK_CANCEL_OPTION,
                                             JOptionPane.INFORMATION_MESSAGE);
      if (answer == JOptionPane.OK_OPTION) {
        wrongName = (jTextFieldPlayerTypeName.getText().equals("")) ||
            BusinessCore.getScenario().getPlayerTypes().keySet().contains(
                jTextFieldPlayerTypeName.getText());
        if (wrongName) {
          String mess = new String("The name '" +
                                   jTextFieldPlayerTypeName.getText() +
                                   "' is already used. Please choose an other name.");
          if (!jTextFieldPlayerTypeName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, mess, "Name conflict",
                                          JOptionPane.WARNING_MESSAGE);
          }
        }
      }
    }
    while (answer == JOptionPane.OK_OPTION && wrongName);
    if ( (answer == JOptionPane.OK_OPTION)) {
      BusinessCore.getScenario().addPlayerType(new PlayerType(
          jTextFieldPlayerTypeName.getText()));
    }

  }

  /**
   * Display a popup which allow the user to specify the type of player to
   * remove.
   */
  private void removePlayerTypeWithPopup() {
    JLabel jLabel = new JLabel("Which operator do you wish to remove ?");
    JComboBox jComboBox = new JComboBox();
    JPanel panel = new JPanel(new GridLayout(2, 1));

    for (int i = 0; i < jTable1.getRowCount(); i++) {
      jComboBox.addItem(jTable1.getValueAt(i, 0).toString());
    }

    if (jComboBox.getItemCount() == 0) {
      JOptionPane.showConfirmDialog(this.jScrollPane1,
          "There is no player type in this experiment for now.",
                                    "Error: no player type",
                                    JOptionPane.OK_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
      return;
    }

    panel.add(jLabel);
    panel.add(jComboBox);

    int response = JOptionPane.showConfirmDialog(this.jScrollPane1, panel,
                                                 "Removing a player type.",
                                                 JOptionPane.OK_CANCEL_OPTION,
                                                 JOptionPane.
                                                 INFORMATION_MESSAGE);

    if (response == JOptionPane.OK_OPTION) {
      BusinessCore.getScenario().removePlayerType(BusinessCore.getScenario().
                                                  getPlayerType(jComboBox.
          getSelectedItem().toString()));
    }
  }
}
