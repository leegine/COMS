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
/*            DividendLimitation CLASS SECTION                 */
/***************************************************************/
/**
 * <p>Title : DividendLimitation</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class DividendLimitation implements XmlExportable, XmlLoadable {

  private int dividendDetailled = 3;
  private int windowSize = 1;
  private boolean displayWindowSize = false;
  private boolean displaySessionLength = false;
  private boolean displayHoldingValueForExperiment = false;
  private boolean displayHoldingValueForWindow = false;
  private boolean displayOperationsCosts = false;

  private DividendModel divModel;

  private String assetName;
  private String playerType;

  private Vector dividendInfoListener = new Vector();

  public void setDisplayWindowSize(boolean displayWindowSize) {
    if (this.displayWindowSize != displayWindowSize) {
      this.displayWindowSize = displayWindowSize;
      this.fireDividendInfoUpdated();
    }
  }

  public void setDisplaySessionLength(boolean displaySessionLength) {
    if (this.displaySessionLength != displaySessionLength) {
      this.displaySessionLength = displaySessionLength;
      this.fireDividendInfoUpdated();
    }
  }

  public void setDisplayHoldingValueForExperiment(boolean displayHoldingValueForExperiment) {
    if (this.displayHoldingValueForExperiment != displayHoldingValueForExperiment) {
      this.displayHoldingValueForExperiment = displayHoldingValueForExperiment;
      this.fireDividendInfoUpdated();
    }
  }

  public void setWindowSize(int windowSize) {
    if (this.windowSize != windowSize) {
      this.windowSize = windowSize;
      this.divModel.setWindowSize(this.playerType,windowSize);
      this.fireDividendInfoUpdated();
    }
  }

  public void setDisplayHoldingValueForWindow(boolean displayHoldingValueForWindow) {
    if (this.displayHoldingValueForWindow != displayHoldingValueForWindow) {
      this.displayHoldingValueForWindow = displayHoldingValueForWindow;
      this.fireDividendInfoUpdated();
    }
  }


  public void setDisplayOperationsCosts(boolean displayOperationsCosts) {
    if (this.displayOperationsCosts != displayOperationsCosts) {
      this.displayOperationsCosts = displayOperationsCosts;
      this.fireDividendInfoUpdated();
    }
  }

  public void setDividendDetailledproperties(int numberOfTheButton) {
    if (this.dividendDetailled != numberOfTheButton) {
      this.dividendDetailled = numberOfTheButton;
      this.fireDividendInfoUpdated();
    }
  }

  public int getDividendDetailledproperties() {
    return this.dividendDetailled;
  }


  public boolean isDisplayingWindowSize() {
    return displayWindowSize;
  }

  public boolean isDisplayingSessionLength() {
    return displaySessionLength;
  }

  public boolean isDisplayingHoldingValueForExperiment() {
    return displayHoldingValueForExperiment;
  }

  public int getWindowSize() {
    return windowSize;
  }



  public boolean isDisplayingOperationsCosts() {
    return displayOperationsCosts;
  }

  public String getAssetName() {
    return assetName;
  }

  public String getPlayerType() {
    return playerType;
  }

  public boolean isDisplayHoldingValueForWindow() {
    return displayHoldingValueForWindow;
  }



  public DividendLimitation(String playerType, String asset) {
    this.playerType = playerType;
    this.assetName = asset;
    this.divModel = BusinessCore.getAsset(asset).getDividendModel();
  }

  public DividendLimitation(String playerType, Element node) {
    this.playerType = playerType;
    this.loadFromXml(node);
  }

  public void addListener(DividendInfoListener listener) {
    this.dividendInfoListener.add(listener);
  }

  public void removeListener(DividendInfoListener listener) {
    this.dividendInfoListener.remove(listener);
  }

  public void loadFromXml(Element node) {
    Utils.logger.debug("Loading dividend info...");

    String assetName = node.getAttributeValue("asset");
    if (assetName == null) {
      Utils.logger.error("Invalid xml DividendInfo node : attribute asset not found.");
      return;
    }
    this.assetName = assetName;
    this.divModel = BusinessCore.getAsset(assetName).getDividendModel();

    String dividendDetailledProperties = node.getChild("DividendDetailledProperties").getAttributeValue("value");
    String windowSize = node.getChild("WindowSize").getAttributeValue("value");
    String displayWindowSize = node.getChild("DisplayWindowSize").getAttributeValue("value");
    String displaySessionLength = node.getChild("DisplaySessionLength").getAttributeValue("value");
    String displayHoldingValueForExperiment = node.getChild("DisplayHoldingValueForExperiment").getAttributeValue("value");
    String displayHoldingValueForWindow = node.getChild("DisplayHoldingValueForWindow").getAttributeValue("value");
    String displayOperationsCosts = node.getChild("DisplayOperationsCosts").getAttributeValue("value");

    if ((dividendDetailledProperties == null) ||
        (windowSize == null) ||
        (displayWindowSize == null) ||
        (displaySessionLength == null) ||
        (displayHoldingValueForExperiment == null) ||
        (displayHoldingValueForWindow == null) ||
        (displayOperationsCosts == null)) {
      Utils.logger.error("Invalid xml dividend info node. One of the child node is missing or the attribute value of one or more child could not be found. ");
      return;
    }

    Utils.logger.debug("Reading dividendDetailledProperties... " + dividendDetailledProperties);
    int dividendProperties;
    if ("Dividends shown for the next period".equals(dividendDetailledProperties))
      dividendProperties = 1;
    else if ("Dividends shown for the window".equals(dividendDetailledProperties))
      dividendProperties = 2;
    else if ("Dividends shown for the experiment".equals(dividendDetailledProperties))
      dividendProperties = 0;
    else dividendProperties=3;
    this.setDividendDetailledproperties(dividendProperties);


 //   this.setDividendShown(Boolean.valueOf(dividendShown).booleanValue());

    Utils.logger.debug("Reading windowSize... " + windowSize);
    this.setWindowSize(Integer.parseInt(windowSize));

    Utils.logger.debug("Reading displayWindowSize... " + displayWindowSize);
    this.setDisplayWindowSize(Boolean.valueOf(displayWindowSize).booleanValue());

    Utils.logger.debug("Reading displaySessionLength... " + displaySessionLength);
    this.setDisplaySessionLength(Boolean.valueOf(displaySessionLength).booleanValue());

    Utils.logger.debug("Reading displayHoldingValueForExperiment... " + displayHoldingValueForExperiment);
    this.setDisplayHoldingValueForExperiment(Boolean.valueOf(displayHoldingValueForExperiment).booleanValue());

    Utils.logger.debug("Reading displayHoldingValueForWindow... " + displayHoldingValueForWindow);
    this.setDisplayHoldingValueForWindow(Boolean.valueOf(displayHoldingValueForWindow).booleanValue());

    Utils.logger.debug("Reading displayOperationsCosts... " + displayOperationsCosts);
    this.setDisplayOperationsCosts(Boolean.valueOf(displayOperationsCosts).booleanValue());

  }

  public void saveToXml(Element node) {
    node.setAttribute("asset",this.getAssetName());

    String dividendProperties;
    if (this.getDividendDetailledproperties() == 0){
        dividendProperties = "Dividends shown for the experiment";
    }
    else if (this.getDividendDetailledproperties() == 1) {
      dividendProperties = "Dividends shown for the next period";
    }
    else {
      if (this.getDividendDetailledproperties() == 2) {
        dividendProperties = "Dividends shown for the window";
      }
      else {
        dividendProperties = "Dividends not shown";
      }
    }
    node.addContent(new Element("DividendDetailledProperties").setAttribute("value",dividendProperties));
    node.addContent(new Element("WindowSize").setAttribute("value",Integer.toString(this.getWindowSize())));
    node.addContent(new Element("DisplayWindowSize").setAttribute("value",Boolean.toString(this.isDisplayingWindowSize())));
    node.addContent(new Element("DisplaySessionLength").setAttribute("value", Boolean.toString(this.isDisplayingSessionLength())));
    node.addContent(new Element("DisplayHoldingValueForExperiment").setAttribute("value",Boolean.toString(this.isDisplayingHoldingValueForExperiment())));
    node.addContent(new Element("DisplayHoldingValueForWindow").setAttribute("value",Boolean.toString(this.isDisplayHoldingValueForWindow())));
    node.addContent(new Element("DisplayOperationsCosts").setAttribute("value", Boolean.toString(this.isDisplayingOperationsCosts()) ));
  }

  private void fireDividendInfoUpdated() {
    for(int i = 0; i < this.dividendInfoListener.size(); i++) {
      ((DividendInfoListener)this.dividendInfoListener.elementAt(i)).dividendInfoModified(new DividendInfoEvent(this.assetName,this.playerType,DividendInfoEvent.DIVIDEND_INFO_UPDATED));
    }
  }
}
