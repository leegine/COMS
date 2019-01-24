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

import javax.swing.*;
import jessx.utils.*;
import jessx.server.net.*;

/***************************************************************/
/*                ServerTimer CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : ServerTimer</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class ServerTimer
    extends Thread {

 // private boolean closeServerTimer = false;
  private JButton button;
  private JTextField textField;
  private boolean endOfExp;

  public ServerTimer(JButton button, JTextField textField) {
    this.button = button;
    this.textField = textField;
  }

 // public void closeServerTimer() {
 //   this.closeServerTimer = true;
 // }

  /**
   *
   */
  public void run() {
    while (true) {
      int state = NetworkCore.getExperimentManager().getExperimentState();
      if (state == ExperimentManager.EXP_ON_PER_ON) {
        endOfExp=true;
        textField.setText("Experiment ON");
        button.setText("Period " +
                       (NetworkCore.getExperimentManager().getPeriodNum() +
                        1)
                       + " - " +
                       this.msecToString(NetworkCore.getExperimentManager().
                                         getTimeRemainingInPeriod())
                       + " remaining...");
      }

      else if (state == ExperimentManager.EXP_OFF) {
        if (endOfExp){
          button.setText("End of experiment");
          button.setEnabled(true);
          endOfExp = false;
        }
        textField.setText("Experiment OFF");
      }

      try {
        this.sleep(1000);
      }
      catch (InterruptedException ex) {
        Utils.logger.warn("ServerTimer sleep interrupted. " + ex.toString());
      }
    }
  }

  /**
   *
   * @param ms long
   * @return String
   */
  private String msecToString(long ms) {
    int minute = (int) ms / 60000;
    int second = (int) (ms - minute * 60000) / 1000;
    String seconds;

    if (second < 10) {
      seconds = "0" + second;
    }
    else {
      seconds = Integer.toString(second);
    }
    return ( (minute == 0) ? "" : Integer.toString(minute) + "min ") + seconds +
        "s";
  }

}
