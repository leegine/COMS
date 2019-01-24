package jessx.analysis.gui;

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

import jessx.analysis.*;

/***************************************************************/
/*              AnalysisAboutBox CLASS SECTION                */
/***************************************************************/

/**
 * <p>Title: AnalysisAboutBox</p>
 * <p>Description: This class implements an about box, that will typically be
 * displayed by the frame class when the user click on the about button from
 * the menu.
 * It gives some version info and a link to a tutorial for using / programming
 * new module.</p>
 * @author Thierry Curtil, Clement Plaignaud, Christophe Grosjean
 * @version 1.0
 */

public class AnalysisAboutBox
    extends jessx.utils.gui.AbstractAboutBox {

  /**
   * Create an About box with the different modules used in the analyse of the
   * sessions
   * @param parent The frame created in CoreFrame where the Analysis_AboutBox
   * can be created
   * @see CoreFrame
   * @see AbstractAboutBox
   */
  public AnalysisAboutBox(Frame parent) {
    super(parent);
    Analysis_AboutBoxEnabled();
  }

  /**
   * Find the different caracteristics of each module :
   * Name, Author, Description to write them in the About box.
   */
  public void loadModulesInfo() {

    AnalysisToolsCore.logger.debug(
        "Initiating about box with available modules...");
    Enumeration analysisToolsIterator = AnalysisToolCreator.analyseFactories.
        keys();
    this.getModulesInfoTextArea().append(
        "In this Analyzer, you will find the following functions:\n\n");
    while (analysisToolsIterator.hasMoreElements()) {
      String key = (String) analysisToolsIterator.nextElement();
      AnalysisTool tempTool = null;
      try {

        tempTool = AnalysisToolCreator.createTool(key);

        AnalysisToolsCore.logger.debug("Getting " + tempTool.getToolName() +
                                       " tool info and displaying it on interface.");

        this.getModulesInfoTextArea().append("- " + tempTool.getToolName() +
                                             "\nBy " +
                                             tempTool.getToolAuthor() + "\n" +
                                             tempTool.getToolDescription() +
                                             "\n\n");

        AnalysisToolsCore.logger.debug("following tool added to about box: " +
                                       tempTool.getToolName());
      }
      catch (AnalysisToolNotCreatedException ex1) {
        ex1.printStackTrace();
      }
    }
  }
}
