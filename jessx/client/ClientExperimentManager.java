package jessx.client;

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
import jessx.client.event.*;
import jessx.net.*;

/***************************************************************/
/*           ClientExperimentManager CLASS SECTION             */
/***************************************************************/
/**
 * <p>Title : ClientExperimentManager</p>
 * <p>Description : </p>
 * @author Thierry Curtil, Jeremy Streque
 * @version 1.0
 */

public class ClientExperimentManager implements NetworkListener {

  public static final int EXP_OFF = 0; // experiment stopped
  public static final int EXP_ON_PER_OFF = 1; // experiment on course, period stopped
  public static final int EXP_ON_PER_ON = 2; // experiment on course, period too




  private int experimentState = EXP_OFF;



  /**
   * contains the duration of the current period in mseconds.
   */
  private long currentPeriodDuration;

  /**
   * the number of the current period (begins at 1)
   */
  private int currentPeriod = 0;

  /**
   * date of start of the current period
   * timeInThisPeriod = (now.getTime() - periodStart.getTime())/1000
   */
  private Date syncDate;

  private Vector listeners = new Vector();

  /**
   * @return long the remaining time in milliseconds
   */
  public long getRemainingTimeInPeriod() {
    Date now = new Date();
    return currentPeriodDuration - now.getTime() + syncDate.getTime();
  }

  public int getCurrentPeriod() {
    return this.currentPeriod;
  }

  public int getExperimentState() {
    return this.experimentState;
  }

  private void setExperimentState(int newState) {
    if (newState != this.getExperimentState()) {
      this.experimentState = newState;
    }
  }







  public ClientExperimentManager() {
    ClientCore.addNetworkListener(this,"ExperimentUpdate");
  }

  public void objectReceived(Document doc) {

    if (doc.getRootElement().getName().equals("ExperimentUpdate")) {
      ExpUpdate update = new ExpUpdate(ExpUpdate.CLIENT_READY,"",-1);
      if (!update.initFromNetworkInput(doc.getRootElement())) return;
      switch(update.getUpdateType()) {
        case ExpUpdate.EXPERIMENT_ON : {
          this.setExperimentState(EXP_ON_PER_OFF);
          this.fireExperimentBegins();
          break;
        }
        case ExpUpdate.EXPERIMENT_OFF : {
          this.setExperimentState(EXP_OFF);
          // no firing: the client should be in the right state when it begins
          // and the experiment has not yet begun.
          break;
        }
        case ExpUpdate.EXPERIMENT_BEGINNING : {
          this.setExperimentState(EXP_ON_PER_OFF);

          // pourquoi cette ligne avait ete commentee ?
          // si c'etait pour que les panneaux clients ne soit plus actif des le
          // debut de l'experience, il fallait plutot modifier la methode
          // experimentBegins de GClientFrame ;-p


          fireExperimentBegins();
          break;
        }
        case ExpUpdate.EXPERIMENT_FINISHING : {
          this.setExperimentState(EXP_OFF);
          this.fireExperimentEnds();
          break;
        }
        case ExpUpdate.PERIOD_BEGINNING : {
          currentPeriod++;
          currentPeriodDuration = Integer.parseInt(((ExpUpdate)update).getUpdateMessage());
          this.syncDate = new Date();
          this.currentPeriod = update.getCurrentPeriod();
          this.setExperimentState(EXP_ON_PER_ON);
          /*
          Utils.logger.debug("------------------------\nAppel de initializeInvestments\n");
          ClientCore.getPortfolio().preInitializeInvestments();
          Utils.logger.debug("------------------------\nLe contenu de l'orderbook va etre pris en compte :\n");

          HashMap institutions = ClientCore.getInstitutions();

          Iterator instIter = institutions.keySet().iterator();
          while (instIter.hasNext()) {
          String institutionName = (String) instIter.next();
          Utils.logger.debug("\n--------------\nInstitution " + institutionName + " :");
          if ( (ClientCore.getInstitution(institutionName).getKeepingOrderBook())) {
            Utils.logger.debug("orderbook conserve :");
            ClientCore.getPortfolio().setInvestmentsWhenKeepingOrderBook(institutionName, ClientCore.getInstitution(institutionName).getAssetName());
          } else {
            Utils.logger.debug("orderbook non conserve :");
            ClientCore.getPortfolio().setInvestmentsWhenNotKeepingOrderBook(institutionName);
          }
          }
        Utils.logger.debug("Portfolio mis a jour pour la prochaine periode\n----------------------");
        */
          this.firePeriodBegins();
          break;
        }
        case ExpUpdate.PERIOD_FINISHING : {
          this.setExperimentState(EXP_ON_PER_OFF);
          this.firePeriodEnds();
          break;
        }
      }
    }
  }

  public void addListener(ExperimentDeveloppmentListener listener) {
    this.listeners.add(listener);
  }

  public void removeListener(ExperimentDeveloppmentListener listener) {
    this.listeners.remove(listener);
  }

  private void fireExperimentBegins() {
    for(int i = 0; i < listeners.size(); i++) {
      ((ExperimentDeveloppmentListener)listeners.elementAt(i)).experimentBegins();
    }
  }

  private void fireExperimentEnds() {
    for(int i = 0; i < listeners.size(); i++) {
      ((ExperimentDeveloppmentListener)listeners.elementAt(i)).experimentFinished();
    }
  }

  private void firePeriodBegins() {
    for(int i = 0; i < listeners.size(); i++) {
      ((ExperimentDeveloppmentListener)listeners.elementAt(i)).periodBegins();
    }
  }

  private void firePeriodEnds() {
    for(int i = 0; i < listeners.size(); i++) {
      ((ExperimentDeveloppmentListener)listeners.elementAt(i)).periodFinished();
    }
  }

}
