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
/*                   Scenario CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title: Scenario</p>
 * <p>Description : Manage the parameters of the scenario such as PlayerTypes, Dividend info and Information</p>
 * @authors: Thierry Curtil, Christophe Grosjean, Charles Montez
 * @version: 1.0 (year 2005)
 */

public class Scenario implements XmlExportable, XmlLoadable {

  private HashMap playerTypes = new HashMap();
  private Vector playerTypeListeners = new Vector();
  private Vector vectorInformation = new Vector();
  private Vector infoListners = new Vector();
  private String password="";
  private boolean passwordUsed=false;
  private boolean listOfParticipantsUsed=false;
  private Vector listOfParticipants=new Vector();

  /** Constructor
  */
  public Scenario() {
  }


// Methodes about the dividends --------------

  /**
   * Gets the dividend info about the asset for the given player type.
   * @param assetName String
   * @param playerType String
   * @return DividendLimitation
   * */

  public DividendLimitation getDividendInfo(String assetName, String playerType) {
    Utils.logger.debug("Getting dividend info for asset : " + assetName +
                       " and playerType: " + playerType);
    return ( (PlayerType)this.playerTypes.get(playerType)).getDividendInfo(
        assetName);
  }

  /**
   * Sets the dividend info (stored in <code>value</code>) about the asset for the given player type
   * @param assetName String
   * @param playerType String
   * @param value DividendLimitation
   * */

  public void setDividendInfo(String assetName, String playerType,
                              DividendLimitation value) {
    ( (PlayerType)this.playerTypes.get(playerType)).setDividendInfo(assetName,
        value);
  }

  /**
   * Gets the object PlayerType when its name is given.
   * @param name String
   * @return PlayerType
   * */


// Methodes about the player type. -----------

  public PlayerType getPlayerType(String name) {
    return (PlayerType) playerTypes.get(name);
  }

  /**
   * Gets the hashmap containing the playtypes.
   * @return playerTypes HashMap
   * */

  public HashMap getPlayerTypes() {
    return playerTypes;
  }

  /**
   * Adds a player type to the hashmap playerTypes.
   * @param playerType PlayerType
   * */

  public void addPlayerType(PlayerType playerType) {
    if (playerType != null) {
      playerTypes.put(playerType.getPlayerTypeName(), playerType);
      firePlayerTypeAdded(playerType);

    }
  }

  /**
   * Removes a player type from the hashmap playerTypes
   * @param playerType PlayerTypes
   * */

  public void removePlayerType(PlayerType playerType) {
    if (playerType != null) {
      if (playerTypes.containsValue(playerType)) {
        playerTypes.remove(playerType.getPlayerTypeName());
        firePlayerTypeRemoved(playerType);
      }
    }
  }

  /**
   * Fires the addition of a player type to playerTypeListeners.
   * @param playerType PlayerType
   * */

  protected void firePlayerTypeAdded(PlayerType playerType) {
    for (int i = 0; i < playerTypeListeners.size(); i++) {
      ( (PlayerTypeListener) playerTypeListeners.elementAt(i)).
          playerTypeModified(new PlayerTypeEvent(playerType,
                                                 PlayerTypeEvent.PLAYER_ADDED, null));

    }

  }

  /**
   * Fires the removal of a player type to playerTypeListeners.
   * @param playerType PlayerType
   * */

  protected void firePlayerTypeRemoved(PlayerType playerType) {
    for (int i = 0; i < playerTypeListeners.size(); i++) {
      ( (PlayerTypeListener) playerTypeListeners.elementAt(i)).
          playerTypeModified(new PlayerTypeEvent(playerType,
                                                 PlayerTypeEvent.PLAYER_REMOVED, null));
    }
  }

  /**
   * Adds a new listener to playerTypeListeners
   * @param listener PlayerTypeListener
   * */
  public void addPlayerTypeListener(PlayerTypeListener listener) {
    playerTypeListeners.add(listener);
  }

  /**
   * Removes a listener from playerTypeListeners
   * @param listener PlayerTypeListener
   * */
  public void removePlayerTypeListener(PlayerTypeListener listener) {
    playerTypeListeners.remove(listener);
  }


// Methodes about Programmed Information ------

  /** A getter
   * @return Vector
   */
  public Vector getListInformation() {
    return vectorInformation;
  }

  /** A setter : it add a row at the list of current vectorInformation
   * @param info String[]
   */
  public void addInformation(String[] info) {
    vectorInformation.add(info);
    fireInfoAdded(info);
    Utils.logger.debug("add "+info);
  }
  public void removeInformation(int i) {
    vectorInformation.remove(i);
    fireInfoRemoved(i);
    Utils.logger.debug("remove info number "+i);
  }
  public void removeAllInformation() {
    vectorInformation.clear();
    fireInfoClear();
    Utils.logger.debug("remove all info");
  }
  public void changeInformation(int i, String[] message) {
    vectorInformation.set(i, message);
    Utils.logger.debug("change "+i);
  }



