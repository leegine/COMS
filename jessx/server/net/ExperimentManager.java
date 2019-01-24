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
import java.util.*;

import javax.swing.*;

import org.jdom.*;
import jessx.business.*;
import jessx.net.*;
import jessx.server.net.event.*;
import jessx.utils.*;

/***************************************************************/
/*             ExperimentManager CLASS SECTION                 */
/***************************************************************/

/**
 * <p>Title : ExperimentManager</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class ExperimentManager extends Thread implements Constants {

  public final static int EXP_ON_PER_ON = 2;
  public final static int EXP_ON_PER_OFF = 1;
  public final static int EXP_OFF = 0;

  private int periodNum = -1;
  private Date periodBeginning;

  private int experimentState;

  private Vector listeners = new Vector();

  /**
   *
   * @param listener ExperimentStateListener
   */
  public void addExperimentStateListener(ExperimentStateListener listener) {
  this.listeners.add(listener);
  }

  /**
   *
   * @param listener ExperimentStateListener
   */
  public void removeExperimenetStateListener(ExperimentStateListener listener) {
  this.listeners.remove(listener);
  }

  /**
   *
   */
  private void fireExperimentStateChanged() {
  for(int i = 0; i < listeners.size(); i++) {
      ((ExperimentStateListener)this.listeners.elementAt(i)).experimentStateChanged(this.getExperimentState());
    }
  }

  /**
   *
   * @param newState int
   */
  private void setExperimentState(int newState) {
  this.experimentState = newState;
    fireExperimentStateChanged();
  }

  /**
   *
   * @return int
   */
  public int getExperimentState() {
  return this.experimentState;
  }

  /**
   *
   * @return int
   */
  public int getPeriodNum() {
  return periodNum;
  }

  /**
   *
   * @param ParentFrame JFrame
   * @return boolean
   */
  public boolean beginExperiment(JFrame ParentFrame) {
  Utils.logger.info("Experiment is initiated...");

  Vector information=BusinessCore.getScenario().getListInformation();
  int size = information.size();
  int periodCount=BusinessCore.getGeneralParameters().getPeriodCount();
  int periodDuration=BusinessCore.getGeneralParameters().getPeriodDuration();
  int i = 0;
  boolean noProblem=true;
  while ( i < size & noProblem) {
    if ((Integer.parseInt( ( (String[]) information.get(i))[3]) >=
         periodDuration) ||
        (Integer.parseInt( ( (String[]) information.get(i))[2]) >
         periodCount)){
      String warnMessage = "Some pieces of information are sent after the end of a period\nor after the end of the experiment.\nDo you want to correct this mistake?";
      Utils.logger.warn(warnMessage);
      int reponse=JOptionPane.showConfirmDialog(ParentFrame, warnMessage,
                                    "Initiating experiment error.",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
      if(reponse==JOptionPane.OK_OPTION)
        return false;
      noProblem=false;
    }
    i++;
  }

    Iterator iter = NetworkCore.getPlayerList().keySet().iterator();
    while(iter.hasNext()) {
      String login = (String) iter.next();
      String pc = NetworkCore.getPlayer(login).getPlayerCategory();
      if ((NetworkCore.getPlayer(login).getPlayerStatus() == Player.CLIENT_STATUS_CONNECTED ) && ((pc == null) || !BusinessCore.getScenario().getPlayerTypes().containsKey(pc))) {
        String warnMessage = "Some players don't have a player type. The experiment won't begin before.";
        Utils.logger.warn(warnMessage);
        JOptionPane.showConfirmDialog(ParentFrame,warnMessage,"Initiating experiment error.",JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
        return false;
      }
    }

    if (NetworkCore.getPlayerList().size()==0){
      JOptionPane.showConfirmDialog(ParentFrame, "No player connected",
                                    "Initiating experiment error.",
                                    JOptionPane.CLOSED_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
      return false;
    }

    //Check if the working directory and the file name are correct
    String pwd = BusinessCore.getGeneralParameters().getWorkingDirectory();
    String fileName = BusinessCore.getGeneralParameters().getLoggingFileName();

    if ( (pwd == null) || (pwd.equalsIgnoreCase("")) ||
        (fileName == null) || (fileName.equalsIgnoreCase(""))) {
     JOptionPane.showConfirmDialog(ParentFrame, "You have to choose a name in the general parameters\n to save the results of the experiment.",
                                   "Initiating experiment error.",
                                    JOptionPane.CLOSED_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
      return false;
                                  }
    // if the working directory does not end with the file separator, we add it.
    if (!pwd.substring(pwd.length() -
        1).equals(System.getProperty("file.separator"))) {
      pwd += System.getProperty("file.separator");
    }
  // check if the fileName ends with .xml, and add it if not
    if ( (fileName.length() < 5) ||
        (!fileName.substring(fileName.length() - 4).equalsIgnoreCase(".xml"))) {
      fileName += ".xml";
    }
    File file = new File(pwd+fileName);
    int j=0;
    //To avoid to delete a previous file with the same name, I add an index "j" to complete the name
    while (file.exists()) {
      String name = pwd + fileName;
      name.endsWith("");
      name=name.substring(0, name.lastIndexOf(" ")+1);
      j++;
      BusinessCore.getGeneralParameters().setLoggingFileName((name+j).substring(pwd.length(),(name+j).length()));
      name+=j+".xml";
      file = new File(name);
    }
    try {
      file.createNewFile();
    }
    catch (IOException ex) {
      JOptionPane.showConfirmDialog(ParentFrame, "An error occured.\nCheck the working directory and the name of the logging file in the general parameters\nThen, try to start the experiment.",
                                      "Initiating experiment error.",
                                      JOptionPane.CLOSED_OPTION,
                                      JOptionPane.WARNING_MESSAGE);
      return false;
    }


    Iterator iter2 = NetworkCore.getPlayerList().keySet().iterator();
    while(iter2.hasNext()) {
      String key = (String) iter2.next();
      NetworkCore.getPlayer(key).initClient();
    }
    this.periodNum=-1;
    this.setExperimentState(EXP_ON_PER_OFF);
    NetworkCore.sendToAllPlayers(new ExpUpdate(ExpUpdate.EXPERIMENT_BEGINNING, "", -1));
    this.produceDividendMessages(0);
    //((Thread)this).start();
    Utils.logger.debug("Experiment is on.");
    NetworkCore.arePlayersReady("Click on OK when you are ready\nto begin.");
    return true;
  }

  /**
   *
   */
  public ExperimentManager() {
  this.experimentState = EXP_OFF;
  this.start();
  }

  /**
   *
   */
  public void beginNewPeriod() {
  Utils.logger.debug("Beginning a new period.");
    this.periodNum++;

    Iterator iter = BusinessCore.getInstitutions().keySet().iterator();
    while(iter.hasNext()) {
      Institution inst =BusinessCore.getInstitution( (String) iter.next());
      if (!inst.getKeepingOrderBook()) {
        inst.emptyOrderBook();
      }
    }
    this.resetPortfolios();
    this.setExperimentState(EXP_ON_PER_ON);
    this.periodBeginning = new Date();

    NetworkCore.sendToAllPlayers(new ExpUpdate(ExpUpdate.PERIOD_BEGINNING, Long.toString(this.getTimeRemainingInPeriod()),this.periodNum));
  }

  /**
   *
   */
  private void endPeriodNow() {
  Utils.logger.debug("Ending the period" + (periodNum + 1));
    this.setExperimentState(EXP_ON_PER_OFF);
    NetworkCore.sendToAllPlayers(new ExpUpdate(ExpUpdate.PERIOD_FINISHING, "", this.periodNum));
    Element dividends = this.payDividends();
    this.logDividends(dividends);
    this.logPortfolios();
    if (this.periodNum < BusinessCore.getGeneralParameters().getPeriodCount() - 1) {
      this.produceDividendMessages(this.periodNum + 1);
    }
    //NetworkCore.sendToPlayerCategory(new Message(divMessage), pt.getPlayerTypeName());
    this.resetOrderBooks();


    NetworkCore.getLogManager().saveLogToFile();
    if (this.periodNum == BusinessCore.getGeneralParameters().getPeriodCount()-1){
      endExperimentNow();
    }
    else
      NetworkCore.arePlayersReady("Click on OK when you are ready\nto begin next period.");
  }

  /**
   *
   */
  private void endExperimentNow() {
  NetworkCore.sendToAllPlayers(new ExpUpdate(ExpUpdate.EXPERIMENT_FINISHING, "Experiment is finished", this.periodNum));
  this.setExperimentState(EXP_OFF);

    // buying back assets at holding value.

  }

  /**
   *
   * @param period int
   */
  private void produceDividendMessages(int period) {
  Iterator playerTypes = BusinessCore.getScenario().getPlayerTypes().keySet().iterator();

    while( playerTypes.hasNext() ) {
      PlayerType pt = BusinessCore.getScenario().getPlayerType((String)playerTypes.next());
      String divMessage = "";
      divMessage +="Interest rate :"+BusinessCore.getGeneralParameters().getInterestRate()+"%\n\n";
      Iterator assets = BusinessCore.getAssets().keySet().iterator();
      while( assets.hasNext() ) {
        String key = (String)assets.next();
        divMessage += ":: " + key + " ::\n";

        // period number in session
        if (pt.getDividendInfo(key).isDisplayingSessionLength())
          divMessage +=
              "The session has " +
              BusinessCore.getGeneralParameters().getPeriodCount() +
              " periods.\n";

        // holding value for session
        if (pt.getDividendInfo(key).isDisplayingHoldingValueForExperiment()) {
          divMessage +=
              "The holding value for the session is " +
              BusinessCore.getAsset(key).getDividendModel().getExperimentHoldingValue(period) +
              ".\n";
        }

        // window size.

        if (pt.getDividendInfo(key).isDisplayingWindowSize())
          divMessage +=
              "Following Info are given for the next " +
              pt.getDividendInfo(key).getWindowSize() + " periods :\n";

        // holding value for the window.
         int windowSize = pt.getDividendInfo(key).getWindowSize();
         if (pt.getDividendInfo(key).isDisplayHoldingValueForWindow())
        {   divMessage += "The holding value is " +
            BusinessCore.getAsset(key).getDividendModel().getWindowHoldingValue(period,windowSize) + "\n";
      }
      if(3!=pt.getDividendInfo(key).getDividendDetailledproperties()){
        if (1==pt.getDividendInfo(key).getDividendDetailledproperties()) {
          divMessage += "For the next period: " +
              BusinessCore.getAsset(key).getDividendModel().getDividendAt(period).
              getDetails() + "\n";
        }
        else if (2==pt.getDividendInfo(key).getDividendDetailledproperties()) {
          for (int i = period; i < period + windowSize; i++) {
            divMessage += "For the period " + Integer.toString(i + 1) + ": " +
                BusinessCore.getAsset(key).getDividendModel().getDividendAt(i).
                getDetails() + "\n";
          }
        }
        else {
          for (int i = period; i < BusinessCore.getGeneralParameters().getPeriodCount(); i++) {
            divMessage += "For the period " + Integer.toString(i + 1) + ": " +
                BusinessCore.getAsset(key).getDividendModel().getDividendAt(i).
                getDetails() + "\n";
          }
        }
      }
        divMessage += "_______________\n";

        DividendInfo divInfo = new DividendInfo();
        divInfo.setAssetName(key);
        NetworkCore.sendToPlayerCategory(divInfo,pt.getPlayerTypeName());
      }
      NetworkCore.sendToPlayerCategory(new Message(divMessage), pt.getPlayerTypeName());


    }
  }

  /**
   *
   * @return Element
   */
  private Element payDividends() {
     // An HashMap containing all the different messages to each player...
     HashMap oneMessageForOnePlayer = new HashMap();

     // interest
     Iterator p = NetworkCore.getPlayerList().keySet().iterator();
     float interest = BusinessCore.getGeneralParameters().getInterestRate();
     while (p.hasNext()) {
       Player player = NetworkCore.getPlayer( (String) p.next());
       oneMessageForOnePlayer.put( (String) player.getLogin(),
                                  (String) "For the last period :\n\n" +
                                  "Your cash rewards you " + (
                                      player.getPortfolio().getCash() *
                                      interest / 100) + " " +
                                  Constants.CURRENCY + " of interest.\n\n");
      player.getPortfolio().setCash(player.getPortfolio().getCash() *
                                    (100 + interest) / 100);
    }
     // dividends
     Element dividends = new Element("Dividends");
     Iterator assets = BusinessCore.getAssets().keySet().iterator();
     while (assets.hasNext()) {
       Element div = new Element("dividend");
       dividends.addContent(div);
       Iterator players = NetworkCore.getPlayerList().keySet().iterator();
       String asset = (String) assets.next();
       float dividend = BusinessCore.getAsset(asset).getDividendModel().
           getDividend(this.periodNum);
       div.setAttribute("asset", asset);
       div.setAttribute("value", String.valueOf(dividend));
       while (players.hasNext()) {
         Player pl = NetworkCore.getPlayer((String)players.next());
         String unfinished = (String) oneMessageForOnePlayer.get(pl.getLogin());
         //the following method increases the portfolio with the dividends for the player AND returns the amount added
         float fl = pl.getPortfolio().payDividend(asset, dividend);
         oneMessageForOnePlayer.remove(pl.getLogin());
         oneMessageForOnePlayer.put(pl.getLogin(), unfinished + "- " +
                                    asset + "'s dividends : " +
                                    dividend + " " +
                                    Constants.CURRENCY +
                                    "\n  Your are credited of " + fl + " " +
                                    Constants.CURRENCY + " \n");
       }
     }

     Iterator pIter = NetworkCore.getPlayerList().keySet().iterator();
      while (pIter.hasNext()) {
        Object playeurP = pIter.next();
        NetworkCore.sendToPlayer(new Message( (String) oneMessageForOnePlayer.get(playeurP.toString())), playeurP.toString());
      }

     return dividends;
   }



  private void resetPortfolios() {
    Iterator playerNames = NetworkCore.getPlayerList().keySet().iterator();

    while(playerNames.hasNext()) {
      String playerName = (String) playerNames.next();
      Player player = NetworkCore.getPlayer(playerName);
      Portfolio playerPortfolio = player.getPortfolio();

      if (BusinessCore.getScenario().getPlayerType( (player).getPlayerCategory())!= null) {
        Vector institutionsVect = ( (PlayerType) BusinessCore.getScenario().
                                   getPlayerType( (player).getPlayerCategory())).
            getInstitutionsWherePlaying();

        playerPortfolio.preInitializeInvestments();

        for (int i = 0; i < institutionsVect.size(); i++) {
          String institutionName = (String) institutionsVect.elementAt(i);
          Institution institution = BusinessCore.getInstitution(institutionName);
          String assetName = institution.getAssetName();

          if (!institution.getKeepingOrderBook()) {
            playerPortfolio.setInvestmentsWhenNotKeepingOrderBook(institutionName);
          }
          else {
            playerPortfolio.setInvestmentsWhenKeepingOrderBook(institutionName,
                assetName);
          }
        }
        NetworkCore.getPlayer(playerName).send(playerPortfolio);
      }
    }
  }



  /**
   *
   */
  private void resetOrderBooks() {
  Iterator instits = BusinessCore.getInstitutions().keySet().iterator();
    while(instits.hasNext()) {
      Institution inst=BusinessCore.getInstitution((String)instits.next());
      if (! inst.getKeepingOrderBook()){
        OrderBook orderbook = inst.getOrderBook();
        orderbook.reinit();
        NetworkCore.sendToAllPlayers(orderbook);
      }
    }
  }

  /**
   *
   */
  private void logPortfolios() {
  Iterator players = NetworkCore.getPlayerList().keySet().iterator();
    while(players.hasNext()) {
      Element pl = new Element("Portfolio");
      Player player = NetworkCore.getPlayer((String)players.next());
      pl.setAttribute("player", player.getLogin());
      pl.setAttribute("type", player.getPlayerCategory());
      player.getPortfolio().saveToXml(pl);
      NetworkCore.getLogManager().log(pl);
    }
  }

  /**
   *
   * @param div Element
   */
  private void logDividends(Element div) {
    NetworkCore.getLogManager().log(div);
}


  /**
   *
   * @return long
   */
  public long getTimeRemainingInPeriod() {
  Date now = new Date();
    return
        BusinessCore.getGeneralParameters().getPeriodDuration() * 1000
        - now.getTime()
        + periodBeginning.getTime();
  }

  /**
   *
   * @return long
   */
  public long getTimeInPeriod() {
  Date now = new Date();
    return
        now.getTime()
        - periodBeginning.getTime();
  }

  /**
   *
   */
  public void run() {
    while (true) {
      while (! (this.getExperimentState() == EXP_OFF)) {
        if (this.getExperimentState() != EXP_ON_PER_OFF) {

          long rt = this.getTimeRemainingInPeriod();
          //Utils.logger.debug("It remains " + rt + " ms before the end of the period.");
          if (rt <= 0) {
            endPeriodNow();
          }

        }

        try {
          this.sleep(1000);
        }
        catch (InterruptedException ex) {
          Utils.logger.warn("ExperimentManager sleep was interrupted: " +
                            ex.toString());
        }
      }
      try {
        this.sleep(100);
      }
      catch (InterruptedException ex) {
        Utils.logger.warn("ExperimentManager sleep was interrupted: ");
      }
    }
  }
}
