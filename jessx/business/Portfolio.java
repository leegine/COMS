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
import jessx.business.event.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*                  Portfolio CLASS SECTION                    */
/***************************************************************/
/**
 *
 * <p>Title: Portfolio</p>
 * <p>Description: Portofolio of participants</p>
 * @author Thierry Curtil, Jeremy Streque
 * @version 0.2
 */
public class Portfolio implements XmlExportable, XmlLoadable,
    NetworkWritable, NetworkReadable {
  public Portfolio() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // =============================================================================

// --- private fields ---
  private float cash;
  private float nonInvestedCash;
  private HashMap investedCash = new HashMap();

  // nonInvestedCash represents the cash that is available for new orders,
  // i.e. taking into consideration the previous bid orders, even if they
  // didn't lead to a deal yet

  private HashMap ownings = new HashMap();

  private HashMap nonInvestedOwnings = new HashMap();

  private HashMap investedOwnings = new HashMap();
  // nonInvestedOwnings represents the quantity of ownings the player could sell
  // taking into account the previous ask orders
  // investedAssets represents the number of ownings invested on the institutions

  private Vector listeners = new Vector();
// --- end of private fields ---
// =============================================================================

// --- getters and setters ---
  /**
   * Do not modify values from the hashmap. If you need to change them, use
   * instead <code>addAssetToOwnings</code> with the name of the asset and the
   * qtty you want to set. If the asset already exist in the portfolio, its qtty
   * is updated with the new one.
   * @return HashMap
   */
  public HashMap getOwnings() {
    return this.ownings;
  }

  /**
   * Do not modify values from the hashmap. If you need to change them, use
   * instead <code>addAvailableAssetToOwning</code> with the name of the asset and the
   * qtty you want to set.
   * Returns available ownings in the portfolio (i.e. ownings that have not been reserved
   * for an "ask" previously).
   * @return HashMap
   */

  /**
 * Use that method whenever you want to know the number of assets of an user.
 * @param assetName String
 * @return int
 */

  public int getOwnings(String assetName) {
    return ((Integer)this.ownings.get(assetName)).intValue();
  }


  /* Previous version of the documentation
   * Use this function only during set up of the experiment. After the beginning
   * you should use <code>hasEnoughCash</code> and <code>hasEnoughAsset</code>
   * to check the user solvability, and <code>boughtAsset</code> and
   * <code>soldAsset</code> to modify his portfolio after a deal.
   * @param cash float
   */


  public HashMap getNonInvestedOwnings() {
    return this.nonInvestedOwnings;
  }
  /**
   * This function returns the cash owned by the player, at any time of the
   * experiment. Use <code>setAvailableCash</code> to set a value for the cash
   * that is available, i.e. that is not used for a "bid" order. Use
   * <code>hasEnoughCashForBid</code> to check the solvability for a bid and
   * <code>hasEnoughCashForAsk</code> for an ask.
   *
   * @param cash float
   */
  public void setCash(float cash) {
    if (cash != this.cash) {
      this.cash = cash;
      fireCashUpdated();
    }
  }

  /**
   * This function returns the available cash owned by the player, at any time
   * of the experiment, i.e. that is not used for a "bid" order. Use
   * <code>hasEnoughCashForBid</code> to check the solvability for a bid and
   * <code>hasEnoughCashForAsk</code> for an ask.
   *
   * @param nonInvestedCash float
   */
  public void setNonInvestedCash(float nonInvestedCash) {
    this.nonInvestedCash = nonInvestedCash;
  }


  public void setNonInvestedOwnings(HashMap nonInvestedOwnings) {
    this.nonInvestedOwnings =  (HashMap) nonInvestedOwnings.clone();
  }


  /**
   * Returns a float: the current cash of the user
   * @return float
   */

  public float getCash() {
    return this.cash;
  }
/**
   * Returns the cash of the user that is not used for a bid order,
   * i.e. the available (or "not invested") cash of the player.
   * @return float
   * */

  public float getNonInvestedCash() {
    return this.nonInvestedCash;
  }

  public float getInvestedCash(String institutionName) {
    if (this.investedCash.containsKey(institutionName)) {
      return ((Float)this.investedCash.get(institutionName)).floatValue();
    }
    else return 0f;
  }

  public int getInvestedOwnings(String institutionName) {
    if (this.investedOwnings.containsKey(institutionName)) {
    return ((Integer)this.investedOwnings.get(institutionName)).intValue();
  }
  else return 0;
  }

  public int getNonInvestedOwnings(String assetName) {
    if (this.nonInvestedOwnings.containsKey(assetName)) {
        return ((Integer)this.nonInvestedOwnings.get(assetName)).intValue();
      }
      else {
        return this.getOwnings(assetName);
      }
  }

  public void preInitializeInvestments() {
    this.setNonInvestedCash(this.getCash());

    Iterator owningsIter = this.getOwnings().keySet().iterator();
    while(owningsIter.hasNext()) {
            String key = (String)owningsIter.next();
            this.setNonInvestedOwnings(key, this.getOwnings(key));
    }

    //this.nonInvestedOwnings = this.ownings;
  }

public void setInvestmentsWhenKeepingOrderBook(String institutionName, String assetName) {
  if (this.investedCash.containsKey(institutionName)) {
    this.addToNonInvestedCash(-this.getInvestedCash(institutionName));
  }
  if (this.nonInvestedOwnings.containsKey(assetName)) {
    this.addToNonInvestedOwnings(-this.getInvestedOwnings(institutionName), assetName);
  }
}

public void setInvestmentsWhenNotKeepingOrderBook(String institutionName) {
  if (this.investedCash.containsKey(institutionName)) {
      this.investedCash.put(institutionName, new Float (0f));
      this.investedOwnings.put(institutionName, new Integer (0));
      }
}



/*amount>0 => increase invested cash
  amount<0 => decrease invested cash

   */

  public void addToInvestedCash(float amount, String institutionName) {
      this.investedCash.put(institutionName, new Float ( this.getInvestedCash(institutionName) +  amount));
  }

  public void addToNonInvestedCash(float amount) {
      this.setNonInvestedCash(this.getNonInvestedCash() + amount);
  }

/*quantity>0 => increase invested assets
  quantity<0 => decrease invested assets
   */
  public void addToInvestedOwnings(int quantity, String institutionName) {
      this.investedOwnings.put(institutionName, new Integer (this.getInvestedOwnings(institutionName) + quantity));
    }

  public void addToNonInvestedOwnings(int quantity, String assetName) {
    this.nonInvestedOwnings.put(assetName, new Integer (this.getNonInvestedOwnings(assetName) + quantity));
  }




  public void addToOwnings(int quantity, String assetName) {
      this.ownings.put(assetName, new Integer (this.getOwnings(assetName) + quantity));
    }

    public void addToCash(float amount) {
      this.cash += amount;
    }

  /**
   *
   *
   * Use this methods only during setup. After that, you should use
   * <code>boughtAsset</code> and <code>soldAsset</code> to modify the user
   * portfolio after a deal. During setup you should only use that method when
   * you modify the porfolio content, otherwise listeners won't be noticed.
   *
   * @param assetName String
   * @param qtty int
   */
  public void setOwnings(String assetName, int qtty) {
    if (this.ownings.containsKey(assetName)) {
      if (this.getOwnings(assetName) != qtty) {
        this.ownings.put(assetName, new Integer(qtty));
        fireAssetUpdated(assetName);
      }
    }
    else {
      this.ownings.put(assetName, new Integer(qtty));
      fireAssetAdded(assetName);
    }
  }

  /**
   * Allows the update of quantities of available assets (i.e. that are not put
   * in an ask order).
   *
   * @param assetName String
   * @param qtty int
   */

  public void setNonInvestedOwnings(String assetName, int qtty) {
    this.nonInvestedOwnings.put(assetName, new Integer(qtty));
  }
  /**
   * Allows the update of quantities of owned assets
   *
   * @param assetName String
   */
  public void removeAssetFromOwnings(String assetName) {
    this.ownings.remove(assetName);
    fireAssetRemoved(assetName);
  }






  /**
   * Use that method whenever you want to know the number of available assets
   * ( = not used for an ask order) of an user.
   * @param assetName String
   * @return int
   */




  // --- end of getters and setters ---
// =============================================================================

// --- constructors ---
  /**
   * @param cash float
   * @param ownings HashMap the key is an asset name, the value an Integer
   * (qtty owned)
   */
  public Portfolio(float cash, HashMap ownings) {
    this.setCash(cash);
    Iterator assetNameIterator = ownings.keySet().iterator();
    while(assetNameIterator.hasNext()) {
      String key = (String)assetNameIterator.next();
      this.setOwnings(key,((Integer)ownings.get(key)).intValue());
    }
  }


// --- end of constructors ---
// =============================================================================

// --- public services ---


  /**
   * Return the operation cost according to the quantity and the price of the
   * exchanged assets, and the percentage and minimal costs that have been
   * defined in the setup of the experiment.
   * @param quantity int
   * @param price float
   * @param percentageCost float
   * @param minimalCost float
   * @return float
   * */


  public float operationCost(int quantity, float price, float percentageCost, float minimalCost) {
    float proportionalCost = (( quantity * price * percentageCost ) / 100);
    return Math.max(minimalCost, proportionalCost);
  }

  /**
   * Adds the number of assets that have been bought to the quantity already owned by the player, and updates the cash
   * to take into account the transaction.
   *
   * @param assetName float
   * @param amount float
   * @param quantity int
   */
  public void boughtAssets(String assetName, float amount, int quantity) {
    this.addToCash(-amount);
    this.addToNonInvestedCash(-amount);
    this.addToOwnings(quantity, assetName);
    this.addToNonInvestedOwnings(quantity, assetName);
  }

  public void boughtAssetsInOrderBook(String assetName, float dealPrice, int quantity, String institutionName, float percentageCost, float minimalCost) {
    this.addToCash( - quantity * dealPrice
                    - this.operationCost(quantity, dealPrice, percentageCost, minimalCost));
    this.addToNonInvestedCash(quantity * this.operationCost(1, dealPrice, percentageCost, minimalCost)
                              - this.operationCost(quantity, dealPrice, percentageCost, minimalCost));
    this.addToInvestedCash(-quantity * (dealPrice + quantity * this.operationCost(1, dealPrice, percentageCost, minimalCost)), institutionName);
    this.addToOwnings(quantity, assetName);
    this.addToNonInvestedOwnings(quantity, assetName);
  }

  /**
   * Removes the number of assets that have been sold by the player from his ownings and updates the cash to take into
   * account the transaction.
   *
   * @param assetName String
   * @param amount float
   * @param quantity int
   */
  public void soldAssets(String assetName, float amount, int quantity) {
    this.addToCash(amount);
    this.addToNonInvestedCash(amount);
    this.addToOwnings(-quantity, assetName);
    this.addToNonInvestedOwnings(-quantity, assetName);
  }



  public void soldAssetsInOrderBook(String assetName, float dealPrice, int quantity, String institutionName, float percentageCost, float minimalCost) {
    this.addToCash(quantity * dealPrice - this.operationCost(quantity, dealPrice, percentageCost, minimalCost));
    this.addToInvestedCash(-quantity * this.operationCost(1, dealPrice, percentageCost, minimalCost), institutionName);
    this.addToNonInvestedCash(quantity * dealPrice
                              + quantity * this.operationCost(1, dealPrice, percentageCost, minimalCost)
                              - this.operationCost(quantity, dealPrice, percentageCost, minimalCost));
    this.addToOwnings(-quantity, assetName);
    this.addToInvestedOwnings(-quantity, institutionName);
  }

  public void cancelOrder(int side,
                          float price,
                          int quantity,
                          String assetName,
                          String institutionName,
                          float orderPercentageCost,
                          float orderMinimalCost,
                          float deletionPercentageCost,
                          float deletionMinimalCost) {

    //side = 1 for a bid and 0 for an ask
    this.setCash(this.getCash() - this.operationCost(quantity, price, deletionPercentageCost,deletionMinimalCost));

    this.addToNonInvestedCash(side * price * quantity
                                 + quantity * this.operationCost(1, price, orderPercentageCost, orderMinimalCost)
                                 - this.operationCost(quantity, price, deletionPercentageCost,deletionMinimalCost));

    this.addToInvestedCash(-side * price * quantity
                              - quantity * this.operationCost(1, price, orderPercentageCost, orderMinimalCost), institutionName);

    if (side == Order.ASK) {
      this.addToNonInvestedOwnings(quantity, assetName);
      this.addToInvestedOwnings(-quantity, institutionName);
    }
  }




  public void wantedToBeBoughtAssets(float amount, String institutionName) {
    this.addToNonInvestedCash(-amount);
    this.addToInvestedCash(amount, institutionName);
  }

  public void wantedToBeSoldAssets(float amount, int quantity, String assetName, String institutionName) {
    this.addToNonInvestedCash(-amount);
    this.addToInvestedCash(amount, institutionName);
    this.addToNonInvestedOwnings(-quantity, assetName);
    this.addToInvestedOwnings(quantity, institutionName);
  }



/**
   * Updates the cash at every end of period according to the value of the
   * dividends of each asset.
   * @param assetName String
   * @param dividend float
   * return void
   * */
  public float payDividend(String assetName, float dividend) {
    this.setCash(this.getCash() + (this.getOwnings(assetName)) * dividend);
    return ((this.getOwnings(assetName)) * dividend);
  }

// ---- listeners management -----

  public void addListener(PortfolioListener listener) {
    if(!this.listeners.contains(listener));
    this.listeners.add(listener);
  }

  public void removeListener(PortfolioListener listener) {
    this.listeners.remove(listener);
  }

  public void saveToXml(Element node) {
    Utils.logger.debug("Saving portfolio to xml...");

    // - saving cash
    node.setAttribute("cash",Float.toString(this.getCash()));
    //node.setAttribute("nonInvestedCash",Float.toString(this.getNonInvestedCash()));
    // - saving ownings
    Iterator owningsIter = this.getOwnings().keySet().iterator();
    while(owningsIter.hasNext()) {
      String key = (String)owningsIter.next();
      node.addContent(new Element("Owning")
                      .setAttribute("asset",key)
                      .setAttribute("qtty",
                                    Integer.toString(this.getOwnings(key)))
                      /*.setAttribute("availableQtty", Integer.toString(this.getNonInvestedOwnings(key)))*/);
    }

    // - saving investments
    /*
    Iterator institutionsIter = this.investedOwnings.keySet().iterator();
    while(owningsIter.hasNext()) {
      String key = (String)institutionsIter.next();
      System.out.println("investments on " + key);
      node.addContent(new Element("Investment")
                      .setAttribute("institution",key)
                      .setAttribute("investedOwnings",
                                    Integer.toString(this.getInvestedOwnings(key)))
                      .setAttribute("investedCash", Float.toString(this.getInvestedCash(key))));

    }
    */
  }

  public void loadFromXml(Element node) {
    Utils.logger.debug("Loading portfolio from xml...");


    // - loading cash


    String cash = node.getAttributeValue("cash");
    //String nonInvestedCash = node.getAttributeValue("nonInvestedCash");





    if ((cash == null) /*|| (nonInvestedCash == null)*/) {
      Utils.logger.error("Invalid xml portfolio node: " +
                         "attribute cash not found.");
      return;
    }

    this.setCash(Float.parseFloat(cash));
    //this.setNonInvestedCash(Float.parseFloat(nonInvestedCash));




    // - loading ownings
    Iterator owningsIter = node.getChildren("Owning").iterator();
    while (owningsIter.hasNext()) {
      Element owning = (Element)owningsIter.next();
      String asset = owning.getAttributeValue("asset");
      String qtty = owning.getAttributeValue("qtty");
      //String availableQtty = owning.getAttributeValue("availableQtty");
      if ((asset == null) || (qtty == null) /*|| (availableQtty == null)*/) {
        Utils.logger.error("Invalid owning xml node: " +
                           "attributes asset and/or qtty not found.");
        return;
      }
      this.setOwnings(asset,Integer.parseInt(qtty));
      //this.addAssetToNonInvestedOwnings(asset, Integer.parseInt(availableQtty));

    }

    // - loading investments
    /*
    Iterator institutionsIter = node.getChildren("Investment").iterator();
    while (institutionsIter.hasNext()) {
      Element investment = (Element)institutionsIter.next();
      String institution = investment. getAttributeValue("institution");
      String investedOwnings = investment.getAttributeValue("investedOwnings");
      String investedCash = investment.getAttributeValue("investedCash");
      this.investedOwnings.put(institution, new Integer (Integer.parseInt(investedOwnings)));
      this.investedCash.put(institution, new Float (Float.parseFloat(investedCash)));

    }
    */
  }




  public Object clone() {
    return new Portfolio(this.cash, this.ownings);
  }

  public Element prepareForNetworkOutput(String pt) {
    Element rootPortfolio = new Element("Portfolio");
    this.saveToXml(rootPortfolio);
    return rootPortfolio;
  }

  public boolean initFromNetworkInput(Element rootNode) {
    this.loadFromXml(rootNode);
    return true;
  }

// --- end of public services ---
// =============================================================================

// --- private services ---

  private void fireAssetUpdated(String assetName) {
    for(int i = 0; i < this.listeners.size(); i++) {
      ((PortfolioListener)this.listeners.elementAt(i))
          .portfolioModified(new PortfolioEvent(assetName,
                                                PortfolioEvent.ASSET_UPDATED));
    }
  }


  private void fireCashUpdated() {
    for(int i = 0; i < this.listeners.size(); i++) {
      ((PortfolioListener)this.listeners.elementAt(i))
          .portfolioModified(new PortfolioEvent(PortfolioEvent.CASH_UPDATED));
    }
  }

  private void fireAssetAdded(String assetName) {
    for(int i = 0; i < this.listeners.size(); i++) {
      ((PortfolioListener)this.listeners.elementAt(i))
          .portfolioModified(new PortfolioEvent(assetName,
                                                PortfolioEvent.ASSET_ADDED));
    }
  }


  private void fireAssetRemoved(String assetName) {
    for(int i = 0; i < this.listeners.size(); i++) {
      ((PortfolioListener)this.listeners.elementAt(i))
          .portfolioModified(new PortfolioEvent(assetName,
                                                PortfolioEvent.ASSET_REMOVED));
    }

  }

  private void jbInit() throws Exception {
  }

  // --- end of private services ---
// =============================================================================
}
