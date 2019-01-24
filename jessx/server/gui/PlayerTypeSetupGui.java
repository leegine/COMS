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
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*            PlayerTypeSetupGui CLASS SECTION                 */
/***************************************************************/
/**
 * <p>Title : PlayerTypeSetupGui</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class PlayerTypeSetupGui extends JPanel implements DisplayableNode, PlayerTypeListener, ActionListener, InstitutionListener {

  PlayerType playerType;

  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JTable jTable1;
  JTable jTable2 = new JTable(new JessXTableModel(new Object[] {"Operators played"},0));
  JPopupMenu popup;
  private JMenuItem menuItem1;
  private JMenuItem menuItem2;
  private TableModelPortfolioSetup tableModel;

  public PlayerTypeSetupGui(PlayerType pt) {
    super();
    this.playerType = pt;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    Iterator opIter = this.playerType.getOperatorsPlayed().keySet().iterator();
    while(opIter.hasNext()) {
      ( (DefaultTableModel)this.jTable2.getModel()).addRow(new Object[] {opIter.next().toString()});
    }
    pt.addPlayerTypeListener(this);
    BusinessCore.addInstitutionListener(this);
  }

  public void setEditable() {
    menuItem1.setEnabled(true);
    menuItem2.setEnabled(true);
    tableModel.setCellEditable();
  }

  public void setUneditable() {
    menuItem1.setEnabled(false);
    menuItem2.setEnabled(false);
    tableModel.setCellUneditable();
  }

  public JPanel getPanel() {
    return this;
  }

  public String toString() {
    return this.playerType.getPlayerTypeName();
  }

  public void playerTypeModified(PlayerTypeEvent e) {
    if (e.getEvent() == PlayerTypeEvent.OPERATOR_GRANTED) {
      ((DefaultTableModel)this.jTable2.getModel()).addRow(new Object[] {e.getOperator().toString()});
    }
    else if (e.getEvent() == PlayerTypeEvent.OPERATOR_DENIED) {
      Utils.logger.debug("Following operator right denied to " + this.playerType.getPlayerTypeName() + " : " + e.getOperator().toString());
      for(int i = jTable2.getRowCount() - 1; i >=0 ; i--) {
        if (jTable2.getValueAt(i,0).toString().equalsIgnoreCase(e.getOperator().toString())) {
          ((DefaultTableModel)jTable2.getModel()).removeRow(i);
        }
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equalsIgnoreCase("addOperator")) {
      this.askUserAnOperator();
    }
    else if (e.getActionCommand().equalsIgnoreCase("removeOperator")) {
      this.askUserOperatorToRemove();
    }
  }

  public void institutionsModified(InstitutionEvent e) {
    if (e.getEvent() == InstitutionEvent.INSTITUTION_REMOVED) {
      Iterator operIter = this.playerType.getOperatorsPlayed().keySet().iterator();
      Vector operToDelete = new Vector();
      while(operIter.hasNext()) {
        String key = (String)operIter.next();
        if (this.playerType.getOperatorPlayed(key).getInstitution().equalsIgnoreCase(e.getInstitutionName())) {
          //this.playerType.removeOperatorPlayed(this.playerType.getOperatorPlayed(key));
          operToDelete.add(this.playerType.getOperatorPlayed(key));
        }
      }
      for(int i =0; i < operToDelete.size(); i++) {
        this.playerType.removeOperatorPlayed((Operator)operToDelete.elementAt(i));
      }
    }
  }

  private void askUserAnOperator() {
    JLabel jLabel = new JLabel("You are going to grant this player type with the right to play an operator.");

    Vector operatorList = new Vector();
    Iterator institIter = BusinessCore.getInstitutions().keySet().iterator();
    while(institIter.hasNext()) {
      String institKey = (String)institIter.next();
      Institution tempInstit = BusinessCore.getInstitution(institKey);

      if (!this.isAlreadyPresent(institKey)) {
        Iterator operIter = ((Institution)BusinessCore.getInstitution(institKey)).getOperators().keySet().iterator();
        while (operIter.hasNext()) {
          String operKey = (String) operIter.next();
          if (!isOperatorPlayed(tempInstit.getOperator(operKey).getCompleteName()))
            operatorList.add(tempInstit.getOperator(operKey));
        }
      }
    }

    JComboBox jComboBox = new JComboBox(operatorList);

    JPanel panel = new JPanel(new GridLayout(2,1));

    panel.add(jLabel);
    panel.add(jComboBox);

    int response = JOptionPane.showConfirmDialog(jScrollPane2,panel,"Grant an operator right.", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

    if (response == JOptionPane.OK_OPTION) {
      Utils.logger.debug("Operator selected : " + jComboBox.getSelectedIndex());
      if (jComboBox.getSelectedIndex()!=-1)
        this.playerType.addOperatorPlayed((Operator)jComboBox.getSelectedItem());
    }
  }


  private boolean isAlreadyPresent(String institutionName) {
    int i = 0;
    while (i < this.jTable2.getRowCount()) {
      String completeName = jTable2.getValueAt(i,0).toString();
      if (completeName.substring(completeName.lastIndexOf(" on ") + 4).equals(institutionName))return true;
      i++;
    }
    return false;



  }

  private boolean isOperatorPlayed(String operCompleteName) {
    int i = 0;
    while( i < this.jTable2.getRowCount()) {
      if (jTable2.getValueAt(i,0).toString().equals(operCompleteName)) return true;
      i++;
    }
    return false;
  }

  private void askUserOperatorToRemove() {

    JLabel jLabel = new JLabel("Which operator do you wish to remove ?");
    JComboBox jComboBox = new JComboBox();
    JPanel panel = new JPanel(new GridLayout(2,1));

    for(int i = 0; i < jTable2.getRowCount(); i++) {
      jComboBox.addItem(jTable2.getValueAt(i,0).toString());
    }

    if (jComboBox.getItemCount() == 0) {
      JOptionPane.showConfirmDialog(this.jScrollPane2,"This player type hasn't yet been granted with any operator right.\nYou can not deny him any.","Error: no right to deny.",JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE);
      return;
    }

    panel.add(jLabel);
    panel.add(jComboBox);

    int response = JOptionPane.showConfirmDialog(this.jScrollPane2,panel,"Removing an operator.",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

    if (response == JOptionPane.OK_OPTION) {
      this.playerType.removeOperatorPlayed(this.playerType.getOperatorPlayed(jComboBox.getSelectedItem().toString()));
    }
  }


  private void jbInit() throws Exception {

    tableModel = new TableModelPortfolioSetup(playerType.getPortfolio());
    jTable1 = new JTable(tableModel);

    popup = new JPopupMenu();
    menuItem1= new JMenuItem("Grant this player type an operator right");
    menuItem1.setActionCommand("addOperator");
    menuItem1.addActionListener(this);

    jTable1.setRowSelectionAllowed(false);
    popup.add(menuItem1);
    menuItem2 = new JMenuItem("Deny this player type an operator right");
    menuItem2.setActionCommand("removeOperator");
    menuItem2.addActionListener(this);
    popup.add(menuItem2);

    //Add listener to components that can bring up popup menus.
    MouseListener popupListener = new PopupListener(popup);
    jScrollPane2.addMouseListener(popupListener);
    jTable2.addMouseListener(popupListener);


    this.setLayout(gridBagLayout1);
    jTable1.setRowSelectionAllowed(false);
    this.add(jScrollPane1,    new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    jScrollPane1.getViewport().add(jTable1, null);
    this.add(jScrollPane2,    new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    jScrollPane2.getViewport().add(jTable2, null);
  }


}
