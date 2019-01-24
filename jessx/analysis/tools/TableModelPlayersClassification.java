package jessx.analysis.tools;

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

import org.jdom.*;
import jessx.utils.*;

/***************************************************************/
/*         TableModelPlayersResults CLASS SECTION              */
/***************************************************************/

/**
 * <p>Title: TableModelPlayersResults</p>
 * <p>Description : This class enables to create a TableModel
 * with a Document document coming from an experiment creating with jessx
 * and show the results of each player. A right document XML is necessary
 * to understand the following functions. You can have one using the
 * software jessx with several clients and a server.</p>
 * @author Christophe Grosjean
 * @version 1.0
 */

public class TableModelPlayersClassification
    extends AbstractTableModel {

  private Vector vectorName = new Vector();
  private Vector vectorCash = new Vector();
  private HashMap assetValueAtTheEnd = new HashMap();
  private int rowCount;

  /**
   * @param document A right Document XML coming from the software jessx after an experiment
   * @param tableModelPlayersResults TableModelPlayersResults
   */
  public TableModelPlayersClassification(Document document,
                                         TableModelPlayersResults
                                         tableModelPlayersResults) {
    try {
      Utils.logger.debug("TableModelPlayersClassification");
      rowCount = tableModelPlayersResults.getRowCount();
      int columnCount = tableModelPlayersResults.getColumnCount();
      Element experimentNode = document.getRootElement();
      Utils.logger.debug("Asset");
      List assets = experimentNode.getChild("Setup").getChildren("Asset");
      Utils.logger.debug("PeriodNumber");
      int periodNumber = Integer.parseInt(experimentNode.getChild("Setup").
                                          getChild(
                                              "GeneralParameters").getChildText(
          "PeriodNumber"));
      Iterator assetsIter = assets.iterator();
      Hashtable assetAndColumnNumber = new Hashtable();
      while (assetsIter.hasNext()) {
        Element asset = (Element) assetsIter.next();
        Utils.logger.debug("Dividend");
        List dividends = asset.getChild("DividendModel").getChildren("Dividend");
        Utils.logger.debug("name");
        String assetName = asset.getAttributeValue("name");
        assetValueAtTheEnd.put(assetName,
                               assetValueAtTheEnd(dividends, periodNumber));
        int j = 3;
        while (assetName != tableModelPlayersResults.getColumnName(j) &&
               j < columnCount) {
          j += 2;
        }
        Utils.logger.debug("assetAndColumnNumber.put(j,assetName)" + j);
        assetAndColumnNumber.put("" + j, assetName);
      }
      Utils.logger.debug("columnCount:" + columnCount);

      for (int i = 0; i < rowCount; i++) {
        Utils.logger.debug("i:" + i);
        vectorName.add(i, tableModelPlayersResults.getValueAt(i, 0));
        float cash = ( (Float) tableModelPlayersResults.getValueAt(i, 1)).
            floatValue();
        int j = 3;
        while (j < columnCount) {
          Utils.logger.debug("j:" + j);
          cash += ( (Float) tableModelPlayersResults.getValueAt(i, j)).floatValue() *
              ( (Float) assetValueAtTheEnd.get(assetAndColumnNumber.get("" + j))).
              floatValue();
          j += 2;
        }

        vectorCash.add(i, new Float(cash));
      }
      sort();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
  }

  public void sort() {
    Utils.logger.debug("sort");
    int index;
    Object tempName;
    Object tempCash;
    for (int i = 0; i < rowCount; i++) {
      index=i;
      for (int j = i; j < rowCount; j++) {
        if (((Float)vectorCash.get(index)).floatValue() < ((Float)vectorCash.get(j)).floatValue()) {
          index=j;
        }
      }
      tempCash = vectorCash.get(index);
      vectorCash.remove(index);
      vectorCash.add(i, tempCash);
      tempName = vectorName.get(index);
      vectorName.remove(index);
      vectorName.add(i, tempName);
    }
  }

  public Float assetValueAtTheEnd(List dividends, int periodNumber) {
    Utils.logger.debug("assetValueAtTheEnd");
    float assetValueAtTheEnd=0;
    List subDividends=dividends.subList(periodNumber,dividends.size());
    Iterator subDividendsIter = subDividends.iterator();
    while (subDividendsIter.hasNext()) {
      Element dividend = (Element) subDividendsIter.next();
      String divValue=dividend.getAttributeValue("mean");
      assetValueAtTheEnd+=Float.parseFloat(divValue);
    }
    return new Float(assetValueAtTheEnd);
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
    return 3;
  }

  /**
   * @return The class of the column choosen
   * @param column the number of the column
   */
  public Class getColumnClass(int column) {
    if (column == 0)return String.class;
    if (column == 1)return String.class;
    else return Float.class;
  }

  /**
   * @return A string containing the default name of each column
   * @param column the number of the column
   */
  public String getColumnName(int column) {
    switch (column) {
      case 0:
        return "Rank";
      case 1:
        return "Players' Name";
      case 2:
        return "Reassessed Cash ($)";
      default:
        return "Cash ($)";
    }
  }

  /**
   * @return The value true if the cell choosen is editable
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  /**
   * @return The object in the cell choosen
   * @param rowIndex the number of the row to choose the cell
   * @param columnIndex the number of the column to choose the cell
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    Utils.logger.debug("getValueAt: ( " + rowIndex + " ; " + columnIndex + " )");
    switch (columnIndex) {
      case 0: {
        return Integer.toString(rowIndex+1);
      }
      case 1: {
        return this.vectorName.get(rowIndex);
      }
      default:
        return this.vectorCash.get(rowIndex);
    }
  }

}
