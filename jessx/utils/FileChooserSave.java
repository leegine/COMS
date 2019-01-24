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

import java.io.*;

import java.awt.*;
import javax.swing.*;

import org.jdom.*;
import org.jdom.output.*;

/***************************************************************/
/*                  FileChooserSave Class Section              */
/***************************************************************/
/**
 * <p>Title : FileChooserSave</p>
 * <p>Description : </p>
 * @author Christophe Grosjean
 * @version 1.0
 */
public class FileChooserSave {

  int ans = 1;
  String fileName;
  String directoryName;
  public static final int SAVE_DONE = 0;
  public static final int SAVE_UNDONE = 1;


  public FileChooserSave(Document doc, Component parent, String jessXSection, String extension) {

    JFileChooser chooser = Utils.newFileChooser(null, "", extension+" files", extension);
    chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
    int option = chooser.showSaveDialog(parent);

    if (option == JFileChooser.CANCEL_OPTION) {
      ans = SAVE_UNDONE;
      return;
    }
    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
    try {
      System.out.print(chooser.getCurrentDirectory());

      int answer = JOptionPane.OK_OPTION;
      if ( (chooser.getSelectedFile().getAbsolutePath().endsWith("."+extension) &&
            chooser.getSelectedFile().exists()) ||
          new File(chooser.getSelectedFile().getAbsolutePath() +
                   "."+extension).exists()) {
        answer = JOptionPane.showConfirmDialog(parent,
                                               "A file with the same name already exists.\nDo you want to replace it?",
                                               jessXSection,
                                               JOptionPane.YES_NO_OPTION,
                                               JOptionPane.PLAIN_MESSAGE);

      ans = answer*answer; // 0 if done

      }
      if (answer == JOptionPane.OK_OPTION) {
        if (chooser.getSelectedFile().getAbsolutePath().endsWith("."+extension)) {
          sortie.output(doc,
                        new FileOutputStream(chooser.getSelectedFile().
                                             getAbsolutePath()));
        ans = SAVE_DONE;
        fileName = chooser.getSelectedFile().getName().substring(0 , chooser.getSelectedFile().getName().length() - 4);
        directoryName = chooser.getSelectedFile().getParent();
        }
        else {
          sortie.output(doc,
                        new FileOutputStream(new File(
                            chooser.getSelectedFile().getAbsolutePath() +
                            "."+extension)));
          ans = SAVE_DONE;
          fileName = chooser.getSelectedFile().getName();
          directoryName = chooser.getSelectedFile().getParent();
        }
      }

    }
    catch (Exception ex) {
      System.out.print("Error when the file is created...\n" + ex.toString());
      ans = SAVE_UNDONE;

      return;
    }

  }

  public String getDirectoryName() {
    return directoryName;
  }

  public String getFileName() {
    return fileName;
  }

   public int getAnswer() {
   return ans;
 }
}
