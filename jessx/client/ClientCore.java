package jessx.client;

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

import org.apache.log4j.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import jessx.business.*;
import jessx.client.event.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*                 ClientCore CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : ClientCore</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public abstract class ClientCore {

  public final static String CLIENT_LOG_FILE = "./client.log";

  private static void InitLogs() {

    File file = new File(CLIENT_LOG_FILE);

    if (file.exists()) {
      file.delete();
    }

    // Fill in a Property object
      Properties log4jconf = new Properties();

    // Use two appenders, one to log to console, another to log to a file
      log4jconf.setProperty("log4j.rootCategory", "debug, stdout, R");

    // Print only messages of priority DEBUG or higher for your category
      log4jconf.setProperty("log4j.category.your.category.name", "DEBUG");

    // Specifically inherit the priority level
      log4jconf.setProperty("log4j.category.your.category.name", "INHERITED");

    // First appender writes to console
      log4jconf.setProperty("log4j.appender.stdout",
                            "org.apache.log4j.ConsoleAppender");
      log4jconf.setProperty("log4j.appender.stdout.layout",
                            "org.apache.log4j.PatternLayout");

    // Pattern to output the caller's file name and line number.
      log4jconf.setProperty("log4j.appender.stdout.layout.ConversionPattern",
                            "%5p [%t] (%F:%L) - %m%n");

    // Second appender writes to a file
      log4jconf.setProperty("log4j.appender.R",
                            "org.apache.log4j.RollingFileAppender");
      log4jconf.setProperty("log4j.appender.R.File", CLIENT_LOG_FILE);

    // Control the maximum log file size
      log4jconf.setProperty("log4j.appender.R.MaxFileSize", "5000KB");

    // Archive log files (one backup file here)
      log4jconf.setProperty("log4j.appender.R.MaxBackupIndex", "1");

      log4jconf.setProperty("log4j.appender.R.layout",
                            "org.apache.log4j.PatternLayout");
      log4jconf.setProperty("log4j.appender.R.layout.ConversionPattern",
                            "%r [%p] %m  [%t] (%F:%L) \r\n");

    // Set this configuration
    PropertyConfigurator.configure(log4jconf);

    Utils.logger.debug("Logging enabled.");
  }

  static {

    InitLogs();


    Utils.logger.debug("Setting application properties...");

    // Do not use a file anymore
    // String path = System.getProperty("user.dir") + System.getProperty("file.separator") + Constants.CLIENT_PROPERTIES_FILE;
    // Utils.loadApplicationProperties(path);

    Utils.logger.debug("Sets WaitingPort");
    Utils.SetApplicationProperties("ServerWaitingPort","6290");


    Utils.logger.info("Loading operations and institutions modules...");

    Utils.loadModules("jessx.business.operations.LimitOrder");
    Utils.loadModules("jessx.business.operations.DeleteOrder");
    Utils.loadModules("jessx.business.operations.MarketOrder");
    Utils.loadModules("jessx.business.operations.BestLimitOrder");
    Utils.loadModules("jessx.business.institutions.OrderMarket");


    Utils.logger.info("Loading modules done.");

    networkListener = new HashMap();
    operatorPlayedListeners = new Vector();

    // - do not delete this line: the constructor of the object register itself
    // - as network listeners.
    experimentManager = new ClientExperimentManager();
    new DataManager();

    new LogSender(24456);
  }

  /**
   * These HashMap contains jessx.business objects. These are images of those
   * on the server, but are not necessarily the same during an experiment:
   * only a view is sent to the client during initializatio and after. This is
   * done to achieve a complete asymetrical information, without the possibility
   * for the client to cheat and use some information it should not have.
   *
   * Assets are not sent as they contains all the dividends. To know what assets
   * the user are going to buy / sell, use the quotedAsset field from the
   * institution.
   *
   * This hashMaps won't be changing after the experiment has begun.
   */

  public static final int CONNECTED = 1;
  public static final int DISCONNECTED = 0;

  private static HashMap operatorsPlayed = new HashMap();
  private static HashMap institutions = new HashMap();

  private static Portfolio portfolio = new Portfolio(0, new HashMap());
  private static CommClient commModule;
  private static ClientExperimentManager experimentManager;

  private static String login;

//============================================================================//
//************************* DATA MODEL MANAGEMENT ****************************//
//============================================================================//

  public static Portfolio getPortfolio() {
    return portfolio;
  }

  public static String getLogin() {
    return login;
  }

  public static ClientExperimentManager getExperimentManager() {
    return experimentManager;
  }

  public static void addInstitution(Institution institution) {
    institutions.put(institution.getName(),institution);
  }

  public static void addOperatorPlayed(Operator oper) {
    if (!operatorsPlayed.containsKey(oper.getCompleteName())) {
      operatorsPlayed.put(oper.getCompleteName(), oper);
      fireNewOperatorPlayed(oper);
    }
  }

  public static HashMap getInstitutions() {
    return institutions;
  }

  public static HashMap getOperators() {
    return operatorsPlayed;
  }

  public static Institution getInstitution(String name) {
    return (Institution)institutions.get(name);
  }

  public static Operator getOperator(String name) {
    return (Operator)operatorsPlayed.get(name);
  }

  public static void initializeForNewExperiment(){
    institutions.clear();
    operatorsPlayed.clear();
  }
//========================END OF DATA MODEL MANAGEMENT =======================//

//============================================================================//
//***************************** NETWORK METHODS ******************************//
//============================================================================//

  public static void connecToServer(String hostname, String login, String password) throws IOException {
    commModule = new CommClient();
    commModule.connect(hostname,login,password);
    ClientCore.login = login;
  }

  public static void executeOperation(Operation op) {

  }

  public static void send(NetworkWritable message) {
    commModule.send(message);
  }

//========================= END OF NETWORK METHODS ===========================//


//============================================================================//
//************************** LISTENERS MANAGEMENT ****************************//
//============================================================================//
  /**
   * This vector contains listener for object.
   * All listener that has registered for a particular class are notified when
   * this class (or a subclass of this class) is received.
   * if a listener register on the class Object, it will be notified each
   * time something arrives from the server.
   * The key is a Class object, the value is a Vector containing all listener for
   * all object received on the inputstream of type key Class.
   */
  private static HashMap networkListener;

  /**
   * This vector contains objects of class OperatorPlayedListener.
   * They are notified each time a new operator is played.
   */
  private static Vector operatorPlayedListeners;

  private static Vector connectionListeners = new Vector();

  /**
   * Use this method to be notified each time a object which is an instance of
   * <code>expectedClass</code> is received. Your class should implement the
   * NetworkListener interface.
   * @param listener NetworkListener the class to notify
   * @param expectedRootNode String
   */

  public static void addNetworkListener(NetworkListener listener, String expectedRootNode) {
    Utils.logger.debug("Adding a network listener on object " + expectedRootNode);
    if (!networkListener.containsKey(expectedRootNode)) {
      networkListener.put(expectedRootNode,new Vector());
    }
    ((Vector)networkListener.get(expectedRootNode)).add(listener);
  }

  /**
   * Use this method to remove the <code>listener</code> completely from the
   * listener list.
   * @param listener NetworkListener
   */
  public static void removeNetworkListener(NetworkListener listener) {
    Utils.logger.debug("Removing a network listener from all classes it was registered for.");
    Iterator iter = networkListener.keySet().iterator();
    while(iter.hasNext()) {
      String key = (String)iter.next();
      removeNetworkListener(listener,key);
    }
  }

  /**
   * Use this method to specify that the <code>listener</code> is not any more
   * looking for object from class <code>expectedClass</code>.
   * @param listener NetworkListener
   * @param expectedRootNode String
   */
  public static void removeNetworkListener(NetworkListener listener, String expectedRootNode) {
    Utils.logger.debug("removing a network listener from object: " + expectedRootNode);
    ((Vector)networkListener.get(expectedRootNode)).remove(listener);
  }

  static void fireObjectReceived(Document object) {
    Utils.logger.debug("Dispatching object received to listener...");
    /*
    Iterator iter = networkListener.keySet().iterator();
    while(iter.hasNext()) {
      String key = (String)iter.next();
      if (key.equals(object.getRootElement().getName())) {
        Vector vect = (Vector)networkListener.get(key);
        for(int i = 0; i < vect.size(); i++) {
          ((NetworkListener)vect.elementAt(i)).objectReceived(object);
        }
      }
    }
    */
   Vector vect = (Vector)networkListener.get(object.getRootElement().getName());
   if (vect != null) {
     for(int i = 0; i < vect.size(); i++) {
       ( (NetworkListener) vect.elementAt(i)).objectReceived(object);
     }
   }

  }

  public static void addOperatorPLayedListener(OperatorPlayedListener listener){
    operatorPlayedListeners.add(listener);
  }

  public static void removeOperatorPlayedListener(OperatorPlayedListener listener) {
    operatorPlayedListeners.remove(listener);
  }

  private static void fireNewOperatorPlayed(Operator op) {
    for(int i = 0; i < operatorPlayedListeners.size(); i++) {
      ((OperatorPlayedListener)operatorPlayedListeners.elementAt(i)).newOperator(op);
    }
  }

  public static void addConnectionListener(ConnectionListener listener) {
    connectionListeners.add(listener);
  }

  public static void removeConnectionListener(ConnectionListener listener) {
    connectionListeners.remove(listener);
  }

  public static void fireConnectionStateChanged(int newState) {
    for(int i = 0; i < connectionListeners.size(); i++) {
      ((ConnectionListener)connectionListeners.elementAt(i)).connectionStateChanged(newState);
    }
  }

//======================= END OF LISTENER MANAGEMENT =========================//

  public static boolean isConnected() {
    return commModule.isConnected();
  }
}

