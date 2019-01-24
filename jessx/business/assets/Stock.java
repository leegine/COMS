package jessx.business.assets;

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
import org.jdom.*;

/***************************************************************/
/*                    Stock CLASS SECTION                      */
/***************************************************************/

/**
 * <p>Title: Stock</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class Stock extends Asset {

  public Stock() {
  }

  public JPanel getAssetSetupGui() {
    return new StockSetupGui(this);
  }

  public void saveToXml(Element node) {

    // nothing to do more than the standard operations for now
  }

  public void loadFromXml(Element node) {

    // nothing to do more than the standard operations for now.
  }



  static {
    try {
      System.out.println("Loading stock...");
      jessx.business.AssetCreator.assetFactories.put("Stock", Class.forName("jessx.business.assets.Stock"));
    }
    catch (ClassNotFoundException exception) {
      System.out.println("Unabled to locate the Stock class. Reason: bad class name spelling.");
      exception.printStackTrace();
    }
  }

}
