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

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import jessx.business.*;
import jessx.business.event.*;
import jessx.utils.*;

/***************************************************************/
/*         DividendSetupSlidingWindow CLASS SECTION            */
/***************************************************************/
/**
 * <p>Title : DividendSetupSlidingWindow</p>
 * <p>Description : Panel where the parameters of the dividend model of the assets <br>
 * can be defined by the user.</p>
 * @author Thierry Curtil, Christophe Grosjean
 * @version 1.0
 */

public class DividendSetupSlidingWindow extends JPanel implements DisplayableNode,
    ChangeListener, PlayerTypeListener, DividendInfoListener {

 // real content
  private DividendModel dividendModel;
  private String assetName;

  // graphical elements
  JPanel jPanel1 = new JPanel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JTable jTable1;
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  JPanel jpanelNoPlayer = new JPanel();
  private HashMap jPanelDivinfoList = new HashMap();
  TableModelDividendSetup tableModelDividendSetup;


  public DividendSetupSlidingWindow(String assetName, DividendModel divModel) {
    super();
    this.dividendModel = divModel;
    this.assetName = assetName;

    JTextArea jTextArea = new JTextArea(
        "You have to add a category of players\nbefore completing this section!");
    jTextArea.setEditable(false);
    jTextArea.setBackground(jpanelNoPlayer.getBackground());
    jTextArea.setFont(jpanelNoPlayer.getFont());
    jpanelNoPlayer.add(jTextArea);

    // Graphical initialisation...
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    // Now the content of the GUI needs to be filled
    Iterator ptIter = BusinessCore.getScenario().getPlayerTypes().keySet().iterator();
    if (!BusinessCore.getScenario().getPlayerTypes().isEmpty()){
      while (ptIter.hasNext()) {
        String key = (String) ptIter.next();
        this.jPanelDivinfoList.put(key,
                                   new DividendInfoPanel(this.
            getDividendInfo(key)));
        this.jTabbedPane1.add(key, (JPanel)this.jPanelDivinfoList.get(key));
      }
    }
    else {
      this.jTabbedPane1.add("No player",jpanelNoPlayer);
    }
    BusinessCore.getGeneralParameters().addPeriodCountChangeListener(this);
    BusinessCore.getScenario().addPlayerTypeListener(this);
  }

  public void setEditable(){
    Iterator iter = this.jPanelDivinfoList.keySet().iterator();
  while (iter.hasNext()){
    String key = (String) iter.next();
    ((DividendInfoPanel) this.jPanelDivinfoList.get(key)).active();
  }
  tableModelDividendSetup.setCellEditable();
  }


  public void setUneditable(){
  Iterator iter = this.jPanelDivinfoList.keySet().iterator();
  while (iter.hasNext()){
    String key = (String) iter.next();
    ((DividendInfoPanel) this.jPanelDivinfoList.get(key)).desactive();
  }
  tableModelDividendSetup.setCellUneditable();
}

  public String toString() {
    return this.assetName;
  }

  public JPanel getPanel() {
    return this;
  }

  public void stateChanged(ChangeEvent e) {
    if (e.getSource() instanceof JSpinner) {
      int periodNum = new Integer (((JSpinner)e.getSource()).getValue().toString()).intValue();
      ((TableModelDividendSetup)this.jTable1.getModel()).setPeriodCount(periodNum);
    }
  }

  public void playerTypeModified(PlayerTypeEvent e) {
    if (BusinessCore.getAssets().containsKey(assetName)){
      if (e.getEvent() == PlayerTypeEvent.PLAYER_ADDED) {
        jTabbedPane1.remove(jpanelNoPlayer);
        String key = e.getPlayerType().getPlayerTypeName();
        this.jPanelDivinfoList.put(key,
                                   new DividendInfoPanel(this.
            getDividendInfo(key)));
        this.jTabbedPane1.add(key, (JPanel)this.jPanelDivinfoList.get(key));
      }
      else if (e.getEvent() == PlayerTypeEvent.PLAYER_REMOVED) {
        this.jTabbedPane1.remove( (JPanel)this.jPanelDivinfoList.get(e.
            getPlayerType().getPlayerTypeName()));
        this.jPanelDivinfoList.remove(e.getPlayerType().getPlayerTypeName());
        if (BusinessCore.getScenario().getPlayerTypes().isEmpty()) {
          jTabbedPane1.add("No player", jpanelNoPlayer);
        }
      }
    }
  }

  public void dividendInfoModified(DividendInfoEvent e) {
    return;
  }

  /**
   * @param playerType String
   * @return DividendInfo
   */


  private DividendLimitation getDividendInfo(String playerType) {
    Utils.logger.debug("Getting dividend info...");
    DividendLimitation divInf = BusinessCore.getScenario().getDividendInfo(this.assetName,playerType);
    if (divInf == null) {
      Utils.logger.debug("No dividend info found for (" + playerType + ", " + this.assetName + ")");
      divInf = new DividendLimitation(playerType, this.assetName);
      Utils.logger.debug("Creating one...");
      BusinessCore.getScenario().setDividendInfo(this.assetName, playerType, divInf);
      divInf.addListener(this);
    }
    return divInf;
  }


  private void jbInit() throws Exception {
    this.setLayout(gridBagLayout1);
    tableModelDividendSetup=new TableModelDividendSetup(NormalDividend.class,this.dividendModel);
    this.jTable1 = new JTable(tableModelDividendSetup);
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setLayout(gridBagLayout2);
    jScrollPane1.setMinimumSize(new Dimension(50, 50));
    this.add(jPanel1,      new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    jScrollPane1.getViewport().add(jTable1, null);
    jPanel1.add(jScrollPane1, new GridBagConstraints(0, 0, 1, 1, 0.5, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jTabbedPane1, new GridBagConstraints(1, 0, 1, 1, 0.5, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
  }
}
