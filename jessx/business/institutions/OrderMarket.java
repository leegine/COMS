package jessx.business.institutions;

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

import org.jdom.*;
import jessx.business.*;
import jessx.business.operations.*;
import jessx.net.*;
import jessx.server.net.*;
import jessx.utils.*;

/***************************************************************/
/*                 OrderMarket CLASS SECTION                   */
/***************************************************************/

/**
 * <p>Title: OrderMarket</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class OrderMarket extends Institution {

  OrderMarketSetupGui orderMarketSetupGui;

  public OrderMarket() {
    super();
  }

  public void desactivePanel() {
    orderMarketSetupGui.desactive();
  }

  public void activePanel() {
    orderMarketSetupGui.active();
  }

  public JPanel getInstitutionSetupGui() {
  orderMarketSetupGui= new OrderMarketSetupGui(this);
        return orderMarketSetupGui;
  }
  public boolean isOperationValid(Operation op) {
    /**@todo Implement this jessx.business.Institution abstract method*/
    throw new java.lang.UnsupportedOperationException("Method isOperationValid() not yet implemented.");
  }
  public boolean isOperationSupported(Operation op) {
    return (op instanceof Order) || (op instanceof DeleteOrder);
  }

  public void sendWarningMessage(String playerName,  float warningType) {
    if (warningType == Order.NOT_ENOUGH_CASH_FOR_ASK) {
      String warnMessage = "You have not enough cash for the operation costs.";
      NetworkCore.getPlayer(playerName).send(new WarnForClient(warnMessage));
    }
    else if (warningType == Order.NOT_ENOUGH_CASH_FOR_BID) {
      String warnMessage = "You have not enough cash to afford all the bids you placed.";
      NetworkCore.getPlayer(playerName).send(new WarnForClient(warnMessage));
    }
    else if (warningType == Order.NOT_ENOUGH_ASSET_FOR_ASK) {
      String warnMessage = "You have not enough assets to afford all the asks you placed.";
      NetworkCore.getPlayer(playerName).send(new WarnForClient(warnMessage));
    }
    else if (warningType == Order.NOT_ENOUGH_ASKS_IN_ORDERBOOK) {
      String warnMessage = "There are not enough asks in the orderbook to pass your bid.";
      NetworkCore.getPlayer(playerName).send(new WarnForClient(warnMessage));
    }
    else if (warningType == Order.NOT_ENOUGH_BIDS_IN_ORDERBOOK) {
      String warnMessage = "There are not enough bids in the orderbook to pass your ask.";
      NetworkCore.getPlayer(playerName).send(new WarnForClient(warnMessage));
    }
  }



  public void treatOperation(Operation op) {
    if (this.isOperationSupported(op)) {
      super.treatOperation(op);
      if (op instanceof Order) {
        // timestamping operation
        long time=NetworkCore.getExperimentManager().getTimeInPeriod();
        ((Order)op).setTimestamp(time);

        float orderValidity = 0;

        // trying to insert operation
        Utils.logger.info("--> Debut id=" + ((Order)op).getId());
        synchronized (BusinessCore.getInstitution(op.getInstitutionName()).getOrderBook()) {
          Utils.logger.info("--> Debut sync id=" + ((Order)op).getId());
          orderValidity = ( (Order) op).orderValidity( (Order) op,
                                       NetworkCore.getPlayer(((Order)op).getEmitter()).getPortfolio()
                                       );
          if (orderValidity == Order.ORDER_VALID) {

            // logging operation
            ((Order)op).newId();
            NetworkCore.getLogManager().log(op.prepareForNetworkOutput(""));

            ( (Order) op).insertOrder( (Order) op);
          }
        }
        Utils.logger.info("--> Fin id=" + ((Order)op).getId());

        if (orderValidity == Order.ORDER_VALID) {
          // sending modified orderbook
          NetworkCore.sendToAllPlayers(this.getOrderBook());
          NetworkCore.sendToPlayer(NetworkCore.getPlayer(((Order)op).getEmitter()).getPortfolio(), ((Order) op).getEmitter());
          // logging orderbook
          Element orderbook = new Element("OrderBook");
          orderbook.setAttribute("timestamp", Long.toString(time));
          this.getOrderBook().saveToXml(orderbook);
          NetworkCore.getLogManager().log(orderbook);
        }
        else {
          this.sendWarningMessage(((Order)op).getEmitter(),  orderValidity);
        }

      }

      else if (op instanceof DeleteOrder) {
        // timestamping operation
        long time = NetworkCore.getExperimentManager().getTimeInPeriod();

        float deleteOrderValidity = 0;

        // trying to delete order
        synchronized (BusinessCore.getInstitution(op.getInstitutionName()).
                      getOrderBook()) {
          Order orderToDelete = this.getOrderBook().getOrder( ( (DeleteOrder) op).
              getOrderId());
          deleteOrderValidity = ( (DeleteOrder) op).deleteOrderValidity(
              orderToDelete,
              NetworkCore.getPlayer( ( (DeleteOrder) op).getEmitter()).
              getPortfolio());
          if (deleteOrderValidity == ( (DeleteOrder) op).DELETE_ORDER_VALID) {
            this.getOrderBook().deleteOrder( ( (DeleteOrder) op).getOrderId());
          }
        }
        if (deleteOrderValidity == ( (DeleteOrder) op).DELETE_ORDER_VALID) {
          // sending modified orderbook
          NetworkCore.sendToAllPlayers(this.getOrderBook());
          // logging operation
          NetworkCore.getLogManager().log(op.prepareForNetworkOutput(""));
          // logging orderbook
          Element orderbook = new Element("OrderBook");
          orderbook.setAttribute("timestamp", Long.toString(time));
          this.getOrderBook().saveToXml(orderbook);
          NetworkCore.getLogManager().log(orderbook);
        }
        else {
          String warnMessage = "You have not enough cash to afford the cancelling of this order.";
          NetworkCore.getPlayer(( (DeleteOrder) op).getEmitter()).send(new WarnForClient(warnMessage));
        }
      }
    }
  }
  static {
    try {
      System.out.println("Loading OrderMarket...");
      jessx.business.InstitutionCreator.institutionFactories.put("OrderMarket" , Class.forName("jessx.business.institutions.OrderMarket"));
    }
    catch (ClassNotFoundException exception) {
      // it's highly doubtful we won't find jessx.business.institutions.OrderMarket
      System.out.println("Unabled to locate the OrderMarket class. Reason: bad class name spelling.");
      exception.printStackTrace();
    }
  }

  public String toString() {
    return "an institution";
  }

  public JPanel getClientPanel(Operator op) {
    return new OrderMarketClientPanel(op);
  }

  public void loadFromXml(Element node) {

  }

  public void saveToXml(Element parentNode) {

  }

}
