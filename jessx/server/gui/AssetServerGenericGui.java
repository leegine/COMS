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

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*           AssetServerGenericGui CLASS SECTION               */
/***************************************************************/

/**
 * <p>AssetServerGenericGui</p>
 * <p> Description : Enables  to create and delete Assets in the Server's inferface.</p>
 * @authors: Thierry Curtil, Christophe Grosjean, Charles Montez, Clement Plaignaud
 * @version: 1.0 (year 2005)
 */


public class AssetServerGenericGui
    extends JPanel implements DisplayableNode, ActionListener, AssetListener {
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTable jTable1;
  JPopupMenu popup;
  JComboBox jComboBox1 = new JComboBox();
  private static boolean editable = true;

// constructor
  /** Create the panel of assets.
   */
  public AssetServerGenericGui() {
    super(); // creation of the panel (cf. JPanel class)

    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    // registering
    BusinessCore.addAssetListener(this);

  }

  /**
     * Allow the table of assets to be editable.
     * @see JessXExperimentSetupTree
   */
  public void setEditable() {
    this.editable = true;
    this.activeButtonsRemoveIfNecessary();
    return;
  }

  /**
     * Prevent the table of assets from being edited.
     * @see JessXExperimentSetupTree
   */
  public void setUneditable() {

    this.editable = false;
    this.activeButtonsRemoveIfNecessary();
    return;
  }

//Methods from DisplayableNode interface are her redefined
  /**
     * Return the string Assets.
     * @return String
   */
  public String toString() {
    return "Assets";
  }

  /**
     * Return this panel.
     * @return JPanel
   */
  public JPanel getPanel() {
    this.activeButtonsRemoveIfNecessary();
    return this;
  }

//Initialisation method
  /**
     * Draw the panel of assets.
     * @throws Exception
   */
  private void jbInit() throws Exception {
    Utils.logger.debug("AssetServerGenericGui is initializing;");
    this.setLayout(gridBagLayout1);

    jTable1 = new JTable(new JessXTableModel(new String[] {"Asset name",
                                             "Asset type"}, 0));

    popup = new JPopupMenu(); //this is the right-click with the mouse on the background whith displays this menu.

    JMenuItem menuItem = new JMenuItem("Add an asset");
    menuItem.setActionCommand("addAsset");
    menuItem.addActionListener(this);
    popup.add(menuItem);

    menuItem = new JMenuItem("Remove an asset");
    menuItem.setActionCommand("deleteAsset");
    menuItem.addActionListener(this);
    popup.add(menuItem);

    menuItem = new JMenuItem("Remove all assets");
    menuItem.setActionCommand("deleteAllAssets");
    menuItem.addActionListener(this);
    popup.add(menuItem);
    this.activeButtonsRemoveIfNecessary();

    //Add listener to components that can bring up popup menus.
    MouseListener popupListener = new PopupListener(popup);
    jScrollPane2.addMouseListener(popupListener);
    jTable1.addMouseListener(popupListener);

    jScrollPane2.setToolTipText("Right-click here to add or remove assets.");
    jTable1.setToolTipText("Right-click here to add or remove assets.");
    jTable1.setRowSelectionAllowed(false);
    this.add(jScrollPane2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
                                                  , GridBagConstraints.CENTER,
                                                  GridBagConstraints.BOTH,
                                                  new Insets(3, 6, 6, 6), 0, 0));
    jScrollPane2.getViewport().add(jTable1, null);
    this.initAssetTypeComboBox();
  }

  // --- listeners implementation ---

  /**
     * Activate the asked methods when a user click on an item of the popup
     * allowing the user to edit the table of assets.
     * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    Utils.logger.debug("AssetServerGenericGui actionperformed(e) triggers");
    if (! (e.getSource() instanceof JMenuItem)) {
      return;
    }

    if ( ( (JMenuItem) e.getSource()).getActionCommand().equalsIgnoreCase(
        "addAsset")) {
      this.addAssetWithPopup();
    }
    else if ( ( (JMenuItem) e.getSource()).getActionCommand().equalsIgnoreCase(
        "deleteAsset")) {
      this.removeAssetWithPopup();
      this.activeButtonsRemoveIfNecessary();
    }
    else if ( ( (JMenuItem) e.getSource()).getActionCommand().equalsIgnoreCase(
        "deleteAllAssets")) {
      this.removeAllAssetsWithPopup();
      this.activeButtonsRemoveIfNecessary();
    }
  }

  /**
     * Print a line in the log when an asset is added or removed.
     * @param e AssetEvent
   */
  public void assetsModified(AssetEvent e) {
    Utils.logger.debug("AssetServerGenericGui assetsModified");
    if (e.getEvent() == AssetEvent.ASSET_ADDED) {
      this.addAssetToTable(BusinessCore.getAsset(e.getAssetName()));
    }
    else if (e.getEvent() == AssetEvent.ASSET_REMOVED) {
      this.removeAssetFromTable(e.getAssetName());
    }
  }

  // --- end of listeners implementation

  /**
     * Add an asset to the table of assets.
     * @param asset Asset
   */
  private void addAssetToTable(Asset asset) {
    ( (JessXTableModel)this.jTable1.getModel()).addRow(new Object[] {asset.
        getAssetName(), asset.getAssetType()});
  }

  /**
     * Remove an asset from the table of assets.
     * @param assetName String
   */
  private void removeAssetFromTable(String assetName) {
    int row = locateAssetInTable(assetName);
    if (row >= 0) {
      ( (JessXTableModel)this.jTable1.getModel()).removeRow(row);
    }
  }

  /**
     * Return the row where the given asset is displayed.
     * @param assetName String
     * @return int the row of the asset, or -1 if not found.
   */
  private int locateAssetInTable(String assetName) {
    for (int i = 0; i < this.jTable1.getRowCount(); i++) {
      if (this.jTable1.getValueAt(i, 0).toString().equalsIgnoreCase(assetName)) {
        return i;
      }
    }
    return -1;
  }

  /**
     * Draw a popup allowing the user to specify the asset to add.
   */
  private void addAssetWithPopup() {
    JLabel jLabelAssetName = new JLabel("Asset name: ");
    JLabel jLabelAssetType = new JLabel("Asset type: ");
    JTextField jTextFieldAssetName = new JTextField();

    JPanel jPanel = new JPanel(new GridLayout(2, 2));
    jPanel.add(jLabelAssetName);
    jPanel.add(jTextFieldAssetName);
    jPanel.add(jLabelAssetType);
    jPanel.add(jComboBox1);

    int response;
    boolean assetAddedIsNew = false;

    String nameNewAsset;

    while (!assetAddedIsNew) {
      response = JOptionPane.showConfirmDialog(this.jScrollPane2, jPanel,
                                               "Add a new asset.",
                                               JOptionPane.OK_CANCEL_OPTION,
                                               JOptionPane.INFORMATION_MESSAGE);
      jTextFieldAssetName.selectAll();
      if (response == JOptionPane.CANCEL_OPTION) {
        return; // allow to end the method
      }

      // if the user enters some values and says OK
      if ( (!jTextFieldAssetName.getText().equalsIgnoreCase("")) &&
          (jComboBox1.getSelectedIndex() != -1)) {

        nameNewAsset = jTextFieldAssetName.getText();
        if(nameNewAsset.equals("Cash")){
          nameNewAsset+=" ";
        }
        if (jTable1.getRowCount() == 0) {
          assetAddedIsNew = true;
          this.addAsset(nameNewAsset, jComboBox1.getSelectedItem().toString());
          this.activeButtonsRemoveIfNecessary();
          return;
        }
        else {
          //check if the name don't already exists
          int numOfAssets = jTable1.getRowCount();
          assetAddedIsNew = true;
          do {
            Utils.logger.debug(numOfAssets + ";" + nameNewAsset +
                               "; assetAddedIsNew is" + assetAddedIsNew +
                               this.jTable1.getValueAt( (int) (numOfAssets - 1),
                0).toString());
            if (this.jTable1.getValueAt( (int) (numOfAssets - 1), 0).toString().
                equalsIgnoreCase(nameNewAsset)) {
              assetAddedIsNew = false;
            }
            numOfAssets--;
          }
          while ( (numOfAssets > 0) & (assetAddedIsNew));
          //end of the test

          if (assetAddedIsNew) {
            this.addAsset(nameNewAsset, jComboBox1.getSelectedItem().toString());
            this.activeButtonsRemoveIfNecessary();
            return;
          }
          else {
            String mess = new String("The name '" + nameNewAsset +
                                     "' is already used by an asset. Please choose an other name.");
            JOptionPane.showMessageDialog(this, mess, "Name conflict",
                                          JOptionPane.WARNING_MESSAGE);
          }
        }
      } //end of the trial of creation of an asset

    }
  }

  /**
     * Create an asset or print a line in the log if the asset is not created.
     * @param name String
     * @param type String
   */
  private void addAsset(String name, String type) {

    try {
      Asset newAsset = AssetCreator.createAsset(type);
      newAsset.setAssetName(name);
      BusinessCore.addAsset(newAsset);
    }

    catch (AssetNotCreatedException ex) {
      Utils.logger.error("Unable to add an asset: " + ex.toString());
      JOptionPane.showMessageDialog(null,
                                    "Unable to add the asset: " + name + "'.",
                                    "Exception during the addition of the asset",
                                    JOptionPane.WARNING_MESSAGE);
      ex.printStackTrace();
    }
  }

  /**
     * Draw a popup which allow a user to specify the asset to delete.
   */
  private void removeAssetWithPopup() {
    Utils.logger.debug("removeAssetWithPopup");

    JPanel dialogPane = new JPanel(new GridLayout(2, 1));
    JLabel question = new JLabel("Which asset do you want to remove ?");
    JComboBox jComboBox = new JComboBox();
    for (int i = 0; i < this.jTable1.getRowCount(); i++) {
      jComboBox.addItem(this.jTable1.getValueAt(i, 0));
    }

    dialogPane.add(question);
    dialogPane.add(jComboBox);

    int chosenOption = JOptionPane.showConfirmDialog(null, dialogPane,
        "Delete an asset", JOptionPane.OK_CANCEL_OPTION);

    if (chosenOption == JOptionPane.OK_OPTION) {
      this.removeAssetAtLine(jComboBox.getSelectedIndex());
    }
    else {
      return;
    }

  }

  /**
     * Remove all the assets of the table of assets.
   */
  private void removeAllAssetsWithPopup() {
    if (jTable1.getRowCount() == 0) {
      JOptionPane.showMessageDialog(null, "There is no asset to remove.",
                                    "No asset", JOptionPane.INFORMATION_MESSAGE);
      return;
    }
    int chosenOption = JOptionPane.showConfirmDialog(null,
        "Are you sure you want to delete all the assets ?",
        "Delete all the assets", JOptionPane.YES_NO_OPTION);

    if (chosenOption == JOptionPane.YES_OPTION) {
      int numberOfAssets = jTable1.getRowCount();
      while (numberOfAssets > 0) {
        this.removeAssetAtLine(numberOfAssets - 1);
        numberOfAssets--;
      }
    }
    else {
      return;
    }

  }

  /**
     * Remove the line of the table linked with the removed asset.
     * @param row int
   */
  private void removeAssetAtLine(int row) {
    BusinessCore.removeAsset(BusinessCore.getAsset(jTable1.getValueAt(row, 0).
        toString()));
  }

  /**
     * Enable or disable the items of the popup allowing a user to edit the
     * table of assets.
   */
  private void activeButtonsRemoveIfNecessary() {
    if (editable == true) {
      if (jTable1.getRowCount() == 0) {
        popup.getComponent(0).setEnabled(true);
        popup.getComponent(1).setEnabled(false);
        popup.getComponent(2).setEnabled(false);
      }
      else {
        popup.getComponent(0).setEnabled(true);
        popup.getComponent(1).setEnabled(true);
        popup.getComponent(2).setEnabled(true);
      }
    }
    else {
      popup.getComponent(0).setEnabled(false);
      popup.getComponent(1).setEnabled(false);
      popup.getComponent(2).setEnabled(false);
    }
  }

  /**
   * Put the names of all assets in a combo box.
   */
  private void initAssetTypeComboBox() {
    Utils.logger.debug("initAssetTypeComboBox");
    Iterator keyIterator = AssetCreator.assetFactories.keySet().iterator();
    while (keyIterator.hasNext()) {
      String key = (String) keyIterator.next();
      this.jComboBox1.addItem(key);
    }
  }
}
