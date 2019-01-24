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
/*         InstitutionServerGenericGui CLASS SECTION           */
/***************************************************************/
/**
 * <p>Title : InstitutionServerGenericGui</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class InstitutionServerGenericGui extends JPanel implements DisplayableNode, ActionListener, InstitutionListener, AssetListener {

  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1;
  JPopupMenu popup;
  MouseListener popupListener;

  /**
     * Return the string Institutions.
     * @return String
   */
  public String toString() {
   return "Institutions";
 }

 /**
    * Return this panel.
    * @return JPanel
  */
 public JPanel getPanel() {
   return this;
 }

 /**
    * Allow this panel to be displayed and warn the user if it can't.
  */
 public InstitutionServerGenericGui() {
    super();

    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    BusinessCore.addAssetListener(this);
    BusinessCore.addInstitutionListener(this);
  }

  /**
     * Allow the panel to be editable.
   */
  public void setEditable(){
  jScrollPane1.addMouseListener(popupListener);
  jTable1.addMouseListener(popupListener);
  popup.getComponent(0).setEnabled(true);
  popup.getComponent(1).setEnabled(true);
}

  /**
     * Prevent the panel to be edited.
   */
  public void setUneditable(){
  jScrollPane1.addMouseListener(popupListener);
  jTable1.addMouseListener(popupListener);
  popup.getComponent(0).setEnabled(false);
  popup.getComponent(1).setEnabled(false);
}

  /**
     * Display the panel.
     * @throws Exception
   */
  private void jbInit() throws Exception {
    this.setLayout(gridBagLayout1);

    jTable1 = new JTable(new JessXTableModel(new String[] {"Institution name", "Institution Type", "Quoted Asset"}, 0));


    popup = new JPopupMenu();
    JMenuItem menuItem = new JMenuItem("Add an institution");
    menuItem.setActionCommand("addInstitution");
    menuItem.addActionListener(this);

    jScrollPane1.setToolTipText("Right-click here to add or delete an institution");
    jTable1.setToolTipText("Right-click here to add or delete an institution");
    popup.add(menuItem);
    menuItem = new JMenuItem("Remove an institution");
    menuItem.setActionCommand("deleteInstitution");
    menuItem.addActionListener(this);
    popup.add(menuItem);

    popupListener = new PopupListener(popup);

    //Add listener to components that can bring up popup menus.
    jScrollPane1.addMouseListener(popupListener);
    jTable1.addMouseListener(popupListener);

    jTable1.setRowSelectionAllowed(false);

    this.add(jScrollPane1,    new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(3, 6, 6, 6), 0, 0));
    jScrollPane1.getViewport().add(jTable1, null);
  }

// --- Listeners implementation ---

  /**
     * Verify if the user has asked to modify the table and execute the method
     * associated with the action asked by the user.
     * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JMenuItem) {
      if (((JMenuItem)e.getSource()).getActionCommand().equalsIgnoreCase("addInstitution")) {
        this.addInstitutionWithPopup();
      }
      else if (((JMenuItem)e.getSource()).getActionCommand().equalsIgnoreCase("deleteInstitution")) {
        this.deleteInsitutionWithPopup();
      }
    }
  }

  /**
     * Verify if the table of assets has been modified and remove the row of
     * the institution related to asset which has been removed.
     * @param e AssetEvent
   */
  public void assetsModified(AssetEvent e) {
    if (e.getEvent() == AssetEvent.ASSET_ADDED) {

    }
    else if (e.getEvent() == AssetEvent.ASSET_REMOVED) {
      this.removeInstitutionRelatedTo(e.getAssetName());
    }
  }

  /**
     * Verify if the table of institutions has been modified and add or remove
     * row according to the choose of the user.
     * @param e InstitutionEvent
   */
  public void institutionsModified(InstitutionEvent e) {
    if (e.getEvent() == InstitutionEvent.INSTITUTION_ADDED) {
      this.addInstitutionToTable(BusinessCore.getInstitution(e.getInstitutionName()));
    }
    else if (e.getEvent() == InstitutionEvent.INSTITUTION_REMOVED) {
      this.removeInstitutionFromTable(e.getInstitutionName());
    }
  }

