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
/*                HoldingValue CLASS SECTION                   */
/***************************************************************/

/**
 * <p>Title: HoldingValue</p>
 * <p>Description: An analysis that plots price evolution on the market.</p>
 * @author Mohamed Hamamouchi, Christophe Grosjean
 * @version 1.0
 */

public class HoldingValue extends JPanel implements jessx.analysis.AnalysisTool {
  public HoldingValue() {
  }

  private Document doc;
  private long sessionDuration = 0;
  private float periodDuration;
  private float interest;
  private HashMap datasetInstitution = new HashMap();
  private List institutions;

  /**
   * How the tool will be displayed on the graphic user interface.
   * @return String the name of the tool
   */
  public String getToolName() {
    return "Holding Value";
  }

  /**
   * The about box displays some info about loaded tools.
   * @return String authors of the tool
   */
  public String getToolAuthor() {
    return "EC-Lille, USTL - 2005 - Mohamed Hamamouchi.";
  }

  /**
   * A description of the tool
   * @return String a short description of the tool.
   * @see AnalysisTool
   */
  public String getToolDescription() {
    return "An analysis that plots price evolution on the market and the holding value.";
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
   *
   *
   * @param nPeriod int
   * @param meanDividend List
   * @return float
   */
  public float gethv(int nPeriod,List meanDividend ){
    AnalysisToolsCore.logger.debug("gethv"+nPeriod);


      float hv = 0;
      for (int i =nPeriod; i < meanDividend.size(); i++) {
        AnalysisToolsCore.logger.debug(meanDividend.get(i));
        float div=Float.parseFloat((String)meanDividend.get(i));
        AnalysisToolsCore.logger.debug(""+div);

        hv = hv * (1 + interest / 100) + div;
      }
      hv *= 100;
      hv = (int) (hv + .5);
      hv = hv / 100;
      AnalysisToolsCore.logger.debug("gethv"+nPeriod);

      return hv;
    }

    /**
     * @param nPeriod int
     * @param size int
     * @param meanDividend List
     * @return float
     */
    public float gethvSize(int nPeriod,int size,List meanDividend ){
  AnalysisToolsCore.logger.debug("gethvSize"+nPeriod);


    float hv = 0;
    for (int i =nPeriod; i < nPeriod+size; i++) {
      AnalysisToolsCore.logger.debug(meanDividend.get(i));
      float div=Float.parseFloat((String)meanDividend.get(i));
      AnalysisToolsCore.logger.debug(""+div);

      hv = hv * (1 + interest / 100) + div;
    }
    hv *= 100;
    hv = (int) (hv + .5);
    hv = hv / 100;
    AnalysisToolsCore.logger.debug("gethv"+nPeriod);

    return hv;
  }




    /**
     *
     * @return JPanel
     */
    public JPanel drawGraph() {
      System.out.print("drawGraph");
    JPanel returnPanel = new JPanel();
    this.initDataSet();
    Iterator institutionsIter = institutions.iterator();

           JTabbedPane tempTabbedPane = new JTabbedPane();
           while(institutionsIter.hasNext()) {
             Element instit = (Element)institutionsIter.next();
             String institutionName =instit.getAttributeValue("name");
             XYSeriesCollection dataset = new XYSeriesCollection();
             dataset.addSeries((XYSeries) datasetInstitution.get(institutionName+"price"));
             dataset.addSeries((XYSeries) datasetInstitution.get(instit.getAttributeValue("quotedAsset")+"HV"));
             dataset.addSeries((XYSeries) datasetInstitution.get(instit.getAttributeValue("quotedAsset")+"HVsize"));
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
           "Holding Value","Time (s)","Price ("+Constants.CURRENCY+")",
           dataset,
           org.jfree.chart.plot.PlotOrientation.VERTICAL,
           true,true,false);

         return chart;
       }

  private void initDataSet() {
    AnalysisToolsCore.logger.debug("Open XML");
    Element experimentNode = this.doc.getRootElement();
    periodDuration=Float.parseFloat(experimentNode.getChild("Setup").
                           getChild("GeneralParameters").getChildText(
          "PeriodDuration"));
  interest = Float.parseFloat(experimentNode.getChild("Setup").
                          getChild("GeneralParameters").getChildText(
         "InterestRate"));

    institutions = experimentNode.getChild("Setup").getChildren("Institution");
    Iterator institutionsIter = institutions.iterator();
    while (institutionsIter.hasNext()) {
      Element institution = (Element) institutionsIter.next();
      String InstitutionName = institution.getAttributeValue("name");
      datasetInstitution.put(InstitutionName + "price", new XYSeries("Price"));
      AnalysisToolsCore.logger.debug("New Institution" + InstitutionName);
    }

    List periods = experimentNode.getChildren("Period");
    AnalysisToolsCore.logger.debug("List of periods created");
    Iterator periodsIter = periods.iterator();
    while (periodsIter.hasNext()) {
      Element period = (Element) periodsIter.next();

      List deals = period.getChildren("Deal");
      AnalysisToolsCore.logger.debug("List of deals created");
      Iterator dealIter = deals.iterator();
      float time;
      float price;
      while (dealIter.hasNext()) {
        Element deal = (Element) dealIter.next();
        time = Float.parseFloat(deal.getChild("Deal").getAttributeValue(
            "timestamp")) / 1000 + this.sessionDuration;
        price = Float.parseFloat(deal.getChild("Deal").getAttributeValue("price"));
        ( (XYSeries) datasetInstitution.get(deal.getChild("Deal").
                                            getAttributeValue("institution") +
                                            "price")).
            add(time, price);

      }

      this.sessionDuration +=periodDuration;
    }
    List assets = experimentNode.getChild("Setup").getChildren("Asset");
    AnalysisToolsCore.logger.debug("List of Assets created");
    Iterator assetsIterator = assets.iterator();
    HashMap dividendForTheAsset=new HashMap();
    while (assetsIterator.hasNext()) {
      Element asset = (Element) assetsIterator.next();
      String assetName = asset.getAttributeValue("name");
      AnalysisToolsCore.logger.debug("New Asset" + assetName);
         Element dividendModel = asset.getChild("DividendModel");
         int size = 0;
         if (dividendModel.getAttributeValue("size") != null) {
           size = Integer.parseInt( (String) dividendModel.getAttributeValue(
               "size"));
         }
         List dividendMeans = dividendModel.getChildren("Dividend");
         AnalysisToolsCore.logger.debug("List of Dividend created");
         Iterator dividendMeansIterator = dividendMeans.iterator();
         while(dividendMeansIterator.hasNext()){
           List dividend=new Vector();
           for(int i=0;i<dividendMeans.size(); i++){
             Element dividendMean = (Element) dividendMeansIterator.next();
             String div = dividendMean.getAttributeValue("mean");
             dividend.add(div);
           }
         dividendForTheAsset.put(assetName,dividend);

         }
         AnalysisToolsCore.logger.debug("end");
         datasetInstitution.put(assetName + "HV", new XYSeries("Holding value on the experiment"));
         datasetInstitution.put(assetName + "HVsize", new XYSeries("Holding value on the window"));

         for (int j=0;j<periods.size();j++){
           List meanDividend =(List) dividendForTheAsset.get(assetName);
           ( (XYSeries) datasetInstitution.get(assetName + "HV")).add(periodDuration*j,
               this.gethv(j,meanDividend));
           AnalysisToolsCore.logger.debug("gzrsggqrgrd");

           ( (XYSeries) datasetInstitution.get(assetName + "HV")).add(this.
               periodDuration*(j+1), this.gethv(j, meanDividend));
         }
         for (int j=0;j<periods.size();j++){
           List meanDividend =(List) dividendForTheAsset.get(assetName);
           ( (XYSeries) datasetInstitution.get(assetName + "HVsize")).add(periodDuration*j,
               this.gethvSize(j,size,meanDividend));


           ( (XYSeries) datasetInstitution.get(assetName + "HVsize")).add(this.
               periodDuration*(j+1), this.gethvSize(j,size, meanDividend));
         }

         AnalysisToolsCore.logger.debug("superend");

    }
    AnalysisToolsCore.logger.debug("supesuperrend");

  }


  static {
    try {
      AnalysisToolCreator.analyseFactories.put("HoldingValue",Class.forName("jessx.analysis.tools.HoldingValue"));
      System.out.println("Holding Value registered");
    }
    catch (Exception ex) {
      System.out.println("Problem registering HoldingValue tool: " + ex.toString());
    }
  }
}
