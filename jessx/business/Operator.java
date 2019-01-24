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

import java.util.*;

import org.jdom.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*                   Operator CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : Operator</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class Operator implements
    XmlExportable, XmlLoadable, NetworkWritable, NetworkReadable {

  private Vector grantedOperations;
  private String name;
  private int orderBookVisibility;
  private String institution;

  public Vector getGrantedOperations() {
    return grantedOperations;
  }

  public String getName() {
    return name;
  }

  public String getCompleteName() {
    return this.getName() + " on " + this.getInstitution();
  }

  public int getOrderBookVisibility() {
    return this.orderBookVisibility;
  }

  public String getInstitution() {
    return this.institution;
  }

  /**
   *
   * @param operatorName String
   * @param grantedOps Vector of String which are registered operation names.
   * @param orderBookVisibility int the depth the operator will be allowed to see in the orderbook.
   * @param institution String
   */
  public Operator(String institution, String operatorName, Vector grantedOps, int orderBookVisibility) {
    this.name = operatorName;
    this.grantedOperations = grantedOps;
    this.orderBookVisibility = orderBookVisibility;
    this.institution = institution;
  }

  public Operator(Element xmlOper, String institutionName) {
    this.institution = institutionName;
    this.grantedOperations = new Vector();
    this.loadFromXml(xmlOper);
  }

  public boolean isGrantedTo(String operationName) {
    return grantedOperations.contains(operationName);
  }

  public void grant(String operationName) {
    if (!this.grantedOperations.contains(operationName)) {
      this.grantedOperations.add(operationName);
    }
  }

  public void deny(String operationName) {
    if (this.grantedOperations.contains(operationName)) {
      this.grantedOperations.remove(operationName);
    }
  }

  public void setOrderbookVisibility(int depth) {
    this.orderBookVisibility = depth;
  }

  public void setName(String operName) {
    this.name = operName;
  }

  public String toString() {
    return this.getName() + " on " + this.getInstitution();
  }

  public void loadFromXml(Element node) {
    Utils.logger.debug("Loading operator from xml...");

    // - loading generic parameters: name, orderBookVisibility
    Utils.logger.debug("loading name...");
    String operName = node.getAttributeValue("name");
    if (operName == null) {
      Utils.logger.error("Invalid xml operator node: attribute name not found.");
      System.exit(1);
    }
    Utils.logger.info("Operator name: " + operName);
    this.setName(operName);

    Utils.logger.debug("loading orderbook visibility...");
    String obVisibility = node.getAttributeValue("orderbookVisibility");
    if (obVisibility == null) {
      Utils.logger.error("Invalid xml operator node: attribute orderbookVisibility not found.");
      System.exit(1);
    }
    this.setOrderbookVisibility(Integer.parseInt(obVisibility));
    Utils.logger.info("the operator can see " + obVisibility + " deep in the orderbook.");

    // - loading granted operations
    Utils.logger.debug("Loading operations granted...");
    Iterator grantedOpIter = node.getChildren("GrantedOperation").iterator();
    while(grantedOpIter.hasNext()) {
      Element oper = (Element)grantedOpIter.next();
      String opName = oper.getAttributeValue("name");
      if (opName == null) {
        Utils.logger.error("Invalid xml GrantedOperation node: attribute name not found.");
        System.exit(1);
      }
      Utils.logger.info("Granting the operation " + opName);
      this.grant(opName);
    }
  }

  public void saveToXml(Element parentNode) {

    // - saving name or orderbook visibility
    Utils.logger.debug("Saving operator " + this.getName() + "to xml...");
    parentNode.setAttribute("name",this.getName());
    parentNode.setAttribute("orderbookVisibility", Integer.toString(this.getOrderBookVisibility()));

    // - saving granted operations
    Utils.logger.debug("Saving granted operations...");

    for(int i = 0; i < this.getGrantedOperations().size(); i++) {
      Element grantedOp = new Element("GrantedOperation");
      grantedOp.setAttribute("name",this.getGrantedOperations().elementAt(i).toString());
      parentNode.addContent(grantedOp);
    }
  }

  public Element prepareForNetworkOutput(String pt) {
    Element rootOperator = new Element("Operator");
    this.saveToXml(rootOperator);
    return rootOperator;
  }

  public boolean initFromNetworkInput(Element rootNode) {
    this.loadFromXml(rootNode);
    return true;
  }

}
