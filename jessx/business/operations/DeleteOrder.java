package jessx.business.operations;

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
import org.jdom.*;
import jessx.utils.*;

/***************************************************************/
/*                 DeleteOrder CLASS SECTION                   */
/***************************************************************/
/**
 * <p>Title : DeleteOrder</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class DeleteOrder extends Operation {

  private static final String operationName = "Delete Order";
  public static final int DELETE_ORDER_VALID = 1;
  public static final int NOT_ENOUGH_CASH_FOR_DELETE_ORDER = 2;
  private int orderId;
  private Integer quantity;
  private Float price;

  private void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public int getQuantity() {
    return this.quantity.intValue();
  }

  public float getPrice() {
    return this.price.floatValue();
  }

  public float getOperationCost(float percentCost, float minimalCost) {
    return Math.max(minimalCost,
                    quantity.intValue() * price.floatValue() *
                    percentCost / 100 );
  }



  public float deleteOrderValidity(Order order, Portfolio portfolio) {
    String assetName = BusinessCore.getInstitution(order.getInstitutionName()).getAssetName();
    String institutionName = order.getInstitutionName();
    float orderPercentageCost = BusinessCore.getInstitution(order.getInstitutionName()).getPercentageCost(order.getOperationName());
    float orderMinimalCost = BusinessCore.getInstitution(order.getInstitutionName()).getMinimalCost(order.getOperationName());
    float deletionPercentageCost = BusinessCore.getInstitution(order.getInstitutionName()).getPercentageCost("Delete Order");
    float deletionMinimalCost = BusinessCore.getInstitution(order.getInstitutionName()).getMinimalCost("Delete Order");
    this.price = new Float(order.getOrderPrice(order.getSide()));
    this.quantity = new Integer(order.getMaxQtty());

    if (this.getOperationCost(deletionPercentageCost, deletionMinimalCost) <= portfolio.getNonInvestedCash()) {


     portfolio.cancelOrder(order.getSide(),
                        order.getOrderPrice(order.getSide()),
                        order.getMaxQtty(),
                        assetName,
                        institutionName,
                        orderPercentageCost,
                        orderMinimalCost,
                        deletionPercentageCost,
                        deletionMinimalCost);

      return this.DELETE_ORDER_VALID;
    }
    else
      return this.NOT_ENOUGH_CASH_FOR_DELETE_ORDER;
  }

  public DeleteOrder(int orderId) {
    this.setOrderId(orderId);
  }

  public DeleteOrder() {

  }

  /**
   * Should this Operation be visible with common operations ?
   * @return boolean
   */
  public boolean isVisibleInTheClientPanel() {
    return false;
  }

  /**
   * The name of the operation should be used in user interface.
   * It's also the one used to register into the factory.
   * @return String the operation name
   */
  public String getOperationName() {
    return operationName;
  }

  /**
   * this class has no client panel because it's not displayed with common
   * actions
   * @param institution String
   * @return ClientInputPanel
   */

  public ClientInputPanel getClientPanel(String institution) {
    return null;
  }

  public boolean initFromNetworkInput(Element node) {

    // trying to init from super class
    if (!super.initFromNetworkInput(node)) {
      // something went wrong... returning false.
      return false;
    }

    // getting delete order particular info
    Element deleteOrder = node.getChild("DeleteOrder");
    String orderId = deleteOrder.getAttributeValue("orderId");
    //String price = deleteOrder.getAttributeValue("price");
    //String quantity = deleteOrder.getAttributeValue("qtty");

    if ( (orderId == null) /*|| (price == null) || (quantity == null)*/ ) {
      Utils.logger.error("Invalid xml deleteorder node: the attribute orderId is missing.");
      return false;
    }

    // setting the value read.
    this.orderId = Integer.parseInt(orderId);
    //this.price = new Float(price);
    //this.quantity = new Integer(quantity);
    return true;
  }

  public Element prepareForNetworkOutput(String pt) {
    // the Operation super class has some info to put into the message
    // like the emitter and so on.
    Element root = super.prepareForNetworkOutput(pt);

    // the particular info the delete order need
    Element deleteOrder = new Element("DeleteOrder");
    deleteOrder.setAttribute("orderId",Integer.toString(orderId));
    //deleteOrder.setAttribute("price", this.price.toString());
    //deleteOrder.setAttribute("qtty", this.quantity.toString());
    // adding it to the root produce by the super class.
    root.addContent(deleteOrder);

    return root;
  }

  static {
    try {
      System.out.println("Loading DeleteOrder...");
      jessx.business.OperationCreator.operationFactories.put(operationName , Class.forName("jessx.business.operations.DeleteOrder"));
    }
    catch (ClassNotFoundException exception) {
      // it's highly doubtful we won't find jessx.business.operations.DeleteOrder
      System.out.println("Unabled to locate the DeleteOrder class. Reason: probably a bad class name spelling.");
      exception.printStackTrace();
    }
  }

}
