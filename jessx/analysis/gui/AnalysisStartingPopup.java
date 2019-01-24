package jessx.analysis.gui;

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

import java.net.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/***************************************************************/
/*            AnalysisStartingPopup CLASS SECTION              */
/***************************************************************/

/**
 * <p>Title: AnalysisStartingPopup</p>
 * <p>Description: Popup briefly displayed at the launch of JessX analyzer.</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class AnalysisStartingPopup
    extends JDialog {

  ImageIcon logoJessx = new ImageIcon();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JLabel jLabel_progression = new JLabel();
  Border border1;
  Border border2;

  /**Constructor
   * @param parent Frame
   */

  public AnalysisStartingPopup(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public AnalysisStartingPopup() {
    this(null);
  }

  /** Method initializing the component
   * @throws java.lang.Exception
   */

  private void jbInit() throws Exception {

    border1 = BorderFactory.createLineBorder(SystemColor.controlText, 1);
    border2 = BorderFactory.createLineBorder(SystemColor.controlText, 1);
    this.setUndecorated(true);
    this.getContentPane().setLayout(gridBagLayout1);
    this.setModal(false);
    this.setResizable(false);

    String fileSeparator = System.getProperty("file.separator");
    String imagesDir = System.getProperty("user.dir") + fileSeparator +
        "images" + fileSeparator;

    logoJessx = new ImageIcon(new URL("file:" + imagesDir +
                                      "logo_JessX_small.PNG"));

    jLabel1.setBackground(Color.white);
    jLabel1.setMaximumSize(new Dimension(387, 169));
    jLabel1.setMinimumSize(new Dimension(387, 169));
    jLabel1.setPreferredSize(new Dimension(387, 169));
    jLabel1.setIcon(logoJessx);
    jLabel1.setText("");

    jPanel1.setLayout(gridBagLayout2);
    jLabel_progression.setBackground(Color.white);
    jLabel_progression.setMaximumSize(new Dimension(800, 64));
    jLabel_progression.setMinimumSize(new Dimension(200, 16));
    jLabel_progression.setPreferredSize(new Dimension(200, 16));
    jPanel1.setBackground(SystemColor.inactiveCaptionText);
    jPanel1.setBorder(border2);
    this.getContentPane().add(jPanel1,
                              new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jLabel_progression, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
        new Insets(6, 6, 6, 6), 0, 0));
  }

  /** Overridden so we can exit when window is closed
   * @param e a new WindowEvent
   */

  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }

  /** Close the dialog
   *
   */

  void cancel() {
    dispose();
  }
}
