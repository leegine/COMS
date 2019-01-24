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

import java.io.*;
import org.jdom.*;

/***************************************************************/
/*           NetworkWritable INTERFACE SECTION                 */
/***************************************************************/
/**
 * <p>Title : NetworkWritable</p>
 * <p>Description : This interface ensure that all object implementing it
 * will be able to output themselves as an xml formatted String.</p>
 * <p>Classes that implement also <code>jessx.net.NetworkReadable</code> are
 * somehow serializable. This other serialization method is implemented for
 * mainly two reasons. First it allows to suppress some information from objects
 * that are serialized depending on the <code>PlayerType</code> of the
 * targetted <code>Player</code>. Secondly, the client does not need to be
 * written in java. It just need to have access to an xml parser. (and as the
 * xml String are simple ones some works on the String itself could do it, too).
 * </p>
 * @author Thierry Curtil
 * @version 1.0
 * @see jessx.net.NetworkReadable
 */

public interface NetworkWritable extends Serializable {

  /**
   * Prepare an object for writing on a network outpustream.
   * The Element is an <code>org.jdom</code> xml structure.<br />
   * The String defining the playerType is given in case the object need some
   * restrictions, so it knows how to apply them.
   * This has sense only for objects sent from the server to the client.
   * If the String is <code>null</code>, the parameter should be ignored without
   * raising any exception, and if it is a non valid player type name, the
   * function should log it using <code>Utils.logger.warn("error message here");
   * </code> but that error should be ignored and the method should continue as
   * if the parameter was <code>null</code>
   * @param pt String the playerType.
   * @return Element xml node.
   */
  public Element prepareForNetworkOutput(String pt);
}
