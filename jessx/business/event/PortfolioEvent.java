package jessx.business.event;

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
/*             PortfolioEvent CLASS SECTION                    */
/***************************************************************/

/**
 * <p>Title: PortfolioEvent</p>
 * <p>Description :</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class PortfolioEvent {

  public final static int CASH_UPDATED = 0;
  public final static int ASSET_UPDATED = 1;
  public final static int ASSET_ADDED = 2;
  public final static int ASSET_REMOVED = 3;
  public final static int ALL_UPDATED = 4;


  private String assetUpdated;
  private int event;

  public PortfolioEvent(String assetUpdated, int event) {
    this.assetUpdated = assetUpdated;
    this.event = event;
  }

  public PortfolioEvent(int event) {
    this(null,event);
  }

  /**
   * return the name of the assetupdated only if the efent is ASSET_UPDATED
   * send back null otherwise.
   * @return String
   */
  public String getAssetUpdated() {
    return this.assetUpdated;
  }

  public int getEvent() {
    return this.event;
  }

}
