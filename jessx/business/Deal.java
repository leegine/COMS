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

import org.jdom.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*                    Deal CLASS SECTION                       */
/***************************************************************/
/**
 * <p>Title: Deal</p>
 * <p>Description: </p>
 * @author Thierry Curtil
 * @version 0.2
 */

public class Deal implements NetworkWritable, NetworkReadable, XmlExportable {

  private float price;
  private String institution;
  private int quantity;
  private long timestamp;
  private String buyer;
  private String seller;

  private String buyerOperation;
  private String sellerOperation;
  private float maxBidPrice;


  /* Getters */

  public float getDealPrice(){
    return price;
  }

  public float getMaxBidPrice() {
    return maxBidPrice;
  }

  public String getDealInstitution(){
    return institution;
  }

  public int getQuantity(){
    return quantity;
  }

  public long getTimestamp(){
    return this.timestamp;
  }

  public String getBuyer(){
  return this.buyer;
}

public String getSeller(){
  return this.seller;
  }

  public String getBuyerOperation(){
  return this.buyerOperation;
}

  public String getSellerOperation(){
    return this.sellerOperation;
  }



  public Deal(String institution, float price, int quantity, long timestamp, String buyer, String seller, float maxBidPrice, String buyerOperation, String sellerOperation) {
    this.institution = institution;
    this.price = price;
    this.quantity = quantity;
    this.timestamp = timestamp;
    this.buyer = buyer;
    this.seller = seller;
    this.maxBidPrice = maxBidPrice;
    this.buyerOperation = buyerOperation;
    this.sellerOperation = sellerOperation;
  }

  public boolean initFromNetworkInput(Element root) {
    String timestamp = root.getAttributeValue("timestamp");
    String institution = root.getAttributeValue("institution");
    String price = root.getAttributeValue("price");
    String quantity = root.getAttributeValue("quantity");
    String buyer = root.getAttributeValue("buyer");
    String seller = root.getAttributeValue("seller");
    String maxBidPrice = root.getAttributeValue("maxBidPrice");
    String buyerOperation = root.getAttributeValue("buyerOperation");
    String sellerOperation = root.getAttributeValue("sellerOperation");


    if ((timestamp == null) || (institution == null) ||
        (price == null) || (quantity == null)|| (maxBidPrice == null) || (buyer == null) ||(seller == null) || (buyerOperation ==null) || (sellerOperation ==null)) {
      Utils.logger.error("Invalid deal xml node: one of the attribute is missing.");
      return false;
    }

    this.timestamp = Long.parseLong(timestamp);
    this.institution = institution;
    this.price = Float.parseFloat(price);
    this.quantity = Integer.parseInt(quantity);
    this.buyer = buyer;
    this.seller = seller;
    this.maxBidPrice = Float.parseFloat(maxBidPrice);
    this.buyerOperation = buyerOperation;
    this.sellerOperation = sellerOperation;

    return true;
  }

  public Element prepareForNetworkOutput(String pt) {
    Element root = new Element("Deal");

    root.setAttribute("timestamp",Long.toString(timestamp))
        .setAttribute("institution",this.institution)
        .setAttribute("price",Float.toString(this.price))
        .setAttribute("quantity",Integer.toString(this.quantity))
        .setAttribute("buyer", this.buyer)
        .setAttribute("seller", this.seller)
        .setAttribute("maxBidPrice",Float.toString(maxBidPrice))
        .setAttribute("buyerOperation", this.buyerOperation)
        .setAttribute("sellerOperation", this.sellerOperation);


    return root;
  }

  public void saveToXml(Element node) {
    Element dNode = new Element("Deal");

    dNode.setAttribute("timestamp",Long.toString(timestamp))
        .setAttribute("institution",this.institution)
        .setAttribute("price",Float.toString(this.price))
        .setAttribute("quantity",Integer.toString(this.quantity))
        .setAttribute("buyer",this.buyer)
        .setAttribute("seller",this.seller)
        .setAttribute("maxBidPrice",Float.toString(maxBidPrice))
        .setAttribute("buyerOperation", this.buyerOperation)
        .setAttribute("sellerOperation", this.sellerOperation);

    node.addContent(dNode);


  }

}
