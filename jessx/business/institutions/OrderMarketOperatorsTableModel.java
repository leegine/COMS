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

import javax.swing.table.*;
import java.util.*;
import jessx.business.*;
import jessx.utils.*;
import jessx.business.event.*;

/***************************************************************/
/*       OrderMarketOperatorsTableModel CLASS SECTION          */
/***************************************************************/
/**
 * <p>Title : OrderMarketOperatorsTableModel</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class OrderMarketOperatorsTableModel
    extends AbstractTableModel implements OperatorListener {

  private HashMap operators;
  private Vector allowedOperations;
  private Vector mainColumn = new Vector();
  private boolean cellEditablePossible = true;

  public OrderMarketOperatorsTableModel(Institution institution) {
    this.operators = institution.getOperators();
    this.mainColumn = new Vector(operators.keySet());
    this.allowedOperations = institution.getSupportedOperation();
    institution.addNewOperatorListener(this);
  }

  public int getRowCount() {
    return mainColumn.size();
  }

  public int getColumnCount() {
    return allowedOperations.size() + 2;
  }

  public Class getColumnClass(int col) {
    if (col == 0) {
      return String.class;
    }
    else if (col == 1) {
      return Integer.class;
    }
    else {
      return Boolean.class;
    }
  }

  public String getColumnName(int col) {
    switch (col) {
      case 0:
        return "Operator Name";
      case 1:
        return "Visible Orderbook depth";
      default:
        return (this.allowedOperations.elementAt(col - 2)).toString();
    }
  }

  public boolean isCellEditable(int row, int col) {
    Utils.logger.debug("isCellEditable(" + row + "," + col + ")");
    if (cellEditablePossible) {
      return (col > 0);
    }
    return false;
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    Utils.logger.debug("Getting a value at (" + rowIndex + ", " + columnIndex +
                       ")");
    if (columnIndex == 0) {
      Utils.logger.debug("value: " +
                         this.mainColumn.elementAt(rowIndex).toString());
      return this.mainColumn.elementAt(rowIndex).toString();
    }
    else {
      Operator oper = (Operator)this.operators.get(this.mainColumn.elementAt(
          rowIndex).toString());

      if (columnIndex == 1) {
        Utils.logger.debug("value: " + oper.getOrderBookVisibility());
        return new Integer(oper.getOrderBookVisibility());
      }
      else {
        Utils.logger.debug("value: " +
                           oper.isGrantedTo(this.allowedOperations.
                                            elementAt(columnIndex - 2).toString()));
        return new Boolean(oper.isGrantedTo(this.allowedOperations.elementAt(
            columnIndex - 2).toString()));
      }
    }
  }

  public void setValueAt(Object object, int row, int col) {
    Utils.logger.debug("Setting a value: " + object + " at (" + row + ", " +
                       col + ")");
    if (col > 1) {
      Operator oper = (Operator)this.operators.get(this.mainColumn.elementAt(
          row).toString());
      if ( ( (Boolean) object).booleanValue()) {
        oper.grant(this.getColumnName(col));
      }
      else {
        oper.deny(this.getColumnName(col));
      }
    }
    else if (col == 1) {
      Operator oper = (Operator)this.operators.get(this.mainColumn.elementAt(
          row).toString());
      oper.setOrderbookVisibility( ( (Integer) object).intValue());
    }
    else if (col == 0) {
      Operator oper = (Operator)this.operators.get(this.mainColumn.elementAt(
          row).toString());
      this.mainColumn.setElementAt(object.toString(), row);
      oper.setName(object.toString());
    }
    fireTableCellUpdated(row, col);
  }

  public void operatorsModified(OperatorEvent e) {
    if (e.getEvent() == OperatorEvent.OPERATOR_ADDED) {
      this.addRow(e.getOperatorName());
    }
    else if (e.getEvent() == OperatorEvent.OPERATOR_REMOVED) {
      this.removeRow(e.getOperatorName());
    }
  }

  private void addRow(String operName) {
    this.mainColumn.add(operName);
    this.fireTableRowsInserted(this.mainColumn.size() - 1,
                               this.mainColumn.size() - 1);
  }

  private void removeRow(String operName) {
    int row = this.mainColumn.indexOf(operName);
    this.mainColumn.remove(operName);
    this.fireTableRowsDeleted(row, row);
  }

  public void setUneditable() {
    cellEditablePossible = false;
  }

  public void setEditable() {
    cellEditablePossible = true;
  }
}
