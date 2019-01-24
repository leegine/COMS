package jessx.analysis.tools;

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
import java.util.List;

import java.awt.*;
import javax.swing.*;

import org.jdom.*;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import jessx.analysis.*;
import jessx.utils.*;

/***************************************************************/
/*                BasicAnalysis CLASS SECTION                  */
/***************************************************************/

/**
 * <p>Title: BasicAnalysis</p>
 * <p>Description: An analysis that plots price evolution on the market.</p>
 * @author Thierry Curtil, Julien Terrier, Christophe Grosjean
 * @version 1.0
 */

public class BasicAnalysis extends JPanel implements jessx.analysis.AnalysisTool {
  public BasicAnalysis() {
  }

  private Document doc;
  private long sessionDuration = 0;
  private HashMap datasetInstitution = new HashMap();
  private List institutions;

  /**
   * How the tool will be displayed on the graphic user interface.
   * @return String the name of the tool
   */
  public String getToolName() {
    return "Basic analysis";
  }

  /**
   * The about box displays some info about loaded tools.
   * @return String authors of the tool
   */
  public String getToolAuthor() {
    return "EC-Lille, USTL - 2005 - Thierry Curtil, Julien Terrier, Christophe Grosjean.";
  }

  /**
   * A description of the tool
   * @return String a short description of the tool.
   * @see AnalysisTool
   */
  public String getToolDescription() {
    return "An analysis that plots price evolution on the market.";
  }

  /**
   * this function will be called first. The core gives at this time the xml
   * document to the module.
   * @param xmlDoc Document to analyse.
   */
  public void setDocument(Document xmlDoc) {
    this.doc = xmlDoc;
  }

