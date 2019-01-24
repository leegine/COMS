package jessx.utils;

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
/*                      IMPORT SECTION                         */
/***************************************************************/

import java.net.*;

/***************************************************************/
/*                  JarClassLoader Class Section               */
/***************************************************************/

/**
 * <p>Title: JarClassLoader</p>
 * <p>Description: This class find and loads class files from jar.</p>
 * @author Thierry Curtil
 * @version 1.0
 */

public class JarClassLoader extends URLClassLoader {

  /**
   * Constructor: need a jarPath.
   * @param jarPath String the location of the jar.
   * @throws MalformedURLException if the location is malformed
   */
  public JarClassLoader(String jarPath) throws MalformedURLException {
    super(new URL[] {new URL(jarPath)});
  }

  /**
   * Call this method to load a class from the jar given to the constructor.
   * @param className String the name of the class. (it must contain the 'package path')
   * @throws ClassNotFoundException raised if the class is not found
   * @return Class if the class is found.
   */
  public Class findClass(String className) throws ClassNotFoundException {
    return super.findClass(className);
  }
}
