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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import org.jdom.*;
import jessx.business.*;
import jessx.business.event.*;
import jessx.net.*;
import jessx.server.net.*;
import jessx.server.net.event.*;
import jessx.utils.*;

/***************************************************************/
/*             CommunicationGui CLASS SECTION                  */
/***************************************************************/

/**
 * <p>Title : CommunicationGui</p>
 * <p>Description :</p>
 * @authors: Grosjean Christophe, Charles Montez
 * @version 1.0
 */

public class CommunicationGui
    extends JPanel implements PlayerTypeListener,XmlExportable, ExperimentStateListener {


  String[] chatToBeSaved;
// Variables
  // 3 panels principaux :(les indices marquent l'appartenance des objets aux panels)
  JPanel jPanel1 = new JPanel(); // "Saisie du message"
  JPanel jPanel2 = new JPanel(); // "Parametres d'envoi"
  JPanel jPanel3 = new JPanel(); // "Historique des message crees"

  GridBagLayout gridBagLayout0 = new GridBagLayout(); // celui du panel this
  GridBagLayout gridBagLayout1 = new GridBagLayout(); // celui du panel 1
  GridBagLayout gridBagLayout2 = new GridBagLayout(); // celui du panel 2
  GridBagLayout gridBagLayout3 = new GridBagLayout(); // panel 3

  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea11Message = new JTextArea();

  JComboBox jComboBox2Receivers = new JComboBox();
  JComboBox jComboBox2inTable = new JComboBox();
  JLabel jLabel2To = new JLabel();
  JButton jButton2Send = new JButton();
  JLabel jLabelSpace = new JLabel();

  JScrollPane jScrollPane3 = new JScrollPane();
  TableModelMessages tableModelMessages = new TableModelMessages(4, (JPanel) this); //Four columns
  JTable jTable3Receivers = new JTable(tableModelMessages);

  CommuncationGui_jTable3Receivers_mouseAdapter
      communcationGui_jTable3Receivers_mouseAdapter = new
      CommuncationGui_jTable3Receivers_mouseAdapter(this);

  //borders
  Border border1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
      white,
      new Color(156, 156, 158)), "Message");
  Border border2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
      white,
      new Color(156, 156, 158)), "Properties");
  Border border3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
      white,
      new Color(156, 156, 158)), "Summary");

  String[] message = new String[] {
      "", "", "", ""}
      ;

  CommunicationGui_jButton2Send_mouseAdapter1
      communicationGui_jButton2Send_mouseAdapter = new
      CommunicationGui_jButton2Send_mouseAdapter1(this);

  public CommunicationGui() {
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    BusinessCore.getScenario().addPlayerTypeListener(this);
    NetworkCore.getExperimentManager().addExperimentStateListener(this);
  }

  // Initialiser
  private void jbInit() throws Exception {
    this.setLayout(gridBagLayout0);

    jPanel1.setBorder(border1);
    jPanel1.setLayout(gridBagLayout1);

    jTextArea11Message.setBorder(BorderFactory.createEtchedBorder());
    jTextArea11Message.setMargin(new Insets(2, 2, 2, 2));
    jTextArea11Message.setText("Write here your message");
    jTextArea11Message.setRows(3);
    jLabelSpace.setText(" ");
    jScrollPane1.getViewport().add(jTextArea11Message);

    jPanel2.setLayout(gridBagLayout2);
    jPanel2.setBorder(border2);

    jLabel2To.setHorizontalAlignment(SwingConstants.RIGHT);
    jLabel2To.setText("To :");

    jTable3Receivers.addMouseListener(
        communcationGui_jTable3Receivers_mouseAdapter);
    tableModelMessages.setEditableCellsPossible(false);
    jButton2Send.setEnabled(false);
    jButton2Send.setText("Send");
    jComboBox2Receivers.setEnabled(false);
    jComboBox2Receivers.setMaximumRowCount(5);
    jComboBox2Receivers.addItem("All players");
    jComboBox2inTable.setMaximumRowCount(5);
    jComboBox2inTable.addItem("All players");

    jPanel3.setLayout(gridBagLayout3);
    jPanel3.setBorder(border3);

    jScrollPane3.getViewport().add(jTable3Receivers);
    jTable3Receivers.getColumnModel().getColumn(1).setCellEditor(new
        DefaultCellEditor(jComboBox2inTable));

    this.add(jPanel1, new GridBagConstraints(0, 0, 1, 1, 0.3, 20.0
                                             , GridBagConstraints.NORTH,
                                             GridBagConstraints.BOTH,
                                             new Insets(5, 5, 0, 5), 1, 1));
    this.add(jPanel2, new GridBagConstraints(0, 1, 1, 1, 0.3, 20.0
                                             , GridBagConstraints.CENTER,
                                             GridBagConstraints.BOTH,
                                             new Insets(0, 5, 0, 5), 1, 1));
    this.add(jPanel3, new GridBagConstraints(0, 2, 1, 1, 0.3, 60.0
                                             , GridBagConstraints.SOUTH,
                                             GridBagConstraints.BOTH,
                                             new Insets(0, 5, 5, 5), 1, 1));

    jPanel1.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
        , GridBagConstraints.NORTH, GridBagConstraints.BOTH,
        new Insets(5, 5, 5, 5), 1, 1));

    jPanel3.add(jScrollPane3, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 582, 56));
    jPanel2.add(jLabel2To, new GridBagConstraints(1, 0, 1, 1, 5.0, 0.0
                                                  , GridBagConstraints.CENTER,
                                                  GridBagConstraints.BOTH,
                                                  new Insets(0, 5, 5, 5), 0, 0));
    jPanel2.add(jButton2Send, new GridBagConstraints(4, 0, 1, 1, 15.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 5, 5, 5), 0, 0));
    jPanel2.add(jComboBox2Receivers,
                new GridBagConstraints(2, 0, 1, 1, 40.0, 0.0
                                       , GridBagConstraints.CENTER,
                                       GridBagConstraints.BOTH,
                                       new Insets(0, 5, 5, 5), 0, 0));
    jPanel2.add(jLabelSpace, new GridBagConstraints(3, 0, 1, 1, 40.0, 0.0
        , GridBagConstraints.CENTER,
        GridBagConstraints.BOTH,
        new Insets(0, 5, 5, 5), 0, 0));
  }

  public void saveToXml(Element parentNode){
  Element messageNode = new Element("ChatMessage");
  messageNode.setAttribute("Subject",this.chatToBeSaved[0]);
  messageNode.setAttribute("Receivers",this.chatToBeSaved[1]);
  messageNode.setAttribute("Period",this.chatToBeSaved[2]);
  messageNode.setAttribute("Time",this.chatToBeSaved[3]);
  parentNode.addContent(messageNode);
  }

  public void playerTypeModified(PlayerTypeEvent e) {
    if (e.getEvent() == PlayerTypeEvent.PLAYER_ADDED) {
      this.jComboBox2Receivers.addItem(e.getPlayerType().getPlayerTypeName());
      this.jComboBox2inTable.addItem(e.getPlayerType().getPlayerTypeName());
    }
    else if (e.getEvent() == PlayerTypeEvent.PLAYER_REMOVED) {
      tableModelMessages.deletePlayerType(e.getPlayerType().getPlayerTypeName());
      this.jComboBox2Receivers.removeItem(e.getPlayerType().getPlayerTypeName());
      this.jComboBox2inTable.removeItem(e.getPlayerType().getPlayerTypeName());
    }
  }

  public void experimentStateChanged(int newState){
    if (newState == ExperimentManager.EXP_OFF){
     this.setCommunicationGuiAllUneditable();
    }
    else {
      this.setCommunicationGuiAllEditable();
    }
  }

  public void jButton2Send_mouseClicked(MouseEvent e) {
    if (NetworkCore.getExperimentManager().getExperimentState() !=
        NetworkCore.getExperimentManager().EXP_OFF) {
      if (NetworkCore.getExperimentManager().getExperimentState() ==
          NetworkCore.getExperimentManager().EXP_ON_PER_ON) {
        chatToBeSaved =new String[] {message[0], message[1],
                                  Integer.toString(NetworkCore.
            getExperimentManager().getPeriodNum() + 1),
                                  Float.toString(Math.round(NetworkCore.
            getExperimentManager().getTimeInPeriod() / 100) / 10)};
        tableModelMessages.addRow(chatToBeSaved);
        saveToXml(BusinessCore.getElementToSaveChat());
      }
      else {
        chatToBeSaved = new String[] {
            message[0], message[1],"Period off", "No time"};
            tableModelMessages.addRow(chatToBeSaved);
        saveToXml(BusinessCore.getElementToSaveChat());
      }
    }
    else {
      chatToBeSaved = new String[] {
          message[0], message[1], "Experiment off", "No time"};
      tableModelMessages.addRow(chatToBeSaved);
      //Nothing to save here because the experiment is already finished
    }
    if ("All players".equals(message[1])) {
      NetworkCore.sendToAllPlayers(new Information(message[0]));
    }
    else {
      NetworkCore.sendToPlayerCategory(new Information(message[0]),
                                            message[1]);
    }
  }

        public void jButton2Send_mouseEntered(MouseEvent e) {
    message = new String[] {
        jTextArea11Message.getText(),
        jComboBox2Receivers.getSelectedItem().toString(),
        "",
        ""
    };
  }

  private void setCommunicationGuiAllUneditable() {
    jComboBox2Receivers.setEnabled(false);
    jButton2Send.setEnabled(false);
    jButton2Send.removeMouseListener(
        communicationGui_jButton2Send_mouseAdapter);
    tableModelMessages.setEditableCellsPossible(false);
  }

  private void setCommunicationGuiAllEditable() {
    jComboBox2Receivers.setEnabled(true);
    jButton2Send.setEnabled(true);
    if(jButton2Send.getMouseListeners().length==1)
    jButton2Send.addMouseListener(communicationGui_jButton2Send_mouseAdapter);
    tableModelMessages.setEditableCellsPossible(true);
  }

  void jTable3Receivers_mouseClicked(MouseEvent e) {
    int row = this.jTable3Receivers.getSelectedRow();
    this.jTextArea11Message.setText(jTable3Receivers.getValueAt(row, 0).
                                    toString());
    this.jComboBox2Receivers.setSelectedItem(jTable3Receivers.getValueAt(row, 1).
                                             toString());
  }

}

/***************************************************************/
/*                   EVENT CLASSES SECTION                     */
/***************************************************************/

class CommunicationGui_jButton2Send_mouseAdapter1
    extends MouseAdapter {
  private CommunicationGui adaptee;
  CommunicationGui_jButton2Send_mouseAdapter1(CommunicationGui
                                              adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseEntered(MouseEvent e) {
    adaptee.jButton2Send_mouseEntered(e);
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jButton2Send_mouseClicked(e);
  }
}

class CommuncationGui_jTable3Receivers_mouseAdapter
    extends java.awt.event.MouseAdapter {
  CommunicationGui adaptee;

  CommuncationGui_jTable3Receivers_mouseAdapter(CommunicationGui adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTable3Receivers_mouseClicked(e);
  }
}
