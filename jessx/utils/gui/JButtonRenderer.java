package jessx.utils.gui;

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

import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;

/***************************************************************/
/*           JButtonRenderer INTERFACE SECTION                 */
/***************************************************************/
/**
 * <p>Title: JButtonRenderer</p>
 * <p>Description:</p>
 * @author Thierry Curtil, Julien Terrier
 * @version 0.2
 */


public class JButtonRenderer extends JButton implements TableCellRenderer{
  public JButtonRenderer() {
      setOpaque(true); //MUST do this for background to show up.
    }

    public Component getTableCellRendererComponent(
                            JTable table, Object jButton,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
      Color newColor;
      String newtext;
      Font newFont;
      if ((jButton != null) && (jButton instanceof JButton)) {
        newColor = ( (JButton) jButton).getBackground();
        newtext = ( (JButton) jButton).getText();
        newFont = ((JButton)jButton).getFont();
        setBackground(newColor);
        this.setFont(newFont);
        this.setBorderPainted(false);
      }
      else {
        newtext = "";
      }

      setText(newtext);
      this.setHorizontalAlignment(this.CENTER);
      this.setVerticalAlignment(this.CENTER);
      return this;
    }
  }
