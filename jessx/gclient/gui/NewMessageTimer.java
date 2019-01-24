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
 * <p>Title : NewMessageTimer</p>
 * <p>Description : </p>
 * @author Christophe Grosjean
 * @version 1.0
 */

public class NewMessageTimer
    extends Thread {
  JTextArea jtextArea;
  JScrollPane jScrollPane;
  int previousTextAreaHeight;
  int windowHeight;
  int messageHeight;

  public NewMessageTimer(JTextArea jTextAreaCommunication,JScrollPane jScrollPaneCommunication,int jTextAreaCommunicationHeightBeforeUpdate,int heightOfWindow) {
    jtextArea = jTextAreaCommunication;
    jScrollPane = jScrollPaneCommunication;
    previousTextAreaHeight = jTextAreaCommunicationHeightBeforeUpdate;
    windowHeight = heightOfWindow;
  }
  /**
   *
   */
  public void run() {
    try {
        messageHeight = jtextArea.getHeight() - previousTextAreaHeight;
        if (windowHeight >= messageHeight) {
            // set the scroll bar in order that the players don't need to adjust it to get new information
            jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum());
        }
        else {
          jScrollPane.getVerticalScrollBar().setValue(jScrollPane.getVerticalScrollBar().getMaximum() - messageHeight + windowHeight);
        }

        jtextArea.setBackground(Color.RED);
        //create the effect of dashed color in the information box at the end of each period
        this.sleep(300);
        jtextArea.setBackground(UIManager.getColor("Button.background").brighter());
        this.sleep(300);
        jtextArea.setBackground(UIManager.getColor("Button.background").darker());
        this.sleep(300);
        jtextArea.setBackground(UIManager.getColor("Button.background").brighter());
        this.sleep(300);
        jtextArea.setBackground(UIManager.getColor("Button.background"));
    }
      catch (InterruptedException ex) {
        Utils.logger.warn("NewMessageTimer sleep interrupted. " + ex.toString());
        jtextArea.setBackground(UIManager.getColor("Button.background"));
      }

  }
}
