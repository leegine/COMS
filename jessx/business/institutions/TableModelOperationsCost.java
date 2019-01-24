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

import jessx.business.*;

/***************************************************************/
/*       TableModelOperationsCost CLASS SECTION                */
/***************************************************************/
/**
 * <p>Title : TableModelOperationsCost</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class TableModelOperationsCost
    extends AbstractTableModel {

  private Institution institution;
  private boolean cellEditablePossible = true;

  public TableModelOperationsCost(Institution institution) {
    this.institution = institution;
  }

  /**
   * @return The number of rows
   */
  public int getRowCount() {
    return this.institution.getSupportedOperation().size();
  }

  /**
   * @return The number of columns
   */
  public int getColumnCount() {
    return 3; //cost
  }

  /**
   * @return The value true if the cell choosen is editable
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if (cellEditablePossible) {
      return (columnIndex != 0);
    }
    return false;
  }

  /**
   * @return A string containing the default name of each column
   * @param column the number of the column
   */
  public String getColumnName(int column) {
    if (column == 0)
      return "Operation";
    else
      return (column == 1) ? "Percentage" : "Minimal cost";
  } //cost

  /**
   * @return The class of the column choosen
   * @param column the number of the column
   */
  public Class getColumnClass(int column) {
    return (column == 0) ? String.class : Float.class;
  } //cost

  /**
   * @return The object in the cell choosen
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex == 0) {
      return this.institution.getSupportedOperation().elementAt(rowIndex);
    }
    else {
      if (columnIndex == 2) {
        return new Float(this.institution.getMinimalCost(this.institution.
            getSupportedOperation().
            elementAt(rowIndex).toString()));
      }
      else {
        return new Float(this.institution.getPercentageCost(this.institution.
            getSupportedOperation().
            elementAt(rowIndex).toString()));
      }
    }
  }

  public void setUneditable() {
    cellEditablePossible = false;
  }

  public void setEditable() {
    cellEditablePossible = true;
  }

  public void setValueAt(Object value, int row, int col) {
    if (col == 2) {
      this.institution.setMinimalCost(this.institution.getSupportedOperation().
                                      elementAt(row).toString(), (Float) value);
    }
    if (col == 1) {
      this.institution.setPercentageCost(this.institution.getSupportedOperation().
                                         elementAt(row).toString(),
                                         (Float) value);
    }
  }
}
