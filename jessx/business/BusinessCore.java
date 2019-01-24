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
import jessx.utils.*;


/***************************************************************/
/*             BusinessCore CLASS SECTION                      */
/***************************************************************/
/**
 * <p>Title : BusinessCore</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 0.1
 */

public abstract class BusinessCore {

  private static HashMap institutions= new HashMap();
  private static HashMap assets = new HashMap();
  private static Scenario scenario = new Scenario();
  private static Element chatNode;

  /**
   * These two listeners are used during the setup of the eperiment, so that
   * all panels that wan't to know when an asset is added or removed are
   * notified.
   */
  private static Vector assetsListeners = new Vector();
  private static Vector institutionsListeners = new Vector();


  private static GeneralParameters generalParam;

  public static void init() {

  }

  public static void setGeneralParameters(GeneralParameters genParams) {
    generalParam = genParams;
  }

  public static GeneralParameters getGeneralParameters() {
    return generalParam;
  }

  public static Scenario getScenario() {
    return scenario;
  }


  public static Institution getInstitution(String institutionName) {
    return (Institution)institutions.get(institutionName);
  }

  /**
   * Do not use that method to add or remove institutions. Use the methods
   * implemented: they will fire listeners of that modification. Otherwise,
   * all listeners classes won't be notified.
   * @return HashMap
   */
  public static HashMap getInstitutions() {
    return institutions;
  }

  public static Asset getAsset(String assetName) {
    return (Asset)assets.get(assetName);
  }

  /**
   * Do not use that method to add or remove assets. Use the methods
   * implemented: they will fire listeners of that modification. Otherwise,
   * all listeners classes won't be notified.
   * @return HashMap
   */
  public static HashMap getAssets() {
    return assets;
  }



  public static void addAsset(Asset asset) {
    if (asset != null) {
      assets.put(asset.getAssetName(), asset);
      fireAssetAdded(asset);
    }
  }

  public static void removeAsset(Asset asset) {
    if (asset != null) {
      if (assets.containsValue(asset)) {
        assets.remove(asset.getAssetName());
        fireAssetRemoved(asset);
      }
    }
  }

  public static void addInstitution(Institution institution) {
    if (institution != null) {
      institutions.put(institution.getName(), institution);
      fireInstitutionAdded(institution);
    }
  }

  public static void removeInstitution(Institution institution) {
    if (institution != null) {
      if (institutions.containsValue(institution)) {
        institutions.remove(institution.getName());
        fireInstitutionRemoved(institution);
      }
    }
  }

  public static void saveToXml(Element rootNode) {

    Element genParamNode = new Element("GeneralParameters");
    BusinessCore.getGeneralParameters().saveToXml(genParamNode);
    rootNode.addContent(genParamNode);


    Vector assetKeys = Utils.convertAndSortMapToVector(BusinessCore.getAssets());
    int keysCount = assetKeys.size();

    for (int i = 0; i < keysCount; i++) {
      Element asset = new Element("Asset");
      Asset.saveAssetToXml(asset,BusinessCore.getAsset((String) assetKeys.get(i)));
      rootNode.addContent(asset);
    }

    Vector instKeys = Utils.convertAndSortMapToVector(BusinessCore.getInstitutions());
    keysCount = instKeys.size();

    for (int i = 0; i < keysCount; i++) {
      Element institution = new Element("Institution");
      Institution
          .saveInstitutionToXml(institution,
                              BusinessCore.getInstitution((String) instKeys.get(i)));
      rootNode.addContent(institution);
    }

    Element scenarioNode = new Element("Scenario");
    BusinessCore.getScenario().saveToXml(scenarioNode);
    rootNode.addContent(scenarioNode);


    chatNode = new Element("Chat");
    rootNode.addContent(chatNode);
  }

  public static Element getElementToSaveChat(){
    return chatNode;
  }

  public static void loadFromXml(Element root,JFrame parentFrame) {
    // - loading general parameters...
    Element genParam = root.getChild("GeneralParameters");
    if (genParam == null) {
      Utils.logger.error("Invalid xml format: GeneralParameters nodes not found.");
      JOptionPane.showMessageDialog(parentFrame,"The file you choose is incorrect.","Error",JOptionPane.WARNING_MESSAGE);
      return;
    }

    BusinessCore.getGeneralParameters().loadFromXml(genParam);

    // - loading assets...
    Iterator assetNodes = root.getChildren("Asset").iterator();
    while(assetNodes.hasNext()) {
      Asset asset = Asset.loadAssetFromXml((Element)assetNodes.next());
      BusinessCore.addAsset(asset);
    }

    // - loading institutions
    Iterator institutionNodes = root.getChildren("Institution").iterator();
    while(institutionNodes.hasNext()) {
      Institution institution =
          Institution.loadInstitutionFromXml((Element)institutionNodes.next());
      BusinessCore.addInstitution(institution);
    }

    // - loading scenario
    Element scenario = root.getChild("Scenario");
    if (scenario == null) {
      Utils.logger.error("Invalid xml files: scenario node not found.");
      return;
    }
    BusinessCore.getScenario().loadFromXml(scenario);

  }


  // --- protected fire methods ---

  protected static void fireAssetAdded(Asset asset) {
    for(int i = 0; i < assetsListeners.size(); i++) {
      ((AssetListener)assetsListeners.elementAt(i))
          .assetsModified(new AssetEvent(asset.getAssetName(),
                                         AssetEvent.ASSET_ADDED));
    }
  }

  protected static void fireAssetRemoved(Asset asset) {
    for(int i = 0; i < assetsListeners.size(); i++) {
      ((AssetListener)assetsListeners.elementAt(i))
          .assetsModified(new AssetEvent(asset.getAssetName(),
                                         AssetEvent.ASSET_REMOVED));
    }
  }

  protected static void fireInstitutionAdded(Institution institution) {
    for(int i = 0; i < institutionsListeners.size(); i++) {
      ((InstitutionListener)institutionsListeners.elementAt(i))
          .institutionsModified(new InstitutionEvent(institution.getName(),
                                                     InstitutionEvent.INSTITUTION_ADDED));
    }
  }

  protected static void fireInstitutionRemoved(Institution institution) {
    for(int i = 0; i < institutionsListeners.size(); i++) {
      ((InstitutionListener)institutionsListeners.elementAt(i))
          .institutionsModified(new InstitutionEvent(institution.getName(),
                                                     InstitutionEvent.INSTITUTION_REMOVED));
    }
  }

  // --- Listeners methods ---

  public static void addAssetListener(AssetListener listener) {
    assetsListeners.add(listener);
  }

  public static void addInstitutionListener(InstitutionListener listener) {
    institutionsListeners.add(listener);
  }

  public static void removeAssetListener(AssetListener listener) {
    assetsListeners.remove(listener);
  }

  public static void removeInstitutionListener(InstitutionListener listener) {
    institutionsListeners.remove(listener);
  }
}
