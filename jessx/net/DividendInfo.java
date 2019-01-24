package jessx.net;


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
import jessx.business.*;
import jessx.server.net.*;

/***************************************************************/
/*                 DividendInfo CLASS SECTION                  */
/***************************************************************/
/**
 * <p>Title : DividendInfo</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class DividendInfo implements NetworkWritable, NetworkReadable {


  private float experimentHoldingValue = -1;
  private float windowHoldingValue = -1;
  private int windowPeriodCount = -1;
  private int expPeriodCount = -1;
  private float interestRate = -1;
  private Vector periodInfo = new Vector();
  private String assetName;
  private boolean showOperationsCosts = true;

  public void setAssetName(String assetName) {
    this.assetName = assetName;
  }

  public float getExperimentHoldingValue() {
    return experimentHoldingValue;
  }

  public float getWindowHoldingValue() {
    return windowHoldingValue;
  }

  public int getWindowPeriodCount() {
    return windowPeriodCount;
  }

  public int getExpPeriodCount() {
    return expPeriodCount;
  }

  public float getInterestRate() {
    return interestRate;
  }

  public Vector getPeriodInfo() {
    return periodInfo;
  }

  public boolean getIsShowingOperationsCosts() {
    return showOperationsCosts;
  }

  public String getAssetName() {
    return assetName;
  }

  public DividendInfo() {

  }

  public String produceInfoReport() {
    String message = "";
    message += ":: " + this.assetName + " ::\n";

    // period number in session
    if (this.expPeriodCount != -1)
      message += "The session has " + this.getExpPeriodCount() + " periods.\n";

    // holding value for session
    if (this.experimentHoldingValue != -1)
      message += "The holding value for the session is " + this.experimentHoldingValue + ".\n";


    // window size.
    if (this.windowPeriodCount != -1)
      message += "Following Info are given for the next " + this.windowPeriodCount + " periods\n";

    // holding value for the window.
    if (this.windowHoldingValue != -1)
      message += "The holding value is " + this.windowHoldingValue + "\n";

    for(int i = 0; i < this.periodInfo.size(); i++) {
      message += (String)this.periodInfo.elementAt(i);
    }

    return message;
  }

  public boolean initFromNetworkInput(Element rootNode) {

    // checking root node name...
    if (!rootNode.getName().equals("DividendInfo")) return false;

    // getting assetName
    String assetName = rootNode.getAttributeValue("asset");
    if (assetName == null) return false;
    this.assetName = assetName;

    String sessionLength = rootNode.getAttributeValue("sessionLength");
    String expHV = rootNode.getAttributeValue("expHV");
    String windowLength = rootNode.getAttributeValue("windowLength");
    String windowHV = rootNode.getAttributeValue("windowHV");
    String interestRate = rootNode.getAttributeValue("interestRate");
    String showOperationsCosts = rootNode.getAttributeValue("showOperationsCosts");

    if (sessionLength != null) this.expPeriodCount = Integer.parseInt(sessionLength);
    if (expHV != null) this.experimentHoldingValue = Float.parseFloat(expHV);
    if (windowLength != null) this.windowPeriodCount = Integer.parseInt(windowLength);
    if (windowHV != null) this.windowHoldingValue = Float.parseFloat(windowHV);
    if (interestRate != null) this.interestRate = Float.parseFloat(interestRate);

    if (showOperationsCosts != null)
    {
      if (showOperationsCosts.equals("true"))
        this.showOperationsCosts = true;
      else
        this.showOperationsCosts = false;
    }


    Iterator details = rootNode.getChildren("Details").iterator();
    this.periodInfo.removeAllElements();
    while(details.hasNext()) {
      Element node = (Element)details.next();
      String period = node.getAttributeValue("period");
      if (period != null) {
        this.periodInfo.add("For the period " + Integer.toString(Integer.parseInt(period)+1) + ": " + node.getText() + "\n");
      }
    }

    return true;
  }

  public Element prepareForNetworkOutput(String pt) {
    if (assetName == null) return null;

    Element divInfo = new Element("DividendInfo");
    divInfo.setAttribute("asset", this.assetName);

    DividendLimitation divLimit = BusinessCore.getScenario().getPlayerType(pt)
        .getDividendInfo(this.assetName);

    // session length
    if (divLimit.isDisplayingSessionLength())
      divInfo.setAttribute("sessionLength",
                           Integer.toString(BusinessCore.getGeneralParameters().getPeriodCount()));

    // holding value for session

    int period = NetworkCore.getExperimentManager().getPeriodNum() + 1;

    if (divLimit.isDisplayingHoldingValueForExperiment())
      divInfo.setAttribute("expHV",
                           Float.toString(BusinessCore
                                          .getAsset(this.assetName)
                                          .getDividendModel()
                                          .getExperimentHoldingValue(period)));




    // window size.
    if (divLimit.isDisplayingWindowSize())
      divInfo.setAttribute("windowLength", Integer.toString(divLimit.getWindowSize()));

    // holding value for the window.
    int windowSize = divLimit.getWindowSize();

    if (divLimit.isDisplayHoldingValueForWindow())
      divInfo.setAttribute("windowHV",
                         Float.toString(BusinessCore
                                        .getAsset(this.assetName)
                                        .getDividendModel()
                                        .getWindowHoldingValue(period,windowSize)) );


    //Holding value for the window can be displayed or hidden

    if (divLimit.isDisplayingOperationsCosts())
      divInfo.setAttribute("showOperationsCosts", "true");
    else
      divInfo.setAttribute("showOperationsCosts", "false");




    // interest rate
    divInfo.setAttribute("interestRate",
                         Float.toString(BusinessCore.getGeneralParameters().getInterestRate()));

    // detailled infos
    if (3!=divLimit.getDividendDetailledproperties()){
      if (1==divLimit.getDividendDetailledproperties()) {
        Element details = new Element("Details");
        details.setAttribute("period", Integer.toString(period));
        details.setText(BusinessCore.getAsset(this.assetName).getDividendModel().
                        getDividendAt(period).getDetails());
        divInfo.addContent(details);
      }
      else if (2==divLimit.getDividendDetailledproperties()){
        for (int i = period; i < period + windowSize; i++) {
          Element details = new Element("Details");
          details.setAttribute("period", Integer.toString(i));
          details.setText(BusinessCore.getAsset(this.assetName).getDividendModel().
                          getDividendAt(i).getDetails());
          divInfo.addContent(details);
        }
      }
      else {
        for (int i = period; i < period + windowSize; i++) {
          Element details = new Element("Details");
          details.setAttribute("period", Integer.toString(i));
          details.setText(BusinessCore.getAsset(this.assetName).getDividendModel().
                          getDividendAt(i).getDetails());
          divInfo.addContent(details);
        }
      }

    }
    return divInfo;
  }
}
