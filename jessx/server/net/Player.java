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
import java.util.*;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import jessx.business.*;
import jessx.business.event.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*                  Player CLASS SECTION                       */
/***************************************************************/
/**
 * <p>Title : Player</p>
 * <p>Description :</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class Player
    extends Thread implements PortfolioListener {

  public final static int CLIENT_STATUS_CONNECTED = 1; // status options
  public final static int CLIENT_STATUS_DISCONNECTED = 0; // if disconnected, state has no sens

  public final static int CLIENT_READY = 0; // state options
  public final static int CLIENT_BUSY = 1;

  private String login;
  private String password;
  private String playerType; //The group of each player
  private String javaversion;
  private Socket socket;

  private OutputStream output;
  private DataOutputStream dataOutput;
  private InputStream input;
  private DataInputStream dataInput;

  private Portfolio portfolio;

  private int status = 1;
  private int state = 2;

  private Vector listeners = new Vector();

  /**
   *
   * @param e PortfolioEvent
   */
  public void portfolioModified(PortfolioEvent e) {
    this.send(this.portfolio);
  }

  /**
   * Return the player's login
   * @return String Player's name
   */
  public String getLogin() {
    return this.login;
  }

  /**
 * Return the player's password
 * @return String Player's password
 */
public String getPassword() {
  return this.password;
}


  /**
   * Return the player's group
   * @return String Name of the group
   */
  public String getPlayerCategory() {
    return this.playerType;
  }

  /**
   * Return the player's status  (CLIENT_STATUS_CONNECTED = 1;
   * CLIENT_STATUS_DISCONNECTED = 0). But if disconnected, state has
   * no really sens
   * @return int
   */
  public int getPlayerStatus() {
    return this.status;
  }

  /**
   *
   * @return int
   */
  public int getPlayerState() {
    return (status == CLIENT_STATUS_DISCONNECTED) ? CLIENT_READY : this.state;
  }

  /**
   * Return the player's IP address
   * @return String Player's IP address
   */
  public String getPlayerIP() {

    /**
     * @todo recuperer deja la verrsion de la machine virtuelle du client pas du
     * serveur et faire une autre methode pour ca: on a besoin de l'ip ailleurs
     * dans l'application et getIp renvoie l'ip du joueur comme c'est ecrit dans
     * la javadoc, pas autre chose !!!
     */
    return this.socket.getInetAddress().getHostAddress();
  }

  /**
   * Give to the player its group and its protfolio
   * @param playerCategory String The player's group
   */
  public void setPlayerCategory(String playerCategory) {
    if (! (NetworkCore.getExperimentManager().getExperimentState() ==
           ExperimentManager.EXP_OFF)
        && ! ( (this.playerType == null) || (this.playerType.equals(""))))return;
    Utils.logger.info("player " + this.getLogin() + " is now a " +
                      playerCategory);
    this.playerType = playerCategory;

    Utils.logger.debug("Initiating his portfolio with the " + playerCategory +
                       " default portfolio.");
    this.portfolio = (Portfolio) BusinessCore.getScenario().getPlayerType(
        playerCategory).getPortfolio().clone();
      this.portfolio.setNonInvestedCash(this.portfolio.getCash());
      this.portfolio.setNonInvestedOwnings(this.portfolio.getOwnings());
      this.portfolioModified(null);
      this.portfolio.addListener(this);

    if (! (NetworkCore.getExperimentManager().getExperimentState() ==
           ExperimentManager.EXP_OFF)) {
      this.initClient();
    }
  }

  // Java Version

  /** To avoid any problem with the Java version, the server is given by each
   * player's computer their Java version. All java version are allowed after
   * JRE 1.4.0..
   * Called by the TablelModelPlayersStatus wich
   * displays it on the server interface to help the experimenter.
   * @return String
   */
  public String getJavaversion(){
   return javaversion;       }

  /** Every player can have a different java version. The
  *  <code>String javaversion</code> is given the information at the begining
  * of the connection, with the login and the password.
  * Called by PreConnectionClient
  * @param javaVersion String
  */
 public void setJavaversion(String javaVersion){
    javaversion = javaVersion;       }

  /**
   *
   * @return Portfolio
   */
  public Portfolio getPortfolio() {
    return this.portfolio;
  }

  /**
   *
   * @param newStatus int
   */
  private void setStatus(int newStatus) {
    this.status = newStatus;
    this.firePlayerStateChanged();
  }

  /**
   *
   * @param newState int
   */
  public void setState(int newState) {
    this.state = newState;
    this.firePlayerStateChanged();
  }

  /**
   *
   * @param connecSettings PreConnectionClient
   */
  public void reinitConnection(PreConnectionClient connecSettings) {
    input = connecSettings.getInputStream();
    dataInput = new DataInputStream(input);
    output = connecSettings.getOutputStream();
    dataOutput = new DataOutputStream(output);
    socket = connecSettings.getSocket();
    this.setStatus(CLIENT_STATUS_CONNECTED);
    //this.setState(CLIENT_READY);
    if (! (NetworkCore.getExperimentManager().getExperimentState() ==
           ExperimentManager.EXP_OFF) && (this.playerType != null)) {
      this.initClient();
    }
  }

  public void playerDeleteByServer(){
    this.setStatus(CLIENT_STATUS_DISCONNECTED);
    try {
      input.close();
      output.flush();
      output.close();
    }
    catch (IOException ex) {
      Utils.logger.warn("playerDeleteByServer");
    }
  }

  /**
   *
   * @param connecSettings PreConnectionClient
   */
  public Player(PreConnectionClient connecSettings) {
    this.reinitConnection(connecSettings);
    listenToClient();
  }

  /**
   *
   *
   * @param connecSettings PreConnectionClient
   * @param playerType String
   */
  public Player(PreConnectionClient connecSettings, String playerType) {
    this(connecSettings);
    this.playerType = playerType;
  }

  /**
   *
   * @param connecSettings PreConnectionClient
   * @param playerType String
   * @param login String
   * @param password String
   */
  public Player(PreConnectionClient connecSettings, String playerType,
                String login, String password) {
    this(connecSettings, playerType);
    this.login = login;
    this.password = password;
  }

  /**
   *
   */
  public void listenToClient() {
    this.start();
  }

  /**
   * <p>Use this method to send an <code>Object</code> to the client.
   * If the client is offline, nothing is done. If an error occurs while sending
   * the object, the error is logged and the execution continue as if nothing
   * happened. Normally, end of streams are detected by the <code>run()</code>
   * method.<br />
   * This method is <code>synchronized</code> so that two or more threads won't
   * write to the client at the same time.<br />
   * The object is completely written to the stream without waiting to
   * completely fill buffers, by using the <code>flush()</code> method on the
   * output stream.</p>
   *
   * @param message Object the object to send to the client.
   */
  public synchronized void send(NetworkWritable message) {
    // we are going to write to the client only if he is online.
    if (this.getPlayerStatus() == CLIENT_STATUS_CONNECTED) {

      Utils.logger.debug("preparing object " + message.getClass().toString() +
                           " for the client " + login + ".");

      // getting the message
      Element rootNode = message.prepareForNetworkOutput(this.
          getPlayerCategory());


      this.send(new Document(rootNode));
    }
  }

  public synchronized void send(Document document) {
    // we are going to write to the client only if he is online.
    if (this.getPlayerStatus() == CLIENT_STATUS_CONNECTED) {
      try {

        Utils.logger.debug("Writing rootNode " + " to the client " + login + ".");

        XMLOutputter xmlWritter = new XMLOutputter(Format.getRawFormat());
        StringWriter writer = new StringWriter();
        xmlWritter.output(document, writer);
        dataOutput.writeUTF(writer.getBuffer().toString() + "[JessX-end]");
        dataOutput.flush();
        // this output method flushes the stream.
      }
      catch (IOException ex) {
        Utils.logger.error("Error writing to the client: " + ex.toString());
      }
    }
  }


  /**
   * Add the new <code>PlayerStateListener</code> to the <code>Vector</code> called <code>listeners</code>
   * @param listener PlayerStateListener
   */
  public void addListener(PlayerStateListener listener) {
    this.listeners.add(listener);
  }

  /**
   * Remove the <code>PlayerStateListener</code> to the <code>Vector</code> called <code>listeners</code>
   * @param listener PlayerStateListener
   */
  public void removeListener(PlayerStateListener listener) {
    this.listeners.remove(listener);
  }

  /**
   *
   */
  private void firePlayerStateChanged() {
    for (int i = 0; i < listeners.size(); i++) {
      ( (PlayerStateListener) listeners.elementAt(i)).playerStateChanged(this.
          getLogin());
    }
  }

  /**
   * When the client is disconnected, wait for a new connection.
   */
  public void run() {
    // - we want the thread to never stop running. (or to stop running just when
    // - the server is shuting down. Reason: a client that has disconnected can
    // - reconnect and recover quickly all its data.

    /** @todo plutot sauver les clients au format xml et les recharger si necessaire.
     * ca occupera moins de ressources, et on pourra facilement recharger une
     * experience la ou elle en etait. Meme en cas de probleme sur le serveur,
     * tant que l'exception est bien geree (c'est a dire que les players sont
     * ecrits dans l'xml avant que le serveur ne s'arrete)
     *
     * en fait non. Garder les threads en mode veille jusqu'a ce que le client
     * se reconnecte, par contre la sauvegarde de l'experience au format xml
     * permettrait de redemarrer une experience la ou elle en etait.
     * (utile en cas de planatage server ou de manque de temps pour finir
     * l'experience, ou d'experience trop longue pour finir en une seule fois).
     */

    String dataRemaining = "";

    while (true) {

      while (this.status == CLIENT_STATUS_CONNECTED) {

        try {
          Utils.logger.debug("Waiting for some data...");
          dataRemaining = this.readXmlFromNetwork(dataRemaining +
                                                  dataInput.readUTF());
        }
        catch (IOException ex1) {
          Utils.logger.warn("Client stream has been closed. " + ex1.toString());
          this.setStatus(CLIENT_STATUS_DISCONNECTED);
        }
      }

      try {
        this.sleep(1000);
      }
      catch (InterruptedException ex) {
        Utils.logger.debug("Sleep of the client " + login +
                           " was interrupted for an unknown reason. (" +
                           ex.toString() + ")[IGNORED]");
      }
    }
  }

  /**
   * Check if the client is ready or not (a pop up may be opened)
   * @param explanation String
   */
  public void isClientReady(String explanation) {
    this.setState(CLIENT_BUSY);
    this.send(new ExpUpdate(ExpUpdate.CLIENT_READY, explanation,
                            NetworkCore.getExperimentManager().
                            getPeriodNum()));
  }

  /**
   * This method will send to the client all the info it needs to run:
   * - his portfolio
   * - his playerType (a group of operators who can play together with the same properties)
   * - institutions on which he is playing.
   * It is supposed the player has a playertype. Otherwise, the client won't be
   * initiated.
   */
  public void initClient() {
    this.send(this.getPortfolio());

    Vector institutionList = new Vector();

    Iterator iter = BusinessCore.getScenario().getPlayerType(this.
        getPlayerCategory()).getOperatorsPlayed().keySet().iterator();
    while (iter.hasNext()) {
      String opCompleteName = (String) iter.next();

      int index = opCompleteName.lastIndexOf(" on ");

      String institutionName = opCompleteName.substring(index + 4);
      if (!institutionList.contains(institutionName)) {
        institutionList.add(institutionName);
      }

    }

    Utils.logger.debug("Sending institutions the client needs.");
    for (int i = 0; i < institutionList.size(); i++) {
      this.send(BusinessCore.getInstitution( (String) institutionList.elementAt(
          i)));
    }

    Utils.logger.debug("Sending the operators the player is allowed to play.");
    Iterator iter2 = BusinessCore.getScenario().getPlayerType(this.
        getPlayerCategory()).getOperatorsPlayed().keySet().iterator();
    while (iter2.hasNext()) {
      this.send(new OperatorPlayed( (String) iter2.next()));
    }
  }

  private void fireObjectReceived(Document xmlDoc) {
    Utils.logger.info("Object received. Type: " +
                      xmlDoc.getRootElement().getName());
    if (xmlDoc.getRootElement().getName().equals("ExperimentUpdate")) {
     this.setState(CLIENT_READY);
    }
    else if (xmlDoc.getRootElement().getName().equals("Warn")) {
      this.send(xmlDoc);
    }
    else if (xmlDoc.getRootElement().getName().equals("Operation")) {
      try {
        Operation op = Operation.initOperationFromXml(xmlDoc.getRootElement());
        BusinessCore.getInstitution(op.getInstitutionName()).treatOperation(op);
      }
      catch (OperationNotCreatedException ex) {
        Utils.logger.error(
            "Could not create operation received from client. [IGNORED]");
      }
    }
  }

  /**
   * @param data String old + new data
   * @return String the data that could not be parsed. To be sent next time
   * before the new data.
   */
  private String readXmlFromNetwork(String data) {

    int begin = data.indexOf("<?");
    int end = data.indexOf("[JessX-end]", begin);

    if ( (begin != -1) && (end != -1)) {
      String message = data.substring(begin, end);
      SAXBuilder sax = new SAXBuilder();

      try {
        fireObjectReceived(sax.build(new StringReader(message)));
      }
      catch (IOException ex) {
        // no way it can happen...
      }
      catch (JDOMException ex) {
        Utils.logger.error("Could not read message : " + message + ". Error: " +
                           ex.toString());
      }
      return readXmlFromNetwork(data.substring(end + "[JessX-end]".length()));
    }
    else {
      if (begin == -1) {
        return "";
      }
      else {
        // here end == -1 otherwise we would have matched the first if.
        return data.substring(begin);
      }
    }
  }
}
