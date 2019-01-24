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

import javax.swing.*;

import org.jdom.*;
import jessx.business.event.*;
import jessx.net.*;
import jessx.server.net.*;
import jessx.utils.*;

/***************************************************************/
/*                Institution CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : Institution</p>
 * <p>Description : </p>
 * @author Thierry Curtil, Jeremy Streque
 * @version 1.0
 */

public abstract class Institution implements
    XmlExportable, XmlLoadable, NetworkWritable, NetworkReadable {

  private String name;
  private String quotedAsset;
  private OrderBook orderBook= new OrderBook();
  private Vector supportedOperation = new Vector();
  private HashMap operators = new HashMap();
  private HashMap operationMinimalCosts = new HashMap();//cost
  private HashMap operationPercentageCosts = new HashMap();//cost

  private Vector operatorListeners = new Vector();
  private boolean keepingOrderBook;

  public Vector getSupportedOperation() {
    return this.supportedOperation;
  }

  public Institution() {
    this.initSupportedOperation();
  }

  public void setName(String institutionName) {
    this.name = institutionName;
    orderBook.setInstitution(this.name);
  }

  public String getName() {
    return this.name;
  }

  public void setAsset(Asset asset) {
    if (this.quotedAsset == null) {
      this.quotedAsset = asset.getAssetName();
    }
  }

  public String getAssetName() {
    return this.quotedAsset;
  }

  public OrderBook getOrderBook() {
    return this.orderBook;
  }

  public JPanel getServerPanel() {
    return this.getInstitutionSetupGui();
  }

  public abstract JPanel getClientPanel(Operator op);

  public String getInstitutionType() {
    return this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1);
  }

  public abstract JPanel getInstitutionSetupGui();

  public abstract void desactivePanel();

  public abstract void activePanel();

  /**
   * treat the operation in this method.
   * The Institution.treatOperation just decrease the operator cash due to the
   * operation cost, so do not forget to implement the operation effects by
   * overloading this metods.
   * @param op Operation
   */
  public void treatOperation(Operation op) {
    if (this.isOperationSupported(op)) {

      // getting the operation cost.
      Portfolio playerPortfolio = NetworkCore.getPlayer(op.getEmitter()).getPortfolio();
      //playerPortfolio.setCash(playerPortfolio.getCash() - this.getOperationCost(op));
      //because of the TRANSACTION costs instead of OPERATION costs

    }
  }


  private void initSupportedOperation() {

    Iterator operationIterator = OperationCreator.operationFactories.keySet().iterator();
    while (operationIterator.hasNext()) {
      String opKey = (String)operationIterator.next();
      try {
        if (this.isOperationSupported(OperationCreator.createOperation(opKey))) {
          this.supportedOperation.add(opKey);
          Utils.logger.info("New operation authorized: " + opKey);
        }
      }
      catch (OperationNotCreatedException ex) {
        Utils.logger.warn("Unable to instantiate the following operation: " + opKey );
      }
    }
  }

  public void emptyOrderBook() {
    this.orderBook.getAsk().removeAllElements();
    this.orderBook.getBid().removeAllElements();
  }

