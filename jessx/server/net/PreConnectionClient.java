package jessx.server.net;

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
import java.net.*;

import org.jdom.*;
import org.jdom.output.*;
import jessx.net.*;
import jessx.utils.*;
import jessx.business.BusinessCore;
import java.util.Vector;

/***************************************************************/
/*             PreConnectionClient CLASS SECTION               */
/***************************************************************/
/**
 * <p>Title : preConnectionlient</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class PreConnectionClient {

  private Socket socket;
  private InputStream input;
  private OutputStream output;

  /**
   * @return OutputStream
   */
  public OutputStream getOutputStream() {
  return this.output;
  }

  /**
   * @return InputStream
   */
  public InputStream getInputStream() {
    return this.input;
  }

  /**
   * @return Socket
   */
  public Socket getSocket() {
    return this.socket;
  }

  /**
   * Add to the Socket an OutputStream and an InputStream
   * @param socket Socket
   */
  public PreConnectionClient(Socket socket) {
    this.socket = socket;
    try {
      Utils.logger.debug("Getting communication streams...");
      this.output = socket.getOutputStream();
      this.input = socket.getInputStream();
    }
    catch (IOException ex) {
      Utils.logger.error("Error while getting streams from the socket. " + ex.toString());
      return;
    }
  }

  /**
   * Ask the client to identify and add it in the player list if authorization
   * granted.
   */
  public void initiatePlayer() {
    // - getting login, password, and java version (after the client has been accepted)
    String login;
    String pw;
    String javaversion;
    Document doc = null;
    try {
      Utils.logger.debug("Waiting for the client to send his identification.");
      DataInputStream dataInput = new DataInputStream(input);
      String data = "";
      while(doc == null) {
        data += dataInput.readUTF();
        Utils.logger.info(data);
        doc = Utils.readXmlFromNetwork(data);
      }
    }
    catch (IOException ex1) {
      this.loginFailed("Could not read the login from the client. Error: " + ex1.toString());
      return;
    }

    login = doc.getRootElement().getAttributeValue("login");
    pw = doc.getRootElement().getAttributeValue("password");
    javaversion = doc.getRootElement().getAttributeValue("javaversion");


    // - looking for this client in the client list. (in case he has already been connected once)
    Player player = NetworkCore.getPlayer(login);
    if (player == null &&
        (NetworkCore.getExperimentManager().getExperimentState() ==
         ExperimentManager.EXP_OFF)) {
      // - the player has never connected. We need to verify is some connection
      // - restrictions applies.

      if (arePasswordAndLoginValid(login, pw)) {
        Player newPlayer = new Player(this, "", login, pw);
        NetworkCore.addPlayer(newPlayer);
        newPlayer.setJavaversion(javaversion);
      }
      else {
        this.loginFailed("Invalid login or password.");
        return;
      }
    }
    else {
      if (NetworkCore.getExperimentManager().getExperimentState()==ExperimentManager.EXP_OFF){
        this.loginFailed(
            "A client with the same login is already connected once.");
      }
    this.loginFailed("Experiment has already begun.");
      return;
    }

    try {
      Utils.logger.info("Player " + login + " accepted on server");
      StringWriter writer = new StringWriter();
      (new XMLOutputter(Format.getRawFormat())).output(new Document((new Message("Connection accepted.")).prepareForNetworkOutput("")), writer);
      (new DataOutputStream(output)).writeUTF(writer.getBuffer().toString() + "[JessX-end]");
      output.flush();
    }
    catch (IOException ex2) {
      Utils.logger.error("Something fails when sending back the accepted message to the client: " + ex2.toString());
    }
  }

  /**
   * Explains error when a client choos a login and close the  InputStream and the OutputStream
   * @param reason String Contains the message which indicates why the login is wrong
   */
  private void loginFailed(String reason) {
    try {
      StringWriter writer = new StringWriter();
      (new XMLOutputter(Format.getRawFormat())).output(new Document((new Message("login failed: " + reason)).prepareForNetworkOutput("")), writer);
      (new DataOutputStream(output)).writeUTF(writer.getBuffer().toString() + "[JessX-end]");
      output.flush();
      Utils.logger.error("login failed. reason: " + reason);
      output.close();
      input.close();
    }
    catch (IOException ex) {
      Utils.logger.error("Could not send to the client his login failed. " + ex.toString());
    }
    }

        /**
         * Check if the password is correct
         * Called by PreConnectionClient
         * @param login String
         * @param password String
         * @return boolean
         */
        public boolean arePasswordAndLoginValid(String login, String password) {
      if (BusinessCore.getScenario().isPasswordUsed()) {
        if (!BusinessCore.getScenario().islistOfParticipantsUsed() ||
            password.equals(BusinessCore.getScenario().getPassword()))
          return password.equals(BusinessCore.getScenario().getPassword());
      }
      if (BusinessCore.getScenario().islistOfParticipantsUsed()) {
        Vector vector = BusinessCore.getScenario().getlistOfParticipants();
        int i = 0;
        while (! ( ( ( (String[]) vector.get(i))[0].equals(login)) &&
                  ( ( (String[]) vector.get(i))[1].equals(password))) &&
               i < vector.size() - 1) {
          i++;
        }
        return ( ( (String[]) vector.get(i))[0].equals(login)) &&
            ( ( (String[]) vector.get(i))[1].equals(password));
      }
      else return true;
    }

}
