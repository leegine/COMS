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
import jessx.net.*;
import org.jdom.*;
import jessx.utils.*;

/***************************************************************/
/*                  OrderBook CLASS SECTION                    */
/***************************************************************/

/**
 * <p>Title : OrderBook</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */
public class OrderBook implements NetworkWritable, NetworkReadable, XmlExportable {

  private String institution;

  private Vector bid = new Vector(); // of type Order
  private Vector ask = new Vector(); // of type Order

  public Vector getBid() {
    return bid;
  }

  public Vector getAsk() {
    return ask;
  }

  public String getInstitution() {
    return this.institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public void reinit() {
    this.ask.clear();
    this.bid.clear();
  }

  public OrderBook() {

  }

  public boolean initFromNetworkInput(Element root) {

    String institution = root.getAttributeValue("institution");

    if (institution == null) {
      Utils.logger.debug("Invalid orderbook xml node: institution attribute is missing.");
      return false;
    }

    this.institution = institution;

    this.bid.removeAllElements();
    this.ask.removeAllElements();

    Element askNode = root.getChild("Ask");
    Element bidNode = root.getChild("Bid");

    if (askNode != null) {
      Iterator iter = askNode.getChildren().iterator();
      while(iter.hasNext()) {
        try {
          Operation op = Operation.initOperationFromXml( (Element) iter.next());
          if (op instanceof Order) {
            this.ask.add(op);
          }
        }
        catch (OperationNotCreatedException ex) {
          Utils.logger.error("Unable to load operation. [IGNORED]");
        }
      }
    }

    if (bidNode != null) {
      Iterator iter = bidNode.getChildren().iterator();
      while(iter.hasNext()) {
        try {
          Operation op = Operation.initOperationFromXml( (Element) iter.next());
          if (op instanceof Order) {
            this.bid.add(op);
          }
        }
        catch (OperationNotCreatedException ex) {
          Utils.logger.error("Unable to load operation. [IGNORED]");
        }
      }
    }

    return true;
  }

  public Element prepareForNetworkOutput(String pt) {

    Element askNode = new Element("Ask");
    Element bidNode = new Element("Bid");

    int i=0; // ask index
    while(i < ask.size()) {
      if (((Order)ask.elementAt(i)).isVisibleInOrderbook()) {
        askNode.addContent(((Order)ask.elementAt(i)).prepareForNetworkOutput(pt));
      }
      i++;
    }

    i=0; // bid index
    while(i < bid.size()) {
      if (((Order)bid.elementAt(i)).isVisibleInOrderbook()) {
        bidNode.addContent(((Order)bid.elementAt(i)).prepareForNetworkOutput(pt));
      }
      i++;
    }

    Element root = new Element("OrderBook");
    root.addContent(askNode);
    root.addContent(bidNode);
    root.setAttribute("institution", this.institution);
    return root;
  }

  public void saveToXml(Element rootNode) {
    Element askNode = new Element("Ask");
    Element bidNode = new Element("Bid");

    int i=0; // ask index

    while(i < ask.size()) {
      askNode.addContent(((Order)ask.elementAt(i)).prepareForNetworkOutput(""));
      i++;
    }

    i=0; // bid index
    while(i < bid.size()) {
      bidNode.addContent(((Order)bid.elementAt(i)).prepareForNetworkOutput(""));
      i++;
    }

    rootNode.addContent(askNode);
    rootNode.addContent(bidNode);
    rootNode.setAttribute("institution", this.institution);
  }


  public Order getOrder(int orderId) {

      for (int i = this.ask.size() - 1; i >= 0; i--) {
        if ( ( (Order)this.ask.elementAt(i)).getId() == orderId) {

          return ( (Order)this.ask.elementAt(i));

        }
      }
      for (int i = this.bid.size() - 1; i >= 0; i--) {
        if ( ( (Order)this.bid.elementAt(i)).getId() == orderId) {

          return ( (Order)this.bid.elementAt(i));

        }
      }
      return (Order)this.ask.elementAt(0);
      //Should never happen
  }

  public void deleteOrder(int orderId) {

    Utils.logger.debug("Deleting order Id = " + orderId + " ...");

    for(int i = this.ask.size()-1; i>=0; i--) {
      if ( ((Order)this.ask.elementAt(i)).getId() == orderId) {
        Utils.logger.debug("Order found. It was an ask. Removing ...");
        this.ask.removeElementAt(i);
        Utils.logger.debug("Order successfully removed.");
      }
    }
    for(int i = this.bid.size()-1; i>=0; i--) {
      if ( ((Order)this.bid.elementAt(i)).getId() == orderId) {
        Utils.logger.debug("Order found. It was an bid. Removing ...");
        this.bid.removeElementAt(i);
        Utils.logger.debug("Order successfully removed.");
      }
    }
  }

  public void clearPlayer(String playerName) {
    for(int i = this.ask.size() - 1; i >= 0; i--) {
      if ( ((Order)this.ask.elementAt(i)).getEmitter().equals(playerName)) {
        this.ask.removeElementAt(i);
      }
    }
    for(int i = this.bid.size() - 1; i >= 0; i--) {
      if ( ((Order)this.bid.elementAt(i)).getEmitter().equals(playerName)) {
        this.bid.removeElementAt(i);
      }
    }
  }



}