// ============================ //
// --- operators management --- //
// ============================ //

  public void addOperator(Operator operator) {
    if (operator != null) {
      this.operators.put(operator.getName(), operator);
      fireOperatorAdded(operator.getName());
    }
  }

  public void removeOperator(String operatorName) {
    if (operatorName != null) {
      if (operators.containsKey(operatorName)) {
        this.operators.remove(operatorName);
        fireOperatorRemoved(operatorName);
      }
    }
  }

  public void removeAllOperators() {
    Vector operKeys = new Vector(this.getOperators().keySet());

    for(int i = 0; i < operKeys.size(); i++) {
      this.removeOperator((String)operKeys.elementAt(i));
    }
  }

  /**
   * You must not use this method to add or remove opertor, because listening
   * classes won't be noticed.
   * @return HashMap
   */
  public HashMap getOperators() {
    final HashMap ops = this.operators; // is it working ???
    return ops;
  }

  public Operator getOperator(String name) {
    return (Operator)this.operators.get(name);
  }

  public void addNewOperatorListener(OperatorListener listener) {
    this.operatorListeners.add(listener);
  }

  public void removeOperatorListener(OperatorListener listener) {
    this.operatorListeners.remove(listener);
  }

  public void fireOperatorAdded(String operName) {
    for(int i = 0; i < this.operatorListeners.size();i++) {
      ((OperatorListener)this.operatorListeners.elementAt(i)).operatorsModified(new OperatorEvent(operName,this.getName(),OperatorEvent.OPERATOR_ADDED));
    }
  }

  public void fireOperatorRemoved(String operName) {
    for(int i = 0; i < this.operatorListeners.size();i++) {
      ((OperatorListener)this.operatorListeners.elementAt(i)).operatorsModified(new OperatorEvent(operName,this.getName(),OperatorEvent.OPERATOR_REMOVED));
    }
  }

