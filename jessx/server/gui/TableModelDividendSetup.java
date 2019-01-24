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

import javax.swing.table.*;

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*          TableModelDividendSetup CLASS SECTION              */
/***************************************************************/
/**
 * <p>Title : TableModelDividendSetup</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class TableModelDividendSetup extends AbstractTableModel implements DividendListener {
  public TableModelDividendSetup() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private Class dividendDistribution;
  private DividendModel dividendModel;
  private boolean cellEditable=true;

  public TableModelDividendSetup(Class dividend, DividendModel divModel) {
    if (dividend.getSuperclass().toString().equalsIgnoreCase(Dividend.class.toString())) {
      this.dividendDistribution = dividend;
    }
    else {
      this.dividendDistribution = NormalDividend.class;
    }
    this.dividendModel = divModel;
    this.dividendModel.addDividendListener(this);
  }

  public void setPeriodCount(int i) {
    this.dividendModel.setPeriodCount(i);
  }

  /**
   * @return The number of rows
   */
  public int getRowCount() {
    return this.dividendModel.getDefinedPeriodCount();
  }

  /**
   * @return The number of columns
   */
  public int getColumnCount() {
    return this.createDividend().getParamCount() + 1;
  }

  /**
   * @return The class of the column choosen
   * @param column the number of the column
   */
  public Class getColumnClass(int column) {
    return (column == 0 ) ? Integer.class :this.createDividend().getParamClass(column - 1);
  }

  /**
 * @return A string containing the default name of each column
 * @param column the number of the column
 */
  public String getColumnName(int column) {
    return (column == 0 ) ? "Period" : this.createDividend().getParamNames()[column-1];
  }

  /**
   * @return The object in the cell choosen
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    if (columnIndex == 0) {
      return new Integer(rowIndex + 1);
    }
    else {
      return this.dividendModel.getDividendAt(rowIndex).getParameter(columnIndex - 1);
    }
  }

  public void setValueAt(Object value, int row, int col) {
    this.dividendModel.getDividendAt(row).setParameter(col - 1,value);
  }

  /**
   * @return The value true if the cell choosen is editable
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if (cellEditable){
      return (columnIndex > 0);
    }
  return false;
  }

  public void dividendModified(DividendEvent e) {
    if (e.getEvent() == DividendEvent.DIVIDEND_UPDATED) {
      this.fireTableRowsUpdated(e.getPeriod(),e.getPeriod());
    }
  }

  private Dividend createDividend() {
    try {
      Dividend div = (Dividend)this.dividendDistribution.newInstance();
      return div;
    }
    catch (Exception ex) {
      Utils.logger.error("Failed to create dividend object from the class given to the constructor.");
      Utils.logger.error("error: " + ex.toString());
      Utils.logger.error("Will return a NormalDividend instead of a " + this.dividendDistribution.toString());
    }
    return new NormalDividend();
  }

  public void setCellEditable(){
    this.cellEditable=true;
  }

  public void setCellUneditable(){
  this.cellEditable=false;
}


  private void jbInit() throws Exception {
  }
}
