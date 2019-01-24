package jessx.business.institutions;

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

import javax.swing.*;

import jessx.business.*;
import jessx.business.operations.*;
import jessx.client.*;
import jessx.utils.*;
import jessx.utils.gui.*;

/***************************************************************/
/*            TWaitingOrdersModel CLASS SECTION                */
/***************************************************************/

/**
 * <p>Title : TWaitingOrdersModel</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */
public class TWaitingOrdersModel extends TStrippedLinesTableModel {

  private Operator operator;
  private boolean deleteOrderGranted;

  public TWaitingOrdersModel(int ec, Operator op) {
    super(null,ec);
    this.operator = op;
    deleteOrderGranted = operator.isGrantedTo((new DeleteOrder()).getOperationName());
  }

  public int getColumnCount() {
    return (deleteOrderGranted ? 4 : 3);
  }

  public String getColumnName(int colIndex) {
    switch(colIndex) {
       case 0 : return "Side";
       case 1 : return "Price (" + Constants.CURRENCY + ")";
       case 2 : return "Quantity";
       case 3 : return "Delete";
       default : Utils.logger.error("waiting order table column index out of bound");
         return "";
    }
  }

  public boolean isCellEditable(int row, int col) {
    if(ClientCore.getExperimentManager().EXP_ON_PER_ON == ClientCore.getExperimentManager().getExperimentState()){
      return (col > 2);
    }
    return false;
  }

  public void setValueAt(Object object, int row, int col) {
    if (col != 3) {
      if (object == null) {
        this.removeRow(row);
      }
      else {
        super.setValueAt(object, row, col);
      }
    }
    else {
      ((JButton)object).setBackground(super.getRowColor(row));
      super.setValueAtNoConversion(object,row,col);
    }
  }

}