  protected void fireInfoAdded(String[] info){
    for (int i = 0; i < infoListners.size(); i++) {
        ( (ProgrammedInfoListener) infoListners.elementAt(i)).
            programmedInfoModified(new
                                   ProgrammedInfoEvent((Object) info,
            ProgrammedInfoEvent.PROGRAMMEDINFO_ADDED));
        Utils.logger.debug("fireadd"+ info);
      }

  }
  protected void fireInfoRemoved(int num) {
    for (int i = 0; i < infoListners.size(); i++) {
      ( (ProgrammedInfoListener) infoListners.elementAt(i)).
          programmedInfoModified(new
                                 ProgrammedInfoEvent((Object) new Integer(num),
          ProgrammedInfoEvent.PROGRAMMEDINFO_REMOVED));
      Utils.logger.debug("fireremov"+ num);
    }
  }
  protected void fireInfoClear() {
    for (int i = 0; i < infoListners.size(); i++) {
         ( (ProgrammedInfoListener) infoListners.elementAt(i)).
             programmedInfoModified(new
                                    ProgrammedInfoEvent((Object) new Integer(-1),
             ProgrammedInfoEvent.PROGRAMMEDINFO_ALLREMOVED));
    }
          Utils.logger.debug("fireremovall"+ -1);
  }



  protected void fireProgammedInfoLoad() {
    for (int i = 0; i < infoListners.size(); i++) {
      ( (ProgrammedInfoListener) infoListners.elementAt(i)).
          programmedInfoModified(new
                                 ProgrammedInfoEvent((Object) vectorInformation,
          ProgrammedInfoEvent.PROGRAMMEDINFO_LISTLOADED));
    }
  }



  public void addProgrammedInfoListener(ProgrammedInfoListener listener) {
    infoListners.add(listener);
  }

  public void removedInfoListener(ProgrammedInfoListener listener) {
    infoListners.remove(listener);
  }

// Methodes about the XML

  /**
   * Saves all the player types and the information sent on the network to an XML backup:
   * first the 'PlayerType' 's data, secodly
   * @param node Element
   * */
  public void saveToXml(Element node) {

    Utils.logger.debug("Saving scenario...");
    Vector keys = Utils.convertAndSortMapToVector(this.getPlayerTypes());

    int keysCount = keys.size();
    for (int i = 0; i < keysCount; i++) {
      Element ptNode = new Element("PlayerType");
      this.getPlayerType((String) keys.get(i)).saveToXml(ptNode);
      node.addContent(ptNode);
    }

    int size = vectorInformation.size();
    Element informationNode = new Element("Information");
    for (int i = 0; i < size; i++) {
      Element infoNode = new Element("Information");
      infoNode.setAttribute("Content", ( (String[]) vectorInformation.get(i))[0]);
      infoNode.setAttribute("Category", ( (String[]) vectorInformation.get(i))[1]);
      infoNode.setAttribute("Period", ( (String[]) vectorInformation.get(i))[2]);
      infoNode.setAttribute("Time", ( (String[]) vectorInformation.get(i))[3]);
      informationNode.addContent(infoNode);
    }
    node.addContent(informationNode);
  }

  /**
   * Loads the player types from the XML
   * @param node Element
   * */
  public void loadFromXml(Element node) {
    Utils.logger.debug("Loading scenario...");
    // Loading Playertypes
    // Todo : Test pour verifier que les donnees sont bien en accord! pour le Player Type
    Iterator ptIter = node.getChildren("PlayerType").iterator();
    while (ptIter.hasNext()) {
      Element ptElem = (Element) ptIter.next();
      PlayerType pt = new PlayerType(ptElem);
      this.addPlayerType(pt);
    }
    // Loading Programmed Information
    //Todo : Test pour verifier que les donnees sont bien en accord! pour les infos
    Iterator infoIter = node.getChild("Information").getChildren("Information").iterator();
    Utils.logger.debug(infoIter);

    if(vectorInformation.size()>0) removeAllInformation();

    while (infoIter.hasNext()) {
      Element infoElem = (Element) infoIter.next();
      // testing if the content of the xml fits to the expected datas
      Utils.logger.debug("crea infoElem =" + infoElem);
      String infoContent = new String(infoElem.getAttributeValue("Content")); //create a copy of the content of the message
      if (true);
      Utils.logger.debug("------" + infoContent);
      String infoReceivers = new String(infoElem.getAttributeValue("Category"));
      if (true);
      String infoPeriode = new String(infoElem.getAttributeValue("Period"));
      if (true);
      String infoTime = new String(infoElem.getAttributeValue("Time"));
      if (true);

      vectorInformation.add(new String[] {infoContent,infoReceivers,infoPeriode,infoTime} ); // the pieces of info are with the scenario

      Utils.logger.debug(vectorInformation);
    }

    fireProgammedInfoLoad(); // need to fill the graphical interface with the datas
  }

  public void setPasswordUsed(boolean b) {
    this.passwordUsed = b;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public boolean isPasswordUsed() {
    return this.passwordUsed;
  }

  public void setlistOfParticipantsUsed(boolean b) {
    this.listOfParticipantsUsed = b;
  }

  public void setlistOfParticipants(Vector listOfParticipants) {
    this.listOfParticipants = listOfParticipants;
  }

  public Vector getlistOfParticipants() {
    return this.listOfParticipants;
  }

  public boolean islistOfParticipantsUsed() {
    return this.listOfParticipantsUsed;
  }

}
