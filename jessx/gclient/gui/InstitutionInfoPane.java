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

import java.util.*;

import java.awt.*;
import javax.swing.*;

import org.jdom.*;
import jessx.client.*;
import jessx.client.event.*;
import jessx.net.*;
import jessx.utils.Constants;

/***************************************************************/
/*           InstitutionInfoPane CLASS SECTION                 */
/***************************************************************/
/**
 * <p>Title : InstitutionInfoPane</p>
 * <p>Description : </p>
 * @author Thierry Curtil, Christophe Grosjean
 * @version 2.0
 */


public class InstitutionInfoPane extends JPanel implements NetworkListener,Constants {

  private String assetName;
  private String opCompleteName;
  private String institutionName;
  private JScrollPane jScrollPane1=new JScrollPane();
  private JSplitPane jSplitPane1=new JSplitPane();
  JTextArea jTextArea1 = new JTextArea();
  GridBagLayout gridBagLayout1 = new GridBagLayout();

  public InstitutionInfoPane(String opCompleteName) {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    int index = opCompleteName.lastIndexOf(" on ");
    this.opCompleteName = opCompleteName;
    this.institutionName = opCompleteName.substring(index + 4);
    this.assetName = ClientCore.getInstitution(institutionName).getAssetName();
    ClientCore.addNetworkListener(this, "DividendInfo");
  }

  /**
   * display the transaction costs of different institutions, i.e.exchanges
   * @return String
   */

public String institutionReport() {
  String message = ":: Operations Costs on " + institutionName + " ::\n";

  Vector grantedOperations =ClientCore.getOperator(opCompleteName).getGrantedOperations();

  for (int i = 0; i < grantedOperations.size(); i++) {
    String operation = grantedOperations.elementAt(i).toString();
    message += "* " + operation + " : \n";
    String percentageCost = Float.toString(ClientCore.getInstitution(
          institutionName).getPercentageCost(operation));
    String minimalCost = Float.toString(ClientCore.getInstitution(
          institutionName).getMinimalCost(operation));
    message += "  - Percentage cost : " + percentageCost +
          "\n  - Minimal cost : " + minimalCost + "\n\n";
  }
  message += "_______________\n\n";
  return message;
  }


  /**
   * show the dividend information in the XML document
   * @param xmlDoc Document
   */
  public void objectReceived(Document xmlDoc) {
    if (xmlDoc.getRootElement().getName().equals("DividendInfo")) {
      DividendInfo divInfo = new DividendInfo();
      divInfo.initFromNetworkInput(xmlDoc.getRootElement());
      if (divInfo.getAssetName().equals(this.assetName))
      {
        if (divInfo.getIsShowingOperationsCosts())
          this.jTextArea1.setText(this.institutionReport() + divInfo.produceInfoReport());
        else
          this.jTextArea1.setText(divInfo.produceInfoReport());
      }
    }
  }

  private void jbInit() throws Exception {
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    this.setLayout(gridBagLayout1);
    this.add(jScrollPane1, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
    GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 2, 4), 0, 0));
    jTextArea1.setBackground(UIManager.getColor("Panel.background"));
    jTextArea1.setEnabled(true);
    jTextArea1.setEditable(false);
    jTextArea1.setLineWrap(true);
    jTextArea1.setWrapStyleWord(true);
    jTextArea1.setFont(FONT_CLIENT_TEXTAREA);
    this.setLayout(gridBagLayout1);
    jScrollPane1.getViewport().add(jTextArea1, null);
  }

}