// --- End of listeners implementation ---

  /**
     * Add an institution to the table of institions.
     * @param institution Institution
   */
  private void addInstitutionToTable(Institution institution) {
    ((DefaultTableModel)this.jTable1.getModel()).addRow(new String[] {institution.getName(),institution.getInstitutionType(),institution.getAssetName()});
  }

  /**
     * Remove an institution from the table of institutions.
     * @param institutionName String
   */
  private void removeInstitutionFromTable(String institutionName) {
    int row = locateInsitutionInTable(institutionName);
    ((JessXTableModel)this.jTable1.getModel()).removeRow(row);
  }

  /**
     * Remove an institution related to an asset from the table of institutions.
     * @param assetName String
   */
  private void removeInstitutionRelatedTo(String assetName) {
    for(int i = this.jTable1.getRowCount() - 1; i >= 0 ; i--) {
      if (BusinessCore.getInstitution(jTable1.getValueAt(i,0).toString()).getAssetName().equalsIgnoreCase(assetName)) {
        BusinessCore.removeInstitution(BusinessCore.getInstitution(jTable1.getValueAt(i,0).toString()));
      }
    }
  }

  /**
     * Display a popup which allow the user to specify an institution to add to
     * the table of institutions.
   */
  private void addInstitutionWithPopup() {

    // declaring component that will be displayed on the popup
    JLabel jLabelInstitutionName = new JLabel("Insitution name : ");
    JTextField jTextFieldInstitutionName = new JTextField("");

    JLabel jLabelInstitutionType = new JLabel("Institution type : ");
    JComboBox jComboBoxInstitutionType = new JComboBox();

    JLabel jLabelAssetQuoted = new JLabel("Asset quoted : ");
    JComboBox jComboBoxAssetQuoted = new JComboBox();

    JPanel jPanel = new JPanel(new GridLayout(3,2));

    // filling comboBoxes
    Iterator assetsIterator = BusinessCore.getAssets().keySet().iterator();
    if (!assetsIterator.hasNext()) {
      JOptionPane.showConfirmDialog(this.jScrollPane1,"There no asset to be quoted. You should define an asset first.","error: no defined asset",JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
      return;
    }

    while (assetsIterator.hasNext()) {
      jComboBoxAssetQuoted.addItem(assetsIterator.next());
    }

    Iterator institIterator = InstitutionCreator.institutionFactories.keySet().iterator();
    while(institIterator.hasNext()) {
      jComboBoxInstitutionType.addItem(institIterator.next());
    }


    // adding components to the content panel.
    jPanel.add(jLabelInstitutionName);
    jPanel.add(jTextFieldInstitutionName);
    jPanel.add(jLabelInstitutionType);
    jPanel.add(jComboBoxInstitutionType);
    jPanel.add(jLabelAssetQuoted);
    jPanel.add(jComboBoxAssetQuoted);

    // displaying dialog
    int answer;
    boolean wrongName= false;
    do {
      answer = JOptionPane.showConfirmDialog(this,jPanel,"New Institution",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

      if (answer == JOptionPane.OK_OPTION) {
        wrongName = (jTextFieldInstitutionName.getText().equals("")) ||
            BusinessCore.getInstitutions().keySet().contains(
                jTextFieldInstitutionName.getText());
        if (wrongName) {
          String mess = new String("The name '" +
                                   jTextFieldInstitutionName.getText() +
                                   "' is already used. Please choose an other name.");
          if (!jTextFieldInstitutionName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, mess, "Name conflict",
                                          JOptionPane.WARNING_MESSAGE);
          }
        }
        String assetName=jComboBoxAssetQuoted.getSelectedItem().toString();
        Iterator instit=BusinessCore.getInstitutions().keySet().iterator();
        boolean sameAssetNeverUsed=true;
        while (instit.hasNext()&&sameAssetNeverUsed){
          String instName=(String)instit.next();
          if(((Institution)BusinessCore.getInstitutions().get(instName)).getAssetName().equals(assetName))
            sameAssetNeverUsed=false;
        }
        if (!sameAssetNeverUsed) {
          int ans = JOptionPane.showConfirmDialog(this, "An institution with the same asset already exists.\nDo you want to correct this choice?",
                                                  "Warning",JOptionPane.YES_NO_OPTION,
                                                  JOptionPane.WARNING_MESSAGE);
            wrongName=(ans == JOptionPane.OK_OPTION);
        }
      }
    }
    while (answer == JOptionPane.OK_OPTION && wrongName);
    if ( (answer == JOptionPane.OK_OPTION)) {
      if ((jTextFieldInstitutionName.getText().length() != 0) &&
          (jComboBoxInstitutionType.getSelectedIndex() != -1) &&
          (jComboBoxAssetQuoted.getSelectedIndex() != -1)) {

        this.addInstitution(jTextFieldInstitutionName.getText(),
                            jComboBoxInstitutionType.getSelectedItem().toString(),
                            jComboBoxAssetQuoted.getSelectedItem().toString());
      }
    }
  }

  /**
     * Display a popup which allow the user to specify the institution to
     * remove from the table.
   */
  private void deleteInsitutionWithPopup() {

    if (jTable1.getRowCount() == 0) {
      JOptionPane.showMessageDialog(null,"There is no institution to delete.","No institution.",JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    JPanel dialogPane = new JPanel(new GridLayout(2,1));
    JLabel question = new JLabel("Which institution do you want to delete ?");
    JComboBox jComboBox = new JComboBox();
    for(int i = 0; i < this.jTable1.getRowCount(); i++) {
      jComboBox.addItem(this.jTable1.getValueAt(i,0));
    }

    dialogPane.add(question);
    dialogPane.add(jComboBox);

    int chosenOption = JOptionPane.showConfirmDialog(null,dialogPane,"Delete an institution",JOptionPane.OK_CANCEL_OPTION);

    if (chosenOption == JOptionPane.OK_OPTION) {
      BusinessCore.removeInstitution(BusinessCore.getInstitution(jTable1.getValueAt(jComboBox.getSelectedIndex(),0).toString()));
    }
    else {
      return;
    }
  }

  /**
     * Locate an institution in the table of institutions.
     * @param name String
     * @return int
   */
  private int locateInsitutionInTable(String name) {
    for(int i = 0; i < this.jTable1.getRowCount(); i++) {
      if (this.jTable1.getValueAt(i,0).toString().equalsIgnoreCase(name)) {
        return i;
      }
    }
    return -1;
  }

  /**
     * Add an institution to the table of institutions.
     * @param institutionName String
     * @param institutionType String
     * @param quotedAsset String
   */
  private void addInstitution(String institutionName, String institutionType, String quotedAsset) {
    try {
      Institution newInstitution = InstitutionCreator.createInstitution(institutionType);
      newInstitution.setName(institutionName);
      newInstitution.setAsset(BusinessCore.getAsset(quotedAsset));

      BusinessCore.addInstitution(newInstitution);
    }
    catch (InstitutionNotCreatedException ex) {
      Utils.logger.warn(ex.toString() + ". Error creating an institution whose type was in combobox.");
      ex.printStackTrace();
    }
  }
}
