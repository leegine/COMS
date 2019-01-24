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

import javax.swing.table.*;

import jessx.business.*;
import jessx.business.event.*;

/***************************************************************/
/*         TableModelPortfolioSetup CLASS SECTION              */
/***************************************************************/
/**
 * <p>Title : TableModelPortfolioSetup</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class TableModelPortfolioSetup
    extends AbstractTableModel implements PortfolioListener, AssetListener {

  Portfolio portfolio;
  Vector mainColumn = new Vector();
  private boolean cellEditable = true;

  public TableModelPortfolioSetup(Portfolio p) {

    super();

    this.portfolio = p;
    float cash = portfolio.getCash();
    mainColumn.add("Cash");
    portfolio.setCash(Math.round(cash));

    // adding listeners
    this.portfolio.addListener(this);
    BusinessCore.addAssetListener(this);

    Iterator assetIter = BusinessCore.getAssets().keySet().iterator();
    while (assetIter.hasNext()) {
      String key = (String) assetIter.next();
      if (portfolio.getOwnings().containsKey(key)) {
        this.mainColumn.add(key);
      }
      else {
        this.addRow(key);
      }
    }
  }

  /**
   * @return The number of rows
   */
  public int getRowCount() {
    return mainColumn.size();
  }

  /**
   * @return The number of columns
   */
  public int getColumnCount() {
    return 2;
  }

  /**
   * @return The class of the column choosen
   * @param column the number of the column
   */
  public Class getColumnClass(int column) {
    return (column == 0) ? String.class : Integer.class;
  }

  /**
   * @return A string containing the default name of each column
   * @param column the number of the column
   */
  public String getColumnName(int column) {
    return (column == 0) ? "Assets" : "Quantity";
  }

  /**
   * @return The value true if the cell choosen is editable
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if (cellEditable) {
      return columnIndex == 1;
    }
    return false;
  }

  /**
   * @return The object in the cell choosen
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    //Utils.logger.debug("getting table cell value, cell (" + rowIndex + ", " + columnIndex + ")");
    if (columnIndex == 0) {
      //Utils.logger.debug("Retrieving value... " + mainColumn.elementAt(rowIndex));
      return mainColumn.elementAt(rowIndex);
    }
    else {
      if (rowIndex != 0) {
        //Utils.logger.debug("Retrieving value... " + portfolio.getOwnings(mainColumn.elementAt(rowIndex).toString()));
        return new Integer(portfolio.getOwnings(mainColumn.elementAt(rowIndex).
                                                toString()));
      }
      else {
        //Utils.logger.debug("Retrieving value... " + Math.round(this.portfolio.getCash()));
        return new Integer(Math.round(this.portfolio.getCash()));
      }
    }
  }

  public void setValueAt(Object object, int row, int col) {
    if (col == 1) {
      if (row == 0) {
        this.portfolio.setCash( ( (Integer) object).intValue());
        this.portfolio.setNonInvestedCash(( (Integer) object).intValue());

      }
      else {
        this.portfolio.setOwnings(this.mainColumn.elementAt(row).toString(),
                                        ( (Integer) object).intValue());
       this.portfolio.setNonInvestedOwnings(this.mainColumn.elementAt(row).toString(),
                                        ( (Integer) object).intValue());

      }
    }
  }

  public void portfolioModified(PortfolioEvent e) {
    if (e.getEvent() == PortfolioEvent.ASSET_UPDATED) {
      this.fireTableCellUpdated(this.mainColumn.indexOf(e.getAssetUpdated()), 1);
    }
    else if (e.getEvent() == PortfolioEvent.CASH_UPDATED) {
      this.fireTableCellUpdated(0, 1);
    }
    else if (e.getEvent() == PortfolioEvent.ASSET_ADDED) {
      if (!this.mainColumn.contains(e.getAssetUpdated())) {
        if (this.mainColumn.contains(e.getAssetUpdated())) {
          this.fireTableCellUpdated(this.mainColumn.indexOf(e.getAssetUpdated()),
                                    1);
        }
        else {
          this.mainColumn.add(e.getAssetUpdated());
          this.fireTableRowsInserted(this.mainColumn.size() - 1,
                                     this.mainColumn.size() - 1);
        }
      }
    }
    else if (e.getEvent() == PortfolioEvent.ASSET_REMOVED) {
      if (this.mainColumn.contains(e.getAssetUpdated())) {
        int row = mainColumn.indexOf(e.getAssetUpdated());
        this.mainColumn.remove(e.getAssetUpdated());
        this.fireTableRowsDeleted(row, row);
      }
    }
  }

  public void assetsModified(AssetEvent e) {
    if (e.getEvent() == AssetEvent.ASSET_ADDED) {
      this.addRow(e.getAssetName());
    }
    else if (e.getEvent() == AssetEvent.ASSET_REMOVED) {
      this.removeRow(e.getAssetName());
    }
  }

  private void addRow(String assetName) {
    this.addRow(assetName, 0);
  }

  private void addRow(String assetName, int qtty) {
    this.portfolio.setOwnings(assetName, qtty);
  }

  private void removeRow(String assetName) {
    this.portfolio.removeAssetFromOwnings(assetName);
  }

  public void setCellEditable() {
    this.cellEditable = true;
  }

  public void setCellUneditable() {
    this.cellEditable = false;
  }
}
