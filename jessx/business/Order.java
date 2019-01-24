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
import jessx.server.net.*;

/***************************************************************/
/*                    Order CLASS SECTION                      */
/***************************************************************/
/**
 * <p>Title : Order</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public abstract class Order extends Operation
    implements NetworkWritable, NetworkReadable {

  public static final int ASK = 0;
  public static final int BID = 1;
  public static final int ORDER_VALID = 1;
  public static final int NOT_ENOUGH_ASSET_FOR_ASK = 2;
  public static final int NOT_ENOUGH_CASH_FOR_ASK = 3;
  public static final int NOT_ENOUGH_CASH_FOR_BID = 4;
  public static final int NOT_ENOUGH_BIDS_IN_ORDERBOOK = 5;
  public static final int NOT_ENOUGH_ASKS_IN_ORDERBOOK = 6;

  private static int lastId = 0;


  private long timestamp;
  private int side;
  private int id;

/**
   * Returns the timestamp when the order is placed.
   * @return long
   * */

  public long getTimestamp() {
    return this.timestamp;
  }

/**
   * Sets the timestamp of the order
   * @param timestamp long
   * */

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
/**
   * Returns the "side" of the order : 0 for a bid and 1 for an ask
   * @return side int
   * */
  public int getSide() {
    return side;
  }
/**
   * Sets the side of the order : 0 for a bid and 1 for an ask
   * @param side int
   * */
  public void setSide(int side) {
    this.side = side;
  }


  public float getTransactionFees( float dealAmount, float percentageCost, float minimalCost) {
    return Math.max(percentageCost * dealAmount / 100, minimalCost);
  }

  public float getDealAmount(int side, float dealAmount, float percentageCost, float minimalCost) {
    return (side * dealAmount + getTransactionFees(dealAmount, percentageCost, minimalCost));
  }
  //this.getSide() returns 1 for a bid, and 0 for an ask

/**
   * Gets the identification number of the order.
   * @return id int
   * */
  public int getId() {
    return this.id;
  }

  public void newId() {
    id = lastId;
    lastId++;

  }

/**
   * Sets the <code>id</code> to <code>lastId</code>, that is the last
   * identification number that is available. Then, increments <code>lastId</code>.
   */

  public Order() {
    id = lastId;
    lastId++;

  }
/**
   * Gets the identification number idS of the order contained in the Element root.
   * If it exists, id is set to idS; overwise, id is set to lastId,
   * and lastId is incremented.
   * @param root Element
   */

  public Order(Element root) {
    String idS = root.getChild("Order").getAttributeValue("id");
    if (idS != null) {

      this.id = Integer.parseInt(idS);
      }
    else {
      id = lastId;
      lastId++;

    }
    this.initFromNetworkInput(root);
  }

  public abstract float getMaxPrice();
  public abstract float getMinPrice();

  public abstract int getMinQtty();
  public abstract int getMaxQtty();
//  public abstract long getTime();

  public abstract boolean isExecutingImmediately();
  public abstract boolean hasDefinedPrice();
  public abstract boolean isVisibleInOrderbook();

  public abstract void definePrice(float price);
  public abstract void stopImmediateExecution();
/**
   * Inits the order from the network output.
   * It gets the attributes side and timestamp of the order contaiend in the XML,
   *  and saves it in side and timestamp. Returns true if both attributes are
   * stored in the XML.
   * @param root Element
   * @return boolean
   * */

  public boolean initFromNetworkInput(Element root) {

    // operation is reading what it wrote previously...
    if (!super.initFromNetworkInput(root)) {
      return false;
    }

    // we read now Order particular fields...
    Element order = root.getChild("Order");

    String idS = order.getAttributeValue("id");
    String side = order.getAttributeValue("side");
    String timestamp = order.getAttributeValue("timestamp");

    if ((side == null) || (timestamp == null) || (idS == null)) {
      Utils.logger.error("Invalid xml order node: one of the attribute is missing.");
      return false;
    }

    this.id = Integer.parseInt(idS);
    this.side = Integer.parseInt(side);
    this.timestamp = Long.parseLong(timestamp);

    return true;
  }
