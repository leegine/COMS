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
import java.util.*;

import java.awt.*;
import javax.swing.*;

import org.jdom.*;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import jessx.business.*;
import jessx.client.*;
import jessx.client.event.*;
import jessx.net.*;
import jessx.utils.*;

/***************************************************************/
/*           MarketEvolutionGraph CLASS SECTION                */
/***************************************************************/
/**
 * <p>Title: MarketEvolutionGraph</p>
 * <p>Description:</p>
 * @author T.E.A.M
 * @version 0.2
 */

public class MarketEvolutionGraph
    extends JPanel implements NetworkListener {

  private HashMap dealXYSeries = new HashMap();
  private XYSeriesCollection dataset = new XYSeriesCollection();
  private float timeSinceBeginning = 0;
  private JPanel priceBar = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JLabel priceBarTitle = new JLabel();
  private JList listPriceAndQty = new JList();

  private void increaseTimeFromBeginning(long time) {
    this.timeSinceBeginning += time;
    //synchronization of the time in the chart with the real-time
  }

  public MarketEvolutionGraph() {
    super();
    ClientCore.addNetworkListener(this, "ExperimentUpdate");
  }

  /**
   *
   * @param xmlDoc Document
   */
  public void objectReceived(Document xmlDoc) {
    if (xmlDoc.getRootElement().getName().equals("ExperimentUpdate")) {
      ExpUpdate update = new ExpUpdate(0, "", 0);
      if (update.initFromNetworkInput(xmlDoc.getRootElement())) {
        if (update.getUpdateType() == ExpUpdate.PERIOD_BEGINNING) {
          if (update.getCurrentPeriod() != 0) {
            this.increaseTimeFromBeginning(Long.parseLong(update.
                getUpdateMessage()));
            //make the current period length in accord with xml document
          }
        }
      }
    }
  }

  public void addAssetEvolution(String institutionName) {
    dealXYSeries.put(institutionName, new XYSeries(institutionName));
    dataset.addSeries( (XYSeries) dealXYSeries.get(institutionName));
    //dataset is related with market conditions and institutions.
  }

  public void addDeal(Deal deal) {
    if (50<( (XYSeries) dealXYSeries.get(deal.getDealInstitution())).getItemCount())
    ( (XYSeries) dealXYSeries.get(deal.getDealInstitution())).remove(0);
    ( (XYSeries) dealXYSeries.get(deal.getDealInstitution())).add( (float) (
        deal.getTimestamp() + this.timeSinceBeginning) / 1000,
        deal.getDealPrice());

    //add the current price and time of deals into the XYseries
    Utils.logger.debug("deal price: " + deal.getDealPrice());
    Utils.logger.debug("deal timestamp: " + deal.getTimestamp());
    Utils.logger.debug("deal time since beginning: " + this.timeSinceBeginning);
    //display the price and time of each point of the graph in the DOS window

    JFreeChart chart = ChartFactory.createXYLineChart(
        null,
        "Time (s)",
        "Price (" + Constants.CURRENCY + ")",
        dataset,
        org.jfree.chart.plot.PlotOrientation.VERTICAL,
        true,
        true,
        false);

    this.removeAll();
    this.add(new ChartPanel(chart), new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(4, 4, 4, 4), 0, 0));
    this.add(priceBar, new GridBagConstraints(0, 0, 1, 1, 0, 0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.BOTH,
                                              new Insets(4, 4, 4, 4), 0, 0));

    DefaultListModel model = (DefaultListModel) listPriceAndQty.getModel();
    if (model.getSize()>4)
    model.removeElementAt(0);
    model.addElement(new Float(deal.getDealPrice())+" / "+deal.getQuantity()+"  ");
    listPriceAndQty.setModel(model);

    //set the position of the chart

    // chart.plotChanged(new PlotChangeEvent(new Plot()));
  }

  /**
   * create the market chart from JFreeChart factory
   * this is the initial graph without dealings
   */
  public void initGraphic() {

    JFreeChart chart = ChartFactory.createXYLineChart(
      null,
      "Time (s)",
      "Price (" + Constants.CURRENCY + ")",
        dataset,
        org.jfree.chart.plot.PlotOrientation.VERTICAL,
        true,
        false,
        false);
    this.setLayout(new GridBagLayout());
    this.add(new ChartPanel(chart), new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(4, 4, 4, 4), 0, 0));
    //set the layout of the chart


    //Essai Charles

    priceBar.setLayout(gridBagLayout1);

    priceBarTitle.setText("Last Deals : Price/Qty");
    priceBarTitle.setFont(new java.awt.Font(priceBarTitle.getFont().getName(), Font.BOLD, priceBarTitle.getFont().getSize()));
    listPriceAndQty.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    listPriceAndQty.setVisibleRowCount( -1);
    DefaultListModel model = new DefaultListModel();
    listPriceAndQty.setBackground(new Color(224, 223, 227));
    listPriceAndQty.setOpaque(false);
    listPriceAndQty.setRequestFocusEnabled(false);
    listPriceAndQty.setModel(model);
    priceBar.add(priceBarTitle, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST,
        GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 1, 1));
    priceBar.add(listPriceAndQty, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST,
        GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 1, 1));
    this.add(priceBar, new GridBagConstraints(0, 0, 1, 1, 0, 0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.BOTH,
                                              new Insets(4, 4, 4, 4), 0, 0));
    //-->charles


  }

}
