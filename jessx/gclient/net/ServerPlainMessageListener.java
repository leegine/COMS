package jessx.gclient.net;

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

import org.jdom.*;
import jessx.client.*;
import jessx.client.event.*;
import jessx.utils.*;
import java.awt.Color;

/***************************************************************/
/*          ServerPlainMessageListener CLASS SECTION           */
/***************************************************************/
/**
 * <p>Title : ServerPlainMessageListener</p>
 * <p>Description :</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class ServerPlainMessageListener
    implements NetworkListener {

  public ServerPlainMessageListener() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private JFrame parent;
  /**
   * define the frame
   * @param frame JFrame
   */

  public ServerPlainMessageListener(JFrame frame) {
    this.parent = frame;
    ClientCore.addNetworkListener(this, "Message");
  }
  /**
   * set the graphic properties of the text box which shows the information
   * @param doc Document
   */

  public void objectReceived(Document doc) {
    if (doc.getRootElement().getName().equals("Message")) {
      String message = doc.getRootElement().getText();
      int rows =message.length()/50+1;
      int columns =(rows<2)?message.length():50;

      JTextArea jTextArea = new JTextArea(message,rows,columns);
      JScrollPane jScrollPane = new JScrollPane();
      jScrollPane.getViewport().add(jTextArea, null);
      jTextArea.setEditable(false);
      jTextArea.setOpaque(false);
      jTextArea.setAutoscrolls(true);
      jTextArea.setVisible(true);
      jScrollPane.doLayout();
      jTextArea.setBackground(new Color(203,230,211));
      if (doc.getRootElement().getText().equals("Connection accepted.")) {
        jScrollPane.setBorder(null);
      }
      new PopupWithTimer(120, jScrollPane, jTextArea.getPreferredSize(),
                         "JessX Server", parent).run();
    }
  }
  /**
   *
   * @throws Exception
   */

  private void jbInit() throws Exception {
  }
}
