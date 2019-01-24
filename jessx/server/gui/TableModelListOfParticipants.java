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

import javax.swing.*;
import javax.swing.table.*;

/***************************************************************/
/*        TableModelListOfParticipants CLASS SECTION           */
/***************************************************************/
/**
 * <p>Title : TableModelListOfParticipants</p>
 * <p>Description : </p>
 * @author : Christophe Grosjean, Charles Montez
 * @version 1.0
 */

public class TableModelListOfParticipants
    extends AbstractTableModel {
  private int rowCount = 0;
  private int columnCount = 3;
  private Vector listOfParticipants = new Vector();

  /**
   * @return The number of rows
   */
  public int getRowCount() {
    return rowCount;
  }

  /**
   * @return The number of columns
   */
  public int getColumnCount() {
    return columnCount;
  }

  /**
   * @return The value true if the cell choosen is editable
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return columnIndex==2;
  }

  /**
   * @return A string containing the default name of each column
   * @param column the number of the column
   */
  public String getColumnName(int column) {
    if (column == 0)
      return "Name";
    if (column == 1)
      return "Password";
    return "Delete";
  }

  /**
   * @return The class of the column choosen
   * @param column the number of the column
   */
  public Class getColumnClass(int column) {
    if (column == 2)
      return JButton.class;
    return String.class;
  }

  /**
   * @return The object in the cell choosen
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex ==0) {
      return ((String[]) listOfParticipants.get(rowIndex))[0];
    }
    if (columnIndex == 1) {
      return ( (String[]) listOfParticipants.get(rowIndex))[1];
    }
    else {
      JButton deleteButton = new JButton();
      deleteButton.setText("Delete");
      return deleteButton;
    }
  }




  public void addRow(String name,String password) {
    listOfParticipants.add(new String[] {name,password});
    rowCount++;
    this.fireTableRowsInserted(rowCount, rowCount);
  }

  public void deleteRow(int row) {
    listOfParticipants.remove(row);
    rowCount--;
    this.fireTableDataChanged();
  }

  public void removeAll() {
    rowCount = 0;
    listOfParticipants.clear();
    this.fireTableDataChanged();
  }

  public Vector getListParticipants(){
      return listOfParticipants;
  }
}
