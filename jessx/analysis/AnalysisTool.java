package jessx.analysis;

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

import javax.swing.*;

import org.jdom.*;

/***************************************************************/
/*                Analysable INTERFACE SECTION                 */
/***************************************************************/

/**
 * <p>Title: Analysable</p>
 * <p>Description: This interface should (must in fact ^^) be implemented by all
 * analysis module. The core need these services to be implemented.<br />
 * setDocument(Document xmlDoc) will be called first. The argument is the xml
 * document to analyse. Then drawGraph() is called. The return parameter is a
 * JPanel that should contain the result of the analysis.</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public interface AnalysisTool {

  /**
   * How the tool will be displayed on the graphic user interface.
   * @return String the name of the tool
   */
  public String getToolName();

  /**
   * The about box displays some info about loaded tools.
   * @return String authors of the tool
   */
  public String getToolAuthor();

  /**
   * A description of the tool (should be short: < 20 words)
   * @return String a short description of the tool.
   */
  public String getToolDescription();

  /**
   * this function will be called first. The core gives at this time the xml
   * document to the module.
   * @param xmlDoc Document to analyse.
   */
  public void setDocument(Document xmlDoc);

  /**
   * Then the core calls this function. The panel return should contain a graph,
   * or any kind of analysis.
   * @return JPanel containing the analysis.
   */
  public JPanel drawGraph();

}
