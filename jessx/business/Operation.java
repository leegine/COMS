package jessx.business;

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

import jessx.net.*;
import org.jdom.*;
import jessx.utils.*;

/***************************************************************/
/*                  Operation CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : Operation</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public abstract class Operation implements NetworkReadable, NetworkWritable {

  private String emitter;
  private String institutionName;

/**
   * Returns the emitter if the operation
   * @return emitter String
   * */

  public String getEmitter() {
    return this.emitter;
  }

  /**
   * Sets the emitter of the operation
   *
   * @param emitter String
   */
  public void setEmitter(String emitter) {
    this.emitter = emitter;
  }

  public abstract float getOperationCost(float percentCost, float minimalCost);



/**
  *Returns the name of the institution where the assets are exchanged.
  * @return institutionName String
  * */

  public String getInstitutionName() {
    return institutionName;
  }

  /**
   * Sets the name of the institution where the assets are exchanged.
   *
   * @param institutionName String
   */
  public void setInstitutionName(String institutionName) {
    this.institutionName = institutionName;
  }

  public Operation() {
  }

  public  abstract ClientInputPanel getClientPanel(String institution);

  public abstract String getOperationName();
/**
   * Prepares the new operation for its network output
   * @param pt String (not effective yet)
   * @return root
   * */
  public Element prepareForNetworkOutput(String pt) {
    Element root = new Element("Operation");
    root.setAttribute("type",this.getOperationName());
    root.setAttribute("emitter",this.getEmitter());
    root.setAttribute("institution",this.institutionName);
    return root;
  }
/**
   * Inits the new operation from the network input;
   * returns true if the XML is valid, false overwise.
   * @param root Element
   * @return boolean
   * */
  public boolean initFromNetworkInput(Element root) {
    // sub classes should do some job... by overloading
    // this method.
    String emitter = root.getAttributeValue("emitter");
    String institution = root.getAttributeValue("institution");

    if (emitter == null) {
      Utils.logger.error("Invalid xml operation node: attribute emitter not found.");
      return false;
    }

    this.institutionName = institution;
    this.emitter = emitter;

    return true;
  }

  public abstract boolean isVisibleInTheClientPanel();
/**
   * Inits the operation from XML, with only tle type of the operation.
   * @param root Element
   * @throws OperationNotCreatedException
   * @return op Operation
   * */
  public static Operation initOperationFromXml(Element root) throws
      OperationNotCreatedException {

    Operation op = OperationCreator.createOperation(root.getAttributeValue("type"));
    op.initFromNetworkInput(root);
    return op;
  }

}