// =================================== //
// --- end of operators management --- //
// =================================== //


  public void setMinimalCost(String operation, Float minCost) {
    this.operationMinimalCosts.put(operation, minCost);
  }//cost

  public void setPercentageCost(String operation, Float percentageCost) {
    this.operationPercentageCosts.put(operation, percentageCost);
  } //cost

  public void setKeepingOrderBook(boolean possible) {
    this.keepingOrderBook = possible;
  } //cost

  public boolean getKeepingOrderBook() {
  return this.keepingOrderBook;
} //cost



  public float getMinimalCost(String operation) {
    if (this.operationMinimalCosts.containsKey(operation)) {
      return ( (Float)this.operationMinimalCosts.get(operation)).floatValue();
    }
    else {
      return 0f;
    }
  }//cost

  public float getPercentageCost(String operation) {
    if (this.operationPercentageCosts.containsKey(operation)) {
      return ( (Float)this.operationPercentageCosts.get(operation)).floatValue();
    }
    else {
      return 0f;
    }//cost
  }

  public float getOperationCost(Operation op) {
    return op.getOperationCost(this.getPercentageCost(op.getOperationName()),
                               this.getMinimalCost(op.getOperationName()));
  }


  public abstract boolean isOperationSupported(Operation op);
  public abstract boolean isOperationValid(Operation op);

  /**
   * This function must be implemented by the sub class in order to save the
   * parameters that is not saved by the Institution class.
   * @param node Element
   */
  public abstract void saveToXml(Element node);

  /**
   * This function must load the parameters the save function has saved. All
   * standard institution parameters a automatically loaded.
   * @param node Element
   */
  public abstract void loadFromXml(Element node);


  public static void saveInstitutionToXml(Element node, Institution institutionToSave) {

    Utils.logger.debug("Saving institution " + institutionToSave.getName() + " to xml...");
    node.setAttribute("type",institutionToSave.getInstitutionType()).setAttribute("name",institutionToSave.getName());
    node.setAttribute("quotedAsset",institutionToSave.getAssetName());

    Element keepOrderBook = new Element("KeepOrderBook");
    keepOrderBook.setAttribute("allow",String.valueOf(institutionToSave.keepingOrderBook));
    node.addContent(keepOrderBook);

    // - saving operations costs
    Utils.logger.debug("Saving operations costs...");
    Element operationsCost = new Element("OperationsCost");
    for(int i = 0; i < institutionToSave.getSupportedOperation().size(); i++) {
      Element operCost = new Element("Operation");
      String operName = (String)institutionToSave.getSupportedOperation().elementAt(i);
      operCost.setAttribute("name",operName);
      operCost.setAttribute("percentageCost",new Float (institutionToSave.getPercentageCost(operName)).toString());
      operCost.setAttribute("minimalCost",new Float(institutionToSave.getMinimalCost(operName)).toString());
      operationsCost.addContent(operCost);
    }//cost
    node.addContent(operationsCost);

    // - saving defined operators.
    Utils.logger.debug("Saving operators...");
    Element operators = new Element("Operators");
    Iterator operatorIter = institutionToSave.getOperators().keySet().iterator();
    while(operatorIter.hasNext()) {
      String key = (String) operatorIter.next();
      Element op = new Element("Operator");
      institutionToSave.getOperator(key).saveToXml(op);
      operators.addContent(op);
    }
    node.addContent(operators);

    // - saving specific parameters
    Utils.logger.debug("Saving specific parameters...");
    institutionToSave.saveToXml(node);
  }

  /**
   * Asset *must* be loaded first
   * @param node Element
   * @return Institution
   */
  public static Institution loadInstitutionFromXml(Element node) {

    Utils.logger.debug("Loading an institution...");

    // getting institution type...
    Utils.logger.debug("Getting institution type...");
    String institutionType = node.getAttributeValue("type");
    if (institutionType == null) {
      Utils.logger.error("Invalid institution node: the attribute type is not defined.");
      return null;
    }
    Utils.logger.info("Type found: " + institutionType);

    Utils.logger.debug("Getting institution name...");
    String institutionName = node.getAttributeValue("name");
    if (institutionName == null) {
      Utils.logger.error("invalid institution node: the attribute name is not defined.");
      return null;
    }
    Utils.logger.info("Insitution name: " + institutionName);

    Institution institution;
    try {
      Utils.logger.debug("Creating institution...");
      institution = InstitutionCreator.createInstitution(institutionType);
    }
    catch (InstitutionNotCreatedException ex) {
      Utils.logger.error("Institution could not be created. " + ex.toString());
      return null;
    }

    institution.setName(institutionName);

    // - loading quotedAsset
    Utils.logger.debug("Loading quotedAsset...");
    String quotedAsset = node.getAttributeValue("quotedAsset");
    if (quotedAsset == null) {
      Utils.logger.error("invlid xml file: undefined quotedAsset in the institution node.");
      return null;
    }
    institution.quotedAsset = quotedAsset;

    Element keepOrderBook = node.getChild("KeepOrderBook");
    institution.setKeepingOrderBook(keepOrderBook.getAttributeValue("allow").equals("true"));

    // - loading cost of operations
    Utils.logger.debug("Loading operations cost...");
    Element operationCostNode = node.getChild("OperationsCost");
    Iterator costIter = operationCostNode.getChildren("Operation").iterator();
    while(costIter.hasNext()) {
      Element cost = (Element)costIter.next();
      String operationName = cost.getAttributeValue("name");
      Utils.logger.debug("loading costs");
      Float percentageCostValue = new Float(cost.getAttributeValue("percentageCost"));//cost
      Float minimalCostValue = new Float(cost.getAttributeValue("minimalCost"));//cost

      if ((operationName != null) && (minimalCostValue != null)) {
        institution.setPercentageCost(operationName,percentageCostValue);
        institution.setMinimalCost(operationName,minimalCostValue);
        Utils.logger.debug("Cost loaded: operation " + operationName + "Percentage costs" + percentageCostValue + "Minimal costs " + minimalCostValue);
      }//cost
      else {
        Utils.logger.error("Error reading cost: invalid xml format.");
        return null;
      }
    }

    // - loading operators.
    Utils.logger.debug("Loading operators...");
    Element operatorsNode = node.getChild("Operators");
    Iterator operIter = operatorsNode.getChildren("Operator").iterator();
    while(operIter.hasNext()) {
      Element oper = (Element)operIter.next();
      Operator operator = new Operator(oper, institution.getName());
      institution.addOperator(operator);
    }

    // - loading specific parameters
    Utils.logger.debug("Loading specific parameters...");
    institution.loadFromXml(node);
    return institution;
  }

  public Element prepareForNetworkOutput(String pt) {
    Element rootNode = new Element("Institution");
    Institution.saveInstitutionToXml(rootNode,this);
    return rootNode;
  }

  public boolean initFromNetworkInput(Element rootNode) {
    this.loadFromXml(rootNode);
    return true;
  }
}
