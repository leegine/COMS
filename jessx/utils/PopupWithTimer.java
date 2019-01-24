package jessx.utils;

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
/*                      IMPORT SECTION                         */
/***************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/***************************************************************/
/*                  PopupWithTimer Class Section               */
/***************************************************************/
/**
 * <p>Title : PopupWithTimer
 * <p>Description : This is a class which provides something
 * which looks like a pop up modified. After a time choosen,
 * this window is closed when you the method run()</p>
 * @author Christophe Grosjean
 * @version 1.0
 */
public class PopupWithTimer
    extends Thread {


  private Dialogue dialog;
  private int time;
  private String name;
  private JFrame parentComponent;
  private JButton jbuttonClose = new JButton("Ok");
  private boolean closeActivated = false;

  /**
   * @param timeSeconde int
   * @param comp Component
   * @param frameSize Dimension
   * @param title String
   * @param parent Component
   */

  public PopupWithTimer(int timeSeconde, Component comp, Dimension frameSize,
                        String title, JFrame parent) {
    dialog = new Dialogue(parent);
    parentComponent = parent;
    time = timeSeconde;
    name = title;
    jbuttonClose.addActionListener(new
                                   PopupWithTimer_jbuttonClose_actionAdapter(this));
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBackground(new Color(203,230,211));
    panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED,
                                                    Color.white, Color.white,
                                                    new Color(115, 114, 105),
                                                    new Color(165, 163, 151)));
    panel.add(comp, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
                                           , GridBagConstraints.CENTER,
                                           GridBagConstraints.BOTH,
                                           new Insets(0, 0, 0, 0), 0, 283));

    dialog.getContentPane().setLayout(new GridBagLayout());
    dialog.getContentPane().add(jbuttonClose,
                                new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    dialog.getContentPane().add(panel,
                                new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Utils.logger.debug("Dimension Screen"+screenSize);
    Utils.logger.debug("Dimension Frame"+frameSize);
    dialog.setLocation( (screenSize.width - frameSize.width) / 2,
                       (screenSize.height - Math.min(frameSize.height + 75 + jbuttonClose.HEIGHT, 400)) / 2);
    //dialog.setSize(Math.max(frameSize.width + 15, 250),
    //               Math.min(frameSize.height + 75 + jbuttonClose.HEIGHT, 400));
    dialog.setSize(Math.max(frameSize.width + 15, 250),
                   Math.min(frameSize.height + 100 + jbuttonClose.HEIGHT, 500));

  }

  public void run() {
    dialog.show();
    dialog.setDefaultCloseOperation(dialog.EXIT_ON_CLOSE);
    parentComponent.setFocusable(false);
    dialog.getRootPane().setDefaultButton(this.jbuttonClose);
        try {

      int j, i = time;
      while (!closeActivated && i > 0 && !dialog.closeEvent) {
        i--;
        dialog.setTitle(name + " :" + i + "s");
        j = 0;
        while (!closeActivated && 10 > j++ && !dialog.closeEvent)
          sleep(100);
      }
    }
    catch (InterruptedException ex) {
      parentComponent.setFocusable(true);
      dialog.hide();
    }
    parentComponent.setFocusable(true);
    dialog.hide();
  }

  public void jbuttonClose_actionPerformed(ActionEvent e) {
    closeActivated = true;
  }


}

/***************************************************************/
/*                 Dialogue Classes SECTION                    */
/***************************************************************/
class Dialogue
    extends JDialog {
  boolean closeEvent = false;
  public Dialogue(JFrame parent) {
    super(parent);
  }

  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      closeEvent = true;
    }
  }
}

/***************************************************************/
/*                   EVENT CLASSES SECTION                     */
/***************************************************************/
class PopupWithTimer_jbuttonClose_actionAdapter
    implements java.awt.event.ActionListener {
  PopupWithTimer adaptee;

  PopupWithTimer_jbuttonClose_actionAdapter(PopupWithTimer adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jbuttonClose_actionPerformed(e);
  }
}