/**
   * Prepares the order to be sent on the network output: sets the attributes
   * id, side and timestsmp of the XML.
   * @param pt String (pt = player type)
   * @return root
   * */
  public Element prepareForNetworkOutput(String pt) {

    // operation is adding what it needs...
    Element root = super.prepareForNetworkOutput(pt);

    // we add order particular fields in a child named order.
    Element order = new Element("Order");
    order.setAttribute("id",Integer.toString(this.id));
    order.setAttribute("side",Integer.toString(this.side));
    order.setAttribute("timestamp",Long.toString(this.timestamp));
    root.addContent(order);
    return root;
  }
/**
   * Inserts an order in the orderbook, eventually including the execution
   * of this order.
   * @param order Order
   * */
  public void insertOrder(Order order) {
    Utils.logger.info("insertOrder " + order.getId() + " " + order.getMaxQtty());
    OrderBook orderbook = BusinessCore.getInstitution(order.getInstitutionName()).getOrderBook();

    // comparison are differents whether the side is bid or ask
    if ( order.getSide() == Order.ASK) {
      Utils.logger.info("ask");
      // we look if this ask is not better or equal to the best ask limit
      if ( (!order.isExecutingImmediately()) && (orderbook.getAsk().size() > 0) &&
          ( ( (Order) orderbook.getAsk().elementAt(0)).getMinPrice() <= order.getMinPrice())) {
        // it's not better - just insert it into the ask list
        this.insertOrderWithoutExec(order);
      }
      else {
        // it's better, try to execute it - but first look if there is something
        // to be exchanged with.
        if (orderbook.getBid().size() > 0) {
          // executing the order
          if ( (!order.hasDefinedPrice()) && (order.isVisibleInOrderbook()) ) {
            order.definePrice(((Order) orderbook.getBid().elementAt(0)).getMaxPrice());
            //the price of the bid must be attributed to the best limit order (ask)
            //(since it must be given a price for the orderbook)
            //(order.hasDefinedPrice will return true after)
          }
          executeOrders( (Order) orderbook.getBid().elementAt(0), order);

          // if the order was bigger than the first counterpart
          if ( ( (Order) orderbook.getBid().elementAt(0)).getMaxQtty() == 0) {
            // then erase it as its quantity is zero
            orderbook.getBid().removeElementAt(0);
            if (order.getMaxQtty() > 0) {
              // if it remains a positive quantity in the order, just redo the same
              insertOrder(order);
            }
          }
          else {
            // if it remains a positive quantity in the order and in the counterpart,
            // then the order can no more be executed, save in into the orderBook
            if ( (order.hasDefinedPrice()) && (order.getMaxQtty() > 0) ) {
              //There should be a (order.isVisibleInOrderbook) test too,
              //but it is not necessary for the while
              if (order.isExecutingImmediately())
                order.stopImmediateExecution();
              //Orders visible in the orderbook and executing immediately
              //(at the beginning) must be set as "not executing immediately"
              orderbook.getAsk().insertElementAt(order, 0);
            }
          }
        }
        else {
          if ( (order.isVisibleInOrderbook()) && (order.hasDefinedPrice()) ) {
            insertOrderWithoutExec(order);
          }
          else {
            String warnMessage = "This ask has not been fully passed because the orderbook is not deep enough";
            NetworkCore.getPlayer(order.getEmitter()).send(new WarnForClient(warnMessage));
          }
        }
      }
    }
    else {
      Utils.logger.info("bid");
      // we look if this bid is not better or equal to the best bid limit
      if ( (!order.isExecutingImmediately()) && (orderbook.getBid().size() > 0) &&
           ( ( (Order) orderbook.getBid().elementAt(0)).getMaxPrice() >= order.getMaxPrice())) {
       // it's not better - just insert it into the ask list
       this.insertOrderWithoutExec(order);
     }
     else {
       // it's better, try to execute it - but first look if there is something
       // to be exchanged with.
       if (orderbook.getAsk().size() > 0) {
         // executing the order
         if ( (!order.hasDefinedPrice()) && (order.isVisibleInOrderbook()) ) {
           order.definePrice(((Order) orderbook.getAsk().elementAt(0)).getMinPrice());
           //the price of the ask must be attributed to the best limit order (bid)
           //(since it must be given a price for the orderbook)
           //(order.hasDefinedPrice will return true after)
         }
         executeOrders( (Order) orderbook.getAsk().elementAt(0), order);

         // if the order was bigger than the first counterpart
         if ( ( (Order) orderbook.getAsk().elementAt(0)).getMaxQtty() == 0) {
           // then erase it as its quantity is zero
           orderbook.getAsk().removeElementAt(0);
           if (order.getMaxQtty() > 0) {
             // if it remains a positive quantity in the order, just redo the same
             insertOrder(order);
            }
          }
          else {
            // if it remains a positive quantity in the order and in the counterpart,
            // then the order can no more be executed, save in into the orderBook
            if ( (order.hasDefinedPrice()) && (order.getMaxQtty() > 0) ) {
              //There should be a (order.isVisibleInOrderbook) test too,
              //but it is not necessary for the while
              if (order.isExecutingImmediately())
                order.stopImmediateExecution();
              //Orders visible in the orderbook and executing immediately
              //(at the beginning) must be set as "not executing immediately"
              orderbook.getBid().insertElementAt(order, 0);
            }
          }
        }
        else {
          if ( (order.isVisibleInOrderbook()) && (order.hasDefinedPrice()) ) {
            insertOrderWithoutExec(order);
          }
          else {
            String warnMessage = "This bid has not been fully passed because the orderbook is not deep enough";
            NetworkCore.getPlayer(order.getEmitter()).send(new WarnForClient(warnMessage));
          }
      }
      }
    }

  }
