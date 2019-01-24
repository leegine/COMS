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

import java.util.*;

/***************************************************************/
/*              AnalysisToolCreator CLASS SECTION              */
/***************************************************************/
/**
 * <p>Title: AnalysisToolCreator</p>
 * <p>Description: This class is the place where all analysis module register.
 * So when you need to instantiate a analysis tool, just look into the public
 * static Hashtable for a factory, that will be able to create your module.
 * This class is abstract so that no insatiation of it can be achieved. But
 * as all methods are static, they can be called.</p>
 * @author Thierry Curtil
 * @version 1.0
 */
public abstract class AnalysisToolCreator {
  /**
   * A field where all available module factory are saved.
   */
  public static Hashtable analyseFactories = new Hashtable();

  /**
   * This method returns a analysisTool corresponding to a given name.
   * @param name String the tool's name
   * @throws AnalysisToolNotCreatedException if something goes wrong when looking for / loading the tool.
   * @return AnalysisTool the found tool.
   */
  public static AnalysisTool createTool(String name) throws
      AnalysisToolNotCreatedException {

    AnalysisToolsCore.logger.info("Trying to create the following tool: " +
                                  name);
    AnalysisToolsCore.logger.debug("Looking for its factory in the Hashtable.");
    Class analysisClass = (Class) analyseFactories.get(name);
    if (analysisClass == null) {
      AnalysisToolsCore.logger.debug(
          "Factory not found. The tool has never been loaded.");
      AnalysisToolsCore.logger.debug("As all tools from the AnalysisTools directory has already been loaded, we are looking in the classPath.");

      try {
        Class.forName("jessx.analysis.tools." + name);

        analysisClass = (Class) analyseFactories.get(name);
        if (analysisClass == null) {
          AnalysisToolsCore.logger.warn("Tools not found in the classPath.");
          throw (new AnalysisToolNotCreatedException());
        }
      }
      catch (ClassNotFoundException e) {
        AnalysisToolsCore.logger.warn("Tools not found in the classPath.");
        throw (new AnalysisToolNotCreatedException());
      }
    }
    AnalysisToolsCore.logger.debug(
        "Returning the result of the create method of the factory: the tool.");
    try {
      return (AnalysisTool) analysisClass.newInstance();
    }
    catch (IllegalAccessException ex) {
      AnalysisToolsCore.logger.error("error creating the requested tool: " +
                                     ex.toString());
      ex.printStackTrace();
      return null;
    }
    catch (InstantiationException ex) {
      AnalysisToolsCore.logger.error("error creating the requested tool: " +
                                     ex.toString());
      ex.printStackTrace();
      return null;
    }
  }
}