  /**
   * After the function setDocument, the core calls this function.
   * The panel return should contain a graph,
   * or any kind of analysis.
   * @return JPanel containing the analysis.
   */
  public JPanel drawGraph() {
    JPanel returnPanel = new JPanel();
    this.initDataSet();
    Iterator institutionsIter = institutions.iterator();

           JTabbedPane tempTabbedPane = new JTabbedPane();
           while(institutionsIter.hasNext()) {
             Element instit = (Element)institutionsIter.next();
             String institutionName =instit.getAttributeValue("name");
             XYSeriesCollection dataset = new XYSeriesCollection();
             dataset.addSeries((XYSeries) datasetInstitution.get(institutionName+"ask"));
             dataset.addSeries((XYSeries) datasetInstitution.get(institutionName+"bid"));
             dataset.addSeries((XYSeries) datasetInstitution.get(institutionName+"price"));
             JPanel institution =new ChartPanel(this.CreateChart(dataset));
             tempTabbedPane.add(institution,institutionName);
           }
           GridBagLayout gridBagLayout1 = new GridBagLayout();
           returnPanel.setLayout(gridBagLayout1);
           returnPanel.add(tempTabbedPane, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 4, 4), 0, 0));
           return returnPanel;
  }

  /**
   * Create a new chart with the current dataset
   * @param dataset XYSeriesCollection
   * @return JFreeChart
   */
  private JFreeChart CreateChart(XYSeriesCollection dataset){

    JFreeChart chart = ChartFactory.createXYLineChart(
           "Best limits","Time (s)","Price ("+Constants.CURRENCY+")",
           dataset,
           org.jfree.chart.plot.PlotOrientation.VERTICAL,
           true,true,false);

         return chart;
       }

  private void initDataSet() {
    AnalysisToolsCore.logger.debug("Open XML");
    Element experimentNode = this.doc.getRootElement();

    institutions = experimentNode.getChild("Setup").getChildren("Institution");
    Iterator institutionsIter = institutions.iterator();
    while(institutionsIter.hasNext()) {
      Element institution = (Element)institutionsIter.next();
      String institutionName =institution.getAttributeValue("name");
      datasetInstitution.put(institutionName+"ask",new XYSeries("Best Ask Limit"));
      datasetInstitution.put(institutionName+"bid",new XYSeries("Best Bid Limit"));
      datasetInstitution.put(institutionName+"price",new XYSeries("Price"));
      AnalysisToolsCore.logger.debug("New Institution"+institutionName);

    }


    List periods = experimentNode.getChildren("Period");
    AnalysisToolsCore.logger.debug("List of periods created");
    Iterator periodsIter = periods.iterator();
    while(periodsIter.hasNext()) {
      Element period = (Element)periodsIter.next();
      List orderBooks = period.getChildren("OrderBook");
      AnalysisToolsCore.logger.debug("List of OrderBooks created");
      Iterator orderBooksIter = orderBooks.iterator();

      while(orderBooksIter.hasNext()) {
        Element orderBook = (Element)orderBooksIter.next();
        float price = this.getBestBidFromOrderBook(orderBook);
        if (price >= 0) {
         ((XYSeries) datasetInstitution.get(orderBook.getChild("Bid").getChild("Operation").getAttributeValue("institution")+"bid")).
             add(Float.parseFloat(orderBook.getAttributeValue("timestamp"))/1000 + this.sessionDuration, price);
        }

        price = this.getBestAskFromOrderBook(orderBook);
        if (price >= 0) {
          ((XYSeries) datasetInstitution.get(orderBook.getChild("Ask").getChild("Operation").getAttributeValue("institution")+"ask")).
              add(Float.parseFloat(orderBook.getAttributeValue("timestamp"))/1000 + this.sessionDuration, price);
        }

      }

      List deals = period.getChildren("Deal");
      AnalysisToolsCore.logger.debug("List of deals created");
      Iterator dealIter = deals.iterator();
      float time;
      float price;
      while (dealIter.hasNext()) {
        Element deal = (Element)dealIter.next();
        time = Float.parseFloat(deal.getChild("Deal").getAttributeValue("timestamp"))/1000 + this.sessionDuration;
        price = Float.parseFloat(deal.getChild("Deal").getAttributeValue("price"));
        ((XYSeries) datasetInstitution.get(deal.getChild("Deal").getAttributeValue("institution")+"price")).
            add(time, price);
        ((XYSeries) datasetInstitution.get(deal.getChild("Deal").getAttributeValue("institution")+"ask")).
            add(time, price);
        ((XYSeries) datasetInstitution.get(deal.getChild("Deal").getAttributeValue("institution")+"bid")).
            add(time, price);
      }

      this.sessionDuration += Float.parseFloat(experimentNode.getChild("Setup").getChild("GeneralParameters").getChildText("PeriodDuration"));
    }

  }

  private float getBestBidFromOrderBook(Element orderBook) {
    if ((orderBook.getChild("Bid").getChild("Operation") != null)&&(orderBook.getChild("Ask").getChild("Operation") != null)) {
      if (orderBook.getChild("Bid").getChild("Operation").
                           getChild("LimitOrder") != null) {
        return Float.parseFloat(orderBook.getChild("Bid").getChild("Operation").
                                getChild("LimitOrder").getAttributeValue("price"));
      }
      else{
        return Float.parseFloat(orderBook.getChild("Bid").getChild("Operation").
                                getChild("BestLimitOrder").getAttributeValue("price"));
      }
    }
    else {
      return -1;
    }
  }

  private float getBestAskFromOrderBook(Element orderBook) {
    if ((orderBook.getChild("Ask").getChild("Operation") != null) && (orderBook.getChild("Bid").getChild("Operation") != null)) {
      if(orderBook.getChild("Ask").getChild("Operation").getChild("LimitOrder")!=null){
        return Float.parseFloat(orderBook.getChild("Ask").getChild("Operation").getChild("LimitOrder").getAttributeValue("price"));
      }
      else{
        return Float.parseFloat(orderBook.getChild("Ask").getChild("Operation").getChild("BestLimitOrder").getAttributeValue("price"));
      }
      }
    else {
      return -1;
    }
  }

  static {
    try {
      AnalysisToolCreator.analyseFactories.put("BasicAnalysis",Class.forName("jessx.analysis.tools.BasicAnalysis"));
      System.out.println("Basic analysis registered");
    }
    catch (Exception ex) {
      System.out.println("Problem registering BasicAnalysis tool: " + ex.toString());
    }
  }
}
