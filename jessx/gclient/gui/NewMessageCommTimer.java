package jessx.gclient.gui;


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

import java.awt.*;
import javax.swing.*;

import jessx.utils.*;

/***************************************************************/
/*             NewMessageTimer CLASS SECTION                   */
/***************************************************************/
/**
 * <p>Title : NewMessageCommTimer</p>
 * <p>Description : Text flashing when a message is received in the Communication panel</p>
 * @author Rémi Quilliet
 * @version 1.0
 */

public class NewMessageCommTimer
    extends Thread {
  JTabbedPane jTabbedPane;
  int index;


  public NewMessageCommTimer(JTabbedPane jTabbedPaneServerComm,int indexcomm) {

    jTabbedPane = jTabbedPaneServerComm;
    index = indexcomm;
  }


  public void run() {
    try {
        jTabbedPane.setForegroundAt(index, Color.red);
        this.sleep(400);
        jTabbedPane.setForegroundAt(index, Color.black);
        this.sleep(400);
        jTabbedPane.setForegroundAt(index, Color.red);
        this.sleep(400);
        jTabbedPane.setForegroundAt(index, Color.black);
        this.sleep(400);
        jTabbedPane.setForegroundAt(index, Color.red);
        this.sleep(400);
        if (jTabbedPane.getSelectedIndex() == index)
           jTabbedPane.setForegroundAt(index, Color.black);
         else jTabbedPane.setForegroundAt(index, Color.red);

    }
      catch (InterruptedException ex) {
        Utils.logger.warn("NewMessageCommTimer sleep interrupted. " + ex.toString());
        jTabbedPane.setForegroundAt(index, Color.black);
      }

  }
}
