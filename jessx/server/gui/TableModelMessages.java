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
import jessx.business.BusinessCore;

/***************************************************************/
/*              TableModelMessages CLASS SECTION               */
/***************************************************************/
/**
 * <p>Title : TableModelMessages</p>
 * <p>Description : </p>
 * @author : Christophe Grosjean, Charles Montez
 * @version 1.0
 */

public class TableModelMessages extends AbstractTableModel {

  private int rowCount = 0;
  private int columnCount;
  private Vector listMessages = new Vector();
  boolean cellsEditableIsPossible = true;
  JPanel messagesServerGenericGui;

  public TableModelMessages(int columnCount, JPanel panel) {
    this.columnCount=columnCount;
    messagesServerGenericGui = panel;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

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
    return cellsEditableIsPossible;
  }

  /**
   * @return A string containing the default name of each column
   * @param column the number of the column
   */
  public String getColumnName(int column) {
    switch (column) {
      case 0:
        return "Subject";
      case 1:
        return "Receivers";
      case 2:
        return "Period n°";
      case 3:
        return "Time(s)";
      default:
        return "Delete";
    }
  }

  /**
   * @return The class of the column choosen
   * @param column the number of the column
   */
  public Class getColumnClass(int column) {
    switch (column) {
      case 1:
        return JComboBox.class;
      case 2:
        return JSpinner.class;
      case 3:
        return JSpinner.class;
      case 4:
        return JButton.class;
      default:
        return String.class;
    }
  }

  /**
   * @return The object in the cell choosen
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex !=4) {
      return ( (String[]) listMessages.get(rowIndex))[columnIndex];
    }
    else {
      JButton deleteButton = new JButton();
      deleteButton.setText("Delete");
      return deleteButton;
    }
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    switch (columnIndex) {
      case 0: {
        String[] message = new String[] {
                         (aValue.toString()),
                         ( (String[]) listMessages.get(rowIndex))[1],
                         ( (String[]) listMessages.get(rowIndex))[2],
                         ( (String[]) listMessages.get(rowIndex))[3] };
        jessx.business.BusinessCore.getScenario().changeInformation(rowIndex,message);
        listMessages.set(rowIndex, message);
        this.fireTableDataChanged();
        break;
      }
      case 1: {
        String[] message = new String[] {
                         ( (String[]) listMessages.get(rowIndex))[0],
                         (aValue.toString()),
                         ( (String[]) listMessages.get(rowIndex))[2],
                         ( (String[]) listMessages.get(rowIndex))[3]};
          if (((MessagesServerGenericGui) messagesServerGenericGui).checkMessage(message,rowIndex) ) {
                jessx.business.BusinessCore.getScenario().changeInformation(rowIndex,message);
                listMessages.set(rowIndex, message );
                this.fireTableDataChanged();
              }
        break;
        }
        case 2: {
          String[] message = new String[] {
                           ( (String[]) listMessages.get(rowIndex))[0],
                           ( (String[]) listMessages.get(rowIndex))[1],
                           (((JSpinner) aValue).getValue().toString()),
                           ( (String[]) listMessages.get(rowIndex))[3] };
                       this.fireTableDataChanged();
        if (((MessagesServerGenericGui) messagesServerGenericGui).checkMessage(message,rowIndex) ) {
                               jessx.business.BusinessCore.getScenario().changeInformation(rowIndex,message);
                               listMessages.set(rowIndex, message );
                  }
          break;
        }
        case 3: {
          String[] message = new String[] {
                           ( (String[]) listMessages.get(rowIndex))[0],
                           ( (String[]) listMessages.get(rowIndex))[1],
                           ( (String[]) listMessages.get(rowIndex))[2],
                           (((JSpinner) aValue).getValue().toString()) };
                       this.fireTableDataChanged();
         if (((MessagesServerGenericGui) messagesServerGenericGui).checkMessage(message,rowIndex) ) {
                              jessx.business.BusinessCore.getScenario().changeInformation(rowIndex,message);
                              listMessages.set(rowIndex, message );
         }
          break;
        }


            default:
        }
  }

  public void addRow(String[] message) {
    listMessages.add(message);
    rowCount++;
    this.fireTableRowsInserted(rowCount, rowCount);

  }

  public void deleteRow(int row) {
    listMessages.remove(row);
    rowCount--;
    this.fireTableDataChanged();
  }

  public void setEditableCellsPossible(boolean state) {
    cellsEditableIsPossible = state;
  }

  public void  removeAll() {
    listMessages.clear();
    rowCount=0;
    this.fireTableDataChanged();
  }

  public void deletePlayerType(String playerType) {
    for (int i = 0; i < rowCount; i++) {
      if ( ( (String[]) listMessages.get(i))[1].equals(playerType)) {
        this.deleteRow(i);
      }
    }
  }
  public Vector getListMessages() {
    return jessx.business.BusinessCore.getScenario().getListInformation();
  }

  private void jbInit() throws Exception {
  }
}
