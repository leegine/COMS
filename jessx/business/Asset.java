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

import javax.swing.*;

import org.jdom.*;
import jessx.server.gui.*;
import jessx.utils.*;

/***************************************************************/
/*                     Asset CLASS SECTION                     */
/***************************************************************/
/**
 * <p>Title : Asset</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */
public abstract class Asset implements XmlExportable, XmlLoadable {

  private String name;
  private DividendModel dividendModel;

  public Asset() {
    dividendModel = new DividendModel();
  }

  public void setAssetName(String assetname) {
    this.name = assetname;
  }

  public void setDividendModel(DividendModel divModel) {
    this.dividendModel = divModel;
  }

  public DividendModel getDividendModel() {
    return this.dividendModel;
  }

  public String getAssetName() {
    return this.name;
  }

  public static JPanel getAssetServerGenericGui() {
    return new AssetServerGenericGui();
  }

  public abstract JPanel getAssetSetupGui();

  public JPanel getPanel() {
    return this.getAssetSetupGui();
  }

  public String toString() {
    return this.getAssetName();
  }

  public String getAssetType() {
    return this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1);
  }

  /**
   *
   * @param node Element
   * @param assetToSave Asset
   */
  public static void saveAssetToXml(Element node, Asset assetToSave) {

    // - saving standard parameters...
    node.setAttribute("type",assetToSave.getAssetType()).setAttribute("name",assetToSave.getAssetName());

    // - saving dividend model
    Element divModelNode = new Element("DividendModel");
    assetToSave.getDividendModel().saveToXml(divModelNode);
    node.addContent(divModelNode);

    // - saving asset-specific parameters
    assetToSave.saveToXml(node);
  }

  public static Asset loadAssetFromXml(Element node) {

    // - getting asset type
    String assetType = node.getAttributeValue("type");
    if (assetType == null) {
      Utils.logger.error("Invlid xml: no asset type in asset definition.");
      return null;
    }

    // - creating asset object
    Asset asset;
    try {
      asset = AssetCreator.createAsset(assetType);
    }
    catch (AssetNotCreatedException ex) {
      Utils.logger.error("Asset type not found on server: " + ex.toString());
      return null;
    }

    // - setting assetname
    String assetName = node.getAttributeValue("name");
    if (assetName == null) {
      Utils.logger.error("Invalid asset definition in xml files: no asset name given.");
      return null;
    }
    asset.setAssetName(assetName);

    // - loading dividend model
    DividendModel divModel = new DividendModel();
    divModel.loadFromXml(node.getChild("DividendModel"));
    asset.setDividendModel(divModel);

    return asset;
  }
}