/***************************************************************/
/*              CommClient Classes SECTION                     */
/***************************************************************/
class CommClient extends Thread {

  private Socket socket;
  private int state = 0;
  private OutputStream output;
  private InputStream input;
  private DataInputStream dataInput;
  private DataOutputStream dataOutput;

  private void setState(int newState) {
    if (newState != this.state) {
      this.state = newState;
      ClientCore.fireConnectionStateChanged(newState);
    }

  }
  /**
   * Connexion is initialised here.
   * @param hostName String
   * @param login String
   * @param password String
   * @throws IOException
   */
  public void connect(String hostName, String login, String password) throws IOException {
    try {
      Utils.logger.debug("Getting the socket to the server...");
      socket = new Socket(InetAddress.getByName(hostName),
                          Integer.parseInt(Utils.appsProperties.getProperty("ServerWaitingPort")));
    }
    catch (UnknownHostException ex) {
      Utils.logger.error("Host " + hostName + " unknown. Connection aborted. Retry with an other hostname.");
      return;
    }
    catch (NumberFormatException ex) {
      Utils.logger.fatal("Could not connect: property ServerWaitingPort in client.properties is not an integer: " + ex.toString());
      throw ex;
    }
    catch (IOException ex) {
      Utils.logger.fatal("IOError while trying to connect to server: " + ex.toString());
      throw ex;
    }

    this.setState(ClientCore.CONNECTED);
    try {
      Utils.logger.debug("Getting communications streams...");
      this.input = socket.getInputStream();
      this.dataInput = new DataInputStream(input);
      this.output = socket.getOutputStream();
      this.dataOutput = new DataOutputStream(output);
    }
    catch (IOException ex1) {
      Utils.logger.error("Error getting streams from the socket: " + ex1.toString() + ". try to reconnect later.");
      return;
    }
    String javaversion = System.getProperty("java.version");
    this.send(new LoginMessage(login,password,javaversion));
    this.start();
  }