/**
   * Inserts a new order in the orderbook without executing the order.
   * @param order Order
   * */
  private void insertOrderWithoutExec(Order order) {
    Utils.logger.info("insertOrderWithoutExec " + order.getId() + " " + order.getMaxQtty());
    OrderBook orderbook = BusinessCore.getInstitution(order.getInstitutionName()).getOrderBook();

    int i=0;
    Utils.logger.info("Beginning insertion. i=0. side: " + order.side + " - min price: "+ order.getMinPrice() + " - max price " + order.getMaxPrice());
    if (order.side == Order.BID) {
      while ( (orderbook.getBid().size() > i) && (order.getMaxPrice() <= ((Order)orderbook.getBid().elementAt(i)).getMaxPrice())) {
        Utils.logger.info("i=" + i + " - Current orderBook price: " + ((Order)orderbook.getBid().elementAt(i)).getMaxPrice());
        i++;
      }
      Utils.logger.info("Insertion place: i = " + i + (i < orderbook.getBid().size() ? " - Current orderBook price at this place: " + ((Order)orderbook.getBid().elementAt(i)).getMaxPrice() : ""));
      orderbook.getBid().insertElementAt(order, i);

    }
    else {
      while ( (orderbook.getAsk().size() > i) && (order.getMinPrice() >= ((Order)orderbook.getAsk().elementAt(i)).getMinPrice())) {
        Utils.logger.info("i=" + i + " - Current orderBook price: " + ((Order)orderbook.getAsk().elementAt(i)).getMinPrice());
        i++;
      }
      Utils.logger.info("Insertion place: i = " + i + (i < orderbook.getAsk().size() ? " - Current orderBook price at this place: " + ((Order)orderbook.getAsk().elementAt(i)).getMinPrice() : ""));
      orderbook.getAsk().insertElementAt(order, i);
    }
  }
