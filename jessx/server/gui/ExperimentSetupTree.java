package jessx.server.gui;

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

import javax.swing.tree.*;

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*            ExperimentSetupTree CLASS SECTION                */
/***************************************************************/
/**
 * <p>Title : ExperimentSetupTree</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class ExperimentSetupTree
    extends DefaultTreeModel implements AssetListener, InstitutionListener,
    PlayerTypeListener {

  private static DefaultMutableTreeNode experimentRootNode;

  private static DefaultMutableTreeNode assetsNode;
  private static DefaultMutableTreeNode institutionsNode;
  private static DefaultMutableTreeNode scenarioNode;
  private static DefaultMutableTreeNode currentPlayersNode;
  private static DefaultMutableTreeNode playersType;
  private static DefaultMutableTreeNode dividends;
  private static DefaultMutableTreeNode messages;

  ScenarioServerGenericGui scenarioServerGenericGui = new
      ScenarioServerGenericGui(this);
  InstitutionServerGenericGui institutionServerGenericGui = new
      InstitutionServerGenericGui();
  PlayersTypeServerGenericGui playersTypeServerGenericGui = new
      PlayersTypeServerGenericGui(this);
  DividendsServerGenericGui dividendsServerGenericGui = new
      DividendsServerGenericGui();
  MessagesServerGenericGui messagesServerGenericGui = new
      MessagesServerGenericGui();
  ConnectionsPlayersServerGenericGui currentPlayersServerGenericGui = new
      ConnectionsPlayersServerGenericGui();

  private HashMap hashMapDividendSetup = new HashMap();
  private HashMap hashMapplayerTypeSetup = new HashMap();

  public static DefaultMutableTreeNode getScenarioNode() {
    return scenarioNode;
  }

  public static DefaultMutableTreeNode getExperimentRootNode() {
    return experimentRootNode;
  }

  public ExperimentSetupTree(DefaultMutableTreeNode root) {
    super(root);

    this.experimentRootNode = root;

    this.experimentRootNode.setUserObject(BusinessCore.getGeneralParameters());

    this.assetsNode = new DefaultMutableTreeNode(Asset.getAssetServerGenericGui());
    this.experimentRootNode.add(this.assetsNode);

    this.institutionsNode = new DefaultMutableTreeNode(
        institutionServerGenericGui);
    this.experimentRootNode.add(this.institutionsNode);

    this.scenarioNode = new DefaultMutableTreeNode(scenarioServerGenericGui);
    this.playersType = new DefaultMutableTreeNode(playersTypeServerGenericGui);
    this.dividends = new DefaultMutableTreeNode(dividendsServerGenericGui);
    this.messages = new DefaultMutableTreeNode(messagesServerGenericGui);
    this.scenarioNode.add(this.playersType);
    this.scenarioNode.add(this.dividends);
    this.scenarioNode.add(this.messages);
    this.experimentRootNode.add(this.scenarioNode);

    this.currentPlayersNode = new DefaultMutableTreeNode(
        currentPlayersServerGenericGui);
    this.experimentRootNode.add(this.currentPlayersNode);

    // -- registering listener --
    BusinessCore.addInstitutionListener(this);
    BusinessCore.addAssetListener(this);
    BusinessCore.getScenario().addPlayerTypeListener(this);
  }

  public void setAllUneditable() {
    ( (GeneralParameterSetupGui) BusinessCore.getGeneralParameters()).
        setUneditable();
    ( (AssetServerGenericGui) Asset.getAssetServerGenericGui()).setUneditable();
    institutionServerGenericGui.setUneditable();
    scenarioServerGenericGui.setUneditable();
    currentPlayersServerGenericGui.setUneditable();
    playersTypeServerGenericGui.setUneditable();
    dividendsServerGenericGui.setUneditable();
    messagesServerGenericGui.setUneditable();

    HashMap institutions = BusinessCore.getInstitutions();
    Iterator iterator1 = institutions.keySet().iterator();
    while (iterator1.hasNext()) {
      Institution institution = (Institution) BusinessCore.getInstitution( (
          String) iterator1.next());
      institution.desactivePanel();
    }

    Iterator iterator2 = hashMapDividendSetup.keySet().iterator();
    while (iterator2.hasNext()) {
      DividendSetupSlidingWindow dividendSetupSlidingWindow = (
          DividendSetupSlidingWindow) hashMapDividendSetup.get( (
              String) iterator2.next());
      dividendSetupSlidingWindow.setUneditable();
    }

    Iterator iterator3 = hashMapplayerTypeSetup.keySet().iterator();
    while (iterator3.hasNext()) {
      PlayerTypeSetupGui playerTypeSetupGui = (
          PlayerTypeSetupGui) hashMapplayerTypeSetup.get( (
              String) iterator3.next());
      playerTypeSetupGui.setUneditable();
    }

  }

  public void setAllEditable() {
    ( (GeneralParameterSetupGui) BusinessCore.getGeneralParameters()).
        setEditable();
    ( (AssetServerGenericGui) Asset.getAssetServerGenericGui()).setEditable();
    institutionServerGenericGui.setEditable();
    scenarioServerGenericGui.setEditable();
    currentPlayersServerGenericGui.setEditable();
    playersTypeServerGenericGui.setEditable();
    dividendsServerGenericGui.setEditable();
    messagesServerGenericGui.setEditable();

    HashMap institutions = BusinessCore.getInstitutions();
    Iterator iterator = institutions.keySet().iterator();
    while (iterator.hasNext()) {
      Institution institution = (Institution) BusinessCore.getInstitution( (
          String) iterator.next());
      institution.activePanel();
    }

    Iterator iterator2 = hashMapDividendSetup.keySet().iterator();
    while (iterator2.hasNext()) {
      DividendSetupSlidingWindow dividendSetupSlidingWindow = (
          DividendSetupSlidingWindow) hashMapDividendSetup.get( (
              String) iterator2.next());
      dividendSetupSlidingWindow.setEditable();
    }

    Iterator iterator3 = hashMapplayerTypeSetup.keySet().iterator();
    while (iterator3.hasNext()) {
      PlayerTypeSetupGui playerTypeSetupGui = (
          PlayerTypeSetupGui) hashMapplayerTypeSetup.get( (
              String) iterator3.next());
      playerTypeSetupGui.setEditable();
    }

  }

// --- listeners implementation ---

  public void assetsModified(AssetEvent e) {
    if (e.getEvent() == AssetEvent.ASSET_ADDED) {
      this.addAssetToTree(BusinessCore.getAsset(e.getAssetName()));
      this.addDividendNodeToTree(BusinessCore.getAsset(e.getAssetName()));
    }
    else if (e.getEvent() == AssetEvent.ASSET_REMOVED) {
      this.removeAssetFromTree(e.getAssetName());
      this.removeDividendNodeFromTree(e.getAssetName());
    }
  }

  public void institutionsModified(InstitutionEvent e) {
    if (e.getEvent() == InstitutionEvent.INSTITUTION_ADDED) {
      this.addInstitutionToTree(BusinessCore.getInstitution(e.
          getInstitutionName()));
    }
    else if (e.getEvent() == InstitutionEvent.INSTITUTION_REMOVED) {
      this.removeInstitutionFromTree(e.getInstitutionName());
    }
  }

  public void playerTypeModified(PlayerTypeEvent e) {
    if (e.getEvent() == PlayerTypeEvent.PLAYER_ADDED) {
      this.addPlayerTypeToTree(e.getPlayerType());
    }
    else if (e.getEvent() == PlayerTypeEvent.PLAYER_REMOVED) {
      this.removePlayerTypeFromTree(e.getPlayerType().toString());
    }
  }

// --- End of listeners implementation ---

  private void addDividendNodeToTree(Asset asset) {
    DividendSetupSlidingWindow dividendSetupSlidingWindow = new
        DividendSetupSlidingWindow(asset.getAssetName(), asset.getDividendModel());
    hashMapDividendSetup.put(asset.getAssetName(), dividendSetupSlidingWindow);
    this.dividends.add(new DefaultMutableTreeNode(dividendSetupSlidingWindow));
    this.nodesWereInserted(this.dividends,
                           new int[] {dividends.getChildCount() - 1});
  }

  private void removeDividendNodeFromTree(String assetName) {
    hashMapDividendSetup.remove(assetName);
    Enumeration iter = dividends.children();
    while (iter.hasMoreElements()) {
      DefaultMutableTreeNode child = (DefaultMutableTreeNode) iter.nextElement();
      if (child.getUserObject().toString().equalsIgnoreCase(assetName)) {
        int index = dividends.getIndex(child);
        this.dividends.remove(index);
        this.nodesWereRemoved(dividends, new int[] {index}, new Object[] {child});
      }
    }

  }

  private void addAssetToTree(Asset asset) {
    this.assetsNode.add(new DefaultMutableTreeNode(new AssetNode(asset)));
    this.nodesWereInserted(this.assetsNode,
                           new int[] {assetsNode.getChildCount() - 1});
  }

  private void removeAssetFromTree(String assetName) {
    Enumeration iter = assetsNode.children();
    while (iter.hasMoreElements()) {
      DefaultMutableTreeNode child = (DefaultMutableTreeNode) iter.nextElement();
      if (child.getUserObject().toString().equalsIgnoreCase(assetName)) {
        int index = assetsNode.getIndex(child);
        this.assetsNode.remove(index);
        this.nodesWereRemoved(assetsNode, new int[] {index},
                              new Object[] {child});
      }
    }
  }

  private void addInstitutionToTree(Institution institution) {
    this.institutionsNode.add(new DefaultMutableTreeNode(new InstitutionNode(
        institution)));
    this.nodesWereInserted(this.institutionsNode,
                           new int[] {institutionsNode.getChildCount() - 1});
  }

  private void removeInstitutionFromTree(String institutionName) {
    Enumeration iter = institutionsNode.children();
    while (iter.hasMoreElements()) {
      DefaultMutableTreeNode child = (DefaultMutableTreeNode) iter.nextElement();
      if (child.getUserObject().toString().equalsIgnoreCase(institutionName)) {
        int index = institutionsNode.getIndex(child);
        this.institutionsNode.remove(index);
        this.nodesWereRemoved(institutionsNode, new int[] {index},
                              new Object[] {child});
      }
    }
  }

  private void addPlayerTypeToTree(PlayerType pt) {
    PlayerTypeSetupGui playerTypeSetupGui = new PlayerTypeSetupGui(pt);
    hashMapplayerTypeSetup.put(playerTypeSetupGui.toString(),playerTypeSetupGui);
    this.playersType.add(new DefaultMutableTreeNode(playerTypeSetupGui));
    this.nodesWereInserted(this.playersType,
                           new int[] {this.playersType.getChildCount() - 1});
  }

  private void removePlayerTypeFromTree(String playerType) {
    hashMapplayerTypeSetup.remove(playerType);

    Utils.logger.debug("Removing a player type : \"" + playerType +
                       "\" from tree...");
    Enumeration iter = playersType.children();
    while (iter.hasMoreElements()) {
      DefaultMutableTreeNode child = (DefaultMutableTreeNode) iter.nextElement();
      Utils.logger.debug("Scanning player type node named " +
                         child.getUserObject().toString());
      if (child.getUserObject().toString().equalsIgnoreCase(playerType)) {
        int index = this.playersType.getIndex(child);
        this.playersType.remove(index);
        this.nodesWereRemoved(playersType, new int[] {index},
                              new Object[] {child});
      }
    }
  }

}