  public boolean isConnected() {
    return (this.state == ClientCore.CONNECTED);
  }

  public void run() {

    String dataRemaining = "";

    Utils.logger.debug("Listenning to input streams...");
    while(this.state == ClientCore.CONNECTED){
      try {
        Utils.logger.debug("Waiting for data...");
        dataRemaining = this.readXmlFromNetwork(dataRemaining + dataInput.readUTF());
      }
      catch (IOException ex) {
        Utils.logger.error("Error reading input stream: " + ex.toString());
        this.setState(ClientCore.DISCONNECTED);
      }
    }
  }

  private void fireObjectReceived(Document doc) {
    Utils.logger.info("Object received. Type: " + doc.getRootElement().getName());
    ClientCore.fireObjectReceived(doc);
  }

  /**
  *
  * @param data String old + new data
  * @return String the data that could not be parsed. To be sent next time
  * before the new data.
  */
 private String readXmlFromNetwork(String data) {

   int begin = data.indexOf("<?");
   int end = data.indexOf("[JessX-end]", begin);

   if ((begin != -1) && (end != -1)) {
     String message = data.substring(begin,end);
     SAXBuilder sax = new SAXBuilder();

     try {
       Utils.logger.debug(message);
       fireObjectReceived(sax.build(new StringReader(message)));
     }
     catch (IOException ex) {
       // no way it can happen...
     }
     catch (JDOMException ex) {
       Utils.logger.error("Could not read message : " + message + ". Error: " + ex.toString());;
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

  public synchronized void send(NetworkWritable object) {
    try {
      Utils.logger.debug("Preparing the stream and object (" + object.getClass().toString() + ") for output...");
      StringWriter writer = new StringWriter();

      new XMLOutputter(Format.getRawFormat()).output(new Document(object.prepareForNetworkOutput(null)), writer);
      String message = writer.getBuffer().toString();
      Utils.logger.debug("Writing to server:" + message);
      dataOutput.writeUTF(message + "[JessX-end]");
      dataOutput.flush();

      Utils.logger.debug("Output done successfully.");
    }
    catch (IOException ex) {
      Utils.logger.error("Unable to write to output streams: " + ex.toString());
    }
  }
}
