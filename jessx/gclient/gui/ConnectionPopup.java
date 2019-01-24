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
import java.io.*;
import java.util.*;

import java.awt.*;
import javax.swing.*;

import jessx.client.*;
import jessx.utils.*;

/***************************************************************/
/*                ConnectionPopup CLASS SECTION                */
/***************************************************************/

/**
 * <p>Title : ConnectionPopup</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class ConnectionPopup
    implements Constants {

  public ConnectionPopup(Frame parentFrame) {

    //Used to load / save information
    Utils.logger.debug("looking for user home directory.");
    String settingsFile = null;
    String settingsDirectory = Utils.getApplicationSettingsDirectory();
    if (settingsDirectory != null)
      settingsFile = settingsDirectory + "lastLogin";

    Utils.logger.debug("Initializing the connexion pop up.");
    JTextField loginField = new JTextField("Your Name");
    JTextField addressField = new JTextField("localhost");
    JPasswordField passwordField = new JPasswordField();

    //tries to read existing values on the hard drive

    if (settingsFile != null) {
      try {
        Utils.logger.debug("loading last connection parameters...");
        Properties p = new Properties();
        p.load(new FileInputStream(settingsFile));
        String login = p.getProperty("login", "");
        String host = p.getProperty("host", "");

        loginField.setText(login);
        addressField.setText(host);
        Utils.logger.debug("done.");
      }

      catch (Exception e) {
        Utils.logger.error(ERR_CLIENT_READ_LASTLOGIN + settingsFile);
      }
    }

    JPanel panel = new JPanel(new GridLayout(4, 2));//create the panel entity with Login,...to fill with

    panel.add(new JLabel("Login: "));
    panel.add(loginField);
    panel.add(new JLabel("Password: "));
    panel.add(passwordField);
    panel.add(new JLabel("Server address: "));
    panel.add(addressField);

    Utils.logger.info("Launching pop up.");

    int reponse = JOptionPane.showConfirmDialog(parentFrame, panel,
                                                "JessX server",
                                                JOptionPane.CLOSED_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE);
// Get the information which has been filled in
    if (reponse == JOptionPane.OK_OPTION) {
      String host = addressField.getText();
      if (host.equals("")) {// if the server address is nul, we assume that the default address is the local address.
        host = "localhost";
      }
      Utils.logger.info("host: " + host);

      String login = loginField.getText();
      if (!login.equals("")) Utils.logger.info("login: " + login);
      else {// we require that the player should enter a login name
        login = "Your Name";
        Utils.logger.warn("No login entered. Setting a default login: " + login);
      }

      String password = String.valueOf(passwordField.getPassword());
      if (!password.equals("")) Utils.logger.debug("got password. ");
      else Utils.logger.warn("No password entered.");//for the password, it is not mandatory. We just send a message of warn

      //tries to save these values on the hard drive
      if (settingsFile != null) {
        try {
          Utils.logger.debug("Saving parameters to config file...");
          Properties p = new Properties();
          p.setProperty("host", host);
          p.setProperty("login", login);

          p.store(new FileOutputStream(settingsFile), "Login information");
          Utils.logger.debug("done.");
        }
        catch (Exception e) {
          Utils.logger.error(ERR_CLIENT_WRITE_LASTLOGIN + settingsFile);
        }
      }

      try {
        ClientCore.connecToServer(host, login, password);
      }
      catch (IOException ex) {
      Utils.logger.warn("java version : "+System.getProperty("java.version"));
        JOptionPane.showMessageDialog(parentFrame,
                                      "Connection to server failed. Check host, IP and route.");
      }

      password = null;
    }
  }
}
