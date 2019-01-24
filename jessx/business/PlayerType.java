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
import jessx.utils.*;

/***************************************************************/
/*                 PlayerType CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : PlayerType</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class PlayerType implements AssetListener, XmlLoadable, XmlExportable {

// =============================================================================
// --- fields ---
  private HashMap operatorList = new HashMap();
  private Vector institutionsVect = new Vector();
  private Portfolio initialPortfolio;

  private Vector playerTypeListeners = new Vector();

  private String playerTypeName;

  /**
   * The key is the name of an asset, the value is a DividendInfo object
   */
  private HashMap dividendInfo = new HashMap();

// --- end of fields ---
// =============================================================================
// --- getters and setters


/**
   * Returns the name of the player type
   * @return playTypeName String
   * */
  public String getPlayerTypeName() {
    return this.playerTypeName;
  }


/**
   * Returns the list of operators
   * @return operatorList
   * */
  public HashMap getOperatorsPlayed() {
    return this.operatorList;
  }

  /**
   * complete name: "operatorName on institutionName"
   * @param operatorCompleteName String
   * @return Operator
   */
  public Operator getOperatorPlayed(String operatorCompleteName) {
    return (Operator)this.operatorList.get(operatorCompleteName);
  }

  public Vector getInstitutionsWherePlaying() {
    return this.institutionsVect;
  }
/**
   * Returns the portfolio
   * @return initialPortfolio Portfolio
   * */

  public Portfolio getPortfolio() {
    return this.initialPortfolio;
  }

  /**
   * Sets the portfolio and its contents
   *
   * @param portfolio Portfolio
   */
  public void setPortfolio(Portfolio portfolio) {
    this.initialPortfolio = portfolio;
  }

  /**
   * Adds a played operator to <code>operatorList</code>
   *
   * @param oper Operator
   */
  public void addOperatorPlayed(Operator oper) {
    this.operatorList.put(oper.toString(),oper);
    this.fireOperatorPlayedAdded(oper);
  }

  public void addInstitution(String institution) {
    this.institutionsVect.add(institution);
  }

  /**
   * Removes a played operator from <code>operatorList</code>
   *
   * @param oper Operator
   */
  public void removeOperatorPlayed(Operator oper) {
    this.operatorList.remove(oper.toString());
    this.fireOperatorPlayedRemoved(oper);
  }
/**
   * Returns the dividend limitation for the asset (the player cannot have
   * access to the whole information about dividends).
   * @param assetName String
   * @return DividendLimtation
   * */

  public DividendLimitation getDividendInfo(String assetName) {
    return (DividendLimitation)this.dividendInfo.get(assetName);
  }

  /**
   * Sets the dividend limitation for the asset.
   *
   * @param assetName String
   * @param divInfo DividendLimitation
   */
  public void setDividendInfo(String assetName, DividendLimitation divInfo) {
    this.dividendInfo.put(assetName, divInfo);
  }

// --- end of getters and setters
// =============================================================================
// --- Listeners management ---

  /**
   * Listener for the addition of a player type
   *
   * @param listener PlayerTypeListener
   */
  public void addPlayerTypeListener(PlayerTypeListener listener) {
    this.playerTypeListeners.add(listener);
  }

  /**
   * Listener for the removal of a player type
   *
   * @param listener PlayerTypeListener
   */
  public void removePlayerTypListener(PlayerTypeListener listener) {
    this.playerTypeListeners.remove(listener);
  }

// --- end of listeners management ---
// =============================================================================
// --- public services ---


/**
   *PlayerType
   * @param name String
   * */
  public PlayerType(String name) {
    this.playerTypeName = name;
    this.initialPortfolio = new Portfolio(0, new HashMap());

    // - initiating dividend info
    Iterator assetIter = BusinessCore.getAssets().keySet().iterator();
    while (assetIter.hasNext()) {
      String key = (String) assetIter.next();
      this.setDividendInfo(key, new DividendLimitation(this.getPlayerTypeName(),key));
    }

    // - adding asset listener
    BusinessCore.addAssetListener(this);
  }

/**
   * Loads from the XML the Element xmlPTNode (PT = Player Type)
   * @param xmlPTNode Element
   * */

  public PlayerType(Element xmlPTNode) {
    this("");
    this.loadFromXml(xmlPTNode);
  }

