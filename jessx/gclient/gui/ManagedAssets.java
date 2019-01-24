package jessx.gclient.gui;

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

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import org.jdom.*;
import jessx.business.*;
import jessx.client.*;
import jessx.client.event.*;


import jessx.utils.*;
import jessx.utils.gui.JLabelRenderer;


/***************************************************************/
/*           Managed Assets CLASS SECTION                 */
/***************************************************************/
/**
 * <p>Title : Managed Assets</p>
 * <p>Description : </p>
 * @author Rémi Quilliet
 * @version 1.0
 */


public class ManagedAssets extends JPanel
       implements Constants{


  JScrollPane ScrollPaneManagedAssets = new JScrollPane();
  Border border1;
  PortfolioTableModel tableJTable2Model = new PortfolioTableModel(new String[] {"Asset Name"," Quantity"});
  GridBagLayout gridBagLayoutManagedAssets = new GridBagLayout();
  JTable TableManagedAssets = new JTable(tableJTable2Model);



  public ManagedAssets() {
     jbInit();
 }
  public void jbInit() {

    border1 = new TitledBorder(BorderFactory.createEmptyBorder(6,6,6,6),"My Portfolio",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,FONT_CLIENT_TITLE_BORDER);
    this.setEnabled(true);
    this.setBorder(border1);
    this.setMinimumSize(new Dimension(50, 110));
    this.setOpaque(true);
    this.setPreferredSize(new Dimension(200, 110));
    this.setLayout(gridBagLayoutManagedAssets);
    this.add(ScrollPaneManagedAssets, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.5
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


    ScrollPaneManagedAssets.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    ScrollPaneManagedAssets.setAutoscrolls(false);
    ScrollPaneManagedAssets.setRequestFocusEnabled(true);
    ScrollPaneManagedAssets.getViewport().add(TableManagedAssets, null);

    TableManagedAssets.setRowSelectionAllowed(false);
    TableManagedAssets.getColumnModel().getColumn(0).setCellRenderer(new
        JLabelRenderer());
    TableManagedAssets.getColumnModel().getColumn(1).setCellRenderer(new
        JLabelRenderer());
    TableManagedAssets.setRowHeight(18);

  }


}
