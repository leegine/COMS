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
import javax.swing.border.*;
import javax.swing.event.*;

import org.jdom.*;
import jessx.business.*;
import jessx.client.*;
import jessx.client.event.*;


import jessx.utils.*;


/***************************************************************/
/*           PanelServerComm CLASS SECTION                 */
/***************************************************************/
/**
 * <p>Title : PanelServerComm</p>
 * <p>Description : </p>
 * @author Rémi Quilliet
 * @version 1.0
 */


public class PanelServerComm extends JTabbedPane
    implements Constants, OperatorPlayedListener,
    ExperimentDeveloppmentListener, NetworkListener, ConnectionListener{

  //private JTabbedPane jPaneServerComm = new JTabbedPane();
  private JTextArea TextAreaCommunication = new JTextArea();
  private JTextArea TextAreaCommunication2 = new JTextArea();
  private JScrollPane ScrollPaneCommunication = new JScrollPane();
  private JScrollPane ScrollPaneCommunication2 = new JScrollPane();
  private JPanel PanelCommunication = new JPanel();
  private JPanel PanelCommunication2 = new JPanel();
  private int indexcomm12 = 0;
  private int indexcomm22 = 1;
 TitledBorder titledBorder162;
  TitledBorder titledBorder172;
 GridBagLayout gridBagLayoutCommunication = new GridBagLayout();



 public PanelServerComm() {
     jbInit();
 }

 void PanelServerComm_stateChanged(ChangeEvent e) {
     int newIndex = this.getSelectedIndex();
     this.setForegroundAt(newIndex, Color.black);
    }


 public void jbInit() {


   this.addChangeListener(new PanelServerComm_changeAdapter(this));
   titledBorder162 = new TitledBorder(BorderFactory.createEmptyBorder(6,0,0,0),"",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,FONT_CLIENT_TITLE_BORDER);
     titledBorder172 = new TitledBorder(BorderFactory.createEmptyBorder(6,0,0,0),"",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,FONT_CLIENT_TITLE_BORDER);
   TextAreaCommunication.setBackground(UIManager.getColor("Button.background"));
      TextAreaCommunication.setEnabled(true);
      TextAreaCommunication.setBorder(BorderFactory.createLoweredBevelBorder());
      TextAreaCommunication.setEditable(false);
      TextAreaCommunication.setSelectedTextColor(Color.white);
      TextAreaCommunication.setLineWrap(true);
      TextAreaCommunication.setWrapStyleWord(true);
      TextAreaCommunication.setFont(FONT_CLIENT_TEXTAREA);

      TextAreaCommunication2.setBackground(UIManager.getColor("Button.background"));
      TextAreaCommunication2.setEnabled(true);
      TextAreaCommunication2.setBorder(BorderFactory.createLoweredBevelBorder());
      TextAreaCommunication2.setEditable(false);
      TextAreaCommunication2.setSelectedTextColor(Color.white);
      TextAreaCommunication2.setLineWrap(true);
      TextAreaCommunication2.setWrapStyleWord(true);
    TextAreaCommunication2.setFont(FONT_CLIENT_TEXTAREA);
    PanelCommunication.setBorder(titledBorder162);

      PanelCommunication.setMinimumSize(new Dimension(150, 160));
       PanelCommunication.setPreferredSize(new Dimension(150, 160));
       PanelCommunication.setLayout(gridBagLayoutCommunication);


       PanelCommunication2.setBorder(titledBorder172);

       PanelCommunication2.setMinimumSize(new Dimension(150, 100));
      PanelCommunication2.setPreferredSize(new Dimension(150, 100));
  PanelCommunication2.setLayout(gridBagLayoutCommunication);


    PanelCommunication.add(ScrollPaneCommunication,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 2, 4), 0, 0));
        ScrollPaneCommunication.getViewport().add(TextAreaCommunication, null);

        PanelCommunication2.add(ScrollPaneCommunication2,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 2, 4), 0, 0));
    ScrollPaneCommunication2.getViewport().add(TextAreaCommunication2, null);

  this.add(PanelCommunication,"Financial Information", indexcomm12);
  this.add(PanelCommunication2,"Press Review", indexcomm22);


 }




    public void messageReceived(String msg) {
      this.TextAreaCommunication.append("\n===============================\n" +msg);

      NewMessageCommTimer timerMessage = new NewMessageCommTimer(this,
              indexcomm12);
      timerMessage.start();

}
    public void informationReceived(String msg) {
      this.TextAreaCommunication2.append("\n===============================\n" +msg);
      NewMessageCommTimer timerMessage2 = new NewMessageCommTimer(this,
           indexcomm22);
       timerMessage2.start();

}



 public void objectReceived(Document xmlDoc) {


   if (xmlDoc.getRootElement().getName().equals("Initialisation")) {
     System.out.print("\nInitialisation Client\n");
     ClientCore.initializeForNewExperiment();
     TextAreaCommunication.setText("");
     TextAreaCommunication2.setText("");
   }
   else if (xmlDoc.getRootElement().getName().equals("Message")) {
      this.TextAreaCommunication.append("\n===============================\n" +
                                         xmlDoc.getRootElement().getText());



      NewMessageCommTimer timerMessage = new NewMessageCommTimer(this,
              indexcomm12);
      timerMessage.start();

 }
 else if (xmlDoc.getRootElement().getName().equals("Information")) {

   this.TextAreaCommunication2.append("\n===============================\n" +
                                      //timer.getText()+"\n"+
                                      xmlDoc.getRootElement().getText());

   NewMessageCommTimer timerMessage2 = new NewMessageCommTimer(this,
       indexcomm22);
   timerMessage2.start();
 }

}

  public void newOperator(Operator op) {
  }

  public void experimentBegins() {
  }

  public void experimentFinished() {
  }

  public void periodBegins() {
  }

  public void periodFinished() {
  }

  public void connectionStateChanged(int newState) {
  }


}


class PanelServerComm_changeAdapter implements javax.swing.event.ChangeListener {
  PanelServerComm adaptee;

  PanelServerComm_changeAdapter(PanelServerComm adaptee) {
    this.adaptee = adaptee;
  }

  public void stateChanged(ChangeEvent e) {
    adaptee.PanelServerComm_stateChanged(e);
  }
}
