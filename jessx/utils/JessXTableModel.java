package jessx.utils;

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
/*                      IMPORT SECTION                         */
/***************************************************************/

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/***************************************************************/
/*                  JessXTableModel Class Section              */
/***************************************************************/
/**
 * <p>Title : JessXTableModel</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class JessXTableModel extends DefaultTableModel {

  private Vector columnEditionAllowed = new Vector();

  public JessXTableModel(Vector columnName, int rows) {
    super();
    this.setColumnIdentifiers(columnName);
    this.setNumRows(rows);

    for(int i = 0; i < columnName.size(); i++) {
      columnEditionAllowed.add(new Boolean(false));
    }
  }

  public JessXTableModel(Vector columnName, int rows, Boolean[] columnSelectionAllowed) {
    super();
    this.setColumnIdentifiers(columnName);
    this.setNumRows(rows);
    this.columnEditionAllowed = new Vector();

    this.columnEditionAllowed = this.convertToVector(columnSelectionAllowed);
  }


  public JessXTableModel(Object[] columnName, int rows) {
    super();
    this.setColumnIdentifiers(columnName);
    this.setNumRows(rows);

    for(int i = 0; i < columnName.length; i++) {
      columnEditionAllowed.add(new Boolean(false));
    }

  }

  public JessXTableModel(Object[] columnName, int rows, Boolean[] columnSelectionAllowed) {
    super();
    this.setColumnIdentifiers(columnName);
    this.setNumRows(rows);

    this.columnEditionAllowed = this.convertToVector(columnSelectionAllowed);
  }

  public Class getColumnClass(int col) {
    if (this.getRowCount() > 0) {
      return this.getValueAt(0,col).getClass();
    } else {
      return "".getClass();
    }
  }

  public boolean isCellEditable(int row, int col) {
    return ((Boolean)this.columnEditionAllowed.elementAt(col)).booleanValue();
  }

  public void addColumn(Object columnName, Object[] columnData) {
    this.addColumn(columnName, columnData, false);
  }

  public void addColumn(Object columnName, Vector columnData) {
    this.addColumn(columnName, columnData, false);
  }

  public void addColumn(Object columnName) {
    this.addColumn(columnName, false);
  }

  public void addColumn(Object columnName, Object[] columnData, boolean selectionAllowed) {
    super.addColumn(columnName, columnData);
    this.columnEditionAllowed.add(new Boolean(selectionAllowed));
  }

  public void addColumn(Object columnName, Vector columnData, boolean selectionAllowed) {
    super.addColumn(columnName, columnData);
    this.columnEditionAllowed.add(new Boolean(selectionAllowed));
  }

  public void addColumn(Object columnName, boolean selectionAllowed) {
    super.addColumn(columnName);
    this.columnEditionAllowed.add(new Boolean(selectionAllowed));
  }

  public void setEditable(int col, boolean editable) {
    this.columnEditionAllowed.setElementAt(new Boolean(editable),col);
  }
}
