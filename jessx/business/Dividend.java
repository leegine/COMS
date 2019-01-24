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

import jessx.utils.*;
import org.jdom.*;

/***************************************************************/
/*                  Dividend CLASS SECTION                     */
/***************************************************************/
/**
 * <p>Title : Dividend</p>
 * <p>Description : </p>
 * @author Thierry Curtil
 * @version 1.0
 */

public abstract class Dividend implements XmlLoadable, XmlExportable {
  public abstract float getDividend();

  public abstract void setParameter(int i, Object param);
  public abstract Object getParameter(int i);
  public abstract int getParamCount();
  public abstract String[] getParamNames();
  public abstract Class getParamClass(int i);
  public abstract float getNormalValue();
  public abstract String getDetails();

  public abstract void saveToXml(Element node);
  public abstract void loadFromXml(Element node);

  public abstract Object clone();
}
