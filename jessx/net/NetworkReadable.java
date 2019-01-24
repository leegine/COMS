package jessx.net;

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

import org.jdom.*;


/***************************************************************/
/*               NetworkReadable INTERFACE SECTION             */
/***************************************************************/

/**
 * <p>Title : NetworkReadable</p>
 * <p>Description : this interface ensure that class implementing it will be
 * able to init themselves from an xml parsed String (already parsed, so that
 * all mal-formed String have already been eliminated).</p>
 * @author Thierry Curtil
 * @version 1.0
 * @see jessx.net.NetworkWritable
 */
public interface NetworkReadable {

  /**
   * This method should init the object using the xml node given.
   * If the object could not be initialized (because <code>rootNode</code> has
   * not a valid structure for this class for example), the method should return
   * <code>false</code>, and <code>true</code> only if the object has
   * successfully been initialized.
   * @param rootNode Element an xml node representing the object.
   * @return boolean whether the operation was successful or not.
   */
  public boolean initFromNetworkInput(Element rootNode);
}
