package jessx.net;

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

import org.jdom.*;

/***************************************************************/
/*                Information CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : Information</p>
 * <p>Description : This class is used by the researcher to send piece of information
 * to the client about the market. It can be uncertain information (e.g. rise or fall
 * of prices).</p>
 * @author Grosjean Christophe, Charles Montez
 * @version 1.0
 */

public class Information implements NetworkWritable, NetworkReadable{


  private String information;

  public String getInformation() {
    return this.information;
  }

  public Information(String message) {
    this.information = message;
  }

  public boolean initFromNetworkInput(Element node) {
    if (!node.getName().equals("Information")) return false;
    this.information = node.getText();
    return true;
  }

  public Element prepareForNetworkOutput(String pt) {
    Element node = new Element("Information");
    return node.setText(this.information);
  }

}
