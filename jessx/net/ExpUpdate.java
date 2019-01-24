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

import java.io.*;

import org.jdom.*;
import jessx.utils.*;

/***************************************************************/
/*                  ExpUpdate CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : ExpUpdate</p>
 * <p>Description : Experiment Update Class.<br />Objects from this class are
 * used to notify the client of how the experiment is going on. Objects can
 * be sent when the client join, at the beginning on the experiment,
 * at the beginning of each period, at the end of each period, and at the end of
 * the experiment and at each time wants to know if the client is ready.</p>
 * This class can be serialized in an xml fomat, using the <code>NetworkWritable
 * </code> method. It can then be read using the <code>NetworkReadable</code>
 * method.</p>
 * <p>The structure of the xml produced is:<br/>
 * - a root node name <i>ExperimentUpdate</i><br />
 * - a mandatory attribute named <i>updateType</i> containing an
 * <code>integer</code><br />
 * - a mandatory attribute named <i>updateInfo</i> containing an
 * <code>String</code><br />
 * <br />
 * Here is the meaning of the updateType integer value:<br />
 * <ul>
 * <br />Sent when the event occurs on the server:
 * <li>0: the experiment is beginning</li>
 * <li>1: the experiment is finished</li>
 * <li>2: the period is beginning</li>
 * <li>3: the period is finished</li><br />
 * Sent when the client join so that he knows in which state is the experiment:
 * <li>4: the experiment is on</li>
 * <li>5: the experiment is off</li>
 * <br />Sent occasionnaly by the server, so that client can update its timer:
 * <li>6: time update</li>
 * <br />Sent each time the server has the client in a busy state and wants to
 * know if it is ok to continue:
 * <li>7: is client ready</li></ul>
 * See comments in the code for more info about the content of
 * <i>updateInfo</i> in each case.</p>
 * @author Thierry Curtil
 * @version 1.0
 * @see jessx.net.NetworkReadable
 * @see jessx.net.NetworkWritable
 */

public class ExpUpdate implements
    Serializable, NetworkReadable, NetworkWritable {

  /** value:0. updateInfo is in an undefined state. (possibly null) */
  public final static int EXPERIMENT_BEGINNING = 0;
  /** value:1. updateInfo is in an undefined state. (possibly null) */
  public final static int EXPERIMENT_FINISHING = 1;

  /** value:2. updateInfo contains the duration in seconds of the period. */
  public final static int PERIOD_BEGINNING = 2;
  /** value:3. updateInfo is in an undefined state. (possibly null) */
  public final static int PERIOD_FINISHING = 3;

  /** value: 4. This is sent just after the client connection, so that he knows
   in which state the experiment is.*/
  public final static int EXPERIMENT_OFF = 4;
  /** value: 5. This is sent just after the client connection, so that he knows
   in which state the experiment is.*/
  public final static int EXPERIMENT_ON = 5;

  /**
   * value:6 UpdateInfo contains the remaining time in millisec of the period.
   * @deprecated
   */
  public final static int TIME_UPDATE = 6;

  /** value:7. UpdateInfo contains is in an undefined state. The client should
    answer to a message of this type by sending back the same update type.
    otherwise the server won't notice the client is ready */
  public final static int CLIENT_READY = 7;

  /**
   * Should contain the value of one of the final static field above.
   */
  private int updateType;

  /**
   * See comments for the content of this field. (it depends of the updateType
   * value.
   */
  private String updateInfo;

  /**
   * <p>Contains the number of the period in which this update is.<br />
   * contains -1 as long as the experiment as not begun, or as long as this
   * field has no mean.<br />
   * with period beginning: contains the number of the beginning period<br />
   * with period_finishing: contains the number of the finishing period<br />
   */
  private int periodNum = -1;
  /**
   * returns the <code>updateType</code> field value
   * @return int
   */
  public int getUpdateType() {
    return this.updateType;
  }

  /**
   * returns the <code>updateInfo</code> field value
   * @return String
   */
  public String getUpdateMessage() {
    return this.updateInfo;
  }

  public int getCurrentPeriod() {
    return this.periodNum;
  }

  /**
   * Public constructor that fully init the object.
   *
   * @param updateType int
   * @param updateInfo String
   * @param periodNum int
   */
  public ExpUpdate(int updateType, String updateInfo, int periodNum) {
    this.updateInfo = updateInfo;
    this.updateType = updateType;
    this.periodNum = periodNum;
  }

  /**
   * <p>Return an <code>Element</code> containing this object data in a xml
   * format. See above for a descritption of the xml structure.<br />
   * The <code>playerType</code> parameter is ignored as it has no sense in this
   * object. </p>
   * @param playerType String - ignored
   * @return Element
   * @see jessx.net.NetworkWritable
   */
  public Element prepareForNetworkOutput(String playerType) {
    Utils.logger.debug("Xml Formatting experiment update message...");
    Element experimentUpdate = new Element("ExperimentUpdate");
    experimentUpdate
        .setAttribute("updateType",Integer.toString(this.updateType))
        .setAttribute("updateInfo",this.getUpdateMessage())
        .setAttribute("currentPeriod",Integer.toString(this.periodNum));

    return experimentUpdate;
  }

  /**
   * Init this Object from an xml Stream. The stream has already been parsed in
   * an xml well-formed structure. (using sax parser and org.jdom).
   * The method returns false if it could not read the xml structure.
   * @param root Element
   * @return boolean
   * @see jessx.net.NetworkReadable
   */
  public boolean initFromNetworkInput(Element root) {
    Utils.logger.debug("Analysing xml node...");
    if (!root.getName().equals("ExperimentUpdate")) {
      Utils.logger.warn("received a bad xml formatted node: " +
                        "bad node name. [NODE IGNORED]");
      return false;
    }
    String updateType = root.getAttributeValue("updateType");
    String updateInfo = root.getAttributeValue("updateInfo");
    String currentPeriod = root.getAttributeValue("currentPeriod");

    if ((updateInfo == null) || (updateType == null)
        || (currentPeriod == null)) {
      Utils.logger.warn("received a bad xml formatted node: attribute" +
                        " updateInfo or updateType not found [IGNORED]");
      return false;
    }

    this.updateInfo = updateInfo;
    this.updateType = Integer.parseInt(updateType);
    this.periodNum = Integer.parseInt(currentPeriod);
    return true;
  }
}
