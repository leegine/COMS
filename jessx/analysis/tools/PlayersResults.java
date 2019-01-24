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

import java.awt.*;
import javax.swing.*;

import org.jdom.*;
import jessx.analysis.*;
import jessx.utils.*;

/***************************************************************/
/*              PlayersResults CLASS SECTION                   */
/***************************************************************/

/**
 * <p>Title: PlayersResults</p>
 * <p>Description : This class enables to export the results of</p>
 * <p>each players (Name,Cash,Assets...) in a TableModelPlayersResults</p>
 * @author Christophe Grosjean
 * @version 1.0
 */

public class PlayersResults
    extends JPanel implements jessx.analysis.AnalysisTool {

  private Document doc;

  /**
   * How the tool will be displayed on the graphic user interface.
   * @return String the name of the tool
   */
  public String getToolName() {
    return "Players' results";
  }

  /**
   * The about box displays some info about loaded tools.
   * @return String authors of the tool
   */
  public String getToolAuthor() {
    return "EC-Lille, USTL - 2005 - Christophe Grosjean.";
  }

  /**
   * A description of the tool
   * @return String a short description of the tool.
   * @see AnalysisTool
   */
  public String getToolDescription() {
    return "An analysis that creates a summary of the players's results.";
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
    JTabbedPane jTabbedPane=new JTabbedPane();
    JPanel jresults = new JPanel();
    JPanel jClassification = new JPanel();
    jTabbedPane.add(jClassification,"Classification");
    jTabbedPane.add(jresults,"Results");
    JScrollPane jScrollPaneResults = new JScrollPane();
    JScrollPane jScrollPaneClassification = new JScrollPane();
    GridBagLayout gridBagLayoutResults = new GridBagLayout();
    GridBagLayout gridBagLayoutClassification = new GridBagLayout();
    jresults.setLayout(gridBagLayoutResults);
    jClassification.setLayout(gridBagLayoutClassification);
    Utils.logger.debug("TableModelPlayersResults try to create");
    TableModelPlayersResults tableModelPlayersResults=new TableModelPlayersResults(doc);
    JTable jTabResults = new JTable(tableModelPlayersResults);
    JTable jTabClassification = new JTable(new TableModelPlayersClassification(doc,tableModelPlayersResults));
    Utils.logger.debug("TableModelPlayersResults created");
    jScrollPaneResults.add(jTabResults, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.BOTH,
                                              new Insets(4, 4, 4, 4), 0, 0));
    jresults.add(jScrollPaneResults,
                 new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER,
                                        GridBagConstraints.REMAINDER, 1.0, 1.0
                                        , GridBagConstraints.CENTER,
                                        GridBagConstraints.BOTH,
                                        new Insets(0, 0, 0, 0), 0, 0));
    jScrollPaneResults.getViewport().add(jTabResults, null);

    jScrollPaneClassification.add(jTabClassification, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
                                                , GridBagConstraints.CENTER,
                                                GridBagConstraints.BOTH,
                                                new Insets(4, 4, 4, 4), 0, 0));
    jClassification.add(jScrollPaneClassification,
                  new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER,
                                         GridBagConstraints.REMAINDER, 1.0, 1.0
                                         , GridBagConstraints.CENTER,
                                         GridBagConstraints.BOTH,
                                         new Insets(0, 0, 0, 0), 0, 0));
    jScrollPaneClassification.getViewport().add(jTabClassification, null);
    returnPanel.setLayout(new GridBagLayout());
    returnPanel.add(jTabbedPane, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 4, 4), 0, 0));
    return returnPanel;
  }

  static {
    try {
      AnalysisToolCreator.analyseFactories.put("PlayersResults",
          Class.forName("jessx.analysis.tools.PlayersResults"));
      System.out.println("Players Results Analysis registered");
    }
    catch (Exception ex) {
      System.out.println("Problem registering PlayersResults tool: " +
                         ex.toString());
    }
  }
}
