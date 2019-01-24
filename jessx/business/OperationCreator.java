package jessx.business;

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
import jessx.utils.*;


/***************************************************************/
/*                OperationCreator Class Section               */
/***************************************************************/
/**
 * <p>Title: OperationCreator</p>
 * <p>Description: </p>
 * @author Thierry Curtil
 * @version 1.0  2005 Thierry Curtil
 */
public abstract class OperationCreator {
  /**
   * A field where all available module factory are saved.
   */
  public static Hashtable operationFactories = new Hashtable();

  /**
   * This method returns an asset corresponding to a given name.
   *
   * @param name String the asset's name
   * @throws OperationNotCreatedException
   * @return Asset the found asset.
   */
  public static Operation createOperation(String name)
      throws OperationNotCreatedException {

    //Utils.logger.info("Trying to create the following operation: " + name);
    //Utils.logger.debug("Looking for its class in the Hashtable.");
    Class operationClass = (Class) operationFactories.get(name);
    if (operationClass == null) {
      Utils.logger.debug("Class not found. The operation has never been loaded.");
      Utils.logger.debug("As all operations from the modules/operations directory has already been loaded, we are looking in the classPath.");

      try {
        Class.forName("jessx.business.operations." + name);

        operationClass = (Class) operationFactories.get(name);
        if (operationClass == null) {
         // Utils.logger.warn("operation not found in the classPath.");
          throw (new OperationNotCreatedException());
        }
      }
      catch(ClassNotFoundException e) {
        Utils.logger.warn("operation not found in the classPath.");
        throw (new OperationNotCreatedException());
      }
    }
    // Utils.logger.debug("Returning the result of the create method of the factory: the operation.");
    try {
      return (Operation)operationClass.newInstance();
    }
    catch (IllegalAccessException ex) {
      Utils.logger.error("error creating the requested operation: " + ex.toString());
      ex.printStackTrace();
      throw (new OperationNotCreatedException());
    }
    catch (InstantiationException ex) {
      Utils.logger.error("error creating the requested operation: " + ex.toString());
      ex.printStackTrace();
      throw (new OperationNotCreatedException());
    }
  }
}
