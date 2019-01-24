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
import java.text.*;
import java.util.*;

import javax.swing.*;

import jessx.utils.*;

/***************************************************************/
/*                  LogGetter CLASS SECTION                    */
/***************************************************************/
/**
 * <p>Title : LogGetter</p>
 * <p>Description : LogGetter is the server side tool to get logs file from clients, without
 *   having to get them all manually on each computer.</p>
 * @author Thierry
 * @version 1.0
 */
public class LogGetter {

  private int port;
  private String path;

  /**
   * The constructor will:
   * - set the chosen port
   * - create the dir where logs will be saved. (the directory is: ./logs/currentDate/)
   *
   * If the directory already exist a popup if shown and nothing is done.
   * @param port int
   */
  public LogGetter(int port) {

    //if (!(new File("./logs/")).exists()) (new File("./logs/")).mkdir();

    String date = (new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")).format(new Date());
    path = "./logs/"+ date +"/";
    File file = new File(path);

    if (file.exists()) {
      Utils.logger.error("Folder " + file.getAbsolutePath() +
                         " already exists.");
      JOptionPane.showConfirmDialog(null, "Folder " + file.getAbsolutePath() + " already exists.", "Erreur",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }

    Utils.logger.info("Creating directory " + path + " : " + file.mkdirs());

    this.port = port;
  }

  /**
   * This method will connect to the client, wait for the client to send the
   * log file. As long as the client is not closing the socket, the method
   * will wait for data input.
   * Data received are written to ./logs/currentDate/playerName.log
   * @param playerName String
   */
  public void getLog(String playerName) {
    Utils.logger.debug("Getting log for the player: " + playerName);
    String ip = NetworkCore.getPlayer(playerName).getPlayerIP();
    Socket socket;
    InputStream in;
    Utils.logger.debug("- opening socket...");
    try {
      socket = new Socket(ip, port);
      in = new BufferedInputStream(socket.getInputStream());
    }
    catch (UnknownHostException ex) {
      Utils.logger.error(ex.getMessage());
      return;
    }
    catch (IOException ex) {
      Utils.logger.error(ex.getMessage());
      return;
    }

    Utils.logger.debug("- opening file...");
    byte[] buffer = new byte[16384];
    File file = new File(path + playerName + ".log");
    OutputStream out;
    try {
      file.createNewFile();
      out = new BufferedOutputStream(new FileOutputStream(file));
    }
    catch (IOException ex1) {
      Utils.logger.error(ex1.getMessage());
      return;
    }

    Utils.logger.debug("- getting data...");
    try {
      int len;
      while ((len = in.read(buffer)) != -1) {
        out.write(buffer,0,len);
        out.flush();
      }

    }
    catch (IOException ex2) {
      Utils.logger.error(ex2.getMessage());
    }



    Utils.logger.debug("- closing socket and file...");
    try {
      out.close();
      in.close();
    }
    catch (IOException ex3) {
      Utils.logger.error(ex3.getMessage());
      return;
    }

    Utils.logger.debug(playerName + " logs successfully saved.");
  }

  /**
   * This method gets all players names and call the getLog method on each name.
   *
   * @param monitor ProgressMonitor
   */
  public void getLogs(ProgressMonitor monitor) {

    Iterator it = NetworkCore.getPlayerList().keySet().iterator();
    int i = 0;
    while(it.hasNext()) {
      i++;
      String playerName = (String)it.next();
      monitor.setNote(playerName);
      this.getLog(playerName);
      monitor.setProgress(1);
      if (monitor.isCanceled()) {
        Utils.logger.warn("Getting log operation cancelled.");
        break;
      }
    }
    monitor.close();
  }

}
