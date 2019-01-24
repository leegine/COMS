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

import javax.swing.*;

import org.jdom.*;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import jessx.analysis.*;

/***************************************************************/
/*                 DealsVolume CLASS SECTION                   */
/***************************************************************/
/**
 * <p>Title: DealsVolume</p>
 * <p>Description: </p>
 * @authors: Christophe Grosjean , Prince Amine
 * @version 0.1
 */

public class DealsVolume extends JPanel implements jessx.analysis.AnalysisTool {

  private Document doc;

//  private XYSeries cashexchangeSeries = new XYSeries("Cash Exchange");

  private XYSeriesCollection dataset;

  private HashMap dealsXYSeries = new HashMap();

  /**
   * How the tool will be displayed on the graphic user interface.
   * @return String the name of the tool
   */
  public String getToolName() {
    return "Deals volume analysis";
  }

  /**
   * The about box displays some info about loaded tools.
   * @return String authors of the tool
   */
  public String getToolAuthor() {
    return "EC-Lille, USTL - 2005 - Mohamed Amine Hamamouchi, Christophe Grosjean.";
  }

  /**
   * A description of the tool
   * @return String a short description of the tool.
   * @see AnalysisTool
   */
  public String getToolDescription() {
    return "An analysis that plots exchanged volume on the market.";
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
    // Graph creation
    this.initDataSet(); // filling data tableexchanged

    JFreeChart chart = ChartFactory.createXYLineChart(
             "Exchanged Volume", //  Title = Cash
             "Time (s)",
             "Volume (quantity of assets)",
             dataset,
             org.jfree.chart.plot.PlotOrientation.VERTICAL,
             true,true,false);

    return new ChartPanel(chart);
  }

  /**
   * This function enables to complete the dataset to be used in the function
   * drawgraph().
   */
  private void initDataSet() {
    AnalysisToolsCore.logger.debug("Open XML");

    Element experimentNode = this.doc.getRootElement();

    float duration = Float.parseFloat(experimentNode.getChild("Setup").getChild("GeneralParameters").getChildText("PeriodDuration"));
    dataset = new XYSeriesCollection();

    List institutions = experimentNode.getChild("Setup").getChildren("Institution");
    Iterator institutionsIter = institutions.iterator();

    while(institutionsIter.hasNext()) {
    AnalysisToolsCore.logger.debug("New Institution");
    Element institution = (Element)institutionsIter.next();
    String InstitutionName =institution.getAttributeValue("name");
    dealsXYSeries.put(InstitutionName,new XYSeries(InstitutionName));
    dataset.addSeries((XYSeries)dealsXYSeries.get(InstitutionName));
    ((XYSeries) dealsXYSeries.get(InstitutionName)).add(0,0);
    AnalysisToolsCore.logger.debug(InstitutionName);
    }
    long periodDuration = 0;

    List periods = experimentNode.getChildren("Period");
    Iterator periodsIter = periods.iterator();

    float volumexchange = 0;

    while(periodsIter.hasNext()) {
      Element period = (Element)periodsIter.next();
      List deals = period.getChildren("Deal");
      Iterator dealIter = deals.iterator();

      while (dealIter.hasNext()) {
        Element deal = (Element)dealIter.next();
        {
          volumexchange = (Float.parseFloat(deal.getChild("Deal").getAttributeValue(
              "quantity")));
          ( (XYSeries) dealsXYSeries.get(deal.getChild("Deal").getAttributeValue(
              "institution"))).add(Float.parseFloat(deal.getChild("Deal").
                                                    getAttributeValue("timestamp")) /
                                   1000 + periodDuration, 0);
          ( (XYSeries) dealsXYSeries.get(deal.getChild("Deal").getAttributeValue(
              "institution"))).add(Float.parseFloat(deal.getChild("Deal").
                                                    getAttributeValue("timestamp")) /
                                   1000 + periodDuration, volumexchange);
          ( (XYSeries) dealsXYSeries.get(deal.getChild("Deal").getAttributeValue(
              "institution"))).add(Float.parseFloat(deal.getChild("Deal").
                                                    getAttributeValue("timestamp")) /
                                   1000 + periodDuration, 0);
        }
      }
      periodDuration += duration;
    }

  }

  static {
    try {
      AnalysisToolCreator.analyseFactories.put("DealsVolume",Class.forName("jessx.analysis.tools.DealsVolume"));
      System.out.println("Deals Volume Analysis registered");
    }
    catch (Exception ex) {
      System.out.println("Problem registering DealsVolume tool: " + ex.toString());
    }
  }
}
