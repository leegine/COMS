package jessx.client;

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
import jessx.business.*;
import jessx.client.event.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*                DataManager CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : DataManager</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class DataManager implements NetworkListener{

  public DataManager() {
    ClientCore.addNetworkListener(this, "Institution");
    ClientCore.addNetworkListener(this, "Portfolio");
    ClientCore.addNetworkListener(this, "OperatorPlayed");
  }

  public void objectReceived(Document doc) {
    if (doc.getRootElement().getName().equals("Portfolio")) {
      ClientCore.getPortfolio().initFromNetworkInput(doc.getRootElement());
    }
    else if (doc.getRootElement().getName().equals("OperatorPlayed")) {
      OperatorPlayed opPlayed = new OperatorPlayed("");
      if (opPlayed.initFromNetworkInput(doc.getRootElement())) {
        Institution inst = ClientCore.getInstitution(opPlayed.getInstitutionName());
        if (inst != null) {
          Operator op = inst.getOperator(opPlayed.getOperatorName());
          if (op != null) {
            ClientCore.addOperatorPlayed(op);
          }
          else {
            Utils.logger.warn("The operator has not been found on the institution given. (" +
                              opPlayed.getInstitutionName() + ", " +
                              opPlayed.getOperatorName() + ")");
          }
        }
        else {
          Utils.logger.warn("Operator plays on an institution we did not have. (" +
                            opPlayed.getInstitutionName() + ", " +
                            opPlayed.getOperatorName() + ")");
        }
      }
    }
    else if (doc.getRootElement().getName().equals("Institution")) {
      Institution instit = Institution.loadInstitutionFromXml(doc.getRootElement());
      ClientCore.addInstitution(instit);
    }
  }
}
