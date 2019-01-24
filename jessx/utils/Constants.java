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

/***************************************************************/
/*               Constants INTERFACE SECTION                   */
/***************************************************************/
/**
 * <p>Title: Constants</p>
 * <p>Description: Constants (strings...) used by the software</p>
 * @author Franck PEREZ, Thierry Curtil, Julien Terrier
 * @version 0.2
 */
public interface Constants {


  //Version of the software :
        // For the next programmers, you increment here the version of JessX.

      // 1.0 : JessX
      // 1.1 : Virtual Traders

  public final String VERSION = "1.3";
  public final String DATE_OF_RELEASE = "Released on March 2007";
      //"Released on May 2006";

  //Other information
  public final String WEB_PAGE = "www.jessx.net";
      //"http://eleves.ec-lille.fr/~ecoxp03/";

  public final String LOG_DIALOG_FILTER_DESCRIPTION = "JessX XML log files (*.xml)";
  public final String LOG_DIALOG_FILTER_EXTENSION = ".xml";

  public final String GENERAL_SETTINGS_DIRECTORY = ".ecoxp";

  public final String ERR_NO_USER_DIR = "Could not locate or create home settings directory";

  public final Color COLOR_ODD_LINE = new Color(208,208,208);
  public final Color COLOR_EVEN_LINE = new Color(218,218,218);

// not used anymore
//  public final String SERVER_PROPERTIES_FILE = "server.properties";
//  public final String CLIENT_PROPERTIES_FILE = "client.properties";

  public final String ERR_CLIENT_READ_LASTLOGIN = "Unable to load login configuration file : ";
  public final String ERR_CLIENT_WRITE_LASTLOGIN = "Error while writing login configuration file : ";


  public final String CURRENCY = "$";

/**
  //Server GUI
  public final String[] SERVER_GUI_CLIENT_STATE_HEADER = {"Num","Login","IP","Online","Status","Group"};


  //XML Config
  public final String CONF_XML_ROOT = "experiment";
  public final String CONF_XML_TITLE = "title";
  public final String CONF_XML_GENERAL_DESCRIPTION = "generalDescription";
  public final String CONF_XML_CURRENCY = "currency";
  public final String CONF_XML_NUMBER_OF_SESSIONS = "numberOfSessions";
  public final String CONF_XML_LEARNING_SESSION = "learningSession";
  public final String CONF_XML_AFTER_SETUP_JOINING = "afterStartupJoining";
  public final String CONF_XML_MCQP = "mcqp";
  public final String CONF_XML_ASSET = "asset";


  public final String CONF_XML_PLAYER_CATEGORY = "playerCategory";
  public final String CONF_XML_PLAYER_CATEGORY_NAME = "name";
  public final String CONF_XML_PLAYER_CATEGORY_TYPE = "type";
  public final String CONF_XML_PLAYER_CATEGORY_NUMBEROFPLAYERS = "numberOfPlayers";
  public final String CONF_XML_PLAYER_CATEGORY_DESC= "description";
  public final String CONF_XML_PLAYER_CATEGORY_MAXDEPTH= "maxDepth";


  public final String CONF_XML_ORDER_PERMISSION = "orderPermission";
  public final String CONF_XML_ORDER_PERMISSION_TYPE1 = "Market Order";
  public final String CONF_XML_ORDER_PERMISSION_TYPE2 = "Limit Order";
  public final String CONF_XML_ORDER_PERMISSION_TYPE3 = "All or None Order";
  public final String CONF_XML_ORDER_PERMISSION_TYPE4 = "Stop Order";



  public final String CONF_UNSAVED = "** no file **";
  public final String CONF_DEFAULT_CONF_FILENAME = "ecoxp.xml.defaults";

  // state of the server (Market)
  public final String MARKET_STATE_SETUP = "Setting up the experiment";
  public final String MARKET_STATE_LEARNING_SESSION_INIT = "The server is setting up the learning session. Please wait.";
  public final String MARKET_STATE_LEARNING_SESSION_ON = "The learning session has begun. Please wait for the participants to finish it.";
  public final String MARKET_STATE_LEARNING_SESSION_FINISHED = "All participants have finished the learning session. You can validate";
  public final String MARKET_STATE_MCQP_INIT = "The server is setting up the mcqp. Please wait.";
  public final String MARKET_STATE_MCQP_ON = "The MCQP has begun. Please wait for the participant to finish it.";
  public final String MARKET_STATE_MCQP_FINISHED = "All participants have finished tje MCQP. You can validate to begin the experiment";
  public final String MARKET_STATE_SESSION_INIT = "The server is setting up the session parameters. Please Wait.";
  public final String MARKET_STATE_SESSION_ON = "The session has begun. Please wait the end of the session.";
  public final String MARKET_STATE_SESSION_FINISHED = "All participants have finished the session. You can validate to begin next session.";
*/
  // Table colors : SUPERGREEN
  public final Color CLIENT_STATE_GREEN = new Color(145,220,180);
  public final Color CLIENT_STATE_RED = new Color(220,160,155);
  public final Color CLIENT_STATE_WHITE = new Color(255,255,255);

  // clients interface
  public final Color CLIENT_BUY_INACTIVE = new Color(200, 250, 190);
  public final Color CLIENT_SELL_INACTIVE = new Color(190, 230, 250);
  public final Color CLIENT_EXECUTE_INACTIVE = new Color(208, 208, 208);

  public final Color CLIENT_BUY_ACTIVE = new Color(145,220,180);
  public final Color CLIENT_SELL_ACTIVE = new Color(170, 190, 245);
  public final Color CLIENT_EXECUTE_ACTIVE = new Color(255, 185, 185);

  public final Color CLIENT_BUY_INTERMEDIARY = new Color(145,220,180);
  public final Color CLIENT_SELL_INTERMEDIARY = new Color(170,190,245);

  //public final Color CLIENT_BUY_HL = new Color(90,190,170);
  //public final Color CLIENT_SELL_HL = new Color(150,150,240);
  public final Color CLIENT_BUY_HL = new Color(255,255,255);
  public final Color CLIENT_SELL_HL = new Color(255,255,255);
/*
  public final Color CMDLINE_INCOMPLETE = new Color(255,200,80); //orange
  public final Color CMDLINE_INVALID = new Color(180,180,180); //grey
  public final Color CMDLINE_VALID = new Color(200, 250, 190);

  public final Color POPUP_NOVALIDATION_BG = SystemColor.menu;

  public final String SONAR_SERVER_NAME = "Server Sonar";

  public final int UDP_LISTENING_PORT_SERVER = 61387;
  public final int UDP_LISTENING_PORT_CLIENT = 61388;
**/
  public final Font FONT_CLIENT_TITLE_BORDER = new Font("Verdana", Font.BOLD, 12);
  public final Font FONT_CLIENT_TEXTAREA = new Font("Verdana", Font.PLAIN, 11);
  public final Font FONT_CLIENT_TEXTAREA_LARGE = new Font("Verdana", Font.PLAIN, 12);
  public final Font FONT_CLIENT_LABEL_UP = new Font("Verdana", Font.BOLD, 14);

  public final Font FONT_DEFAULT_LABEL = new Font("Verdana", Font.PLAIN, 13);

}
