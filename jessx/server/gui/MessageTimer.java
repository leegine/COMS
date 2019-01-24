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

import jessx.business.*;
import jessx.net.*;
import jessx.server.net.*;
import jessx.utils.*;

/***************************************************************/
/*                 MessageTimer CLASS SECTION                  */
/***************************************************************/
/**
 * <p>Title : MessageTimer</p>
 * <p>Description : </p>
 * @author Christophe Grosjean
 * @version 1.0
 */
public class MessageTimer
    extends Thread {
  private Vector listInformation;
  private Vector listInformationSorted;

  public MessageTimer(Vector Informations) {
    checkInformationsToSend(Informations);
    this.listInformation = (Vector) Informations.clone();
    this.listInformationSorted = (Vector)this.sort().clone();
    Utils.logger.info("MessageTimer created...");
  }

  public Vector sort() {
    Vector listSorted = new Vector();
    int size = listInformation.size();
    for (int i = size - 1; i >= 0; i--) {
      String[] temp = (String[]) listInformation.get(i);
      int index = i;
      for (int j = i; j >= 0; j--) {
        boolean comparePeriod = (Integer.parseInt( ( (String[]) listInformation.
            get(j))[2]) <Integer.parseInt(temp[2]));
        if (comparePeriod ||
            ( (Integer.parseInt( ( (String[]) listInformation.get(j))[2]) ==
               Integer.parseInt(temp[2])) &
             (Integer.parseInt( ( (String[]) listInformation.get(j))[3]) <
              Integer.parseInt(temp[3])))) {
          temp = (String[]) listInformation.get(j);
          index = j;
        }
      }
      listSorted.add(new String[] {temp[0], temp[1], temp[2], temp[3]});
      listInformation.remove(index);
    }
    return listSorted;
  }

  public void checkInformationsToSend(Vector information) {
    int periodCount = BusinessCore.getGeneralParameters().getPeriodCount();
    int periodDuration = BusinessCore.getGeneralParameters().getPeriodDuration();
    int size = information.size();
    for (int i = size-1; i >=0; i--) {
      if ( (Integer.parseInt( ( (String[]) information.get(i))[3]) >=
            periodDuration) ||
          (Integer.parseInt( ( (String[]) information.get(i))[2]) >
           periodCount))
       information.remove(i);
    }
  }

  public void run() {
    int size = listInformationSorted.size();
    if (size != 0) {
      int i = 0; //index of the next message send
      //Time between two pieces of information or between the beginning of a
      //period and the first one
      long timeTemp;
      try {
        listInformationSorted.add(listInformationSorted.get(size - 1));
        while (NetworkCore.getExperimentManager().getExperimentState() !=
               ExperimentManager.EXP_OFF & (i < size)) {
          while ( (NetworkCore.getExperimentManager().getExperimentState() ==
                   ExperimentManager.EXP_ON_PER_ON) & (i < size)) {
            timeTemp = (1000 *
                        Integer.parseInt( ( (String[]) listInformationSorted.
                                           get(i))[3]) -
                        NetworkCore.getExperimentManager().
                        getTimeInPeriod());
            if (timeTemp > 0)
              sleep(timeTemp);
            if (NetworkCore.getExperimentManager().getPeriodNum() + 1 ==
                Integer.parseInt( ( (String[]) listInformationSorted.get(i))[2])) {

              do {
                if ( ( (String[]) listInformationSorted.get(i))[1].equals(
                    "All players")) {
                  NetworkCore.sendToAllPlayers(new Information( ( (String[])
                      listInformationSorted.get(i))[0]));
                }
                else {
                  NetworkCore.sendToPlayerCategory(new Information( ( (
                      String[])
                      listInformationSorted.get(i))[0]), ( (String[])
                      listInformationSorted.get(i))[1]);
                }
                i++;
              }
              while ((i < size)& Integer.parseInt( ( (String[]) listInformationSorted.
                                        get(i-1))[3]) ==
                     Integer.parseInt( ( (String[]) listInformationSorted.
                                        get(i))[3])  &
                     NetworkCore.getExperimentManager().getPeriodNum() + 1 ==
                     Integer.parseInt( ( (String[]) listInformationSorted.get(i))[
                                      2])
                  );
            }
            else {
              while ( (i < size) &
                     (NetworkCore.getExperimentManager().getPeriodNum() +
                      1 >
                      Integer.parseInt( ( (String[]) listInformationSorted.get(
                          i))[2]))) {
                i++;
                Utils.logger.warn("A message has not be sent");
              }
              long time = NetworkCore.getExperimentManager().
                  getTimeRemainingInPeriod();
              if (time > 0)
                this.sleep(time);
            }
          }
          while ( (NetworkCore.getExperimentManager().getExperimentState() ==
                   ExperimentManager.EXP_ON_PER_OFF) & (i < size)) {
            this.sleep(300);
            timeTemp = Math.abs(1000 *
                                Integer.parseInt( ( (String[])
                listInformationSorted.
                get(i))[3]));
          }
        }
      }
      catch (InterruptedException ex1) {
        Utils.logger.warn("MessageTimer sleep interrupted. " +
                          ex1.toString());
      }

    }
  }
}