/**
   * Returns the name of the playerType
   * @return String
   * */

  public String toString() {
    return this.getPlayerTypeName();
  }

  /**
   * Updates the dividend infos in the case of an addition or a removal of assets
   *
   * @param e AssetEvent
   */
  public void assetsModified(AssetEvent e) {
    if (e.getEvent() == AssetEvent.ASSET_ADDED) {
      if (dividendInfo.get(e.getAssetName()) == null) {
        this.dividendInfo.put(e.getAssetName(), new DividendLimitation(this.getPlayerTypeName(),e.getAssetName()));
      }
    }
    else if (e.getEvent() == AssetEvent.ASSET_REMOVED) {
      this.dividendInfo.remove(e.getAssetName());
    }
  }

  /**
   * Saves to the XML the player type and its attributes.
   *
   * @param node Element
   */
  public void saveToXml(Element node) {
    Utils.logger.debug("Saving playerType " + this.getPlayerTypeName() + "...");

    // - saving playerType name
    Utils.logger.debug("Saving playerType name...");
    node.setAttribute("name",this.getPlayerTypeName());

    // - saving operator list

    Vector keys = Utils.convertAndSortMapToVector(getOperatorsPlayed());
    int keysCount = keys.size();
    Utils.logger.debug("Saving operator list...");
    Element opPlayedNode = new Element("OperatorsPlayed");

    for (int i = 0; i < keysCount; i++) {
      Element op = new Element("Operator");
      op.setAttribute("name",keys.get(i).toString());
      opPlayedNode.addContent(op);
      Utils.logger.info("\n" + keys.get(i).toString() + "\n");
    }
    node.addContent(opPlayedNode);

    // - saving initial portfolio
    Utils.logger.debug("Saving initial portfolio...");
    Element portfolioNode = new Element("Portfolio");
    this.getPortfolio().saveToXml(portfolioNode);
    node.addContent(portfolioNode);

    // - saving dividendInfo
    Utils.logger.debug("Saving dividends Info...");

    Iterator divInfosIter = this.dividendInfo.keySet().iterator();
    while(divInfosIter.hasNext()) {
      String key = (String)divInfosIter.next();
      Utils.logger.debug("Saving dividend info on the asset: " + key);
      Element divInfoNode = new Element("DividendInfo");
      ((DividendLimitation)this.getDividendInfo(key)).saveToXml(divInfoNode);
      node.addContent(divInfoNode);
    }

  }

  /**
   * Institution *must* have been loaded first
   *
   * @param node Element
   */
  public void loadFromXml(Element node) {
    Utils.logger.debug("Loading playerType...");

    // - loading name
    Utils.logger.debug("Loading name...");
    String ptName = node.getAttributeValue("name");
    if (ptName == null) {
      Utils.logger.error("Invalid xml playertype node: attribute name not found.");
      return;
    }
    this.playerTypeName = ptName;
    Utils.logger.info("playertype name : " + ptName);

    // - loading operator list
    Utils.logger.debug("operator played...");
    Iterator opPlayedIter = node.getChild("OperatorsPlayed").getChildren("Operator").iterator();
    while(opPlayedIter.hasNext()) {
      Element op = (Element)opPlayedIter.next();

      String opCompleteName = op.getAttributeValue("name");
      if (opCompleteName == null) {
        Utils.logger.error("Invalid operatorPlayed xml node : attribue name not found.");
        return;
      }

      Utils.logger.debug("Operator found. Complete name: " + opCompleteName);

      int index = opCompleteName.lastIndexOf(" on ");
      String opName = opCompleteName.substring(0,index);
      String institutionName = opCompleteName.substring(index + 4);

      Utils.logger.debug("Operator name: >-" + opName + "-< institution name: >-" + institutionName + "-<");

      this.addOperatorPlayed(BusinessCore.getInstitution(institutionName).getOperator(opName));
      this.addInstitution(institutionName);
    }

    // - loading initial portfolio
    Utils.logger.debug("Loading portfolio...");
    Portfolio portfolio = new Portfolio(0f, new HashMap());
    Utils.logger.debug("\n" + "PlayerType avant " + this.initialPortfolio.getNonInvestedCash() + "\n");
    portfolio.loadFromXml(node.getChild("Portfolio"));
    this.setPortfolio(portfolio);
    Utils.logger.debug("\n" + "PlayerType apres " + this.initialPortfolio.getNonInvestedCash() + "\n");

    // - loading dividendInfo
    Utils.logger.debug("Loading dividend Infos...");
    Iterator divInfoNodes = node.getChildren("DividendInfo").iterator();
    while(divInfoNodes.hasNext()) {
      DividendLimitation divInfo = new DividendLimitation(this.getPlayerTypeName(),(Element)divInfoNodes.next());
      this.setDividendInfo(divInfo.getAssetName(),divInfo);
    }
  }

// --- end of public services ---
// =============================================================================
// --- private services ---
  /**
   * Fires the addition of an operator played by the members of this player type.
   *
   * @param oper Operator
   */
  private void fireOperatorPlayedAdded(Operator oper) {
    for(int i = 0; i < this.playerTypeListeners.size(); i++) {
      ((PlayerTypeListener)this.playerTypeListeners.elementAt(i)).playerTypeModified(new PlayerTypeEvent(this,PlayerTypeEvent.OPERATOR_GRANTED, oper));
    }
  }

  /**
   * Fires the removal of an operator played by the members of this player type.
   *
   * @param oper Operator
   */
  private void fireOperatorPlayedRemoved(Operator oper) {
    for(int i = 0; i < this.playerTypeListeners.size(); i++) {
      ((PlayerTypeListener)this.playerTypeListeners.elementAt(i)).playerTypeModified(new PlayerTypeEvent(this,PlayerTypeEvent.OPERATOR_DENIED, oper));
    }
  }

// --- end of private services ---
// =============================================================================
}
