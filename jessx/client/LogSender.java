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

import java.net.*;
import java.io.*;
import jessx.utils.*;

/***************************************************************/
/*                   LogSender CLASS SECTION                   */
/***************************************************************/
/**
 * <p>Title : LogSender</p>
 * <p>Description : </p>
 * @author T.E.A.M
 * @version 1.0
 */

public class LogSender implements Runnable {

  private int port;

  /**
   * Lance automatiquement le logSender.
   * @param port int
   */
  public LogSender(int port) {
    this.port = port;
    (new Thread(this)).start();
  }

  /**
   * run
   */
  public void run() {

    File file = new File("./client.log");
    if (!file.exists()) {
      Utils.logger.error("Logging file not found... (excepting ./client.log)");
      return;
    }

    try {
      ServerSocket socketserver = new ServerSocket(port);
      while(true) {
        Socket socket = socketserver.accept();
        OutputStream out = new BufferedOutputStream(socket.getOutputStream());
        InputStream in = new BufferedInputStream(new FileInputStream(file));

        Utils.logger.debug("Reading " + in.available() + " bytes from the stream");
        byte[] buffer = new byte[in.available()];

          long s = in.read(buffer);
          Utils.logger.debug(s + " bytes read.");

          Utils.logger.debug("writing them on network...");
          out.write(buffer);
          out.flush();
          Utils.logger.debug("done.");

        Utils.logger.debug("Closing connection...");
        in.close();
        out.close();
        socket.close();
        Utils.logger.debug("done.");
      }
    }
    catch (IOException ex) {
      Utils.logger.debug(ex.getMessage());
    }

  }

}
