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
import javax.swing.event.*;

/***************************************************************/
/*              DividendModel CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : DividendModel</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class DividendModel implements XmlExportable, XmlLoadable, ChangeListener {

  private Vector dividendList = new Vector();
  private Vector dividendListener = new Vector();

  private int periodCount = 1;
  private HashMap windowSizes = new HashMap();
  private String biggestWindowSize;

  public void setPeriodCount(int periodCount) {
    this.periodCount = periodCount;
    this.setDividendListSize();
  }

  public void setWindowSize(String playerType, int size) {

    this.windowSizes.put(playerType,new Integer(size));
    if (!this.windowSizes.containsKey(biggestWindowSize)) {
      this.biggestWindowSize = playerType;
      this.setDividendListSize();
    }

    this.calcBiggestWinSize();
  }

  public int getPeriodCount() {
    return periodCount;
  }

  public int getBiggestWindowSize() {
    if (this.windowSizes.containsKey(this.biggestWindowSize)) {
      return ( (Integer)this.windowSizes.get(this.biggestWindowSize)).intValue();
    }
    else {
      return 1;
    }
  }

  /**
   * public float getExperimentHoldingValue() { float hv = 0; for( int i = 0; i < this.dividendList.size(); i++) {hv =
   * ((Dividend)this.dividendList.elementAt(i)).getNormalValue(); } return hv; }
   *
   * @param offset int
   * @return float
   */
  public float getExperimentHoldingValue(int offset) {
    float hv = 0;
    float interest = BusinessCore.getGeneralParameters().getInterestRate();
    for( int i = offset; i < this.dividendList.size(); i++) {
      hv =  hv * (1 + interest/100)  + (((Dividend)this.dividendList.elementAt(i)).getNormalValue());
    }
    hv *= 100;
    hv = (int) (hv + .5);
    hv = hv/100;
    return hv;
  }








  public float getWindowHoldingValue(int offset, int size) {
    float hv = 0;
    float interest = BusinessCore.getGeneralParameters().getInterestRate();
    for( int i = offset; (i < this.dividendList.size()) && (i < offset + size); i++) {
      hv = hv* (1 + interest/100) + ((Dividend)this.dividendList.elementAt(i)).getNormalValue();
    }
    hv *= 100;
    hv = (int) (hv + .5);
    hv = hv/100;
    return hv;
  }


  public float getDividend(int period) {
    return ((Dividend)dividendList.elementAt(period)).getDividend();
  }

  public Dividend getDividendAt(int period) {
    Utils.logger.debug("Dividend object at period " + period + " was asked.");
    return (Dividend)dividendList.elementAt(period);
  }

  /**
   * @deprecated modify instead periodCount or the window size. It will expand
   * automatically the size of the dividend list. Then modify directly each
   * dividend property (use getDividendAt method and then the setters fo the
   * dividend object.
   * @param div Dividend
   * @param period int
   */
  public void setDividend(Dividend div, int period) {
    if (period < dividendList.size()) {
      this.dividendList.setElementAt(div, period);
      this.fireDividendUpdated(period);
    }
  }

  private void setDividendListSize() {
    int size = this.getPeriodCount() + this.getBiggestWindowSize() - 1;
    if (this.dividendList.size() < size) {
      this.dividendList.add(new NormalDividend());
      this.setDividendListSize();
    }
    else if (this.dividendList.size() > size) {
      this.dividendList.setSize(size);
    }
  }

  private void calcBiggestWinSize() {
    Iterator iter = this.windowSizes.keySet().iterator();
    while(iter.hasNext()) {
      String key = (String) iter.next();
      if (((Integer)this.windowSizes.get(key)).intValue() >
          ((Integer)this.windowSizes.get(this.biggestWindowSize)).intValue()) {
        this.biggestWindowSize = key;
      }
    }
    this.setDividendListSize();
  }

  public void addDividendListener(DividendListener listener) {
    this.dividendListener.add(listener);
  }

  public void removeDividendListener(DividendListener listener) {
    this.dividendListener.remove(listener);
  }

  public int getDefinedPeriodCount() {
    return this.dividendList.size();
  }

  /**
   * @todo for instance only normal dividend is supported... extend that if needed
   * @param node Element
   */
  public void loadFromXml(Element node) {
    Utils.logger.debug("Loading dividendModel from xml...");

    String periodCount = node.getAttributeValue("periodCount");
    if (periodCount == null) {
      Utils.logger.error("invalid xml DividendModel node: attribute periodCount not found.");
      return;
    }
    this.periodCount = Integer.parseInt(periodCount);

    String biggestWindow = node.getAttributeValue("biggestWindow");
    String size = node.getAttributeValue("size");

    if ((biggestWindow != null) && (size != null)) {
      this.setWindowSize(biggestWindow, Integer.parseInt(size));
    }

    Iterator divNodes = node.getChildren("Dividend").iterator();
    int i = 0;
    while(divNodes.hasNext()) {
      Element divNode = (Element)divNodes.next();
      this.getDividendAt(i).loadFromXml(divNode);
      i++;
    }
  }

  public void saveToXml(Element node) {
    Utils.logger.debug("Saving dividend model to xml...");

    node.setAttribute("periodCount", Integer.toString(this.periodCount));

    if (this.windowSizes.containsKey(this.biggestWindowSize)) {
      node.setAttribute("biggestWindow", this.biggestWindowSize);
      node.setAttribute("size", Integer.toString(this.getBiggestWindowSize()));
    }
    for(int i = 0; i < dividendList.size(); i++) {
      Element dividend = new Element("Dividend");
      ((Dividend)dividendList.elementAt(i)).saveToXml(dividend);
      node.addContent(dividend);
    }
  }

  private void fireDividendUpdated(int period) {
    for(int i = 0; i < this.dividendListener.size(); i++) {
      ((DividendListener)this.dividendListener.elementAt(i)).dividendModified(new DividendEvent((Dividend)this.dividendList.elementAt(period),period,DividendEvent.DIVIDEND_UPDATED));;
    }
  }

  public DividendModel() {
    this.setPeriodCount(BusinessCore.getGeneralParameters().getPeriodCount());
    BusinessCore.getGeneralParameters().addPeriodCountChangeListener(this);
  }

  public void stateChanged(ChangeEvent e) {
    this.setPeriodCount(BusinessCore.getGeneralParameters().getPeriodCount());
  }
}
