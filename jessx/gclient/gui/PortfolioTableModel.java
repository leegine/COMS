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
import java.util.*;

import jessx.business.event.*;
import jessx.client.*;
import jessx.utils.gui.*;

/***************************************************************/
/*          PortfolioTableModel CLASS SECTION                  */
/***************************************************************/
/**
 * <p>Title : PortfolioTableModel</p>
 * <p>Description : </p>
 * @author Thierry Curtil, Remi Quilliet
 * @version 1.0
 *
 */

public class PortfolioTableModel extends TStrippedLinesTableModel
    implements PortfolioListener {

  private Vector mainColumn = new Vector();
  /**
   * we put "Cash" and other assets in the portfolio pane
   * @param columnNames Object[]
   */
  public PortfolioTableModel(Object[] columnNames) {
    super(columnNames,0);
    mainColumn = new Vector();
    mainColumn.add("Cash");
    super.addRow(new Object[]
                 { "Cash", new Float(Math.floor(ClientCore.getPortfolio().getCash() * 100) / 100)});

    Iterator iter = ClientCore.getPortfolio().getOwnings().keySet().iterator();

    while(iter.hasNext()) {

      String key = (String)iter.next();
      super.addRow(new Object[]
                   { key,
                     new Integer(ClientCore.getPortfolio().getOwnings(key))});
      mainColumn.add(key);
    }

    ClientCore.getPortfolio().addListener(this);
  }
  /**
   * We update the quantity of cash in the portfolio pane
   * We also update the quantities of the different equities
   * If new asset is added, we simultaneously add in a new corresponding row
   * @param e PortfolioEvent
   */
  public void portfolioModified(PortfolioEvent e) {
    if (e.getEvent() == PortfolioEvent.CASH_UPDATED) {
      super.setValueAt(new Float(Math.floor(ClientCore.getPortfolio().getCash() * 100) / 100),0,1);
    }
    else if (e.getEvent() == PortfolioEvent.ASSET_UPDATED) {

      int i = mainColumn.indexOf(e.getAssetUpdated());
      super.setValueAt(new Float(Math.floor(ClientCore.getPortfolio().getCash() * 100) / 100),0,1);
      super.setValueAt(new Integer(ClientCore.getPortfolio()
                                             .getOwnings(e.getAssetUpdated())),
                       i,1);
    }
    else if (e.getEvent() == PortfolioEvent.ASSET_ADDED) {
      if (!mainColumn.contains(e.getAssetUpdated())) {
        mainColumn.add(e.getAssetUpdated());
        super.addRow(new Object[]
                     {e.getAssetUpdated(),
                      new Integer(ClientCore.getPortfolio()
                                            .getOwnings(e.getAssetUpdated()))});
      }
    }
  }
}
