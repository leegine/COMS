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
/*                       IMPORT SECTION                        */
/***************************************************************/

import jessx.business.*;

/***************************************************************/
/*                DividendEvent CLASS SECTION                  */
/***************************************************************/

/**
 * <p>Title: DividendEvent</p>
 * <p>Description : An event about the paying of Dividends </p>
 * @author Thierry Curtil, Charles Montez
 * @version 1.0
 */

public class DividendEvent {
  public static final int DIVIDEND_UPDATED = 0;

  private int period;
  private Dividend dividend;
  private int event;

// Constructor
  /**
   * Create an event e.
   * @param value Dividend
   * @param period int
   * @param event int
   */

  public DividendEvent(Dividend value, int period, int event) {
    this.period = period;
    this.event = event;
    this.dividend = value;
  }

  public Dividend getDividend() {
    return this.dividend;
  }

  public int getPeriod() {
    return this.period;
  }

  public int getEvent() {
    return this.event;
  }


}
