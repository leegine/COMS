package jessx.gclient;

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

import jessx.gclient.gui.*;
import java.io.File;
import com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel;

/***************************************************************/
/*              JessXClient CLASS SECTION                      */
/***************************************************************/
/**
 * <p>Title : JessXClient</p>
 * <p>Description :</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class JessXClient {
  boolean packFrame = false;

  //Construct the application
  public JessXClient() {

    GClientFrame frame = new GClientFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    //adapt the window and put the frame in the center of the screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }

  //Main method
  public static void main(String[] args) {
    try {
      File file = new File(System.getProperty("user.dir"), "market.zotm");
      OyoahaLookAndFeel lnf = new OyoahaLookAndFeel();
      if (file.exists()) lnf.setOyoahaTheme(file);
      UIManager.setLookAndFeel(lnf);
  //   UIManager.setLookAndFeel("new com.incors.plaf.kunststoff.KunststoffLookAndFeel()");


      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new JessXClient();
  }
}
