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
/*             InstitutionCreator CLASS SECTION                */
/***************************************************************/
/**
 * <p>Title: InstitutionCreator</p>
 * <p>Description: </p>
 * @author Thierry Curtil
 * @version 1.0
 */
public abstract class InstitutionCreator {
  /**
   * A field where all available module factory are saved.
   */
  public static Hashtable institutionFactories = new Hashtable();

  /**
   * This method returns an asset corresponding to a given name.
   *
   * @param name String the asset's name
   * @throws InstitutionNotCreatedException
   * @return Asset the found asset.
   */
  public static Institution createInstitution(String name)
      throws InstitutionNotCreatedException {

    Utils.logger.info("Trying to create the following institution: " + name);
    Utils.logger.debug("Looking for its class in the Hashtable.");
    Class institutionClass = (Class) institutionFactories.get(name);
    if (institutionClass == null) {
      Utils.logger.error("Class not found. The institution has never been loaded.");
      Utils.logger.error("As all institutions from the modules/institutions directory has already been loaded, we are looking in the classPath.");

      Utils.logger.error("Here is a list of loaded institution: ");
      Iterator inIter = institutionFactories.keySet().iterator();
      while(inIter.hasNext()) {
        Utils.logger.error(inIter.next().toString());
      }
      /**
      try {
        Class.forName("jessx.business.institutions." + name).newInstance();
      }
      catch (ClassNotFoundException ex1) {
        System.out.println(ex1.toString());
      }
      catch (IllegalAccessException ex1) {
        System.out.println(ex1.toString());
      }
      catch (InstantiationException ex1) {
        System.out.println(ex1.toString());
      }



      institutionClass = (Class) institutionFactories.get(name);
      if (institutionClass == null) {
        Utils.logger.warn("institution not found in the classPath.");*/
        throw (new InstitutionNotCreatedException());
      //}

    }
    Utils.logger.debug("Returning the result of the create method of the factory: the institution.");
    try {
      return (Institution)institutionClass.newInstance();
    }
    catch (IllegalAccessException ex) {
      Utils.logger.error("error creating the requested institution: " + ex.toString());
      ex.printStackTrace();
      throw (new InstitutionNotCreatedException());
    }
    catch (InstantiationException ex) {
      Utils.logger.error("error creating the requested institution: " + ex.toString());
      ex.printStackTrace();
      throw (new InstitutionNotCreatedException());
    }
  }
}
