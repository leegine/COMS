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

import javax.swing.filechooser.*;

/***************************************************************/
/*                FileChooserFilter Class Section              */
/***************************************************************/
/**
 * <p>Title: FileChooserFilter</p>
 * <p>Description: filter for jFileChooser</p>
 * @author Franck Perez
 * @version 0.2
 */

public class FileChooserFilter extends FileFilter {

  //Description et extension acceptee par le filtre
  private String description;
  private String extension;

  //Constructeur a partir de la description et de l'extension accept�e
  public FileChooserFilter(String description, String extension){

    if(description == null || extension ==null){
      throw new NullPointerException("The description (or extension) should not be null.");
    }
   this.description = description;
   this.extension = extension;

  }

  //Implementation de FileFilter
  public boolean accept(java.io.File file){

    if(file.isDirectory()) {
      return true;
    }

    String nomFichier = file.getName().toLowerCase();
    return nomFichier.endsWith(extension);

  }

   public String getDescription(){
     return description;
   }

}
