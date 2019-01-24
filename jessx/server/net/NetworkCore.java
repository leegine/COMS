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

import java.util.*;

import org.jdom.*;
import jessx.business.*;
import jessx.business.event.*;
import jessx.net.*;
import jessx.server.*;

/***************************************************************/
/*                 NetworkCore CLASS SECTION                   */
/***************************************************************/

/**
 * <p>Title : NetworkCore</p>
 * <p>Description :</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public abstract class NetworkCore {

  private static ClientConnectionPoint connectionPoint = new
      ClientConnectionPoint();

  /**
   *
   * @return ClientConnectionPoint
   */
  public static ClientConnectionPoint getConnectionPoint() {
    return connectionPoint;
  }

  /**
   * A hashmap containing Player object. The key is the player login.
   */
  private static HashMap playersList = new HashMap();

  private static Vector listeners = new Vector();

  private static ExperimentManager experimentManager = new ExperimentManager();

  private static LogManager logManager = new LogManager();

  /**
   * @return ExperimentManager
   */
  public static ExperimentManager getExperimentManager() {
    return experimentManager;
  }

  /**
   * @return LogManager
   */
  public static LogManager getLogManager() {
    return logManager;
  }

  /**
   * @param login String
   * @return Player
   */
  public static Player getPlayer(String login) {
    return (Player) playersList.get(login);
  }

  /**
   * do not use the hashmap to remove or add some players. Use instead the
   * NetworkCore methods. Otherwise, listeners won't be notified.
   * @return HashMap
   */
  public static HashMap getPlayerList() {
    return playersList;
  }

  /**
   *
   * @param player Player
   */
  public static void addPlayer(Player player) {
    if (playersList.containsKey(player.getLogin())) {
      playersList.put(player.getLogin(), player);
    }
    else {
      playersList.put(player.getLogin(), player);
      firePlayerAdded(player.getLogin());
    }
  }

  /**
   *
   * @param playerName String
   */
  public static void removePlayer(String playerName) {
    if (playersList.containsKey(playerName)) {
      getPlayer(playerName).playerDeleteByServer();

      if (getExperimentManager().getExperimentState() != ExperimentManager.EXP_OFF) {
        String playerType = getPlayer(playerName).getPlayerCategory();
        if(BusinessCore.getScenario().getPlayerType(playerType)!= null){

        }
        Vector institutions = BusinessCore.getScenario().getPlayerType(playerType).getInstitutionsWherePlaying();
        for (int i = 0; i < institutions.size(); i++) {
          synchronized (BusinessCore.getInstitution( (String) institutions.get(i)).getOrderBook()) {
            BusinessCore.getInstitution( (String) institutions.get(i)).getOrderBook().clearPlayer(playerName);
          }
          sendToAllPlayers(BusinessCore.getInstitution( (String) institutions.get(i)).getOrderBook());
        }
      }
      playersList.remove(playerName);
      firePlayerRemoved(playerName);
    }
  }

  /**
   *
   */
  public static void setServerOnline() {
    if (!connectionPoint.isAlive()) {
      connectionPoint.start();
    }
  }

  /**
   *
   */
  public static void setServerOffline() {
    if (!connectionPoint.isAlive()) {
      connectionPoint.destroy();
    }
  }

  /**
   * @todo CaML:
   * On a un pb d'optimisation ici: le fichier xml est recree à partir de
   * l'objet autant de fois qu'il y a de clients. (boucle while sur tout les
   * clients).
   * On ne peut pas le creer une fois pour toute, car le fichier est personnalise
   * en fonction de la categorie de joueur auquel il est envoye.
   *
   * Ce qu'il faudrait faire:
   * 1°/ l'envoi à tous parcourt la liste des categories
   * et envoie le message a chaque categorie. [DONE]
   *
   * 2°/ La methode qui envoie a chaque categorie doit etre modifiee pour ne
   * creer qu'une seule fois le fichier xml.
   *
   * Il faudrait aussi que l'appel à send ne soit pas bloquant comme il l'est
   * actuellement (a cause du mot cle synchronized et des appel I/O). Ce qui
   * me semble le plus logique serait un Thread de plus par client qui
   * s'occuperait des envois. La methode de send ne ferait qu'ajouter le message
   * à une file (appel tres rapide, non bloquant) et le Thread s'occuperait
   * d'envoyer les messages en vidant la file d'attente.
   *
   * Je ferais des que j'aurais le temps.
   *
   * @param message NetworkWritable
   */
  public static void sendToAllPlayers(NetworkWritable message) {
    Iterator categories = BusinessCore.getScenario().getPlayerTypes().keySet().iterator();
    while (categories.hasNext()) {
      String category = (String)categories.next();
      sendToPlayerCategory(message, category);
    }
  }

  /**
   * Send a message to all players from the category <code>playerCat</code>
   * The performance for this operation could be increased by creating a
   * redundant <code>HashMap</code> of <code>Vector</code>. This optimization
   * should be considered in case many clients (more than 15-20) are planned to
   * be used.
   * @param message Object the message to send to the clients.
   * @param playerCat String the player category of the player.
   */
  public static void sendToPlayerCategory(NetworkWritable message,
                                          String playerCat) {
    Iterator iter = getPlayerList().keySet().iterator();
    /**
     * @todo on pourrait ameliorer les performance en creant une deuxieme
     * <code>HashMap</code> dont les clefs seraient des players types, et les
     * valeurs contenant un <code>Vector</code> de <code>Player</code>.
     * La <code>HashMap</code> de <code>Player</code> actuel resterait
     * necessaire pour la recherche d'un <code>Player</code> particulier.
     */

    Document doc = new Document(message.prepareForNetworkOutput(playerCat));

    while (iter.hasNext()) {
      String key = (String) iter.next();
      if (getPlayer(key).getPlayerCategory().equalsIgnoreCase(playerCat)) {
        getPlayer(key).send(doc);
      }
    }
  }

  /**
   *
   * @param message NetworkWritable
   * @param login String
   */
  public static void sendToPlayer(NetworkWritable message, String login) {
    getPlayer(login).send(message);
  }

  /**
   *
   * @param explanation String
   */
  public static void arePlayersReady(String explanation) {
    Iterator iter = playersList.keySet().iterator();
    while (iter.hasNext()) {
      getPlayer( (String) iter.next()).isClientReady(explanation);
    }
  }

  /**
   *
   * @param playerName String
   */
  private static void firePlayerAdded(String playerName) {
    for (int i = 0; i < listeners.size(); i++) {
      ( (PlayerListener) listeners.elementAt(i)).playerListModified(new
          PlayerEvent(playerName, PlayerEvent.PLAYER_ADDED));
    }
  }

  /**
   *
   * @param playerName String
   */
  private static void firePlayerRemoved(String playerName) {
    for (int i = 0; i < listeners.size(); i++) {
      ( (PlayerListener) listeners.elementAt(i)).playerListModified(new
          PlayerEvent(playerName, PlayerEvent.PLAYER_REMOVED));
    }
  }

  /**
   *
   * @param listener PlayerListener
   */
  public static void addListener(PlayerListener listener) {
    listeners.add(listener);
  }

  /**
   *
   * @param listener PlayerListener
   */
  public static void removeListener(PlayerListener listener) {
    listeners.remove(listener);
  }
}
