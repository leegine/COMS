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
import jessx.utils.*;


/***************************************************************/
/*                 NormalDividend CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : NormalDividend</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class NormalDividend extends Dividend {

  private float mean;
  private float variance;

  /**
   * the probability array
   */
  private double[] probability;

  /**
   * The minimum and maximum divident that could be given
   */
  private double minDividend;
  private double maxDividend;



  public float getMean() {
    return mean;
  }

  public float getVariance() {
    return variance;
  }

  public float getDividend() {

    this.setProbabilityArray();

    float prob = (float)Math.random();
    int i = 0;
    while ((i < this.probability.length) && (prob > this.probability[i])) {
      i++;
    }
    i--;
    return (float)Math.floor(100 * (this.minDividend + i * (this.maxDividend - this.minDividend) / this.probability.length))/100;
  }

  public void setProbabilityArray() {
    this.probability = new double[25]; // set 25 steps

    // we initialise the array with the value where the probability is 1%
    // if it is less than 0, we just take the probability of 0 as the less
    // probable event
    // P(Dmin) = 0.01; (Dmin: dividend minimum)
    double xmin = this.mean - this.variance * Math.sqrt(Math.log(100));
    xmin = Math.max(xmin,0);
    double xmax = 2 * this.mean - xmin;

    // each step will increment the divident by this value.
    double delta = (xmax - xmin) / (this.probability.length - 1);

    // calculate the probability of obtaining the divident or less
    // initial value:
    this.probability[0] = delta / ( Math.sqrt(2 * Math.PI) * this.variance) *
        Math.exp(-1 * Math.pow((xmin - this.mean) / (Math.sqrt(2) * this.variance),2));
    // calculating the rest
    for(int i = 1; i < this.probability.length; i++) {
      this.probability[i] = this.probability[i-1] + delta / ( Math.sqrt(2 * Math.PI) * this.variance) *
        Math.exp(-1 * Math.pow((xmin + delta * i - this.mean) / (Math.sqrt(2) * this.variance),2));

    }
    // having a consistent result:
    // for that, we are considering the probability of the last event, the probability
    // of the first. They should be the same. As the probability of an event
    // occuring outside on the left or on the right of the curb.
    // this probability if:
    double pLeft = ( 1 - (this.probability[this.probability.length - 1] + this.probability[0]) ) / 2;

    for(int  i = 0; i < this.probability.length; i++) {
      this.probability[i] += pLeft;
    }

    this.probability[this.probability.length -1 ] = 1;

    this.minDividend = xmin;
    this.maxDividend = xmax;
    /**
     * @todo enhance the distribution that is not so precise (error: ~2%)
     */
  }


  public void setParameter(int i, Object value) {
    Utils.logger.debug("Setting parameter " + i + ". Value class: " + value.getClass().toString() + ", Value=" + value.toString());
    if (i == 0) {
      mean = ((Float)value).floatValue();
    }
    else {
      variance = ( (Float) value).floatValue();
    }
  }

  public Object getParameter(int i) {
    return (i == 0) ? new Float(mean) : new Float(variance);
  }

  public int getParamCount() {
    return 2;
  }

  public Class getParamClass(int i) {
    return Float.class;
  }

  public String[] getParamNames() {
    return new String[] {"Mean", "Variance" };
  }

  public float getNormalValue() {
    return this.mean;
  }

  public String getDetails() {
    String details = "";
    details = "Mean dividend: " + this.mean +
        " ; standard deviation: " + this.variance + ".";
    return details;
  }

  public NormalDividend() {
    Utils.logger.debug("Creating normal dividend ... ");
    this.mean = 0f;
    this.variance = 0f;
  }

  public void loadFromXml(Element node) {
    Utils.logger.info("Loading dividends...");
    String mean = node.getAttributeValue("mean");
    String variance = node.getAttributeValue("variance");
    if ((mean == null) || (variance == null)) {
      Utils.logger.error("invalid xml dividend node: attributes mean and/or variance not found.");
      return;
    }
    Utils.logger.debug("Value found: mean=" + mean + ", variance=" + variance);
    this.setParameter(0,new Float(mean));
    this.setParameter(1,new Float(variance));
  }

  public void saveToXml(Element node) {
    Utils.logger.debug("Saving dividends...");
    node.setAttribute("mean",Float.toString(this.getMean())).setAttribute("variance",Float.toString(this.getVariance()));
  }

  public Object clone() {
    NormalDividend div = new NormalDividend();
    div.mean = this.mean;
    div.variance = this.variance;
    return div;
  }
}
