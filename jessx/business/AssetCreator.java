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
/*                   AssetCreator Class Section                */
/***************************************************************/
/**
 * <p>Title: AssetCreator</p>
 * <p>Description: This class is the place where all assets module register.
 * So when you need to instantiate an asset, just look into the public
 * static Hashtable for a factory, that will be able to create your module, with
 * the createAsset(String name) static method.
 * This class is abstract so that no insatiation of it can be achieved. But
 * as all methods are static, they can be called.</p>
 * @author Thierry Curtil
 * @version 1.0
 */
public abstract class AssetCreator {
  /**
   * A field where all available module factory are saved.
   */
  public static Hashtable assetFactories = new Hashtable();

  /**
   * This method returns an asset corresponding to a given name.
   * @param name String the asset's name
   * @throws AssetNotCreatedException if something goes wrong when looking for / loading the asset.
   * @return Asset the found asset.
   */
  public static Asset createAsset(String name)
      throws AssetNotCreatedException {

    Utils.logger.info("Trying to create the following asset: " + name);
    Utils.logger.debug("Looking for its class in the Hashtable.");
    Class assetClass = (Class) assetFactories.get(name);
    if (assetClass == null) {
      Utils.logger.debug("Class not found. The asset has never been loaded.");
      Utils.logger.debug("As all assets from the modules/assets directory has already been loaded, we are looking in the classPath.");

      try {
        Class.forName("jessx.business.assets." + name);

        assetClass = (Class) assetFactories.get(name);
        if (assetClass == null) {
          Utils.logger.warn("Asset not found in the classPath.");
          throw (new AssetNotCreatedException());
        }
      }
      catch(ClassNotFoundException e) {
        Utils.logger.warn("Asset not found in the classPath.");
        throw (new AssetNotCreatedException());
      }
    }
    Utils.logger.debug("Returning the result of the create method of the factory: the asset.");
    try {
      return (Asset)assetClass.newInstance();
    }
    catch (IllegalAccessException ex) {
      Utils.logger.error("error creating the requested asset: " + ex.toString());
      ex.printStackTrace();
      return null;
    }
    catch (InstantiationException ex) {
      Utils.logger.error("error creating the requested asset: " + ex.toString());
      ex.printStackTrace();
      return null;
    }
  }
}