/**
   * Executes two orders ( one bid and one ask) in the orderbook
   * that can be associated; creates the associated deal.
   * @param orderInBook Order
   * @param newOrder Order
   * */
  private void executeOrders(Order orderInBook, Order newOrder) {
    Utils.logger.info("-executeOrders (old) "+ orderInBook.getId() + " " + orderInBook.getMaxQtty());
    Utils.logger.info("-executeOrders (new)"+ newOrder.getId() + " " + newOrder.getMaxQtty());
    //Utils.logger.debug(orderInBook);
    //Utils.logger.debug("newOrder: ");
    //Utils.logger.debug(newOrder);

    int exchangeableQty = Math.min(orderInBook.getMaxQtty(),
                                   newOrder.getMaxQtty());

    boolean askPossible = newOrder.getMinPrice() <= orderInBook.getMaxPrice();
    boolean bidPossible = newOrder.getMaxPrice() >= orderInBook.getMinPrice();

    if (((newOrder.side == Order.ASK) && askPossible) ||
        ((newOrder.side == Order.BID) && bidPossible)) {
      if (exchangeableQty > 0) {

        float dealPrice = (newOrder.getSide() == Order.BID) ?
            orderInBook.getMinPrice() : orderInBook.getMaxPrice();


        float maxBidPrice = 0;

        if (newOrder.hasDefinedPrice()) {
          maxBidPrice = (newOrder.getSide() == Order.BID) ?
              newOrder.getMaxPrice() : orderInBook.getMaxPrice();
        }
        else
        {
          maxBidPrice = dealPrice;
        }

        String client = newOrder.getEmitter();
        String clientInBook = orderInBook.getEmitter();
        String institutionName = newOrder.getInstitutionName();
        //The same institution for the two orders
        String assetName = BusinessCore.getInstitution(institutionName).getAssetName();

        String buyer;
        String seller;
        String buyerOperation;
        String sellerOperation;


        if (newOrder.getSide() == Order.BID) {
          buyer = client;
          seller = clientInBook;
          buyerOperation = newOrder.getOperationName();
          sellerOperation = orderInBook.getOperationName();
          float sellerPercentageCost = BusinessCore.getInstitution(institutionName).getPercentageCost(sellerOperation);
          float sellerMinimalCost = BusinessCore.getInstitution(institutionName).getMinimalCost(sellerOperation);
          NetworkCore.getPlayer(seller).getPortfolio().soldAssetsInOrderBook(assetName,
                                dealPrice,
                                exchangeableQty,
                                institutionName,
                                sellerPercentageCost,
                                sellerMinimalCost);

          NetworkCore.sendToPlayer(NetworkCore.getPlayer(seller).getPortfolio(), seller);
        }
        else {
          buyer = clientInBook;
          seller = client;
          buyerOperation = orderInBook.getOperationName();
          sellerOperation = newOrder.getOperationName();
          float buyerPercentageCost = BusinessCore.getInstitution(institutionName).getPercentageCost(buyerOperation);
          float buyerMinimalCost = BusinessCore.getInstitution(institutionName).getMinimalCost(buyerOperation);
          NetworkCore.getPlayer(buyer).getPortfolio().boughtAssetsInOrderBook(assetName,
                               dealPrice,
                               exchangeableQty,
                               institutionName,
                               buyerPercentageCost,
                               buyerMinimalCost);

          NetworkCore.sendToPlayer(NetworkCore.getPlayer(buyer).getPortfolio(), buyer);
        }


        Deal deal = new Deal(newOrder.getInstitutionName(),
                             dealPrice,
                             exchangeableQty,
                             newOrder.getTimestamp(),
                             buyer, seller, maxBidPrice, buyerOperation, sellerOperation);

        NetworkCore.sendToAllPlayers(deal);

        Element dealNode = new Element("Deal");
        deal.saveToXml(dealNode);
        NetworkCore.getLogManager().log(dealNode);


        newOrder.setRemainingOrder(exchangeableQty, dealPrice);
        orderInBook.setRemainingOrder(exchangeableQty, dealPrice);


      }
    }
  }

  public float orderValidity(Order order, Portfolio portfolio) {

    OrderBook orderBook = BusinessCore.getInstitution(order.getInstitutionName()).getOrderBook();
    String assetName = BusinessCore.getInstitution(order.getInstitutionName()).getAssetName();
    float percentageCost = BusinessCore.getInstitution(order.getInstitutionName()).getPercentageCost(order.getOperationName());
    float minimalCost = BusinessCore.getInstitution(order.getInstitutionName()).getMinimalCost(order.getOperationName());
    float nonInvestedCash = portfolio.getNonInvestedCash();
    int nonInvestedOwnings = portfolio.getNonInvestedOwnings(assetName);
    boolean canBeAddedInTheOrderBook = order.hasDefinedPrice();

    float orderPrice = this.getOrderPrice(this.getSide());
    int totalQuantity = this.getMaxQtty();
    int orderRemainingQuantity = totalQuantity;

    if (this.getSide() == Order.ASK) {
      if (orderRemainingQuantity > nonInvestedOwnings)
        return NOT_ENOUGH_ASSET_FOR_ASK;
      //When there are not enough ownings in the portFolio


      float immediateDealsAmountWithoutTF = 0;



      for (int i = 0; i<orderBook.getBid().size(); i++) {

        if ((!order.hasDefinedPrice()) && (order.isVisibleInOrderbook())) {
          canBeAddedInTheOrderBook = true;
          orderPrice = ((Order) orderBook.getBid().elementAt(0)).getMaxPrice();
        }

        if (orderPrice <= ((Order) orderBook.getBid().elementAt(i)).getMaxPrice()) {
          if (orderRemainingQuantity > 0) {
            int dealQuantity = Math.min(orderRemainingQuantity,
                                      ( (Order) orderBook.getBid().elementAt(i)).getMaxQtty());
            float dealPrice = ( (Order) orderBook.getBid().elementAt(i)).getMaxPrice();
            immediateDealsAmountWithoutTF += dealPrice * dealQuantity;

            orderRemainingQuantity -= dealQuantity;
            if (this.getTransactionFees(immediateDealsAmountWithoutTF, percentageCost, minimalCost) > nonInvestedCash)
              return NOT_ENOUGH_CASH_FOR_ASK;
          }
          else {
              portfolio.soldAssets(assetName, immediateDealsAmountWithoutTF - this.getTransactionFees(immediateDealsAmountWithoutTF, percentageCost, minimalCost), totalQuantity);
              return ORDER_VALID;
          }
        }
      }

      float dealsCostWithTF =  this.getTransactionFees(immediateDealsAmountWithoutTF, percentageCost, minimalCost);

      /*if (totalQuantity != orderRemainingQuantity) {
        portfolio.soldAssets(assetName,immediateDealsAmountWithoutTF - dealsCostWithTF, totalQuantity - orderRemainingQuantity);
      }*/
      float newOrderCostWithTF = 0;

      if (orderRemainingQuantity == 0) {
        //Theoretically, this second test is not necessary
        if (totalQuantity != orderRemainingQuantity) {
          portfolio.soldAssets(assetName,immediateDealsAmountWithoutTF - dealsCostWithTF, totalQuantity - orderRemainingQuantity);
        }
        return ORDER_VALID;
      }
      else
      if (canBeAddedInTheOrderBook) {
        //if (totalQuantity != orderRemainingQuantity)
          newOrderCostWithTF = orderRemainingQuantity * this.getTransactionFees(orderPrice, percentageCost, minimalCost);
        if (dealsCostWithTF + newOrderCostWithTF > nonInvestedCash) {
          return NOT_ENOUGH_CASH_FOR_ASK;
        }
        else {
            if (totalQuantity != orderRemainingQuantity) {
              portfolio.soldAssets(assetName,immediateDealsAmountWithoutTF - dealsCostWithTF, totalQuantity - orderRemainingQuantity);
            }
            portfolio.wantedToBeSoldAssets(newOrderCostWithTF, orderRemainingQuantity, assetName, this.getInstitutionName());
            return ORDER_VALID;
        }
      } else return NOT_ENOUGH_BIDS_IN_ORDERBOOK;
    }

    else {
        //order is a bid
        float immediateDealsAmountWithoutTF = 0;

        for (int i = 0; i<orderBook.getAsk().size(); i++) {

          if ((!order.hasDefinedPrice()) && (order.isVisibleInOrderbook())) {
            canBeAddedInTheOrderBook = true;
            orderPrice = ((Order) orderBook.getAsk().elementAt(0)).getMinPrice();
          }

          if (orderPrice >= ( (Order) orderBook.getAsk().elementAt(i)).getMinPrice()) {
            if (orderRemainingQuantity > 0) {
              int dealQuantity = Math.min(orderRemainingQuantity,
                                          ( (Order) orderBook.getAsk().elementAt(i)).
                                          getMaxQtty());
              float dealPrice = ( (Order) orderBook.getAsk().elementAt(i)).getMinPrice();
              immediateDealsAmountWithoutTF += dealPrice * dealQuantity;

              orderRemainingQuantity -= dealQuantity;
              if (immediateDealsAmountWithoutTF +
                  this.getTransactionFees(immediateDealsAmountWithoutTF, percentageCost,
                                          minimalCost) > nonInvestedCash)
                return NOT_ENOUGH_CASH_FOR_BID;
            }
            else {
                portfolio.boughtAssets(assetName,
                                       immediateDealsAmountWithoutTF +
                                       this.getTransactionFees(immediateDealsAmountWithoutTF, percentageCost,minimalCost), totalQuantity);
                return ORDER_VALID;
            }
          }
        }

        float dealsCostWithTF = immediateDealsAmountWithoutTF + this.getTransactionFees(immediateDealsAmountWithoutTF, percentageCost, minimalCost);

        /*if (totalQuantity != orderRemainingQuantity) {
          portfolio.boughtAssets(assetName, dealsCostWithTF, totalQuantity - orderRemainingQuantity);
        }*/
        float newOrderCostWithTF = 0;

        if (orderRemainingQuantity == 0) {
          //Theoretically, this second test is not necessary
          if (totalQuantity != orderRemainingQuantity) {
            portfolio.boughtAssets(assetName, dealsCostWithTF, totalQuantity - orderRemainingQuantity);
          }
          return ORDER_VALID;
        }
        else
        if (canBeAddedInTheOrderBook) {
          //if (totalQuantity != orderRemainingQuantity)
            newOrderCostWithTF = orderRemainingQuantity * (orderPrice + this.getTransactionFees(orderPrice, percentageCost, minimalCost));
          if (dealsCostWithTF + newOrderCostWithTF > nonInvestedCash) {
            return NOT_ENOUGH_CASH_FOR_BID;
          }
          else {
              //System.out.println("\n4NIC avant = " + nonInvestedCash);
              if (totalQuantity != orderRemainingQuantity) {
                portfolio.boughtAssets(assetName, dealsCostWithTF, totalQuantity - orderRemainingQuantity);
              }
              portfolio.wantedToBeBoughtAssets(newOrderCostWithTF, this.getInstitutionName());
              return ORDER_VALID;
          }
        }
        else return NOT_ENOUGH_ASKS_IN_ORDERBOOK;
    }
  }




/**
   * Returns the price in the orderbook which is the best current price
   * for the order (two cases, for a bid or for an ask).
   * @param side int
   * @return float
   * */
  public float getOrderPrice(int side) {
    return (side == Order.BID) ? this.getMaxPrice() : this.getMinPrice();
  }

  public abstract void setRemainingOrder(int quantity, float price);
}
